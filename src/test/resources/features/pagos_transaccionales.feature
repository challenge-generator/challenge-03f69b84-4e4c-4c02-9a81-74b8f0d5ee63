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