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