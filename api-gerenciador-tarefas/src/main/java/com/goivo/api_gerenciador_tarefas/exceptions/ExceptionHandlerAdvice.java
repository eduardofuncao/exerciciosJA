package com.goivo.api_gerenciador_tarefas.exceptions;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ExceptionHandlerAdvice {
    
    @ExceptionHandler(TarefaJaConcluidaException.class)
    public ResponseEntity<Map<String, String>> handleTarefaConcluidaException(TarefaJaConcluidaException e) {
        Map<String, String> response = Map.of("status", "200", "message", e.getMessage());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ResponseEntity<Map<String, String>> handleTarefaNaoEncontradaException(TarefaNaoEncontradaException e) {
        Map<String, String> response = Map.of("status", "404", "message", e.getMessage());
        return ResponseEntity.status(404).body(response);
    }
}
 