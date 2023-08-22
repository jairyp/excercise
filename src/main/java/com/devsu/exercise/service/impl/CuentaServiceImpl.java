package com.devsu.exercise.service.impl;

import com.devsu.exercise.model.Cuenta;
import com.devsu.exercise.repo.ICuentaRepo;
import com.devsu.exercise.repo.base.IGenericRepo;
import com.devsu.exercise.service.ICuentaService;
import com.devsu.exercise.service.base.CRUDImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CuentaServiceImpl extends CRUDImpl<Cuenta, Integer> implements ICuentaService {

  private final ICuentaRepo repo;

  @Override
  protected IGenericRepo<Cuenta, Integer> getRepo() {
    return repo;
  }

}
