package com.goivo.api_gerenciador_tarefas;

import java.util.List;
import java.util.Map;

public interface GerenciadorDeTarefasInterface {
    List<Tarefa> listarTarefas();
    List<Tarefa> listarTarefas(String filtro);
    List<String> listarNomesTarefas();
    Map<StatusTarefa, List<Tarefa>> agruparPorStatus(String status);
    int contarTarefasPorStatus(String status);
}
