package br.com.fiap.demo;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SalaDeReuniao implements SalaDeReuniaoInterface {
    private int id;
    private Map<LocalDateTime, Set<String>> reservas;
    private TipoDeSala tipo;

    public SalaDeReuniao(int id, Map<LocalDateTime, Set<String>> reservas, TipoDeSala tipo) {
        this.id = id;
        this.reservas = reservas;
        this.tipo = tipo;
    }

    public SalaDeReuniao() {

    }

    public Map<LocalDateTime, Set<String>> getReservas() {
        return reservas;
    }

    public void setReservas(Map<LocalDateTime, Set<String>> reservas) {
        this.reservas = reservas;
    }

    public TipoDeSala getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeSala tipo) {
        this.tipo = tipo;
    }

    @Override
    public void reservarSala(LocalDateTime dataInicio, Set<String> participantes) {
        //TODO criar exceção para tentativa de reserva em horário já reservado
        reservas.put(dataInicio, participantes);
    }

    @Override
    public void cancelarReserva(LocalDateTime dataReuniao) {
        //TODO exceção para cancelamento de reunião não existe
        reservas.remove(dataReuniao);
    }

    @Override
    public Map<LocalDateTime, Set<String>> listarReservas() {
        return reservas;
    }

    @Override
    public Map<LocalDateTime, Set<String>> buscarReservas(LocalDateTime inicio, LocalDateTime fim) {
        Map<LocalDateTime, Set<String>> reservasNoIntervalo = new HashMap<>();
        reservas.forEach((dataInicio, participantes) -> {
            //LocalDateTime dataFim = dataInicio.plusHours(1);
            if (dataInicio.isAfter(inicio) && dataInicio.isBefore(fim)){
                reservasNoIntervalo.put(dataInicio, participantes);
            }
        });

        return reservasNoIntervalo;
    }

    @Override
    public Map<LocalDateTime, Set<String>> listarReservasOrdenadas() {
        return Map.of();
    }
}
