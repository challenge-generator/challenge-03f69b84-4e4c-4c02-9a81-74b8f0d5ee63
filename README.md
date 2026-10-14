# Implementación de TDD y BDD en un Sistema de Pagos

Como profesional Senior especializado en Automatización de Calidad de Software, debes implementar y aplicar las prácticas de Test Driven Development (TDD) y Behavior Driven Development (BDD) en un sistema de pagos de una fintech. El sistema debe manejar transacciones con un throughput de 1 500 solicitudes por segundo en hora pico, asegurando idempotencia y consistencia en cada transacción. Los actores involucrados son el originador de créditos, el motor antifraude, el buró de riesgos, y el core bancario. El sistema debe registrar cada transacción con detalles de monto, fecha, y estado, y debe emitir un evento al sistema de auditoría por cada transacción aceptada.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | TDD y BDD en Calidad de Software |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de Casos de Prueba

**Objetivo:** Definir y escribir casos de prueba utilizando BDD para describir el comportamiento esperado del sistema.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar y describir los casos de uso del sistema de pagos utilizando lenguaje de negocio.
- Escribir escenarios de prueba que capturen el comportamiento esperado en términos de negocio.

**Entregable:** Documento de casos de prueba en formato Gherkin.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo describirías el comportamiento del sistema a un no técnico.
- Considera los diferentes actores y sus interacciones en el dominio.

</details>

### Fase 2: Implementación de Pruebas Unitarias

**Objetivo:** Implementar pruebas unitarias utilizando TDD para asegurar la correcta funcionalidad de los componentes del sistema.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Escribir pruebas unitarias para cada componente del sistema de pagos.
- Asegurar que las pruebas fallen inicialmente y luego implementar el código necesario para que pasen.

**Entregable:** Conjunto de pruebas unitarias que cubren la funcionalidad del sistema.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo escribir pruebas que fallen inicialmente y luego hacerlas pasar.
- Considera los diferentes componentes y sus responsabilidades en el sistema.

</details>

### Fase 3: Integración de Pruebas y Refactorización

**Objetivo:** Integrar las pruebas unitarias y de comportamiento, y refactorizar el código para mejorar su calidad y mantenibilidad.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Integrar las pruebas unitarias y de comportamiento en un pipeline de CI/CD.
- Refactorizar el código para mejorar su calidad y mantenibilidad, asegurando que las pruebas sigan pasando.

**Entregable:** Pipeline de CI/CD con pruebas integradas y código refactorizado.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo integrar las pruebas en un pipeline de CI/CD.
- Considera las mejores prácticas para refactorizar el código sin romper las pruebas.

</details>

### Fase 4: Evaluación y Mejora Continua

**Objetivo:** Evaluar la efectividad de las pruebas y mejorar continuamente el proceso de desarrollo.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Evaluar la cobertura de las pruebas y identificar áreas de mejora.
- Proponer mejoras al proceso de desarrollo para asegurar la calidad del producto.

**Entregable:** Reporte de evaluación de las pruebas y propuestas de mejora.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo evaluar la efectividad de las pruebas.
- Considera las mejores prácticas para mejorar continuamente el proceso de desarrollo.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es TDD y BDD y cómo se aplican en el desarrollo de software?
- **paraQueSirve**: ¿Para qué sirven las prácticas de TDD y BDD en el contexto de un sistema de pagos?
- **comoSeUsa**: ¿Cómo se utilizan TDD y BDD para mejorar la calidad del producto y la eficiencia del desarrollo?
- **erroresComunes**: ¿Cuáles son los errores comunes al implementar TDD y BDD y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la aplicación de TDD y BDD en un proyecto de software?

## Criterios de Evaluacion

- Definición clara de casos de prueba utilizando BDD.
- Implementación efectiva de pruebas unitarias utilizando TDD.
- Integración de pruebas en un pipeline de CI/CD y refactorización del código.
- Evaluación y propuestas de mejora continua al proceso de desarrollo.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
