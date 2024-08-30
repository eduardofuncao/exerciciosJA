package com.goivo.api_gerenciador_tarefas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.goivo.api_gerenciador_tarefas.exceptions.TarefaNaoEncontradaException;

public class GerenciadorDeTarefas implements GerenciadorDeTarefasInterface{
    private List<Tarefa> tarefas = new ArrayList<>();
    private int id = 0;

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
    public Tarefa atualizarTarefaporId(int id, String titulo, String descricao, LocalDateTime dataCriacao) {
        return getTarefaporId(id).atualizarTarefa(titulo, descricao, dataCriacao);
    }

    @Override
    public Tarefa criarTarefa(String titulo, String descricao, LocalDateTime dataCriacao) {
        Tarefa tarefaCriada = new Tarefa(geraId(), titulo, dataCriacao,descricao, StatusTarefa.PENDENTE);
        tarefas.add(tarefaCriada);
        return tarefaCriada;
    }

    // TODO buscar por id do objeto 
    @Override
    public void excluirTarefaporId(int id) {
        int idPararemover = tarefas.indexOf(getTarefaporId(id));
        tarefas.remove(idPararemover);
    }

    @Override
    public Tarefa getTarefaporId(int id) {
        Tarefa tarefaEncontrada = tarefas.stream()
            .filter(tarefa -> tarefa.getId() == id)
            .findFirst().orElseThrow(() -> new TarefaNaoEncontradaException("Tarefa com id " + id + " não encontrada"));
        return tarefaEncontrada;
    }

    @Override
    public List<Tarefa> listarTarefas() {
        return tarefas;
    }

    @Override
    public List<Tarefa> listarTarefas(String filtroStatus) {
        List<Tarefa> tarefasFiltradas = new ArrayList<>();
        StatusTarefa status = StatusTarefa.valueOf(filtroStatus);
        for (Tarefa tarefa: tarefas) {
            if (tarefa.getStatus().equals(status)) {
                tarefasFiltradas.add(tarefa);
            }
        }
        return tarefasFiltradas;
    }

    @Override
    public List<String> listarNomesTarefas() {
        List<String> nomesTarefas = new ArrayList<>();
        for (Tarefa tarefa: tarefas) {
            nomesTarefas.add(tarefa.getTitulo());
        }
        return nomesTarefas;
    }

    @Override
    public Map<StatusTarefa, List<Tarefa>> agruparPorStatus() {
        Map<StatusTarefa, List<Tarefa>> tarefasPorStatus = new HashMap<>();
        for (StatusTarefa status: StatusTarefa.values()) {
            tarefasPorStatus.put(status, new ArrayList<Tarefa>());
        }
        
        for (Tarefa tarefa: tarefas) {
            StatusTarefa status = tarefa.getStatus();
            tarefasPorStatus.get(status).add(tarefa);
        }

        return tarefasPorStatus;
    }

    @Override
    public int contarTarefasPorStatus(String status) {
        return tarefas.stream().reduce(0, (acc, tarefa) -> {
            if (tarefa.getStatus() == StatusTarefa.valueOf(status)) {
                return acc + 1;
            }
            return acc;
        }, Integer::sum);
    }

    @Override
    public Tarefa marcarComoConcluidaPorId(int id) {
        return getTarefaporId(id).marcarComoConcluida();
    }

    @Override
    public int geraId() {
        return id++;
    }
}
