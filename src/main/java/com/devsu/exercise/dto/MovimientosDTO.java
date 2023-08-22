package com.devsu.exercise.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientosDTO {

  private CuentaMovientoDTO cuenta;

  private Integer id;

  @NotNull(message = "La fecha no puede ser nula")
  private LocalDate fecha;

  @NotEmpty(message = "El tipo de movimiento no puede estar vacío")
  private String tipoMovimiento;

  private BigDecimal valor;

  private BigDecimal saldo;
}
