package com.devsu.exercise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Movimientos {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer movimientoId;
  private LocalDate fecha;
  private String tipoMovimiento;
  private BigDecimal valor;
  private BigDecimal saldo;

  @ManyToOne
  @JoinColumn(name = "cuentaId", referencedColumnName = "cuentaId")
  private Cuenta cuenta;
}
