package com.goivo.api_gerenciador_tarefas;

import java.time.LocalDateTime;

import com.goivo.api_gerenciador_tarefas.exceptions.TarefaJaConcluidaException;

public class Tarefa implements TarefaInterface{
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private StatusTarefa status;

    public Tarefa(int id, String titulo, LocalDateTime dataCriacao, String descricao, StatusTarefa status) {
        this.id = id;
        this.titulo = titulo;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
        this.status = status;
    }

    public Tarefa() {
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    @Override
    public Tarefa atualizarTarefa(String titulo, String descricao, LocalDateTime dataCriacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        return this;
    }

    @Override
    public Tarefa marcarComoConcluida() {
        if (this.getStatus() == StatusTarefa.CONCLUIDA) {
            throw new TarefaJaConcluidaException("A tarefa com id " + this.getId() + " já foi concluída");
        }
        this.setStatus(StatusTarefa.CONCLUIDA);
        return this;
    }

}