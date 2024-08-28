package com.goivo.api_gerenciador_tarefas;

import java.time.LocalDateTime;

public class Tarefa implements TarefaInterface{
    private int id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;

    public Tarefa(int id, String titulo, LocalDateTime dataCriacao, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.dataCriacao = dataCriacao;
        this.descricao = descricao;
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

    @Override
    public Tarefa criarTarefa(String titulo, String descricao, LocalDateTime dataCriacao) {
        return null;
    }

    @Override
    public Tarefa atualizarTarefaPorId(int id) {
        return null;
    }

    @Override
    public void excluirTarefaPorId(int id) {

    }

    @Override
    public Tarefa getTarefaPorId(int id) {
        return null;
    }

    @Override
    public Tarefa marcarComoConcluida(int id) {
        return null;
    }
}
