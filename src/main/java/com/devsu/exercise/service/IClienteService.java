package com.devsu.exercise.service;

import com.devsu.exercise.model.Cliente;
import com.devsu.exercise.service.base.ICRUD;
import java.util.Map;

public interface IClienteService extends ICRUD<Cliente, Integer> {
  Cliente updatePartial(Integer id, Map<String, Object> updates,Cliente obj);

}
