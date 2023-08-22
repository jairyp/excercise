package com.devsu.exercise.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaReporteDTO {
  private Integer numeroCuenta;
  private String tipo;
  private BigDecimal saldoInicial;
  private BigDecimal saldoFinal;
  private List<MovimientoReporteDTO> movimientos;
}
