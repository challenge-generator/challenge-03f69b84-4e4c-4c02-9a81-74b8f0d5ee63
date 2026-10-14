package com.fintech.pagos.infrastructure;

import com.fintech.pagos.domain.BuroRiesgosPort;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;

public class BuroRiesgosClient implements BuroRiesgosPort {
    
    private final String endpoint;
    private final String usuario;
    private final String password;
    private final int timeout;
    private final Map<String, Boolean> cacheRiesgos;
    private final Map<String, Integer> contadorConsultas;
    private final Set<String> clientesConRiesgo;
    private boolean modoSimulacion;
    private boolean ultimoResultado;
    
    private static final Set<String> CLIENTES_ALTO_RIESGO = new HashSet<>();
    
    static {
        CLIENTES_ALTO_RIESGO.add("CLI-001");
        CLIENTES_ALTO_RIESGO.add("CLI-002");
        CLIENTES_ALTO_RIESGO.add("CLI-003");
        CLIENTES_ALTO_RIESGO.add("CLI-999");
    }
    
    public BuroRiesgosClient(String endpoint, String usuario, String password, int timeout) {
        this.endpoint = endpoint;
        this.usuario = usuario;
        this.password = password;
        this.timeout = timeout;
        this.cacheRiesgos = new ConcurrentHashMap<>();
        this.contadorConsultas = new ConcurrentHashMap<>();
        this.clientesConRiesgo = new HashSet<>(CLIENTES_ALTO_RIESGO);
        this.modoSimulacion = true;
        this.ultimoResultado = false;
    }
    
    public BuroRiesgosClient() {
        this("https://buro-riesgos-api.fintech.com", "usuario-sistema", "password-seguro", 5000);
    }
    
    @Override
    public boolean consultarRiesgo(String idCliente) {
        if (idCliente == null || idCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo o vacío");
        }
        
        if (cacheRiesgos.containsKey(idCliente)) {
            Boolean cacheado = cacheRiesgos.get(idCliente);
            ultimoResultado = cacheado;
            return cacheado;
        }
        
        contadorConsultas.merge(idCliente, 1, Integer::sum);
        
        boolean resultado;
        
        if (modoSimulacion) {
            resultado = consultarRiesgoSimulado(idCliente);
        } else {
            resultado = consultarRiesgoReal(idCliente);
        }
        
        cacheRiesgos.put(idCliente, resultado);
        ultimoResultado = resultado;
        
        registrarConsulta(idCliente, resultado);
        
        return resultado;
    }
    
    private boolean consultarRiesgoSimulado(String idCliente) {
        if (clientesConRiesgo.contains(idCliente)) {
            return true;
        }
        
        Integer consultas = contadorConsultas.getOrDefault(idCliente, 0);
        if (consultas > 5) {
            return true;
        }
        
        return false;
    }
    
    private boolean consultarRiesgoReal(String idCliente) {
        Map<String, Object> solicitud = new HashMap<>();
        solicitud.put("idCliente", idCliente);
        solicitud.put("usuario", usuario);
        solicitud.put("timestamp", System.currentTimeMillis());
        
        System.out.println("[BuroRiesgosClient] Consultando riesgo real al endpoint: " + endpoint);
        System.out.println("[BuroRiesgosClient] Solicitud: " + solicitud);
        
        return false;
    }
    
    private void registrarConsulta(String idCliente, boolean tieneRiesgo) {
        Map<String, Object> registro = new HashMap<>();
        registro.put("idCliente", idCliente);
        registro.put("tieneRiesgo", tieneRiesgo);
        registro.put("timestamp", System.currentTimeMillis());
        registro.put("consultaNumero", contadorConsultas.get(idCliente));
        
        System.out.println("[BuroRiesgosClient] Consulta registrada: " + registro);
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
    
    public void agregarClienteAltoRiesgo(String idCliente) {
        clientesConRiesgo.add(idCliente);
    }
    
    public void removerClienteAltoRiesgo(String idCliente) {
        clientesConRiesgo.remove(idCliente);
    }
    
    public boolean isClienteAltoRiesgo(String idCliente) {
        return clientesConRiesgo.contains(idCliente);
    }
    
    public int getContadorConsultas(String idCliente) {
        return contadorConsultas.getOrDefault(idCliente, 0);
    }
    
    public void limpiarCache() {
        cacheRiesgos.clear();
    }
    
    public void resetContador() {
        contadorConsultas.clear();
    }
    
    public Map<String, Boolean> getCacheRiesgos() {
        return new HashMap<>(cacheRiesgos);
    }
    
    public String getEndpoint() {
        return endpoint;
    }
    
    public String getUsuario() {
        return usuario;
    }
    
    public int getTimeout() {
        return timeout;
    }
}