package com.devsu.exercise.service.base;

import com.devsu.exercise.exception.NewModelNotFoundException;
import com.devsu.exercise.repo.base.IGenericRepo;
import java.util.List;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

  protected abstract IGenericRepo<T, ID> getRepo();

  @Override
  public T save(T t) {
    return getRepo().save(t);
  }

  @Override
  public T update(T t, ID id) {
    getRepo().findById(id).orElseThrow(() -> new NewModelNotFoundException("ID NOT FOUND " + id));
    return getRepo().save(t);
  }

  @Override
  public List<T> findAll() {
    return getRepo().findAll();
  }

  @Override
  public T findById(ID id) {
    return getRepo().findById(id)
        .orElseThrow(() -> new NewModelNotFoundException("ID NOT FOUND " + id));
  }

  @Override
  public void delete(ID id) {
    getRepo().findById(id).orElseThrow(() -> new NewModelNotFoundException("ID NOT FOUND " + id));
    getRepo().deleteById(id);
  }
}
