package com.goivo.api_gerenciador_tarefas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goivo.api_gerenciador_tarefas.GerenciadorDeTarefas;
import com.goivo.api_gerenciador_tarefas.Tarefa;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefasController {

    GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas();


    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        return ResponseEntity.ok(gerenciadorDeTarefas.criarTarefa(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataCriacao()));
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(@RequestParam String filtroStatus) {
        if (filtroStatus == null) {
            return ResponseEntity.ok(gerenciadorDeTarefas.listarTarefas());
        }
        return ResponseEntity.ok(gerenciadorDeTarefas.listarTarefas(filtroStatus));
    }

}
