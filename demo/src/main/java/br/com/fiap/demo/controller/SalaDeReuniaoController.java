package br.com.fiap.demo.controller;

import br.com.fiap.demo.Empresa;
import br.com.fiap.demo.SalaDeReuniao;
import br.com.fiap.demo.TipoDeSala;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/fiap/salas")
public class SalaDeReuniaoController {
    Map<LocalDateTime, Set<String>> reservas = new HashMap<>();
    SalaDeReuniao sala1 = new SalaDeReuniao(1, reservas, TipoDeSala.MEDIA );
    SalaDeReuniao sala2 = new SalaDeReuniao(2, reservas, TipoDeSala.PEQUENA );
    List<SalaDeReuniao> salas = Arrays.asList(sala1, sala2);

    Empresa empresa = new Empresa(salas);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

    //TODO endpoint post para criar as salas

    @GetMapping
    public ResponseEntity<List<SalaDeReuniao>> buscarSalasDaEmpresa() {
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDeReuniao> buscarSalaDeReuniao(@PathVariable int id) {
        return ResponseEntity.ok(salas.get(id));
    }

    @GetMapping("/{id}/reservas")
    public ResponseEntity<Map<LocalDateTime, Set<String>>> buscarReservas(@PathVariable int id) {
        return ResponseEntity.ok(empresa.getSalas().get(id).listarReservas());
    }

    @PostMapping("{id}/reservas")
    public ResponseEntity<Map<LocalDateTime, Set<String>>> criarReserva(@PathVariable int id, @RequestBody Map<String, Set<String>> reservas) {
        Map<LocalDateTime, Set<String>> reservasCriadas = new HashMap<>();
        reservas.forEach((dataReuniao, participantes) -> {
            LocalDateTime dataReuniaoFormatted = LocalDateTime.parse(dataReuniao, formatter);
            empresa.getSalas().get(id).reservarSala(dataReuniaoFormatted, participantes);
            reservasCriadas.put(dataReuniaoFormatted, participantes);
        });
        return ResponseEntity.ok(reservasCriadas);
    }

    @GetMapping("/{id}/reservas/{dataReserva}")
    public ResponseEntity<Map<LocalDateTime, Set<String>>> buscarReservaPorData(@PathVariable int id, @PathVariable String dataReserva) {
        LocalDateTime formattedDate = LocalDateTime.parse(dataReserva, formatter);
        return ResponseEntity.ok(empresa.getSalas().get(id).listarReservaPorData(formattedDate));
    }


    @DeleteMapping("/{id}/reservas/{dataReserva}")
    public ResponseEntity<Void> deletarReservaPorData(@PathVariable int id, @PathVariable String dataReserva) {
        LocalDateTime formattedDate = LocalDateTime.parse(dataReserva, formatter);
        empresa.getSalas().get(id).cancelarReserva(formattedDate);
        return ResponseEntity.ok().build();
    }

    //TODO @PutMapping("/{id}/reservas/{dataReserva}")
}
