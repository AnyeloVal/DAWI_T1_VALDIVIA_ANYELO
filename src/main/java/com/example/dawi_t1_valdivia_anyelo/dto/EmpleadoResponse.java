
package com.example.dawi_t1_valdivia_anyelo.dto;

import com.example.dawi_t1_valdivia_anyelo.entity.Empleado;

public class EmpleadoResponse {

    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String especialidad;

    public EmpleadoResponse(Empleado empleado) {
        this.id = empleado.getId();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.email = empleado.getEmail();
        this.especialidad = empleado.getEspecialidad();
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}