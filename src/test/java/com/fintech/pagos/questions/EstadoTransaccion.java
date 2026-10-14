package com.fintech.pagos.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Pregunta Screenplay para verificar el estado final de una transacción.
 * SUPERFICIE DE PRACTICA: el estudiante debe completar la implementación
 * para que retorne el estado correcto según la UI del sistema de pagos.
 */
public class EstadoTransaccion implements Question<String> {

    private static final Target ESTADO_TRANSACCION = Target.the("Estado de la transacción")
            .locatedBy("//div[@class='transaction-status']");

    public static EstadoTransaccion enLaPantalla() {
        return new EstadoTransaccion();
    }

    @Override
    public String answeredBy(Actor actor) {
        // STUB: retorna un valor hardcodeado que el estudiante debe reemplazar
        // por la lógica real de verificación del estado en la UI
        return Text.of(ESTADO_TRANSACCION).answeredBy(actor);
    }
}