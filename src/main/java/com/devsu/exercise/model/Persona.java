package com.devsu.exercise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Persona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idPerson;
  private String nombre;
  private String genero;
  private int edad;
  private String identificacion;
  private String direccion;
  private String telefono;

}
