package com.devsu.exercise.repo;

import com.devsu.exercise.model.Cuenta;
import com.devsu.exercise.model.Movimientos;
import com.devsu.exercise.repo.base.IGenericRepo;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IMovientoRepo extends IGenericRepo<Movimientos, Integer> {

  @Query("SELECT SUM(m.valor) FROM Movimientos m WHERE m.tipoMovimiento = 'RETIRO' AND m.fecha = :fecha AND m.cuenta.numeroCuenta = :numeroCuenta")
  BigDecimal sumRetirosDelDia(@Param("fecha") LocalDate fecha,
      @Param("numeroCuenta") Integer numeroCuenta);

  List<Movimientos> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDate startDate, LocalDate endDate);

}
