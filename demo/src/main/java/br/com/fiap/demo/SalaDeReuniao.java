package br.com.fiap.demo;

import br.com.fiap.demo.exceptions.ReservaNaoEncontradaException;
import br.com.fiap.demo.exceptions.SalaJaReservadaException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        AtomicBoolean allowReservation = new AtomicBoolean(true);
        reservas.forEach((inicio, participantes1) -> {
            LocalDateTime fim = inicio.plusHours(1);
            if (dataInicio.isAfter(inicio) && dataInicio.isBefore(fim)) {
                allowReservation.set(false);
                throw new SalaJaReservadaException("Sala já reservada no período da reunião");
            }
        });
        if (allowReservation.get()) {
            reservas.put(dataInicio, participantes);
        }
    }

    @Override
    public void cancelarReserva(LocalDateTime dataReuniao) {
        if (reservas.containsKey(dataReuniao)) {
            reservas.remove(dataReuniao);
        } else {
            throw new ReservaNaoEncontradaException("Reserva não encontrada para a data " + dataReuniao);
        }

    }

    @Override
    public void atualizarReserva(LocalDateTime dataReuniao, LocalDateTime dataNova, Set<String> participantes) {
        reservas.remove(dataReuniao);
        reservas.put(dataNova, participantes);
    }

    @Override
    public Map<LocalDateTime, Set<String>> listarReservas() {
        return reservas;
    }

    @Override
    public Map<LocalDateTime, Set<String>> listarReservaPorData(LocalDateTime dataReuniao) {
        return Map.of(dataReuniao, reservas.get(dataReuniao));
    }

    @Override
    public Map<LocalDateTime, Set<String>> listarReservasEntreDatas(LocalDateTime inicio, LocalDateTime fim) {
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
        Map<LocalDateTime, Set<String>> reservasOrdenadas = new TreeMap<>(reservas);
        return reservasOrdenadas;
    }
}
