package com.devsu.exercise.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteDTO {

  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private String cliente;
  private List<CuentaReporteDTO> cuentas;
  private BigDecimal totalDebitos;
  private BigDecimal totalCreditos;
}
