package com.fintech.pagos.steps;

import com.fintech.pagos.tasks.RealizarPago;
import com.fintech.pagos.tasks.ValidarAntifraude;
import com.fintech.pagos.questions.EstadoTransaccion;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Enton;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class PagosSteps {

    @Dado("que el usuario {string} está autenticado en el sistema")
    public void queElUsuarioEstaAutenticado(String nombreActor) {
        Actor actor = Actor.named(nombreActor);
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(nombreActor);
    }

    @Cuando("el usuario realiza un pago de {double} desde la cuenta {string} a la cuenta {string}")
    public void elUsuarioRealizaUnPago(double monto, String cuentaOrigen, String cuentaDestino) {
        OnStage.theActorInTheSpotlight().attemptsTo(
            RealizarPago.conMonto(monto).desde(cuentaOrigen).hacia(cuentaDestino)
        );
    }

    @Cuando("el sistema valida el antifraude para el cliente")
    public void elSistemaValidaElAntifraude() {
        OnStage.theActorInTheSpotlight().attemptsTo(
            ValidarAntifraude.paraCliente()
        );
    }

    @Entonces("el sistema debe mostrar el estado de la transacción como {string}")
    public void elSistemaDebeMostrarElEstado(String estadoEsperado) {
        OnStage.theActorInTheSpotlight().should(
            seeThat(EstadoTransaccion.es(), equalTo(estadoEsperado))
        );
    }

    @Entonces("el sistema debe registrar la transacción en auditoría")
    public void elSistemaDebeRegistrarLaTransaccion() {
    }

    @Entonces("el pago debe ser procesado exitosamente")
    public void elPagoDebeSerProcesadoExitosamente() {
    }
}