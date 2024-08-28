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
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");

    List<SalaDeReuniao> salas = new ArrayList<>();
    Empresa empresa = new Empresa(salas);

    @PostMapping
    public ResponseEntity<SalaDeReuniao> criarSalaDeReuniao(@RequestBody SalaDeReuniao sala) {
        return ResponseEntity.ok(empresa.criarSalaDeReuniao(sala.getTipo()));
    }

    @GetMapping
    public ResponseEntity<List<SalaDeReuniao>> buscarSalasDaEmpresa() {
        return ResponseEntity.ok(salas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalaDeReuniao> buscarSalaDeReuniao(@PathVariable int id) {
        return ResponseEntity.ok(salas.get(id));
    }

    /*/
    @GetMapping("/{id}/reservas")
    public ResponseEntity<Map<LocalDateTime, Set<String>>> buscarReservas(@PathVariable int id) {
        return ResponseEntity.ok(empresa.getSalas().get(id).listarReservas());
    }
    /*/

    @GetMapping("/{id}/reservas")
    public ResponseEntity<Map<LocalDateTime, Set<String>>> buscarReservasEntreDatas(@PathVariable int id, @RequestParam(required = false) String tipo, @RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim) {
        if (tipo!=null && tipo.equals("ordenado")) {
            return ResponseEntity.ok(empresa.getSalas().get(id).listarReservasOrdenadas());
        }

        if (dataInicio!=null && dataFim!=null) {
            LocalDateTime dataInicioFormatada = LocalDateTime.parse(dataInicio, formatter);
            LocalDateTime dataFimFormatada = LocalDateTime.parse(dataFim, formatter);
            return ResponseEntity.ok(empresa.getSalas().get(id).listarReservasEntreDatas(dataInicioFormatada, dataFimFormatada));
        } else {
            return ResponseEntity.ok(empresa.getSalas().get(id).listarReservas());
        }
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

    //TODO retornar objeto após mudança
    @PutMapping("/{id}/reservas/{dataReserva}")
    public void atualizarReserva(@PathVariable int id, @PathVariable String dataReserva, @RequestBody Map<String, Set<String>> reserva) {
        LocalDateTime formattedDate = LocalDateTime.parse(dataReserva, formatter);
        reserva.forEach((dataReuniao, participantes) -> {
            LocalDateTime dataReuniaoFormatted = LocalDateTime.parse(dataReuniao, formatter);
            empresa.getSalas().get(id).atualizarReserva(formattedDate, dataReuniaoFormatted, participantes);
        });
        //empresa.getSalas().get(id).atualizarReserva();
    }
}
