package com.goivo.api_gerenciador_tarefas.exceptions;

public class TarefaNaoEncontradaException extends RuntimeException {
    public TarefaNaoEncontradaException(String message) {
        super(message);
    }
}
