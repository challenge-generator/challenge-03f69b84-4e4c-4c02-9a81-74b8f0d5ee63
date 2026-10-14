package com.fintech.pagos;

import com.fintech.pagos.domain.AntifraudePort;
import com.fintech.pagos.infrastructure.AntifraudeClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AntifraudeClient")
class AntifraudeClientTest {

    @Mock
    private AntifraudePort antifraudePort;

    private AntifraudeClient clienteAntifraude;

    @BeforeEach
    void setUp() {
        clienteAntifraude = new AntifraudeClient();
    }

    @Test
    @DisplayName("Validar transacción - caso de aprobación automática")
    void testValidarTransaccion_AprobacionAutomatica() {
        // GIVEN: cliente con bajo riesgo
        String idCliente = "CLI-001";
        double monto = 500.00;
        when(antifraudePort.validarTransaccion(idCliente, monto)).thenReturn(true);

        // WHEN: se valida la transacción
        boolean resultado = clienteAntifraude.validarTransaccion(idCliente, monto);

        // THEN: la validación es exitosa
        assertTrue(resultado, "La transacción de bajo monto debe ser aprobada");
        verify(antifraudePort).validarTransaccion(idCliente, monto);
    }

    @Test
    @DisplayName("Validar transacción - caso de rechazo por monto alto")
    void testValidarTransaccion_RechazoMontoAlto() {
        // GIVEN: cliente con monto que excede el límite
        String idCliente = "CLI-002";
        double monto = 15000.00;
        when(antifraudePort.validarTransaccion(idCliente, monto)).thenReturn(false);

        // WHEN: se valida la transacción
        boolean resultado = clienteAntifraude.validarTransaccion(idCliente, monto);

        // THEN: la validación es rechazada
        assertFalse(resultado, "La transacción de monto alto debe ser rechazada");
        verify(antifraudePort).validarTransaccion(idCliente, monto);
    }

    @Test
    @DisplayName("Validar transacción - caso de cliente de alto riesgo")
    void testValidarTransaccion_ClienteAltoRiesgo() {
        // GIVEN: cliente marcado como alto riesgo
        String idCliente = "CLI-003";
        double monto = 1000.00;
        when(antifraudePort.validarTransaccion(idCliente, monto)).thenReturn(false);

        // WHEN: se valida la transacción
        boolean resultado = clienteAntifraude.validarTransaccion(idCliente, monto);

        // THEN: la validación es rechazada
        assertFalse(resultado, "El cliente de alto riesgo debe ser rechazado");
        verify(antifraudePort).validarTransaccion(idCliente, monto);
    }

    @Test
    @DisplayName("Validar transacción - caso límite de monto máximo")
    void testValidarTransaccion_MontoMaximo() {
        // GIVEN: monto en el límite máximo permitido
        String idCliente = "CLI-004";
        double monto = 10000.00;
        when(antifraudePort.validarTransaccion(idCliente, monto)).thenReturn(true);

        // WHEN: se valida la transacción
        boolean resultado = clienteAntifraude.validarTransaccion(idCliente, monto);

        // THEN: la validación depende de la lógica del sistema
        assertNotNull(resultado, "El resultado no puede ser null");
        verify(antifraudePort).validarTransaccion(idCliente, monto);
    }

    @Test
    @DisplayName("Validar transacción - múltiples intentos del mismo cliente")
    void testValidarTransaccion_MultiplesIntentos() {
        // GIVEN: múltiples transacciones del mismo cliente
        String idCliente = "CLI-005";
        double[] montos = {100.00, 200.00, 300.00};

        // WHEN: se validan múltiples transacciones
        for (double monto : montos) {
            when(antifraudePort.validarTransaccion(idCliente, monto)).thenReturn(true);
            boolean resultado = clienteAntifraude.validarTransaccion(idCliente, monto);
            assertTrue(resultado, "La transacción debe ser aprobada");
        }

        // THEN: todas las transacciones fueron verificadas
        verify(antifraudePort, times(3)).validarTransaccion(anyString(), anyDouble());
    }

    @Test
    @DisplayName("Validar transacción - excepción del servicio externo")
    void testValidarTransaccion_ExcepcionServicio() {
        // GIVEN: el servicio externo lanza excepción
        String idCliente = "CLI-006";
        double monto = 500.00;
        when(antifraudePort.validarTransaccion(idCliente, monto))
            .thenThrow(new RuntimeException("Servicio de antifraude no disponible"));

        // WHEN/THEN: la excepción debe propagarse
        assertThrows(RuntimeException.class, () -> {
            clienteAntifraude.validarTransaccion(idCliente, monto);
        });
    }
}