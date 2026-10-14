package com.fintech.pagos.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ValidarAntifraude implements Task {

    private final String idCliente;
    private final double monto;

    public ValidarAntifraude(String idCliente, double monto) {
        this.idCliente = idCliente;
        this.monto = monto;
    }

    public static ValidarAntifraude conMonto(String idCliente, double monto) {
        return Tasks.instrumented(ValidarAntifraude.class, idCliente, monto);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO: Implementar la validación de reglas antifraude
        // El actor debe invocar al servicio de antifraude para validar la transacción
        // Considerar los siguientes escenarios:
        // - Transacción válida: retorna true
        // - Transacción sospechosa: retorna false
        // - Error de conexión: lanzar excepción específica
        throw new UnsupportedOperationException("ValidarAntifraude no implementado");
    }
}