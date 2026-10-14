package com.fintech.pagos.infrastructure;

import com.fintech.pagos.domain.CoreBancarioPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class CoreBancarioClient implements CoreBancarioPort {

    private static final Logger logger = LoggerFactory.getLogger(CoreBancarioClient.class);
    private static final ConcurrentHashMap<String, String> transaccionesProcesadas = new ConcurrentHashMap<>();
    private static final double MONTO_MINIMO = 1.0;
    private static final double MONTO_MAXIMO = 100000.0;
    private static final String[] BANCOS_VALIDOS = {"BANCOLOMBIA", "DAVIVIENDA", "BBVA", "BANCO_DE_BOGOTA", "AV_VILLAS"};

    public CoreBancarioClient() {
    }

    @Override
    public boolean procesarPago(String idCliente, String cuentaOrigen, String cuentaDestino, double monto, String moneda) {
        if (idCliente == null || idCliente.isBlank()) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo o vacío");
        }
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            throw new IllegalArgumentException("La cuenta de origen no puede ser nulo o vacío");
        }
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            throw new IllegalArgumentException("La cuenta de destino no puede ser nulo o vacío");
        }
        if (monto < MONTO_MINIMO || monto > MONTO_MAXIMO) {
            logger.warn("Monto {} fuera de límites permitidos [{}, {}]", monto, MONTO_MINIMO, MONTO_MAXIMO);
            return false;
        }
        if (moneda == null || (!moneda.equals("COP") && !moneda.equals("USD") && !moneda.equals("EUR"))) {
            logger.warn("Moneda {} no soportada", moneda);
            return false;
        }

        String idTransaccion = generarIdTransaccion(idCliente);
        boolean resultado = simularProcesamientoCore(idTransaccion, monto);
        
        if (resultado) {
            transaccionesProcesadas.put(idTransaccion, idCliente);
            logger.info("Transacción {} procesada exitosamente para cliente {} por monto {} {}", 
                idTransaccion, idCliente, monto, moneda);
        } else {
            logger.warn("Transacción {} rechazada por el core bancario para cliente {}", 
                idTransaccion, idCliente);
        }
        
        return resultado;
    }

    private String generarIdTransaccion(String idCliente) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomSuffix = String.format("%04d", ThreadLocalRandom.current().nextInt(10000));
        return "TXN-" + idCliente + "-" + timestamp.substring(timestamp.length() - 6) + "-" + randomSuffix;
    }

    private boolean simularProcesamientoCore(String idTransaccion, double monto) {
        int probabilidadExito = monto > 50000 ? 85 : 95;
        int resultado = ThreadLocalRandom.current().nextInt(100);
        return resultado < probabilidadExito;
    }

    public boolean verificarTransaccionProcesada(String idTransaccion) {
        return transaccionesProcesadas.containsKey(idTransaccion);
    }

    public int obtenerTotalTransaccionesProcesadas() {
        return transaccionesProcesadas.size();
    }

    public void limpiarCacheTransacciones() {
        transaccionesProcesadas.clear();
        logger.info("Cache de transacciones del core bancario limpiada");
    }
}