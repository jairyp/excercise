package com.devsu.exercise.controller;

import com.devsu.exercise.dto.ReporteDTO;
import com.devsu.exercise.service.impl.MovimientosServiceImpl;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reporte")
@RequiredArgsConstructor
public class ReporteController {

  private final MovimientosServiceImpl service;

  @GetMapping
  public ResponseEntity<ReporteDTO> obtenerReporte(
      @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
      @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin,
      @RequestParam("clienteId") Integer clienteId) {
    return new ResponseEntity<>(service.generarReporte(fechaInicio, fechaFin, clienteId),
        HttpStatus.OK);
  }

}
