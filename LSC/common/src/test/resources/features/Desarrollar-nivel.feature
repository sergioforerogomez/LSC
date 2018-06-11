# language: es

Característica: Desarrollar nivel

  Como usuario
  Quiero desarrollar niveles entre tres categorias:
  * Sustantivo
  * Verbo
  * Predicado
  Para poder practicar los conceptos u oraciones del lenguaje de señas.

  Escenario: 01 Ver todos los niveles - Positivo
    Dado que Sergio quiere ver los datos de todos los niveles
    Cuando realiza una perticion para ver niveles
    Entonces el sistema retorna una lista de niveles
    Y status code "200"

  Esquema del escenario: 02 Ver lecciones con datos validos - Positivo
    Dado que Sergio quiere ver los datos de la leccion "<lesson>"
    Cuando realiza una perticion para ver lecciones
    Entonces el sistema retorna una lista de lecciones
    Y status code "200"

    Ejemplos:
      | lesson      |
      | sustantivos |
      | verbos      |
      | predicados  |

  Escenario: 03 Ver lecciones con datos invalidos - Negativo
    Dado que Sergio quiere ver los datos de una leccion con un id invalido
    Cuando realiza una perticion para ver lecciones
    Entonces el sistema retorna "Error al ver las lecciones, el nivel no existe."
    Y status code "200"