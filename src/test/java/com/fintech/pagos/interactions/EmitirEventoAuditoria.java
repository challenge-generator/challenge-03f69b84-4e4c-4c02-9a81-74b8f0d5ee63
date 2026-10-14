package com.fintech.pagos.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class EmitirEventoAuditoria implements Interaction {

    private final String idTransaccion;
    private final String idCliente;
    private final String tipoEvento;

    public EmitirEventoAuditoria(String idTransaccion, String idCliente, String tipoEvento) {
        this.idTransaccion = idTransaccion;
        this.idCliente = idCliente;
        this.tipoEvento = tipoEvento;
    }

    public static EmitirEventoAuditoria conDetalles(String idTransaccion, String idCliente, String tipoEvento) {
        return Tasks.instrumented(EmitirEventoAuditoria.class, idTransaccion, idCliente, tipoEvento);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO: Implementar la emisión de evento al sistema de auditoría
        // El actor debe publicar un evento con los datos de la transacción
        // Tipos de evento comunes: TRANSACCION_CREADA, TRANSACCION_APROBADA, TRANSACCION_RECHAZADA
        // Considerar manejo de errores si el sistema de auditoría no está disponible
        // La traza de auditoría es crítica para compliance y auditorías regulatorias
        throw new UnsupportedOperationException("EmitirEventoAuditoria no implementado");
    }
}