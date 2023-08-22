package com.devsu.exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cuenta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer cuentaId;

  @Column(unique = true)
  private Integer numeroCuenta;
  private String tipoCuenta;
  private BigDecimal saldoInicial;
  private Boolean estado;
  @ManyToOne
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

}
