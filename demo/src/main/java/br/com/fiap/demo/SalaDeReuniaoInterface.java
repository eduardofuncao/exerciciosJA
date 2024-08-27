package br.com.fiap.demo;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface SalaDeReuniaoInterface {
    void reservarSala(LocalDateTime dataInicio, Set<String> participantes);
    void cancelarReserva(LocalDateTime dataReuniao);
    Map<LocalDateTime, Set<String>> listarReservas();
    Map<LocalDateTime, Set<String>> listarReservaPorData(LocalDateTime dataReuniao);
    Map<LocalDateTime, Set<String>> buscarReservas(LocalDateTime inicio, LocalDateTime fim);
    Map<LocalDateTime, Set<String>> listarReservasOrdenadas();
}
