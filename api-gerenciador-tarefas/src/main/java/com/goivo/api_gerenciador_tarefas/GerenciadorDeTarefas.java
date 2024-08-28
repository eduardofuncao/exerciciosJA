package com.goivo.api_gerenciador_tarefas;

import java.util.List;
import java.util.Map;

public class GerenciadorDeTarefas implements GerenciadorDeTarefasInterface{
    private List<Tarefa> tarefas;

    public GerenciadorDeTarefas() {
    }

    public GerenciadorDeTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }

    @Override
    public List<Tarefa> listarTarefas() {
        return List.of();
    }

    @Override
    public List<Tarefa> listarTarefas(String filtro) {
        return List.of();
    }

    @Override
    public List<String> listarNomesTarefas() {
        return List.of();
    }

    @Override
    public Map<StatusTarefa, List<Tarefa>> agruparPorStatus(String status) {
        return Map.of();
    }

    @Override
    public int contarTarefasPorStatus(String status) {
        return 0;
    }
}
