
package com.example.dawi_t1_valdivia_anyelo.service;

import com.example.dawi_t1_valdivia_anyelo.dto.*;
import java.util.List;

public interface IEmpleadoService {

    List<EmpleadoResponse> listar();

    EmpleadoResponse buscar(Integer id);

    EmpleadoResponse registrar(EmpleadoRequest request);

    EmpleadoResponse actualizar(Integer id, EmpleadoRequest request);

    void eliminar(Integer id);
}