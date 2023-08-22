package com.devsu.exercise.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaDTO {

  private Integer cuentaId;

  private Integer numeroCuenta;

  @NotEmpty(message = "El tipo de cuenta no puede estar vacío")
  private String tipoCuenta;

  private Double saldoInicial;

  private Boolean estado;

  @NotNull(message = "El cliente asociado no puede ser nulo")
  private ClienteDTO cliente;
}
