package com.devsu.exercise.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.devsu.exercise.dto.CuentaDTO;
import com.devsu.exercise.model.Cuenta;
import com.devsu.exercise.service.impl.CuentaServiceImpl;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

  private final CuentaServiceImpl service;
  private final ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<CuentaDTO>> findAll() {
    List<CuentaDTO> list = service.findAll().stream().map(this::convertToDto)
        .collect(Collectors.toList());
    return new ResponseEntity<>(list, OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CuentaDTO> findById(@PathVariable("id") Integer id) {
    Cuenta obj = service.findById(id);
    return new ResponseEntity<>(this.convertToDto(obj), OK);
  }

  @PostMapping
  public ResponseEntity<Void> save(@Valid @RequestBody CuentaDTO dto) {
    Cuenta obj = service.save(convertToEntity(dto));
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getCuentaId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<CuentaDTO> update(@PathVariable("id") Integer id,
      @Valid @RequestBody CuentaDTO dto) {
    dto.setCuentaId(id);
    Cuenta obj = service.update(convertToEntity(dto), id);
    return new ResponseEntity<>(convertToDto(obj), OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    service.delete(id);
    return new ResponseEntity<>(NO_CONTENT);
  }

  private CuentaDTO convertToDto(Cuenta obj) {
    return mapper.map(obj, CuentaDTO.class);
  }

  private Cuenta convertToEntity(CuentaDTO dto) {
    return mapper.map(dto, Cuenta.class);
  }

}
