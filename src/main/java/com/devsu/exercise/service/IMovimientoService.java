package com.devsu.exercise.service;

import com.devsu.exercise.dto.ReporteDTO;
import com.devsu.exercise.model.Movimientos;
import com.devsu.exercise.service.base.ICRUD;
import java.time.LocalDate;

public interface IMovimientoService extends ICRUD<Movimientos, Integer> {

  Movimientos realizarMovimiento(Movimientos movimiento);

  ReporteDTO generarReporte(LocalDate fechaInicio, LocalDate fechaFin, Integer clienteId);
}
