package com.fintech.pagos.models;

import java.time.LocalDateTime;

public record Transaccion(
    String idTransaccion,
    String idCliente,
    String cuentaOrigen,
    String cuentaDestino,
    double monto,
    String moneda,
    LocalDateTime fecha,
    String estado,
    String motivoRechazo
) {
    // Superficie de practica: el participante implementara validaciones y metodos adicionales
}