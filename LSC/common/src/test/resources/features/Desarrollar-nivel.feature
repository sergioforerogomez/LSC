# language: es

Característica: Desarrollar nivel

  Como usuario
  Desarrollar nivel entre tres categorias:
  * Sustantivo
  * Verbo
  * Predicado
  Para poder practicar los conceptos u oraciones del lenguaje de señas.

  Escenario: 01 Ver nivel con datos invalidos - Positivo
    Dado que Sergio quiere ver los datos de un nivel con un id invalido
    Cuando realiza una peticion para ver el nivel
    Entonces el sistema retorna "Error al ver el nivel, el id no existe."
    Y status code "400"

  Escenario: 02 Ver todos los niveles - Positivo
    Dado que Sergio quiere ver los datos de todos los niveles
    Cuando realiza una peticion para ver los niveles
    Entonces el sistema retorna los niveles
    Y status code "200"

  Escenario: 03 Ver practica con datos invalidos - Positivo
    Dado que Sergio quiere ver los datos de una practica con un id invalido
    Cuando realiza una peticion para solicitar la practica
    Entonces el sistema retorna "Error al ver la practica, el id no existe."
    Y status code "400"