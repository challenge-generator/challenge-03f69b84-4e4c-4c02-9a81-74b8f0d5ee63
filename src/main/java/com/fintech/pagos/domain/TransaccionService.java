package com.fintech.pagos.domain;

import com.fintech.pagos.models.Transaccion;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransaccionService {
    private final AntifraudePort antifraudePort;
    private final BuroRiesgosPort buroRiesgosPort;
    private final CoreBancarioPort coreBancarioPort;
    private final AuditoriaPort auditoriaPort;

    public TransaccionService(AntifraudePort antifraudePort,
                             BuroRiesgosPort buroRiesgosPort,
                             CoreBancarioPort coreBancarioPort,
                             AuditoriaPort auditoriaPort) {
        this.antifraudePort = antifraudePort;
        this.buroRiesgosPort = buroRiesgosPort;
        this.coreBancarioPort = coreBancarioPort;
        this.auditoriaPort = auditoriaPort;
    }

    public Transaccion procesarTransaccion(String idCliente, String cuentaOrigen,
                                          String cuentaDestino, double monto, String moneda) {
        // Validacion inicial
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }

        // Generar ID unico para la transaccion
        String idTransaccion = UUID.randomUUID().toString();

        // Llamar al motor antifraude
        boolean esFraude = antifraudePort.validarTransaccion(idCliente, monto);
        if (esFraude) {
            return new Transaccion(idTransaccion, idCliente, cuentaOrigen, cuentaDestino,
                                 monto, moneda, LocalDateTime.now(), "RECHAZADO", "Fraude detectado");
        }

        // Consultar buró de riesgos
        boolean esRiesgoAlto = buroRiesgosPort.consultarRiesgo(idCliente);
        if (esRiesgoAlto) {
            return new Transaccion(idTransaccion, idCliente, cuentaOrigen, cuentaDestino,
                                 monto, moneda, LocalDateTime.now(), "RECHAZADO", "Riesgo alto");
        }

        // Procesar en core bancario
        boolean procesada = coreBancarioPort.procesarPago(idCliente, cuentaOrigen, cuentaDestino, monto, moneda);
        if (!procesada) {
            return new Transaccion(idTransaccion, idCliente, cuentaOrigen, cuentaDestino,
                                 monto, moneda, LocalDateTime.now(), "RECHAZADO", "Error en core bancario");
        }

        // Registrar en auditoria
        auditoriaPort.emitirEvento(idTransaccion, idCliente, "PAGO_PROCESADO");

        return new Transaccion(idTransaccion, idCliente, cuentaOrigen, cuentaDestino,
                             monto, moneda, LocalDateTime.now(), "ACEPTADA", null);
    }

    public Transaccion consultarTransaccion(String idTransaccion) {
        // En una implementacion real, esto consultaria una base de datos
        throw new UnsupportedOperationException("Metodo no implementado - superficie de practica");
    }
}

// Contratos de los puertos (interfaces)
interface AntifraudePort {
    boolean validarTransaccion(String idCliente, double monto);
}

interface BuroRiesgosPort {
    boolean consultarRiesgo(String idCliente);
}

interface CoreBancarioPort {
    boolean procesarPago(String idCliente, String cuentaOrigen, String cuentaDestino, double monto, String moneda);
}

interface AuditoriaPort {
    void emitirEvento(String idTransaccion, String idCliente, String tipoEvento);
}