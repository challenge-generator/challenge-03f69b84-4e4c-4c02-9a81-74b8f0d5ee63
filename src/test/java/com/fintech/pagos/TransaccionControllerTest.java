package com.fintech.pagos;

import com.fintech.pagos.application.TransaccionController;
import com.fintech.pagos.domain.TransaccionService;
import com.fintech.pagos.models.Transaccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas unitarias para TransaccionController con mocks de servicios.
 * SUPERFICIE DE PRACTICA: el estudiante debe completar los tests
 * verificando el comportamiento del controlador REST.
 */
@Disabled("Superficie de práctica - completar implementación de tests")
@WebMvcTest(TransaccionController.class)
class TransaccionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransaccionService transaccionService;

    private Transaccion transaccionExitosa;
    private Transaccion transaccionFallida;

    @BeforeEach
    void setUp() {
        // TODO: El estudiante debe inicializar los objetos de prueba
        // Transacción exitosa de ejemplo
        transaccionExitosa = null; // TODO: inicializar con datos de prueba

        // Transacción fallida de ejemplo
        transaccionFallida = null; // TODO: inicializar con datos de prueba
    }

    @Test
    void procesarTransaccion_endpointPost_retorna201YTransaccionExitosa() throws Exception {
        // TODO: El estudiante debe implementar este test
        // Verificar que POST /api/transacciones retorna 201 Created
        // con la transacción procesada exitosamente

        // Simulamos la respuesta del servicio
        // TODO: when(transaccionService.procesarTransaccion(...)).thenReturn(...)

        // Ejecutamos la petición
        // mockMvc.perform(post("/api/transacciones")
        //     .contentType(MediaType.APPLICATION_JSON)
        //     .content("{\"idCliente\":\"CLI-001\",\"cuentaOrigen\":\"...\"}"))
        //     .andExpect(status().isCreated())
        //     .andExpect(jsonPath("$.idTransaccion").exists())
        //     .andExpect(jsonPath("$.estado").value("EXITOSA"));

        // Placeholder para que compile
        throw new UnsupportedOperationException("Test no implementado");
    }

    @Test
    void procesarTransaccion_servicioRetornaError_retorna400() throws Exception {
        // TODO: El estudiante debe implementar este test
        // Verificar que cuando el servicio lanza excepción, se retorna 400 Bad Request
        throw new UnsupportedOperationException("Test no implementado");
    }

    @Test
    void consultarTransaccion_endpointGet_retorna200YTransaccion() throws Exception {
        // TODO: El estudiante debe implementar este test
        // Verificar que GET /api/transacciones/{id} retorna 200 OK
        throw new UnsupportedOperationException("Test no implementado");
    }

    @Test
    void consultarTransaccion_noExiste_retorna404() throws Exception {
        // TODO: El estudiante debe implementar este test
        // Verificar que cuando la transacción no existe, se retorna 404 Not Found
        throw new UnsupportedOperationException("Test no implementado");
    }

    @Test
    void procesarTransaccion_parametrosInvalidos_retorna400() throws Exception {
        // TODO: El estudiante debe implementar este test
        // Verificar validación de parámetros obligatorios
        throw new UnsupportedOperationException("Test no implementado");
    }

    @Test
    void procesarTransaccion_montoNegativo_retorna400() throws Exception {
        // TODO: El estudiante debe implementar este test
        // Verificar que montos negativos son rechazados
        throw new UnsupportedOperationException("Test no implementado");
    }

    @Test
    void procesarTransaccion_monedaNoSoportada_retorna400() throws Exception {
        // TODO: El estudiante debe implementar este test
        // Verificar que monedas no soportadas son rechazadas
        throw new UnsupportedOperationException("Test no implementado");
    }
}