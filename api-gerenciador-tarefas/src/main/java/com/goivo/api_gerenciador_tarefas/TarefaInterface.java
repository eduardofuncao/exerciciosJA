package com.goivo.api_gerenciador_tarefas;

import java.time.LocalDateTime;

public interface TarefaInterface {
    /*
    id, titulo, descricao, data de criacao
     */

    Tarefa criarTarefa(String titulo, String descricao, LocalDateTime dataCriacao);
    Tarefa atualizarTarefaPorId(int id);
    void excluirTarefaPorId(int id);
    Tarefa getTarefaPorId(int id);

    Tarefa marcarComoConcluida(int id);
}
