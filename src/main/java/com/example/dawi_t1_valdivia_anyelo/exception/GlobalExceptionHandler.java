
package com.example.dawi_t1_valdivia_anyelo.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmpleadoNoEncontradoException.class)
    public ResponseEntity<?> noEncontrado(
            EmpleadoNoEncontradoException e) {

        return ResponseEntity.status(404)
                .body(Map.of("mensaje", e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> datosInvalidos(
            MethodArgumentNotValidException e) {

        Map<String, String> errores = new LinkedHashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.badRequest().body(
                Map.of(
                        "mensaje", "Datos de entrada invalidos",
                        "errores", errores
                )
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> idInvalido(
            MethodArgumentTypeMismatchException e) {

        return ResponseEntity.badRequest()
                .body(Map.of("mensaje", "El ID debe ser un numero valido"));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> jsonInvalido(
            HttpMessageNotReadableException e) {

        return ResponseEntity.badRequest()
                .body(Map.of("mensaje", "El JSON ingresado no es valido"));
    }
}