package com.fintech.pagos.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class RealizarPago implements Task {

    private final double monto;
    private String cuentaOrigen;
    private String cuentaDestino;

    public RealizarPago(double monto) {
        this.monto = monto;
    }

    public static RealizarPago conMonto(double monto) {
        return Tasks.instrumented(RealizarPago.class, monto);
    }

    public RealizarPago desde(String cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
        return this;
    }

    public RealizarPago hacia(String cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
    }
}