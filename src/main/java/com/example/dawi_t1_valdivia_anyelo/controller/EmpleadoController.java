
package com.example.dawi_t1_valdivia_anyelo.controller;

import com.example.dawi_t1_valdivia_anyelo.dto.*;
import com.example.dawi_t1_valdivia_anyelo.service.IEmpleadoService;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    private final IEmpleadoService service;

    public EmpleadoController(IEmpleadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> buscar(
            @PathVariable Integer id) {

        return ResponseEntity.ok(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> registrar(
            @Valid @RequestBody EmpleadoRequest request) {

        EmpleadoResponse empleado = service.registrar(request);

        return ResponseEntity
                .created(URI.create("/api/v1/empleados/" + empleado.getId()))
                .body(empleado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody EmpleadoRequest request) {

        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Integer id) {

        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}