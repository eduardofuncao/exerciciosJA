package com.goivo.api_gerenciador_tarefas;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface GerenciadorDeTarefasInterface {
    Tarefa criarTarefa(String titulo, String descricao, LocalDateTime dataCriacao);
    Tarefa atualizarTarefaporId(int id, String titulo, String descricao, LocalDateTime dataCriacao);
    void excluirTarefaporId(int id);
    Tarefa getTarefaporId(int id);

    List<Tarefa> listarTarefas();
    List<Tarefa> listarTarefas(String filtro);
    List<String> listarNomesTarefas();
    Map<StatusTarefa, List<Tarefa>> agruparPorStatus();
    int contarTarefasPorStatus(String status);

}
