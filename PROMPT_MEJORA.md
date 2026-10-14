# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/resources/features/pagos_transaccionales.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/resources/features/antifraude_validacion.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/resources/features/idempotencia_transacciones.feature` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/runners/RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/steps/PagosSteps.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/tasks/RealizarPago.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/tasks/ValidarAntifraude.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/interactions/LlamarBuroRiesgos.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/interactions/EmitirEventoAuditoria.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/questions/EstadoTransaccion.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/models/Transaccion.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/TransaccionServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/pagos/AntifraudeClientTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/resources/data/transacciones_prueba.csv` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/test/java/com/fintech/pagos/steps/PagosSteps.java` — `org.hamcrest.Matchers`: El import org.hamcrest.Matchers.equalTo pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — `org.springframework.beans`: El import org.springframework.beans.factory.annotation.Autowired pertenece a org.springframework.beans, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — `org.springframework.boot`: El import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest pertenece a org.springframework.boot, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — `org.springframework.http`: El import org.springframework.http.MediaType pertenece a org.springframework.http, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/pagos/TransaccionControllerTest.java` — `org.springframework.test`: El import org.springframework.test.web.servlet.MockMvc pertenece a org.springframework.test, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/pagos/infrastructure/CoreBancarioClient.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/pagos/infrastructure/AuditoriaEventPublisher.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/pagos/application/TransaccionController.java` — `TransaccionService.getAntifraudePort`: Se invoca `getAntifraudePort` sobre `TransaccionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/pagos/application/TransaccionController.java` — `TransaccionService.getBuroRiesgosPort`: Se invoca `getBuroRiesgosPort` sobre `TransaccionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/pagos/infrastructure/AuditoriaEventPublisher.java` — `EventoAuditoria.id`: Se invoca `id` sobre `EventoAuditoria`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `net.serenity-bdd:serenity-bom@4.1.0`: net.serenity-bdd:serenity-bom declara la version 4.1.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Automatización, Senior

### Brecha de conocimiento
Demuestra su compromiso con la práctica de marcos como TDD y BDD, lo cual ademas de fortalecer su liderazgo, se traduce en la excelencia en calidad del producto, eficiencia del desarrollo y satisfacción del cliente, siendo clave para el éxito general del equipo y del proyecto

### Misión / candidato
Profesional Senior especializado en Automatización de Calidad de Software

### Reto
- Tema: TDD y BDD en Calidad de Software
- Seniority: senior-l2
- Tipo: practical
- Título: Implementación de TDD y BDD en un Sistema de Pagos
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de Casos de Prueba — objetivo: Definir y escribir casos de prueba utilizando BDD para describir el comportamiento esperado del sistema. — entregable (NO resolver): Documento de casos de prueba en formato Gherkin.
- Fase 2: Implementación de Pruebas Unitarias — objetivo: Implementar pruebas unitarias utilizando TDD para asegurar la correcta funcionalidad de los componentes del sistema. — entregable (NO resolver): Conjunto de pruebas unitarias que cubren la funcionalidad del sistema.
- Fase 3: Integración de Pruebas y Refactorización — objetivo: Integrar las pruebas unitarias y de comportamiento, y refactorizar el código para mejorar su calidad y mantenibilidad. — entregable (NO resolver): Pipeline de CI/CD con pruebas integradas y código refactorizado.
- Fase 4: Evaluación y Mejora Continua — objetivo: Evaluar la efectividad de las pruebas y mejorar continuamente el proceso de desarrollo. — entregable (NO resolver): Reporte de evaluación de las pruebas y propuestas de mejora.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.fintech</groupId>
    <artifactId>pagos</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <serenity.version>4.1.0</serenity.version>
        <cucumber.version>7.15.0</cucumber.version>
        <junit.version>5.10.0</junit.version>
        <selenium.version>4.15.0</selenium.version>
        <restassured.version>5.3.2</restassured.version>
    </properties>

    <dependencyManagement>
        <dependencies>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.14.2</version>
        </dependency>

            <dependency>
                <groupId>net.serenity-bdd</groupId>
                <artifactId>serenity-bom</artifactId>
                <version>${serenity.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Serenity BDD -->
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-junit5</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- Cucumber -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- REST Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>${restassured.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <testFailureIgnore>true</testFailureIgnore>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-failsafe-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/*TestSuite.java</include>
                    </includes>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>integration-test</goal>
                            <goal>verify</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>net.serenity-bdd</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.version}</version>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/test/java/com/fintech/pagos/models/Transaccion.java ===
package com.fintech.pagos.models;

import java.time.LocalDateTime;

public record Transaccion(
    String idTransaccion,
    String idCliente,
    String cuentaOrigen,
    String cuentaDestino,
    double monto,
    String moneda,
    LocalDateTime fecha,
    String estado,
    String motivoRechazo
) {
    // Superficie de practica: el participante implementara validaciones y metodos adicionales
}

// === ARCHIVO: src/main/java/com/fintech/pagos/domain/TransaccionService.java ===
package com.fintech.pagos.domain;

import com.fintech.pagos.models.Transaccion;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransaccionService {
    private final AntifraudePort antifraudePort;
    private final BuroRiesgosPort buroRiesgosPort;
    private final CoreBancarioPort coreBancarioPort;
    private final AuditoriaPort auditoriaPort;

    public TransaccionService(AntifraudePort antifraudePort,
                             BuroRiesgosPort buroRiesgosPort,
                             CoreBancarioPort coreBancarioPort,
                             AuditoriaPort auditoriaPort) {
        this.antifraudePort = antifraudePort;
        this.buroRiesgosPort = buroRiesgosPort;
        this.coreBancarioPort = coreBancarioPort;
        this.auditoriaPort = auditoriaPort;
    }

    public Transaccion procesarTransaccion(String idCliente, String cuentaOrigen,
                                          String cuentaDestino, double monto, String moneda) {
        // Validacion inicial
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }

        // Generar ID unico para la transaccion
        String idTransaccion = UUID.randomUUID().toString();

        // Llamar al motor antifraude
        boolean esFraude = antifraudePort.validarTransaccion(idCliente, monto);
        if (esFraude) {
            return new Transaccion(idTransaccion, idCliente, cuentaOrigen, cuentaDestino,
                                 monto, moneda, LocalDateTime.now(), "RECHAZADO", "Fraude detectado");
        }

        // Consultar buró de riesgos
        boolean esRiesgoAlto = buroRiesgosPort.consultarRiesgo(idCliente);
        if (esRiesgoAlto) {
            return new Transaccion(idTransaccion, idCliente, cuentaOrigen, cuentaDestino,
                                 monto, moneda, LocalDateTime.now(), "RECHAZADO", "Riesgo alto");
        }

        // Procesar en core bancario
        boolean procesada = coreBancarioPort.procesarPago(idCliente, cuentaOrigen, cuentaDestino, monto, moneda);
        if (!procesada) {
            return new Transaccion(idTransaccion, idCliente, cuentaOrigen, cuentaDestino,
                                 monto, moneda, LocalDateTime.now(), "RECHAZADO", "Error en core bancario");
        }

        // Registrar en auditoria
        auditoriaPort.emitirEvento(idTransaccion, idCliente, "PAGO_PROCESADO");

        return new Transaccion(idTransaccion, idCliente, cuentaOrigen, cuentaDestino,
                             monto, moneda, LocalDateTime.now(), "ACEPTADA", null);
    }

    public Transaccion consultarTransaccion(String idTransaccion) {
        // En una implementacion real, esto consultaria una base de datos
        throw new UnsupportedOperationException("Metodo no implementado - superficie de practica");
    }
}

// Contratos de los puertos (interfaces)
interface AntifraudePort {
    boolean validarTransaccion(String idCliente, double monto);
}

interface BuroRiesgosPort {
    boolean consultarRiesgo(String idCliente);
}

interface CoreBancarioPort {
    boolean procesarPago(String idCliente, String cuentaOrigen, String cuentaDestino, double monto, String moneda);
}

interface AuditoriaPort {
    void emitirEvento(String idTransaccion, String idCliente, String tipoEvento);
}

// === ARCHIVO: src/test/resources/features/pagos_transaccionales.feature ===
#language: es
Funcionalidad: Procesamiento de pagos transaccionales
  Como usuario del sistema de pagos
  Quiero procesar transacciones financieras de manera segura y confiable
  Para que los clientes puedan realizar pagos con garantía de éxito

  @pagos @smoke
  Escenario: Procesamiento exitoso de un pago
    Dado que el cliente "CLI-001" tiene fondos suficientes en su cuenta "CTA-12345"
    Y que el sistema de antifraude approve la transacción
    Y que el buró de riesgos approve el cliente
    Cuando solicito procesar un pago de "500.00" USD desde la cuenta "CTA-12345" hacia la cuenta "CTA-67890"
    Entonces el sistema debe aprobar la transacción
    Y debe registrar la transacción con estado "APROBADA"
    Y debe emitir un evento de auditoría

  @pagos @rechazo
  Escenario: Rechazo de pago por fondos insuficientes
    Dado que el cliente "CLI-002" tiene fondos insuficientes en su cuenta "CTA-11111"
    Cuando solicito procesar un pago de "10000.00" USD desde la cuenta "CTA-11111" hacia la cuenta "CTA-22222"
    Entonces el sistema debe rechazar la transacción
    Y debe registrar la transacción con estado "RECHAZADA"
    Y el motivo debe ser "FONDOS_INSUFICIENTES"

  @pagos @antifraude
  Escenario: Rechazo de pago por detección de fraude
    Dado que el cliente "CLI-003" tiene fondos suficientes
    Pero el sistema de antifraude rechaza la transacción
    Cuando solicito procesar un pago de "8000.00" USD
    Entonces el sistema debe rechazar la transacción
    Y debe registrar la transacción con estado "RECHAZADA"
    Y el motivo debe ser "FRAUDE_DETECTADO"

  @pagos @riesgo
  Escenario: Rechazo de pago por riesgo de cliente
    Dado que el cliente "CLI-004" tiene un perfil de alto riesgo en el buró de riesgos
    Cuando solicito procesar un pago de "2000.00" USD
    Entonces el sistema debe rechazar la transacción
    Y el motivo debe ser "RIESGO_CLIENTE"

  @pagos @moneda
  Escenario: Procesamiento de pago en moneda diferente
    Dado que el cliente "CLI-005" tiene fondos suficientes en dólares
    Cuando solicito procesar un pago de "1000.00" EUR desde "CTA-33333" hacia "CTA-44444"
    Entonces el sistema debe aprobar la transacción
    Y debe registrar la transacción con la moneda "EUR"

  @pagos @monto-minimo
  Esquema del escenario: Rechazo por monto mínimo no alcanzado
    Dado que el cliente "<cliente>" tiene fondos suficientes
    Cuando solicito procesar un pago de "<monto>" USD
    Entonces el sistema debe rechazar la transacción
    Y el motivo debe ser "MONTO_INFERIOR_MINIMO"
    Ejemplos:
      | cliente | monto  |
      | CLI-006 | 0.50   |
      | CLI-007 | 1.00   |
      | CLI-008 | 9.99   |

  @pagos @monto-maximo
  Esquema del escenario: Rechazo por monto máximo excedido
    Dado que el cliente "<cliente>" tiene fondos suficientes
    Cuando solicito procesar un pago de "<monto>" USD
    Entonces el sistema debe rechazar la transacción
    Y el motivo debe ser "MONTO_SUPERIOR_MAXIMO"
    Ejemplos:
      | cliente | monto      |
      | CLI-009 | 15000.00   |
      | CLI-010 | 20000.00   |
      | CLI-011 | 99999.99   |

  @pagos @concurrencia
  Escenario: Múltiples pagos simultáneos del mismo cliente
    Dado que el cliente "CLI-012" tiene fondos suficientes para múltiples transacciones
    Cuando proceso simultáneamente 3 pagos de "100.00" USD cada uno
    Entonces todos los pagos deben ser procesados
    Y cada transacción debe tener un identificador único

// === ARCHIVO: src/test/resources/features/antifraude_validacion.feature ===
#language: es
Funcionalidad: Validación de reglas antifraude
  Como analista de riesgos
  Quiero que el sistema valide reglas antifraude en cada transacción
  Para prevenir actividades fraudulentas y proteger a los clientes

  @antifraude @smoke
  Escenario: Transacción legítima aprobada por antifraude
    Dado que la transacción tiene un monto de "500.00" USD
    Y el cliente tiene historial de transacciones normales
    Y la ubicación del cliente es "MEXICO"
    Cuando el motor antifraude valida la transacción
    Entonces debe aprobar la transacción

  @antifraude @monto-inusual
  Escenario: Detección de monto inusual
    Dado que el cliente tiene un promedio de transacciones de "500.00" USD
    Cuando el motor antifraude valida una transacción de "50000.00" USD
    Entonces debe rechazar la transacción
    Y debe marcar la bandera "MONTO_INUSUAL"

  @antifraude @frecuencia
  Escenario: Detección de alta frecuencia de transacciones
    Dado que el cliente ha realizado "10" transacciones en los últimos "5" minutos
    Cuando el motor antifraude valida una nueva transacción
    Then debe rechazar la transacción
    Y debe marcar la bandera "ALTA_FRECUENCIA"

  @antifraude @geolocalizacion
  Escenario: Detección de transacción desde ubicación de riesgo
    Dado que la transacción se origina desde "VENEZUELA"
    Y el cliente tiene su dirección registrada en "MEXICO"
    Cuando el motor antifraude valida la transacción
    Entonces debe rechazar la transacción
    Y debe marcar la bandera "UBICACION_SOSPECHOSA"

  @antifraude @patron
  Esquema del escenario: Validación de múltiples reglas antifraude
    Dado que el cliente tiene un historial de "<historial>"
    Y el monto de la transacción es "<monto>"
    Y la frecuencia de transacciones es "<frecuencia>"
    Cuando el motor antifraude valida la transacción
    Then el resultado debe ser "<resultado>"
    Ejemplos:
      | historial        | monto    | frecuencia | resultado  |
      | normal           | 500.00   | normal     | APROBADA   |
      | normal           | 55000.00 | normal     | RECHAZADA  |
      | fraude-previo    | 100.00   | normal     | RECHAZADA  |
      | normal           | 100.00   | alta       | RECHAZADA  |

  @antifraude @dispositivo
  Escenario: Detección de dispositivo nuevo
    Dado que el cliente se autentica desde un dispositivo nuevo
    Y el dispositivo no tiene historial de uso
    Cuando el motor antifraude valida la transacción
    Then debe aprobar la transacción con verificación adicional
    Y debe marcar la bandera "DISPOSITIVO_NUEVO"

  @antifraude @velocidad
  Escenario: Detección de velocidad anómala
    Dado que el cliente intenta realizar transacciones desde diferentes países en menos de "10" minutos
    Cuando el motor antifraude valida la última transacción
    Then debe rechazar la transacción
    Y debe marcar la bandera "VELOCIDAD_ANOMALA"

  @antifraude @lista-negra
  Escenario: Cliente en lista negra
    Dado que el cliente "CLI-999" está en la lista negra de fraude
    Cuando el motor antifraude valida cualquier transacción
    Then debe rechazar la transacción inmediatamente
    Y debe marcar la bandera "LISTA_NEGRA"

// === ARCHIVO: src/test/resources/features/idempotencia_transacciones.feature ===
#language: es
Funcionalidad: Idempotencia en transacciones de pago
  Como desarrollador del sistema de pagos
  Quiero garantizar la idempotencia de las transacciones
  Para evitar cobros duplicados y mantener la consistencia de los datos

  @idempotencia @smoke
  Escenario: Primera solicitud de pago con clave de idempotencia
    Dado que genero una clave de idempotencia "IDEMP-2024-001"
    Y que no existe ninguna transacción con esa clave
    Cuando solicito procesar un pago con esa clave de idempotencia
    Then debe crear una nueva transacción
    Y debe registrar la clave de idempotencia asociada

  @idempotencia @duplicado
  Escenario: Solicitud duplicada con la misma clave de idempotencia
    Dado que ya existe una transacción con clave de idempotencia "IDEMP-2024-002"
    Y el estado de la transacción es "APROBADA"
    Cuando reintento la solicitud con la misma clave de idempotencia
    Then debe devolver la transacción existente
    Y no debe crear una nueva transacción
    Y el montodebited de la cuenta debe ser "500.00" USD

  @idempotencia @fallo
  Escenario: Solicitud duplicada de transacción fallida
    Dado que existe una transacción con clave de idempotencia "IDEMP-2024-003"
    Y el estado de la transacción es "FALLIDA"
    Cuando reintento la solicitud con la misma clave de idempotencia
    Then debe permitir crear una nueva transacción
    Y debe generar un nuevo identificador de transacción

  @idempotencia @timeout
  Escenario: Reintento después de timeout del cliente
    Dado que el cliente envía una solicitud de pago
    Y la solicitud supera el tiempo de espera del cliente
    Pero el servidor procesa la transacción correctamente
    Cuando el cliente reintenta con la misma clave de idempotencia
    Then debe devolver la transacción original
    Y no debe procesar un nuevo pago

  @idempotencia @concurrente
  Escenario: Solicitudes concurrentes con misma clave de idempotencia
    Dado que múltiples clientes envían solicitudes simultáneas
    Y todas usan la misma clave de idempotencia "IDEMP-2024-005"
    Cuando el servidor recibe todas las solicitudes al mismo tiempo
    Then solo debe procesar una transacción
    Y debe responder a todos los clientes con el mismo resultado

  @idempotencia @expiracion
  Esquema del escenario: Clave de idempotencia expirada
    Given una clave de idempotencia "IDEMP-2024-006"
    And la transacción original tiene "<dias>" días de antiguedad
    When solicito un nuevo pago con esa clave
    Then debe crear una nueva transacción
    And debe generar un nuevo identificador
    Ejemplos:
      | dias  |
      | 25    |
      | 30    |
      | 31    |

  @idempotencia @formato
  Escenario: Validación de formato de clave de idempotencia
    Dado que el cliente envía una clave de idempotencia con formato inválido
    Cuando el sistema valida la solicitud
    Then debe rechazar la solicitud
    And debe devolver el error "FORMATO_IDEMPOTENCIA_INVALIDO"

  @idempotencia @datos
  Escenario: Cambio de datos en solicitud duplicada
    Dado que existe una transacción con clave de idempotencia "IDEMP-2024-008"
    Y los datos originales son monto "500.00" USD y cuenta destino "CTA-11111"
    Cuando reintento con monto "600.00" USD y cuenta destino "CTA-22222"
    Then debe rechazar la solicitud
    Y debe devolver el error "DATOS_INCONSISTENTES"

  @idempotencia @consulta
  Escenario: Consulta de transacción por clave de idempotencia
    Dado que realizo una consulta buscando por clave de idempotencia "IDEMP-2024-009"
    When el sistema procesa la consulta
    Then debe devolver los detalles de la transacción asociada
    Y debe incluir el estado actual y el historial de cambios

// === ARCHIVO: src/test/java/com/fintech/pagos/runners/RunCucumberTest.java ===
package com.fintech.pagos.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.fintech.pagos.steps"},
    tags = "@pagos",
    plugin = {"pretty", "json:target/cucumber-reports/cucumber.json"},
    monochrome = true
)
public class RunCucumberTest {
}

// === ARCHIVO: src/test/java/com/fintech/pagos/steps/PagosSteps.java ===
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

// === ARCHIVO: src/test/java/com/fintech/pagos/tasks/RealizarPago.java ===
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

// === ARCHIVO: src/test/java/com/fintech/pagos/tasks/ValidarAntifraude.java ===
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

// === ARCHIVO: src/test/java/com/fintech/pagos/interactions/LlamarBuroRiesgos.java ===
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

// === ARCHIVO: src/test/java/com/fintech/pagos/interactions/EmitirEventoAuditoria.java ===
package com.fintech.pagos.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;

public class EmitirEventoAuditoria implements Interaction {

    private final String idTransaccion;
    private final String idCliente;
    private final String tipoEvento;

    public EmitirEventoAuditoria(String idTransaccion, String idCliente, String tipoEvento) {
        this.idTransaccion = idTransaccion;
        this.idCliente = idCliente;
        this.tipoEvento = tipoEvento;
    }

    public static EmitirEventoAuditoria conDetalles(String idTransaccion, String idCliente, String tipoEvento) {
        return Tasks.instrumented(EmitirEventoAuditoria.class, idTransaccion, idCliente, tipoEvento);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // TODO: Implementar la emisión de evento al sistema de auditoría
        // El actor debe publicar un evento con los datos de la transacción
        // Tipos de evento comunes: TRANSACCION_CREADA, TRANSACCION_APROBADA, TRANSACCION_RECHAZADA
        // Considerar manejo de errores si el sistema de auditoría no está disponible
        // La traza de auditoría es crítica para compliance y auditorías regulatorias
        throw new UnsupportedOperationException("EmitirEventoAuditoria no implementado");
    }
}

// === ARCHIVO: src/test/java/com/fintech/pagos/questions/EstadoTransaccion.java ===
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

// === ARCHIVO: src/test/java/com/fintech/pagos/TransaccionServiceTest.java ===
package com.fintech.pagos;

import com.fintech.pagos.domain.AntifraudePort;
import com.fintech.pagos.domain.AuditoriaPort;
import com.fintech.pagos.domain.BuroRiesgosPort;
import com.fintech.pagos.domain.CoreBancarioPort;
import com.fintech.pagos.domain.TransaccionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para TransaccionService siguiendo TDD.
 * SUPERFICIE DE PRACTICA: el estudiante debe implementar las pruebas
 * siguiendo el ciclo rojo-verde-refactor.
 */
@Disabled("Superficie de práctica - implementar siguiendo TDD")
@ExtendWith(MockitoExtension.class)
class TransaccionServiceTest {

    @Mock
    private AntifraudePort antifraudePort;

    @Mock
    private BuroRiesgosPort buroRiesgosPort;

    @Mock
    private CoreBancarioPort coreBancarioPort;

    @Mock
    private AuditoriaPort auditoriaPort;

    private TransaccionService transaccionService;

    @BeforeEach
    void setUp() {
        transaccionService = new TransaccionService(
            antifraudePort,
            buroRiesgosPort,
            coreBancarioPort,
            auditoriaPort
        );
    }

    @Test
    void procesarTransaccion_exitosa_todosLosServiciosRetornanTrue() {
        // FASE ROJA: escribir la prueba que falla
        // FASE VERDE: implementar la lógica mínima para que pase
        // FASE REFACTOR: mejorar el código sin romper las pruebas

        // TODO: El estudiante debe implementar este test
        // Datos de prueba
        String idCliente = "CLI-001";
        String cuentaOrigen = "CUENTA-123";
        String cuentaDestino = "CUENTA-456";
        double monto = 1000.00;
        String moneda = "USD";

        // Configurar mocks
        // TODO: configurar antifraudePort para retornar true
        // TODO: configurar buroRiesgosPort para retornar true
        // TODO: configurar coreBancarioPort para retornar true

        // Ejecutar
        // TODO: llamar a transaccionService.procesarTransaccion()

        // Verificar
        // TODO: verificar que el resultado no es null
        // TODO: verificar que el estado de la transacción es EXITOSA
        // TODO: verificar que se emitió evento de auditoría

        fail("Test no implementado - estudiante debe completar");
    }

    @Test
    void procesarTransaccion_fallida_antifraudeRechaza() {
        // TODO: El estudiante debe implementar este test
        // Simular caso donde el antifraude rechaza la transacción
        fail("Test no implementado - estudiante debe completar");
    }

    @Test
    void procesarTransaccion_fallida_buroRiesgosRechaza() {
        // TODO: El estudiante debe implementar este test
        // Simular caso donde el buró de riesgos detecta riesgo
        fail("Test no implementado - estudiante debe completar");
    }

    @Test
    void procesarTransaccion_fallida_coreBancarioRechaza() {
        // TODO: El estudiante debe implementar este test
        // Simular caso donde el core bancario rechaza el pago
        fail("Test no implementado - estudiante debe completar");
    }

    @Test
    void consultarTransaccion_existe_retornaTransaccion() {
        // TODO: El estudiante debe implementar este test
        // Verificar que se puede consultar una transacción existente
        fail("Test no implementado - estudiante debe completar");
    }

    @Test
    void consultarTransaccion_noExiste_lanzaExcepcion() {
        // TODO: El estudiante debe implementar este test
        // Verificar comportamiento cuando la transacción no existe
        fail("Test no implementado - estudiante debe completar");
    }

    @Test
    void procesarTransaccion_idempotencia_mismaTransaccionDuplicada() {
        // TODO: El estudiante debe implementar este test
        // Verificar que duplicar una transacción con mismo ID retorna la misma
        fail("Test no implementado - estudiante debe completar");
    }
}

// === ARCHIVO: src/test/java/com/fintech/pagos/TransaccionControllerTest.java ===
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

// === ARCHIVO: src/test/java/com/fintech/pagos/AntifraudeClientTest.java ===
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

// === ARCHIVO: src/test/resources/data/transacciones_prueba.csv ===
id_transaccion,id_cliente,cuenta_origen,cuenta_destino,monto,moneda,estado,resultado_esperado,es_valida,requiere_aprobacion_manual,escenario
TXN-001,CLI-001,CUENTA-AHO-001,CUENTA-CTE-002,500.00,PEN,APROBADA,APROBACION_AUTOMATICA,true,false,transaccion_valida_bajo_monto
TXN-002,CLI-002,CUENTA-AHO-002,CUENTA-CTE-003,15000.00,PEN,RECHAZADA,RECHAZO_MONTO_ALTO,false,true,transaccion_rechazada_monto_alto
TXN-003,CLI-003,CUENTA-AHO-003,CUENTA-CTE-004,1000.00,PEN,RECHAZADA,RECHAZO_CLIENTE_ALTO_RIESGO,false,true,cliente_alto_riesgo
TXN-004,CLI-004,CUENTA-AHO-004,CUENTA-CTE-005,10000.00,PEN,PENDIENTE,REQUIERE_REVISION,false,true,monto_maximo_limite
TXN-005,CLI-005,CUENTA-AHO-005,CUENTA-CTE-006,200.00,PEN,APROBADA,APROBACION_AUTOMATICA,true,false,transaccion_valida_multiple_intento_1
TXN-006,CLI-005,CUENTA-AHO-005,CUENTA-CTE-007,300.00,PEN,APROBADA,APROBACION_AUTOMATICA,true,false,transaccion_valida_multiple_intento_2
TXN-007,CLI-006,CUENTA-AHO-006,CUENTA-CTE-008,500.00,PEN,ERROR,SERVICIO_NO_DISPONIBLE,false,true,excepcion_servicio_externo
TXN-008,CLI-007,CUENTA-AHO-007,CUENTA-CTE-009,50.00,PEN,APROBADA,APROBACION_AUTOMATICA,true,false,transaccion_minima_valida
TXN-009,CLI-008,CUENTA-AHO-008,CUENTA-CTE-010,9999.99,PEN,PENDIENTE,REQUIERE_REVISION,false,true,monto_cercano_maximo
TXN-010,CLI-009,CUENTA-AHO-009,CUENTA-CTE-011,0.01,PEN,RECHAZADA,RECHAZO_MONTO_INVALIDO,false,true,monto_minimo_invalido
TXN-011,CLI-010,CUENTA-AHO-010,CUENTA-CTE-012,7500.00,USD,APROBADA,APROBACION_AUTOMATICA,true,false,transaccion_moneda_extranjera
TXN-012,CLI-011,CUENTA-AHO-011,CUENTA-CTE-013,2500.00,PEN,APROBADA,APROBACION_AUTOMATICA,true,false,transaccion_valida_moneda_local
TXN-013,CLI-012,CUENTA-AHO-012,CUENTA-CTE-014,5000.00,PEN,RECHAZADA,RECHAZO_PATRON_SOSPECHOSO,false,true,patron_transaccion_sospechoso
TXN-014,CLI-013,CUENTA-AHO-013,CUENTA-CTE-015,3500.00,PEN,APROBADA,APROBACION_AUTOMATICA,true,false,transaccion_estandar_normal
TXN-015,CLI-014,CUENTA-AHO-014,CUENTA-CTE-016,8000.00,PEN,PENDIENTE,REQUIERE_APROBACION_MANUAL,false,true,transaccion_requiere_aprobacion

// === ARCHIVO: src/main/java/com/fintech/pagos/application/TransaccionController.java ===
package com.fintech.pagos.application;

import com.fintech.pagos.domain.TransaccionService;
import com.fintech.pagos.models.Transaccion;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class TransaccionController {
    
    private final TransaccionService transaccionService;
    private final Map<String, Transaccion> transacciones;
    private final List<Map<String, Object>> historialLlamadas;
    
    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
        this.transacciones = new HashMap<>();
        this.historialLlamadas = new ArrayList<>();
    }
    
    public Map<String, Object> procesarPago(Map<String, Object> solicitud) {
        String idCliente = (String) solicitud.get("idCliente");
        String cuentaOrigen = (String) solicitud.get("cuentaOrigen");
        String cuentaDestino = (String) solicitud.get("cuentaDestino");
        Double monto = ((Number) solicitud.get("monto")).doubleValue();
        String moneda = (String) solicitud.get("moneda");
        
        registrarLlamada("procesarPago", solicitud);
        
        Transaccion transaccion = transaccionService.procesarTransaccion(
            idCliente, 
            cuentaOrigen, 
            cuentaDestino, 
            monto, 
            moneda
        );
        
        if (transaccion != null) {
            transacciones.put(transaccion.id(), transaccion);
        }
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("estado", transaccion != null ? "EXITOSA" : "RECHAZADA");
        respuesta.put("idTransaccion", transaccion != null ? transaccion.id() : null);
        respuesta.put("mensaje", transaccion != null ? "Transacción procesada exitosamente" : "Transacción rechazada");
        respuesta.put("timestamp", System.currentTimeMillis());
        
        return respuesta;
    }
    
    public Map<String, Object> consultarTransaccion(Map<String, String> parametros) {
        String idTransaccion = parametros.get("idTransaccion");
        
        registrarLlamada("consultarTransaccion", parametros);
        
        Transaccion transaccion = transaccionService.consultarTransaccion(idTransaccion);
        
        Map<String, Object> respuesta = new HashMap<>();
        
        if (transaccion != null) {
            respuesta.put("encontrada", true);
            respuesta.put("id", transaccion.id());
            respuesta.put("idCliente", transaccion.idCliente());
            respuesta.put("monto", transaccion.monto());
            respuesta.put("moneda", transaccion.moneda());
            respuesta.put("estado", transaccion.estado());
            respuesta.put("fecha", transaccion.fecha());
        } else {
            respuesta.put("encontrada", false);
            respuesta.put("mensaje", "Transacción no encontrada");
        }
        
        return respuesta;
    }
    
    public Map<String, Object> validarAntifraude(Map<String, Object> solicitud) {
        String idCliente = (String) solicitud.get("idCliente");
        Double monto = ((Number) solicitud.get("monto")).doubleValue();
        
        registrarLlamada("validarAntifraude", solicitud);
        
        boolean esValida = transaccionService.getAntifraudePort().validarTransaccion(idCliente, monto);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("aprobada", esValida);
        respuesta.put("idCliente", idCliente);
        respuesta.put("monto", monto);
        respuesta.put("timestamp", System.currentTimeMillis());
        
        return respuesta;
    }
    
    public Map<String, Object> consultarRiesgo(Map<String, String> parametros) {
        String idCliente = parametros.get("idCliente");
        
        registrarLlamada("consultarRiesgo", parametros);
        
        boolean tieneRiesgo = transaccionService.getBuroRiesgosPort().consultarRiesgo(idCliente);
        
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("idCliente", idCliente);
        respuesta.put("tieneRiesgo", tieneRiesgo);
        respuesta.put("nivelRiesgo", tieneRiesgo ? "ALTO" : "BAJO");
        respuesta.put("timestamp", System.currentTimeMillis());
        
        return respuesta;
    }
    
    public List<Map<String, Object>> obtenerHistorial() {
        return new ArrayList<>(historialLlamadas);
    }
    
    public void limpiarHistorial() {
        historialLlamadas.clear();
    }
    
    private void registrarLlamada(String endpoint, Map<String, ?> parametros) {
        Map<String, Object> llamada = new HashMap<>();
        llamada.put("endpoint", endpoint);
        llamada.put("parametros", parametros);
        llamada.put("timestamp", System.currentTimeMillis());
        llamada.put("id", UUID.randomUUID().toString());
        historialLlamadas.add(llamada);
    }
    
    public Transaccion getTransaccion(String id) {
        return transacciones.get(id);
    }
    
    public int getTotalTransacciones() {
        return transacciones.size();
    }
}

// === ARCHIVO: src/main/java/com/fintech/pagos/infrastructure/AntifraudeClient.java ===
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

// === ARCHIVO: src/main/java/com/fintech/pagos/infrastructure/BuroRiesgosClient.java ===
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

// === ARCHIVO: src/main/java/com/fintech/pagos/infrastructure/CoreBancarioClient.java ===
package com.fintech.pagos.infrastructure;

import com.fintech.pagos.domain.CoreBancarioPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class CoreBancarioClient implements CoreBancarioPort {

    private static final Logger logger = LoggerFactory.getLogger(CoreBancarioClient.class);
    private static final ConcurrentHashMap<String, String> transaccionesProcesadas = new ConcurrentHashMap<>();
    private static final double MONTO_MINIMO = 1.0;
    private static final double MONTO_MAXIMO = 100000.0;
    private static final String[] BANCOS_VALIDOS = {"BANCOLOMBIA", "DAVIVIENDA", "BBVA", "BANCO_DE_BOGOTA", "AV_VILLAS"};

    public CoreBancarioClient() {
    }

    @Override
    public boolean procesarPago(String idCliente, String cuentaOrigen, String cuentaDestino, double monto, String moneda) {
        if (idCliente == null || idCliente.isBlank()) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo o vacío");
        }
        if (cuentaOrigen == null || cuentaOrigen.isBlank()) {
            throw new IllegalArgumentException("La cuenta de origen no puede ser nulo o vacío");
        }
        if (cuentaDestino == null || cuentaDestino.isBlank()) {
            throw new IllegalArgumentException("La cuenta de destino no puede ser nulo o vacío");
        }
        if (monto < MONTO_MINIMO || monto > MONTO_MAXIMO) {
            logger.warn("Monto {} fuera de límites permitidos [{}, {}]", monto, MONTO_MINIMO, MONTO_MAXIMO);
            return false;
        }
        if (moneda == null || (!moneda.equals("COP") && !moneda.equals("USD") && !moneda.equals("EUR"))) {
            logger.warn("Moneda {} no soportada", moneda);
            return false;
        }

        String idTransaccion = generarIdTransaccion(idCliente);
        boolean resultado = simularProcesamientoCore(idTransaccion, monto);
        
        if (resultado) {
            transaccionesProcesadas.put(idTransaccion, idCliente);
            logger.info("Transacción {} procesada exitosamente para cliente {} por monto {} {}", 
                idTransaccion, idCliente, monto, moneda);
        } else {
            logger.warn("Transacción {} rechazada por el core bancario para cliente {}", 
                idTransaccion, idCliente);
        }
        
        return resultado;
    }

    private String generarIdTransaccion(String idCliente) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String randomSuffix = String.format("%04d", ThreadLocalRandom.current().nextInt(10000));
        return "TXN-" + idCliente + "-" + timestamp.substring(timestamp.length() - 6) + "-" + randomSuffix;
    }

    private boolean simularProcesamientoCore(String idTransaccion, double monto) {
        int probabilidadExito = monto > 50000 ? 85 : 95;
        int resultado = ThreadLocalRandom.current().nextInt(100);
        return resultado < probabilidadExito;
    }

    public boolean verificarTransaccionProcesada(String idTransaccion) {
        return transaccionesProcesadas.containsKey(idTransaccion);
    }

    public int obtenerTotalTransaccionesProcesadas() {
        return transaccionesProcesadas.size();
    }

    public void limpiarCacheTransacciones() {
        transaccionesProcesadas.clear();
        logger.info("Cache de transacciones del core bancario limpiada");
    }
}

// === ARCHIVO: src/main/java/com/fintech/pagos/infrastructure/AuditoriaEventPublisher.java ===
package com.fintech.pagos.infrastructure;

import com.fintech.pagos.domain.AuditoriaPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;

public class AuditoriaEventPublisher implements AuditoriaPort {

    private static final Logger logger = LoggerFactory.getLogger(AuditoriaEventPublisher.class);
    private static final ConcurrentLinkedQueue<EventoAuditoria> eventosPendientes = new ConcurrentLinkedQueue<>();
    private static final AtomicLong secuenciaEventos = new AtomicLong(0L);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .withZone(ZoneId.of("UTC"));

    public AuditoriaEventPublisher() {
        logger.info("Inicializado publicador de eventos de auditoría");
    }

    @Override
    public void emitirEvento(String idTransaccion, String idCliente, String tipoEvento) {
        if (idTransaccion == null || idTransaccion.isBlank()) {
            throw new IllegalArgumentException("El ID de transacción no puede ser nulo o vacío para el evento de auditoría");
        }
        if (idCliente == null || idCliente.isBlank()) {
            throw new IllegalArgumentException("El ID de cliente no puede ser nulo o vacío para el evento de auditoría");
        }
        if (tipoEvento == null || tipoEvento.isBlank()) {
            throw new IllegalArgumentException("El tipo de evento no puede ser nulo o vacío para el evento de auditoría");
        }

        EventoAuditoria evento = new EventoAuditoria(
            generarIdEvento(),
            idTransaccion,
            idCliente,
            tipoEvento,
            Instant.now().toString(),
            "PENDIENTE"
        );

        eventosPendientes.add(evento);
        secuenciaEventos.incrementAndGet();
        
        logger.info("Evento de auditoría emitido: tipo={}, transacción={}, cliente={}, secuencia={}", 
            tipoEvento, idTransaccion, idCliente, secuenciaEventos.get());
    }

    public void confirmarEvento(String idEvento) {
        eventosPendientes.removeIf(e -> e.id().equals(idEvento));
        logger.debug("Evento {} confirmado y removido de la cola de pendientes", idEvento);
    }

    public int obtenerEventosPendientes() {
        return eventosPendientes.size();
    }

    public long obtenerSecuenciaActual() {
        return secuenciaEventos.get();
    }

    private String generarIdEvento() {
        return "EVT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public record EventoAuditoria(
        String id,
        String idTransaccion,
        String idCliente,
        String tipo,
        String timestamp,
        String estado
    ) {}

    public void procesarEventosPendientes() {
        logger.info("Procesando {} eventos de auditoría pendientes", eventosPendientes.size());
        int procesados = 0;
        while (!eventosPendientes.isEmpty()) {
            EventoAuditoria evento = eventosPagados.poll();
            if (evento != null) {
                logger.debug("Procesando evento: {}", evento.id());
                procesados++;
            }
        }
        logger.info("Se procesaron {} eventos de auditoría", procesados);
    }
}

// === ARCHIVO: serenity.conf ===
webdriver {
  driver = "chrome"
  auto.download = true
  wait.for.timeout = 5000
  chrome.switches = "--no-sandbox,--disable-dev-shm-usage,--disable-gpu,--start-maximized"
}

serenity {
  project.name = "Pagos Fintech - Suite de Pruebas"
  test.root = "src/test/resources/features"
  report = {
    concurrency = 10
    fresh.store = false
    linkedsources = true
    show.step.details = true
    show.manual.steps = true
    show.scenarios = true
    show.stories = true
    show.tags = true
    story.threshold = 0
    narrative.threshold = 0
  }
  capture.steps.with.pause = true
  restart.browser.for.each.scenario = false
  keep.uncaptured.requests = false
  take.screenshots = "FOR_FAILURES"
  browser.maximized = true
  timeout = 30
}

environments {
  default {
    base.url = "http://localhost:8080"
    api.payments.endpoint = "http://localhost:8080/api/v1/pagos"
    api.antifraude.endpoint = "http://localhost:8080/api/v1/antifraude"
    api.buro.endpoint = "http://localhost:8080/api/v1/riesgos"
    api.core.endpoint = "http://localhost:8080/api/v1/core"
    api.auditoria.endpoint = "http://localhost:8080/api/v1/auditoria"
  }
  qa {
    base.url = "https://qa.fintech-pagos.com"
    api.payments.endpoint = "https://qa.fintech-pagos.com/api/v1/pagos"
    api.antifraude.endpoint = "https://qa.fintech-pagos.com/api/v1/antifraude"
    api.buro.endpoint = "https://qa.fintech-pagos.com/api/v1/riesgos"
    api.core.endpoint = "https://qa.fintech-pagos.com/api/v1/core"
    api.auditoria.endpoint = "https://qa.fintech-pagos.com/api/v1/auditoria"
  }
  production {
    base.url = "https://fintech-pagos.com"
    api.payments.endpoint = "https://fintech-pagos.com/api/v1/pagos"
    api.antifraude.endpoint = "https://fintech-pagos.com/api/v1/antifraude"
    api.buro.endpoint = "https://fintech-pagos.com/api/v1/riesgos"
    api.core.endpoint = "https://fintech-pagos.com/api/v1/core"
    api.auditoria.endpoint = "https://fintech-pagos.com/api/v1/auditoria"
  }
}

tags {
  exclude = ["@ignore", "@wip"]
  include = ["@pagos", "@antifraude", "@riesgos", "@core", "@auditoria", "@smoke", "@regression", "@e2e"]
}

logging {
  level = "INFO"
  patterns = {
    console = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    file = "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  }
}

reports {
  html = "target/site/serenity"
  json = "target/site/serenity/serenity.json"
  junit = "target/site/serenity/junit.xml"
  xml = "target/site/serenity/serenity.xml"
}

// === ARCHIVO: README.md ===
# Sistema de Pagos - Suite de Pruebas Automatizadas

## Descripción del Proyecto

Proyecto de automatización de pruebas para el sistema de pagos de una fintech. Implementa prácticas de TDD (Test Driven Development) y BDD (Behavior Driven Development) utilizando Serenity BDD, Cucumber y JUnit 5.

## Requisitos Previos

- Java Development Kit (JDK) 21 o superior
- Apache Maven 3.9.x o superior
- Google Chrome (última versión estable)
- Git (para gestión de versiones)

## Configuración del Entorno

### Instalación de JDK 21

```bash
# Verificar instalación
java -version

# En Ubuntu/Debian
sudo apt-get install openjdk-21-jdk

# En macOS
brew install openjdk@21
```

### Instalación de Maven

```bash
# Verificar instalación
mvn -version

# En Ubuntu/Debian
sudo apt-get install maven

# En macOS
brew install maven
```

## Estructura del Proyecto

```
pagos/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── fintech/
│   │               └── pagos/
│   │                   ├── domain/           # Lógica de dominio
│   │                   ├── application/      # Controladores REST
│   │                   └── infrastructure/   # Implementaciones externas
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── fintech/
│       │           └── pagos/
│       │               ├── runners/          # Ejecutores Cucumber
│       │               ├── steps/            # Step Definitions
│       │               ├── tasks/            # Screenplay Tasks
│       │               ├── interactions/     # Screenplay Interactions
│       │               ├── questions/        # Screenplay Questions
│       │               └── pages/            # Page Objects
│       └── resources/
│           ├── features/                     # Archivos .feature (Gherkin)
│           └── data/                         # Datos de prueba
├── .github/
│   └── workflows/                            # Pipelines CI/CD
├── serenity.conf                             # Configuración Serenity
└── pom.xml                                   # Dependencias Maven
```

## Ejecución de Pruebas

### Compilación del Proyecto

```bash
mvn clean compile
```

### Ejecución de Pruebas Unitarias

```bash
mvn test
```

### Ejecución de Pruebas BDD

```bash
mvn verify
```

### Ejecución de Pruebas con Reporte Serenity

```bash
mvn clean verify serenity:aggregate
```

### Ejecución por Tags

```bash
# Solo pruebas de pagos
mvn verify -Dcucumber.options="--tags @pagos"

# Solo pruebas de antifraude
mvn verify -Dcucumber.options="--tags @antifraude"

# Pruebas de smoke
mvn verify -Dcucumber.options="--tags @smoke"
```

### Ejecución por Entorno

```bash
# Ambiente QA
mvn verify -Denvironment=qa

# Ambiente Production
mvn verify -Denvironment=production
```

## Reportes de Pruebas

### Reporte HTML Serenity

Los reportes se generan en `target/site/serenity/index.html` después de ejecutar las pruebas.

```bash
# Abrir reporte en navegador
open target/site/serenity/index.html
```

### Reporte JUnit

Los resultados JUnit están disponibles en `target/surefire-reports/` para pruebas unitarias y `target/failsafe-reports/` para pruebas de integración.

## Configuración de Serenity

El archivo `serenity.conf` contiene la configuración principal:

- Configuración de WebDriver (Chrome por defecto)
- URLs de los entornos (default, qa, production)
- Configuración de screenshots y timeouts
- Tags para filtrar escenarios
- Configuración de logging

## Tags Disponibles

| Tag | Descripción |
|-----|-------------|
| @pagos | Casos de prueba de pagos transaccionales |
| @antifraude | Casos de validación antifraude |
| @riesgos | Casos de consulta al buró de riesgos |
| @core | Casos de integración con core bancario |
| @auditoria | Casos de auditoría de transacciones |
| @smoke | Pruebas de smoke testing |
| @regression | Pruebas de regresión completa |
| @e2e | Pruebas end-to-end |
| @wip | Pruebas en progreso (excluidas por defecto) |

## Pipeline CI/CD

El proyecto incluye un pipeline de GitHub Actions en `.github/workflows/ci-pipeline.yml` que ejecuta:

1. Compilación del proyecto
2. Pruebas unitarias
3. Pruebas BDD con Serenity
4. Generación de reportes

Los reportes se publican automáticamente después de cada ejecución.

## Dependencias Principales

- **Serenity BDD 4.1.0**: Framework de automatización
- **Cucumber 7.15.0**: BDD framework
- **JUnit 5.10.0**: Unit testing
- **Selenium 4.15.0**: Web automation
- **REST Assured 5.3.2**: API testing

## Contribución

Para contribuir al proyecto:

1. Crear un branch desde `main`
2. Implementar los cambios siguiendo el patrón Screenplay
3. Agregar o actualizar escenarios Gherkin en `src/test/resources/features`
4. Ejecutar las pruebas localmente
5. Crear Pull Request

## Licencia

Proyecto interno de Fintech - Uso exclusivo

// === ARCHIVO: .github/workflows/ci-pipeline.yml ===
name: CI Pipeline - Pagos Fintech

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]
  workflow_dispatch:

env:
  JAVA_VERSION: '21'
  MAVEN_VERSION: '3.9.6'
  SERENITY_VERSION: '4.1.0'

jobs:
  build:
    name: Build and Compile
    runs-on: ubuntu-latest
    
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
        
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          java-version: ${{ env.JAVA_VERSION }}
          distribution: 'temurin'
          cache: 'maven'
          
      - name: Cache Maven packages
        uses: actions/cache@v3
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-m2-
            
      - name: Build project
        run: mvn clean compile -DskipTests
        
      - name: Verify compilation
        run: |
          if [ ! -d "target/classes" ]; then
            echo "Compilation failed - target/classes not found"
            exit 1
          fi
          echo "Compilation successful"

  unit-tests:
    name: Unit Tests
    runs-on: ubuntu-latest
    needs: build
    
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
        
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          java-version: ${{ env.JAVA_VERSION }}
          distribution: 'temurin'
          cache: 'maven'
          
      - name: Cache Maven packages
        uses: actions/cache@v3
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-m2-
            
      - name: Run unit tests
        run: mvn test -Dtest=*Test
        
      - name: Upload unit test results
        uses: actions/upload-artifact@v3
        if: always()
        with:
          name: unit-test-results
          path: target/surefire-reports/*.xml
          retention-days: 30
          
      - name: Generate test summary
        if: always()
        run: |
          echo "## Unit Test Results" >> $GITHUB_STEP_SUMMARY
          if [ -f "target/surefire-reports/testng-results.xml" ]; then
            echo "Tests executed successfully" >> $GITHUB_STEP_SUMMARY
          else
            echo "No test results found" >> $GITHUB_STEP_SUMMARY
          fi

  bdd-tests:
    name: BDD Tests - Serenity
    runs-on: ubuntu-latest
    needs: build
    
    services:
      chrome:
        image: selenium/standalone-chrome:latest
        ports:
          - 4444:4444
          - 7900:7900
        options: --shm-size=2g
    
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
        
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          java-version: ${{ env.JAVA_VERSION }}
          distribution: 'temurin'
          cache: 'maven'
          
      - name: Cache Maven packages
        uses: actions/cache@v3
        with:
          path: ~/.m2
          key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
          restore-keys: |
            ${{ runner.os }}-m2-
            
      - name: Run BDD tests
        run: mvn verify -Denvironment=qa
        continue-on-error: true
        
      - name: Upload Serenity reports
        uses: actions/upload-artifact@v3
        if: always()
        with:
          name: serenity-reports
          path: target/site/serenity/
          retention-days: 30
          
      - name: Upload test results
        uses: actions/upload-artifact@v3
        if: always()
        with:
          name: bdd-test-results
          path: |
            target/failsafe-reports/*.xml
            target/surefire-reports/*.xml
          retention-days: 30
          
      - name: Generate BDD test summary
        if: always()
        run: |
          echo "## BDD Test Results" >> $GITHUB_STEP_SUMMARY
          TOTAL=$(find target -name "serenity.json" -exec cat {} \; 2>/dev/null | grep -c '"scenario"' || echo "0")
          echo "Total scenarios executed: $TOTAL" >> $GITHUB_STEP_SUMMARY
          if [ -f "target/site/serenity/index.html" ]; then
            echo "Serenity report generated successfully" >> $GITHUB_STEP_SUMMARY
          fi

  quality-gate:
    name: Quality Gate
    runs-on: ubuntu-latest
    needs: [unit-tests, bdd-tests]
    if: always()
    
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
        
      - name: Set up JDK
        uses: actions/setup-java@v4
        with:
          java-version: ${{ env.JAVA_VERSION }}
          distribution: 'temurin'
          cache: 'maven'
          
      - name: Download test results
        uses: actions/download-artifact@v3
        with:
          name: bdd-test-results
          path: target/test-results
          
      - name: Check test results
        run: |
          echo "## Quality Gate Status" >> $GITHUB_STEP_SUMMARY
          
          UNIT_STATUS="${{ needs.unit-tests.result }}"
          BDD_STATUS="${{ needs.bdd-tests.result }}"
          
          echo "Unit Tests: $UNIT_STATUS" >> $GITHUB_STEP_SUMMARY
          echo "BDD Tests: $BDD_STATUS" >> $GITHUB_STEP_SUMMARY
          
          if [ "$UNIT_STATUS" = "success" ] && [ "$BDD_STATUS" != "failure" ]; then
            echo "✅ Quality Gate PASSED" >> $GITHUB_STEP_SUMMARY
            exit 0
          else
            echo "❌ Quality Gate FAILED" >> $GITHUB_STEP_SUMMARY
            exit 1
          fi

  publish-reports:
    name: Publish Reports
    runs-on: ubuntu-latest
    needs: [bdd-tests]
    if: always()
    
    steps:
      - name: Checkout code
        uses: actions/checkout@v4
        
      - name: Download Serenity reports
        uses: actions/download-artifact@v3
        with:
          name: serenity-reports
          path: target/site/serenity
          
      - name: Deploy reports
        uses: actions/upload-pages-artifact@v3
        with:
          path: target/site/serenity
        
      - name: Create deployment
        uses: actions/deploy-pages@v4
        if: github.ref == 'refs/heads/main'
```
