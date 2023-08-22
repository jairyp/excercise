package com.devsu.exercise.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

import com.devsu.exercise.dto.ClienteDTO;
import com.devsu.exercise.model.Cliente;
import com.devsu.exercise.service.impl.ClienteServiceImpl;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteServiceImpl service;
  private final ModelMapper mapper;

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> findAll() {
    List<ClienteDTO> list = service.findAll().stream().map(this::convertToDto)
        .collect(Collectors.toList());
    return new ResponseEntity<>(list, OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Integer id) {
    Cliente obj = service.findById(id);
    return new ResponseEntity<>(this.convertToDto(obj), OK);
  }

  @PostMapping
  public ResponseEntity<Void> save(@Valid @RequestBody ClienteDTO dto) {
    Cliente obj = service.save(convertToEntity(dto));
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(obj.getClienteId()).toUri();
    return ResponseEntity.created(location).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClienteDTO> update(@PathVariable("id") Integer id,
      @Valid @RequestBody ClienteDTO dto) {
    dto.setIdPerson(id);
    Cliente obj = service.update(convertToEntity(dto), id);
    return new ResponseEntity<>(convertToDto(obj), OK);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<ClienteDTO> updatePatch(@PathVariable Integer id,
      @RequestBody Map<String, Object> updates) {
    Cliente obj = service.findById(id);
    if (obj == null) {
      return ResponseEntity.notFound().build();
    }
    service.updatePartial(id, updates, obj);
    return new ResponseEntity<>(convertToDto(obj), OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
    service.delete(id);
    return new ResponseEntity<>(NO_CONTENT);
  }

  private ClienteDTO convertToDto(Cliente obj) {
    return mapper.map(obj, ClienteDTO.class);
  }

  private Cliente convertToEntity(ClienteDTO dto) {
    return mapper.map(dto, Cliente.class);
  }

}
