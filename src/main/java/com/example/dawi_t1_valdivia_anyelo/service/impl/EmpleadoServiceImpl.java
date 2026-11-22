
package com.example.dawi_t1_valdivia_anyelo.service.impl;

import com.example.dawi_t1_valdivia_anyelo.dto.*;
import com.example.dawi_t1_valdivia_anyelo.entity.Empleado;
import com.example.dawi_t1_valdivia_anyelo.exception.EmpleadoNoEncontradoException;
import com.example.dawi_t1_valdivia_anyelo.repository.EmpleadoRepository;
import com.example.dawi_t1_valdivia_anyelo.service.IEmpleadoService;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoRepository repository;

    public EmpleadoServiceImpl(EmpleadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EmpleadoResponse> listar() {
        return repository.findAll().stream()
                .map(EmpleadoResponse::new)
                .toList();
    }

    @Override
    public EmpleadoResponse buscar(Integer id) {
        Empleado empleado = obtenerEmpleado(id);
        return new EmpleadoResponse(empleado);
    }

    @Override
    public EmpleadoResponse registrar(EmpleadoRequest request) {
        Empleado empleado = new Empleado();

        empleado.setNombre(request.getNombre().trim());
        empleado.setApellido(request.getApellido().trim());
        empleado.setEmail(request.getEmail().trim());
        empleado.setEspecialidad(request.getEspecialidad());

        return new EmpleadoResponse(repository.save(empleado));
    }

    @Override
    public EmpleadoResponse actualizar(Integer id, EmpleadoRequest request) {
        Empleado empleado = obtenerEmpleado(id);

        empleado.setNombre(request.getNombre().trim());
        empleado.setApellido(request.getApellido().trim());
        empleado.setEmail(request.getEmail().trim());
        empleado.setEspecialidad(request.getEspecialidad());

        return new EmpleadoResponse(repository.save(empleado));
    }

    @Override
    public void eliminar(Integer id) {
        Empleado empleado = obtenerEmpleado(id);
        repository.delete(empleado);
    }

    private Empleado obtenerEmpleado(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmpleadoNoEncontradoException(id));
    }
}