package com.goivo.api_gerenciador_tarefas.exceptions;

public class TarefaJaConcluidaException extends RuntimeException {
    public TarefaJaConcluidaException(String message) {
        super(message);
    }
}
