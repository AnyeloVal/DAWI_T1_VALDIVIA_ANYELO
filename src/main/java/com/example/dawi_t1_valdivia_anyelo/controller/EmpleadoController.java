package com.example.dawi_t1_valdivia_anyelo.controller;

import com.example.dawi_t1_valdivia_anyelo.model.Empleado;
import com.example.dawi_t1_valdivia_anyelo.repository.EmpleadoRepository;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    private final EmpleadoRepository repository;

    public EmpleadoController(EmpleadoRepository repository) {
        this.repository = repository;
    }

    // GET: Listar todos los empleados
    @GetMapping
    public List<Empleado> listar() {
        return repository.findAll();
    }

    // GET: Buscar empleado por ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscar(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Registrar un empleado
    @PostMapping
    public ResponseEntity<Empleado> registrar(
            @Valid @RequestBody Empleado empleado) {

        empleado.setId(null);
        Empleado guardado = repository.save(empleado);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardado);
    }

    // PUT: Actualizar un empleado
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody Empleado datos) {

        return repository.findById(id).map(empleado -> {

            empleado.setNombre(datos.getNombre());
            empleado.setApellido(datos.getApellido());
            empleado.setEmail(datos.getEmail());
            empleado.setEspecialidad(datos.getEspecialidad());

            return ResponseEntity.ok(repository.save(empleado));

        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Eliminar un empleado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
