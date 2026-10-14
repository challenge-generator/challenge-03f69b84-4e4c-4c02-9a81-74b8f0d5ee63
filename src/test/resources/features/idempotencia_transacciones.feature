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