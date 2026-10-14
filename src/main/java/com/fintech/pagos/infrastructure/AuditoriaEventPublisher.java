package com.fintech.pagos.infrastructure;

import com.fintech.pagos.domain.AuditoriaPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class AuditoriaEventPublisher implements AuditoriaPort {

    private static final Logger logger = LoggerFactory.getLogger(AuditoriaEventPublisher.class);
    private static final ConcurrentLinkedQueue<EventoAuditoria> eventosPendientes = new ConcurrentLinkedQueue<>();
    private static final AtomicLong secuenciaEventos = new AtomicLong(0L);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .withZone(ZoneId.of("UTC"));

    public AuditoriaEventPublisher() {
        logger.info("Inicializado publicador de eventos de auditoría");
    }

    @Override
    public void emitirEvento(String idTransaccion, String idCliente, String tipoEvento) {
        if (idTransaccion == null || idTransaccion.isBlank()) {
            throw new IllegalArgumentException("El ID de transacción no puede ser nulo o vacío para el evento de auditoría");
        }
        if (idCliente == null || idCliente.isBlank()) {
            throw new IllegalArgumentException("El ID de cliente no puede ser nulo o vacío para el evento de auditoría");
        }
        if (tipoEvento == null || tipoEvento.isBlank()) {
            throw new IllegalArgumentException("El tipo de evento no puede ser nulo o vacío para el evento de auditoría");
        }

        EventoAuditoria evento = new EventoAuditoria(
            generarIdEvento(),
            idTransaccion,
            idCliente,
            tipoEvento,
            Instant.now().toString(),
            "PENDIENTE"
        );

        eventosPendientes.add(evento);
        secuenciaEventos.incrementAndGet();
        
        logger.info("Evento de auditoría emitido: tipo={}, transacción={}, cliente={}, secuencia={}", 
            tipoEvento, idTransaccion, idCliente, secuenciaEventos.get());
    }

    public void confirmarEvento(String idEvento) {
        eventosPendientes.removeIf(e -> e.id().equals(idEvento));
        logger.debug("Evento {} confirmado y removido de la cola de pendientes", idEvento);
    }

    public int obtenerEventosPendientes() {
        return eventosPendientes.size();
    }

    public long obtenerSecuenciaActual() {
        return secuenciaEventos.get();
    }

    private String generarIdEvento() {
        return "EVT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public record EventoAuditoria(
        String id,
        String idTransaccion,
        String idCliente,
        String tipo,
        String timestamp,
        String estado
    ) {}

    public void procesarEventosPendientes() {
        logger.info("Procesando {} eventos de auditoría pendientes", eventosPendientes.size());
        int procesados = 0;
        while (!eventosPendientes.isEmpty()) {
            EventoAuditoria evento = eventosPagados.poll();
            if (evento != null) {
                logger.debug("Procesando evento: {}", evento.id());
                procesados++;
            }
        }
        logger.info("Se procesaron {} eventos de auditoría", procesados);
    }
}