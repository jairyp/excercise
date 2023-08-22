package com.devsu.exercise.service.impl;

import com.devsu.exercise.model.Cliente;
import com.devsu.exercise.repo.IClienteRepo;
import com.devsu.exercise.repo.base.IGenericRepo;
import com.devsu.exercise.service.IClienteService;
import com.devsu.exercise.service.base.CRUDImpl;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements IClienteService {

    private final IClienteRepo repo;

    @Override
    protected IGenericRepo<Cliente, Integer> getRepo() {
        return repo;
    }


    @Override
    public Cliente updatePartial(Integer id, Map<String, Object> updates,Cliente obj) {
        for (String key : updates.keySet()) {
            switch (key) {
                case "nombre" -> obj.setNombre((String) updates.get(key));
                case "genero" -> obj.setGenero((String) updates.get(key));
                case "edad" -> obj.setEdad((Integer) updates.get(key));
                case "identificacion" -> obj.setIdentificacion((String) updates.get(key));
                case "direccion" -> obj.setDireccion((String) updates.get(key));
                case "telefono" -> obj.setTelefono((String) updates.get(key));
            }
        }
        return update(obj,id);
    }
}
