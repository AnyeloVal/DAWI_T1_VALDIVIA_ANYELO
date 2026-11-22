
package com.example.dawi_t1_valdivia_anyelo.exception;

public class EmpleadoNoEncontradoException extends RuntimeException {

    public EmpleadoNoEncontradoException(Integer id) {
        super("No existe un empleado con ID " + id);
    }
}