package com.goivo.api_gerenciador_tarefas;

import java.time.LocalDateTime;

public interface TarefaInterface {
    /*
    id, titulo, descricao, data de criacao, isConcluida
     */

    Tarefa atualizarTarefa(String titulo, String descricao, LocalDateTime dataCriacao);
    Tarefa marcarComoConcluida();
}
