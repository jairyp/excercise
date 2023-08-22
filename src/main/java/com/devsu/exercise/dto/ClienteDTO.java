package com.devsu.exercise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {
  private Integer idPerson;

  @NotEmpty(message = "El nombre no puede estar vacío")
  private String nombre;

  private String genero;

  private int edad;

  private String identificacion;

  private String direccion;

  @NotEmpty(message = "El teléfono no puede estar vacío")
  private String telefono;
  @JsonIgnore
  private Integer clienteId;

  @NotEmpty(message = "La contraseña no puede estar vacía")
  private String contrasena;

  private Boolean estado;

}
