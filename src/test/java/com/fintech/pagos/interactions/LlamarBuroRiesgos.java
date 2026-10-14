package com.fintech.pagos.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class LlamarBuroRiesgos implements Interaction {

    private final String idCliente;

    public LlamarBuroRiesgos(String idCliente) {
        this.idCliente = idCliente;
    }

    public static LlamarBuroRiesgos paraCliente(String idCliente) {
        return Tasks.instrumented(LlamarBuroRiesgos.class, idCliente);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO: Implementar la llamada al servicio del buró de riesgos
        // El actor debe consumir el endpoint de consulta de riesgo del cliente
        // Considerar los siguientes escenarios:
        // - Cliente sin riesgo: retorna false
        // - Cliente con riesgo: retorna true
        // - Cliente no encontrado: retornar false o lanzar excepción según lógica de negocio
        // - Timeout: manejar con retry o excepción controlada
        throw new UnsupportedOperationException("LlamarBuroRiesgos no implementado");
    }
}