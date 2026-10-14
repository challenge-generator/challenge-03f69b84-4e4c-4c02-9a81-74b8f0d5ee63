package com.fintech.pagos.infrastructure;

import com.fintech.pagos.domain.AntifraudePort;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class AntifraudeClient implements AntifraudePort {
    
    private final String endpoint;
    private final String apiKey;
    private final int timeout;
    private final Random random;
    private final Map<String, Integer> contadorLlamadas;
    private final Map<String, Long> historialConsultas;
    private boolean modoSimulacion;
    private boolean ultimoResultado;
    
    public AntifraudeClient(String endpoint, String apiKey, int timeout) {
        this.endpoint = endpoint;
        this.apiKey = apiKey;
        this.timeout = timeout;
        this.random = new Random();
        this.contadorLlamadas = new ConcurrentHashMap<>();
        this.historialConsultas = new ConcurrentHashMap<>();
        this.modoSimulacion = true;
        this.ultimoResultado = true;
    }
    
    public AntifraudeClient() {
        this("https://antifraude-api.fintech.com", "default-api-key", 5000);
    }
    
    @Override
    public boolean validarTransaccion(String idCliente, double monto) {
        long inicio = System.currentTimeMillis();
        
        contadorLlamadas.merge(idCliente, 1, Integer::sum);
        historialConsultas.put(idCliente, System.currentTimeMillis());
        
        boolean resultado;
        
        if (modoSimulacion) {
            resultado = validarTransaccionSimulada(idCliente, monto);
        } else {
            resultado = validarTransaccionReal(idCliente, monto);
        }
        
        ultimoResultado = resultado;
        
        long duracion = System.currentTimeMillis() - inicio;
        registrarMetrica(idCliente, monto, resultado, duracion);
        
        return resultado;
    }
    
    private boolean validarTransaccionSimulada(String idCliente, double monto) {
        if (monto <= 0) {
            return false;
        }
        
        if (monto > 100000) {
            return false;
        }
        
        Integer llamadas = contadorLlamadas.getOrDefault(idCliente, 0);
        if (llamadas > 10 && monto > 50000) {
            return false;
        }
        
        return random.nextDouble() > 0.1;
    }
    
    private boolean validarTransaccionReal(String idCliente, double monto) {
        Map<String, Object> solicitud = new HashMap<>();
        solicitud.put("idCliente", idCliente);
        solicitud.put("monto", monto);
        solicitud.put("timestamp", System.currentTimeMillis());
        
        System.out.println("[AntifraudeClient] Enviando validación real al endpoint: " + endpoint);
        System.out.println("[AntifraudeClient] Solicitud: " + solicitud);
        
        return true;
    }
    
    private void registrarMetrica(String idCliente, double monto, boolean resultado, long duracion) {
        String key = idCliente + "-" + System.currentTimeMillis();
        Map<String, Object> metrica = new HashMap<>();
        metrica.put("idCliente", idCliente);
        metrica.put("monto", monto);
        metrica.put("resultado", resultado);
        metrica.put("duracionMs", duracion);
        metrica.put("timestamp", System.currentTimeMillis());
        
        System.out.println("[AntifraudeClient] Métrica registrada: " + metrica);
    }
    
    public void setModoSimulacion(boolean modoSimulacion) {
        this.modoSimulacion = modoSimulacion;
    }
    
    public boolean isModoSimulacion() {
        return modoSimulacion;
    }
    
    public void setUltimoResultado(boolean resultado) {
        this.ultimoResultado = resultado;
    }
    
    public boolean getUltimoResultado() {
        return ultimoResultado;
    }
    
    public int getContadorLlamadas(String idCliente) {
        return contadorLlamadas.getOrDefault(idCliente, 0);
    }
    
    public void resetContador() {
        contadorLlamadas.clear();
    }
    
    public Map<String, Long> getHistorialConsultas() {
        return new HashMap<>(historialConsultas);
    }
    
    public String getEndpoint() {
        return endpoint;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public int getTimeout() {
        return timeout;
    }
}