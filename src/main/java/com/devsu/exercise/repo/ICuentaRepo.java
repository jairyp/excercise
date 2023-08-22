package com.devsu.exercise.repo;

import com.devsu.exercise.model.Cuenta;
import com.devsu.exercise.repo.base.IGenericRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICuentaRepo extends IGenericRepo<Cuenta, Integer> {

  @Query("SELECT c FROM Cuenta c WHERE c.numeroCuenta = :numeroCuenta")
  Optional<Cuenta> findByNumeroCuenta(Integer numeroCuenta);

  @Query("SELECT c FROM Cuenta c WHERE c.cliente.idPerson = :clienteId")
  List<Cuenta> findByClienteId(@Param("clienteId") Integer clienteId);
}
