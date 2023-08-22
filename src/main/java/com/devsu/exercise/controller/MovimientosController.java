package com.devsu.exercise.controller;

import static org.springframework.http.HttpStatus.OK;

import com.devsu.exercise.dto.MovimientosDTO;
import com.devsu.exercise.model.Movimientos;
import com.devsu.exercise.service.impl.MovimientosServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientosController {

  private final MovimientosServiceImpl service;
  private final ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<MovimientosDTO>> findAll() {
    List<MovimientosDTO> list = service.findAll().stream().map(this::convertToDto)
        .collect(Collectors.toList());
    return new ResponseEntity<>(list, OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MovimientosDTO> findById(@PathVariable("id") Integer id) {
    Movimientos obj = service.findById(id);
    return new ResponseEntity<>(this.convertToDto(obj), OK);
  }

  @PostMapping
  public ResponseEntity<MovimientosDTO> realizarMovimiento(@RequestBody MovimientosDTO movimiento) {
    Movimientos actualMovimiento = service.realizarMovimiento(this.convertToEntity(movimiento));
    return new ResponseEntity<>(this.convertToDto(actualMovimiento), HttpStatus.CREATED);
  }

  private MovimientosDTO convertToDto(Movimientos obj) {
    return mapper.map(obj, MovimientosDTO.class);
  }

  private Movimientos convertToEntity(MovimientosDTO dto) {
    return mapper.map(dto, Movimientos.class);
  }

}
