package com.devsu.exercise.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MovimientoReporteDTO {
  private LocalDate fecha;
  private String tipoMovimiento;
  private BigDecimal valor;
}
