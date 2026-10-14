# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Implementación de TDD y BDD en un Sistema de Pagos**.

| | |
|---|---|
| Tema | TDD y BDD en Calidad de Software |
| Nivel | senior-l2 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Serenity BDD 4.1.0 + Cucumber 7.15.0 + JUnit 5.10.0 |
| Patron arquitectonico | Screenplay Pattern con capas de tareas, interacciones, preguntas y modelos de dominio |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- net.serenity-bdd:serenity-core 4.1.0
- net.serenity-bdd:serenity-junit5 4.1.0
- net.serenity-bdd:serenity-cucumber 4.1.0
- io.cucumber:cucumber-java 7.15.0
- io.cucumber:cucumber-junit-platform-engine 7.15.0
- org.junit.jupiter:junit-jupiter-api 5.10.0
- org.junit.jupiter:junit-jupiter-engine 5.10.0
- org.seleniumhq.selenium:selenium-java 4.15.0
- io.rest-assured:rest-assured 5.3.2
- org.apache.maven.plugins:maven-surefire-plugin 3.2.5
- org.apache.maven.plugins:maven-failsafe-plugin 3.2.5
- net.serenity-bdd:serenity-maven-plugin 4.1.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean test-compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean test-compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de Casos de Prueba**: Documento de casos de prueba en formato Gherkin.
- **Fase 2 — Implementación de Pruebas Unitarias**: Conjunto de pruebas unitarias que cubren la funcionalidad del sistema.
- **Fase 3 — Integración de Pruebas y Refactorización**: Pipeline de CI/CD con pruebas integradas y código refactorizado.
- **Fase 4 — Evaluación y Mejora Continua**: Reporte de evaluación de las pruebas y propuestas de mejora.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/resources/features/pagos_transaccionales.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/resources/features/antifraude_validacion.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/resources/features/idempotencia_transacciones.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/runners/RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/steps/PagosSteps.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/tasks/RealizarPago.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/tasks/ValidarAntifraude.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/interactions/LlamarBuroRiesgos.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/interactions/EmitirEventoAuditoria.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/questions/EstadoTransaccion.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/models/Transaccion.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/TransaccionServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/pagos/AntifraudeClientTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/resources/data/transacciones_prueba.csv` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (11)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/test/java/com/fintech/pagos/steps/PagosSteps.java` — `org.hamcrest.Matchers`
      El import org.hamcrest.Matchers.equalTo pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — `org.springframework.beans`
      El import org.springframework.beans.factory.annotation.Autowired pertenece a org.springframework.beans, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — `org.springframework.boot`
      El import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest pertenece a org.springframework.boot, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — `org.springframework.http`
      El import org.springframework.http.MediaType pertenece a org.springframework.http, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — `org.springframework.test`
      El import org.springframework.test.web.servlet.MockMvc pertenece a org.springframework.test, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/pagos/infrastructure/CoreBancarioClient.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/pagos/infrastructure/AuditoriaEventPublisher.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/pagos/application/TransaccionController.java` — `TransaccionService.getAntifraudePort`
      Se invoca `getAntifraudePort` sobre `TransaccionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/pagos/application/TransaccionController.java` — `TransaccionService.getBuroRiesgosPort`
      Se invoca `getBuroRiesgosPort` sobre `TransaccionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/pagos/infrastructure/AuditoriaEventPublisher.java` — `EventoAuditoria.id`
      Se invoca `id` sobre `EventoAuditoria`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `net.serenity-bdd:serenity-bom@4.1.0`
      net.serenity-bdd:serenity-bom declara la version 4.1.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (25)

- `pom.xml`
- `src/test/java/com/fintech/pagos/models/Transaccion.java`
- `src/main/java/com/fintech/pagos/domain/TransaccionService.java`
- `src/test/resources/features/pagos_transaccionales.feature`
- `src/test/resources/features/antifraude_validacion.feature`
- `src/test/resources/features/idempotencia_transacciones.feature`
- `src/test/java/com/fintech/pagos/runners/RunCucumberTest.java`
- `src/test/java/com/fintech/pagos/steps/PagosSteps.java`
- `src/test/java/com/fintech/pagos/tasks/RealizarPago.java`
- `src/test/java/com/fintech/pagos/tasks/ValidarAntifraude.java`
- `src/test/java/com/fintech/pagos/interactions/LlamarBuroRiesgos.java`
- `src/test/java/com/fintech/pagos/interactions/EmitirEventoAuditoria.java`
- `src/test/java/com/fintech/pagos/questions/EstadoTransaccion.java`
- `src/test/java/com/fintech/pagos/TransaccionServiceTest.java`
- `src/test/java/com/fintech/pagos/TransaccionControllerTest.java`
- `src/test/java/com/fintech/pagos/AntifraudeClientTest.java`
- `src/test/resources/data/transacciones_prueba.csv`
- `src/main/java/com/fintech/pagos/application/TransaccionController.java`
- `src/main/java/com/fintech/pagos/infrastructure/AntifraudeClient.java`
- `src/main/java/com/fintech/pagos/infrastructure/BuroRiesgosClient.java`
- `src/main/java/com/fintech/pagos/infrastructure/CoreBancarioClient.java`
- `src/main/java/com/fintech/pagos/infrastructure/AuditoriaEventPublisher.java`
- `serenity.conf`
- `README.md`
- `.github/workflows/ci-pipeline.yml`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/test/java/com/fintech/pagos`
- `src/test/java/com/fintech/pagos/tasks`
- `src/test/java/com/fintech/pagos/interactions`
- `src/test/java/com/fintech/pagos/questions`
- `src/test/java/com/fintech/pagos/models`
- `src/test/java/com/fintech/pagos/runners`
- `src/test/java/com/fintech/pagos/steps`
- `src/test/resources/features`
- `src/test/resources/data`
- `src/main/java/com/fintech/pagos/domain`
- `src/main/java/com/fintech/pagos/application`
- `src/main/java/com/fintech/pagos/infrastructure`
- `.github/workflows`

## Verificacion

```bash
mvn clean test-compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **Screenplay Pattern con capas de tareas, interacciones, preguntas y modelos de dominio**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Tecnología Automatización, Senior
- Brecha que el reto ataca: Demuestra su compromiso con la práctica de marcos como TDD y BDD, lo cual ademas de fortalecer su liderazgo, se traduce en la excelencia en calidad del producto, eficiencia del desarrollo y satisfacción del cliente, siendo clave para el éxito general del equipo y del proyecto
- Mision: Profesional Senior especializado en Automatización de Calidad de Software

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
