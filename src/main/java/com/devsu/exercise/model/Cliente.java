package com.devsu.exercise.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cliente extends Persona {

  private Integer clienteId;
  private String contrasena;
  private Boolean estado;

}
