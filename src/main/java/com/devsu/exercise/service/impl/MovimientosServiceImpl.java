package com.devsu.exercise.service.impl;

import static com.devsu.exercise.service.utils.Constantes.LIMITE_DIARIO;
import static com.devsu.exercise.service.utils.Constantes.RETIRO;

import com.devsu.exercise.dto.CuentaReporteDTO;
import com.devsu.exercise.dto.MovimientoReporteDTO;
import com.devsu.exercise.dto.ReporteDTO;
import com.devsu.exercise.exception.CuentaException;
import com.devsu.exercise.model.Cuenta;
import com.devsu.exercise.model.Movimientos;
import com.devsu.exercise.repo.ICuentaRepo;
import com.devsu.exercise.repo.IMovientoRepo;
import com.devsu.exercise.repo.base.IGenericRepo;
import com.devsu.exercise.service.IMovimientoService;
import com.devsu.exercise.service.base.CRUDImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovimientosServiceImpl extends CRUDImpl<Movimientos, Integer> implements
    IMovimientoService {

  private final IMovientoRepo movientoRepo;
  private final ICuentaRepo cuentaRepo;

  @Override
  protected IGenericRepo<Movimientos, Integer> getRepo() {
    return movientoRepo;
  }

  @Override
  public Movimientos realizarMovimiento(Movimientos movimiento) {
    Cuenta cuenta = cuentaRepo.findByNumeroCuenta(movimiento.getCuenta().getNumeroCuenta())
        .orElseThrow(() -> new CuentaException("Cuenta no encontrada"));

    BigDecimal saldoActual =
        cuenta.getSaldoInicial() != null ? cuenta.getSaldoInicial() : BigDecimal.ZERO;

    if (RETIRO.equals(movimiento.getTipoMovimiento())) {
      log.info("Realizando un Retiro");
      if (saldoActual.compareTo(movimiento.getValor()) < 0) {
        throw new CuentaException("Saldo no disponible");
      }

      BigDecimal retirosDelDia = movientoRepo.sumRetirosDelDia(movimiento.getFecha(),
          movimiento.getCuenta().getNumeroCuenta());
      retirosDelDia = retirosDelDia != null ? retirosDelDia : BigDecimal.ZERO;

      if (retirosDelDia.add(movimiento.getValor()).compareTo(LIMITE_DIARIO) > 0) {
        throw new CuentaException("Cupo diario excedido.");
      }

      cuenta.setSaldoInicial(saldoActual.subtract(movimiento.getValor()));
    } else {
      log.info("Realizando un Deposito");
      cuenta.setSaldoInicial(saldoActual.add(movimiento.getValor()));
    }
    log.info("Transaccion cliente {}", cuenta.getCuentaId());
    cuentaRepo.save(cuenta);
    movimiento.setCuenta(cuenta);
    return movientoRepo.save(movimiento);
  }

  @Override
  public ReporteDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Integer clienteId) {
    ReporteDTO reporte = new ReporteDTO();

    List<Cuenta> cuentasCliente = cuentaRepo.findByClienteId(clienteId);

    List<CuentaReporteDTO> cuentaReportes = new ArrayList<>();

    BigDecimal totalDebitos = BigDecimal.ZERO;
    BigDecimal totalCreditos = BigDecimal.ZERO;

    for (Cuenta cuenta : cuentasCliente) {
      CuentaReporteDTO cuentaReporte = new CuentaReporteDTO();
      cuentaReporte.setNumeroCuenta(cuenta.getNumeroCuenta());
      cuentaReporte.setTipo(cuenta.getTipoCuenta());
      cuentaReporte.setSaldoInicial(cuenta.getSaldoInicial());

      List<Movimientos> movimientosCuenta = movientoRepo.findByCuentaAndFechaBetween(cuenta,
          fechaInicio, fechaFin);

      BigDecimal saldoFinal = cuenta.getSaldoInicial();
      List<MovimientoReporteDTO> movimientoReportes = new ArrayList<>();
      for (Movimientos movimiento : movimientosCuenta) {
        MovimientoReporteDTO movimientoReporte = new MovimientoReporteDTO();
        movimientoReporte.setFecha(movimiento.getFecha());
        movimientoReporte.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoReporte.setValor(movimiento.getValor());
        movimientoReportes.add(movimientoReporte);

        if (RETIRO.equals(movimiento.getTipoMovimiento())) {
          saldoFinal = saldoFinal.subtract(movimiento.getValor());
          totalDebitos = totalDebitos.add(movimiento.getValor());
        } else {
          saldoFinal = saldoFinal.add(movimiento.getValor());
          totalCreditos = totalCreditos.add(movimiento.getValor());
        }
      }
      cuentaReporte.setSaldoFinal(saldoFinal);
      cuentaReporte.setMovimientos(movimientoReportes);
      cuentaReportes.add(cuentaReporte);
    }

    reporte.setFechaInicio(fechaInicio);
    reporte.setFechaFin(fechaFin);
    reporte.setCliente(String.valueOf(clienteId));
    reporte.setCuentas(cuentaReportes);
    reporte.setTotalDebitos(totalDebitos);
    reporte.setTotalCreditos(totalCreditos);

    return reporte;
  }
}
