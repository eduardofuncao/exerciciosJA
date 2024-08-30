package com.goivo.api_gerenciador_tarefas.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goivo.api_gerenciador_tarefas.GerenciadorDeTarefas;
import com.goivo.api_gerenciador_tarefas.StatusTarefa;
import com.goivo.api_gerenciador_tarefas.Tarefa;

@RestController
@RequestMapping("/api/v1/tarefas")
public class TarefasController {

    GerenciadorDeTarefas gerenciadorDeTarefas = new GerenciadorDeTarefas();


    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa tarefaCriada = gerenciadorDeTarefas.criarTarefa(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDataCriacao());
        return ResponseEntity.status(201).body(tarefaCriada);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(@RequestParam(value = "status", required = false) String filtroStatus) {
        if (filtroStatus == null) {
            return ResponseEntity.ok(gerenciadorDeTarefas.listarTarefas());
        }
        return ResponseEntity.ok(gerenciadorDeTarefas.listarTarefas(filtroStatus.toUpperCase()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getTarefa(@PathVariable("id") int id) {
        return ResponseEntity.ok(gerenciadorDeTarefas.getTarefaporId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@RequestBody Tarefa tarefa, @PathVariable("id") int id) {
        Tarefa tarefaAtualizada = gerenciadorDeTarefas.atualizarTarefaporId(id, tarefa.getTitulo(), tarefa.getDescricao(),tarefa.getDataCriacao());
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable("id") int id) {
        gerenciadorDeTarefas.excluirTarefaporId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nomes")
    public ResponseEntity<List<String>> listarNomesTarefas() {
        return ResponseEntity.ok(gerenciadorDeTarefas.listarNomesTarefas());
    }

    @GetMapping("/agrupar")
    public ResponseEntity<Map<StatusTarefa, List<Tarefa>>> agruparPorStatus() {
        return ResponseEntity.ok(gerenciadorDeTarefas.agruparPorStatus());
    }

    /*/
    @GetMapping("/{status}")
    public ResponseEntity<List<Tarefa>> ListarTarefasPorStatus(@PathVariable("status") String status) {
        return ResponseEntity.ok(gerenciadorDeTarefas.listarTarefas(status.toUpperCase()));
    }
    /*/ 

    @GetMapping("/{status}/contar")
    public ResponseEntity<Integer> contarTarefasPorStatus (@PathVariable("status") String status) {
        return ResponseEntity.ok(gerenciadorDeTarefas.contarTarefasPorStatus(status.toUpperCase()));
    }
    
    @PutMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluirTarefa(@PathVariable("id") int id) {
        Tarefa tarefaConcluida = gerenciadorDeTarefas.marcarComoConcluida(id);
        return ResponseEntity.ok(tarefaConcluida);
    }

}
