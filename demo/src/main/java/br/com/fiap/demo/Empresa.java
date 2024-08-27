package br.com.fiap.demo;

import java.time.LocalDateTime;
import java.util.List;
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

    public void reservarSalaPorId(int id, LocalDateTime dataReuniao, Set<String> participantes) {
        salas.get(id).reservarSala(dataReuniao, participantes);
    }

    public List<SalaDeReuniao> listarSalas() {
        return salas;
    }
}
