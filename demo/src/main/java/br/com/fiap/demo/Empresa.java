package br.com.fiap.demo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Empresa {
    private List<SalaDeReuniao> salas;

    public Empresa() {

    }

    public Empresa(List<SalaDeReuniao> salas) {
        this.salas = salas;
    }

    public List<SalaDeReuniao> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaDeReuniao> salas) {
        this.salas = salas;
    }

    public SalaDeReuniao criarSalaDeReuniao(TipoDeSala tipo) {
        Map<LocalDateTime, Set<String>> reservas = new HashMap<>();
        SalaDeReuniao sala = new SalaDeReuniao(salas.size(), reservas, tipo);
        salas.add(sala);
        return sala;
    }
}
