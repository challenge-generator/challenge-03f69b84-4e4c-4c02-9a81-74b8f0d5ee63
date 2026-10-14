package com.fintech.pagos.application;

import com.fintech.pagos.domain.TransaccionService;
import com.fintech.pagos.models.Transaccion;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class TransaccionController {
    
    private final TransaccionService transaccionService;
    private final Map<String, Transaccion> transacciones;
    private final List<Map<String, Object>> historialLlamadas;
    
    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
        this.transacciones = new HashMap<>();
        this.historialLlamadas = new ArrayList<>();
    }
    
    public Map<String, Object> procesarPago(Map<String, Object> solicitud) {
        String idCliente = (String) solicitud.get("idCliente");
        String cuentaOrigen = (String) solicitud.get("cuentaOrigen");
        String cuentaDestino = (String) solicitud.get("cuentaDestino");
        Double monto = ((Number) solicitud.get("monto")).doubleValue();
        String moneda = (String) solicitud.get("moneda");
        
        registrarLlamada("procesarPago", solicitud);
        
        Transaccion transaccion = transaccionService.procesarTransaccion(
            idCliente, 
            cuentaOrigen, 
            cuentaDestino, 
            monto, 
            moneda
        );
        
        if (transaccion != null) {
            transacciones.put(transaccion.id(), transaccion);
        }
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("estado", transaccion != null ? "EXITOSA" : "RECHAZADA");
        respuesta.put("idTransaccion", transaccion != null ? transaccion.id() : null);
        respuesta.put("mensaje", transaccion != null ? "Transacción procesada exitosamente" : "Transacción rechazada");
        respuesta.put("timestamp", System.currentTimeMillis());
        
        return respuesta;
    }
    
    public Map<String, Object> consultarTransaccion(Map<String, String> parametros) {
        String idTransaccion = parametros.get("idTransaccion");
        
        registrarLlamada("consultarTransaccion", parametros);
        
        Transaccion transaccion = transaccionService.consultarTransaccion(idTransaccion);
        
        Map<String, Object> respuesta = new HashMap<>();
        
        if (transaccion != null) {
            respuesta.put("encontrada", true);
            respuesta.put("id", transaccion.id());
            respuesta.put("idCliente", transaccion.idCliente());
            respuesta.put("monto", transaccion.monto());
            respuesta.put("moneda", transaccion.moneda());
            respuesta.put("estado", transaccion.estado());
            respuesta.put("fecha", transaccion.fecha());
        } else {
            respuesta.put("encontrada", false);
            respuesta.put("mensaje", "Transacción no encontrada");
        }
        
        return respuesta;
    }
    
    public Map<String, Object> validarAntifraude(Map<String, Object> solicitud) {
        String idCliente = (String) solicitud.get("idCliente");
        Double monto = ((Number) solicitud.get("monto")).doubleValue();
        
        registrarLlamada("validarAntifraude", solicitud);
        
        boolean esValida = transaccionService.getAntifraudePort().validarTransaccion(idCliente, monto);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("aprobada", esValida);
        respuesta.put("idCliente", idCliente);
        respuesta.put("monto", monto);
        respuesta.put("timestamp", System.currentTimeMillis());
        
        return respuesta;
    }
    
    public Map<String, Object> consultarRiesgo(Map<String, String> parametros) {
        String idCliente = parametros.get("idCliente");
        
        registrarLlamada("consultarRiesgo", parametros);
        
        boolean tieneRiesgo = transaccionService.getBuroRiesgosPort().consultarRiesgo(idCliente);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idCliente", idCliente);
        respuesta.put("tieneRiesgo", tieneRiesgo);
        respuesta.put("nivelRiesgo", tieneRiesgo ? "ALTO" : "BAJO");
        respuesta.put("timestamp", System.currentTimeMillis());
        
        return respuesta;
    }
    
    public List<Map<String, Object>> obtenerHistorial() {
        return new ArrayList<>(historialLlamadas);
    }
    
    public void limpiarHistorial() {
        historialLlamadas.clear();
    }
    
    private void registrarLlamada(String endpoint, Map<String, ?> parametros) {
        Map<String, Object> llamada = new HashMap<>();
        llamada.put("endpoint", endpoint);
        llamada.put("parametros", parametros);
        llamada.put("timestamp", System.currentTimeMillis());
        llamada.put("id", UUID.randomUUID().toString());
        historialLlamadas.add(llamada);
    }
    
    public Transaccion getTransaccion(String id) {
        return transacciones.get(id);
    }
    
    public int getTotalTransacciones() {
        return transacciones.size();
    }
}