# language: es

Característica: Ver diccionario de señas

  Como usuario
  Quiero acceder al diccionario de señas
  Para poder buscar y ver los conceptos en lenguaje de señas y lenguaje natural.

  Escenario: 01 Ver todas las palabras - Positivo
    Dado que Sergio quiere ver los datos de todas las palabras
    Cuando realiza una peticion para solicitar las palabras
    Entonces el sistema retorna las palabras
    Y status code "200"

  Escenario: 02 Ver una palabra inexistente - Negativo
    Dado que Sergio quiere ver los datos de una palabra inexistente
    Cuando realiza una peticion para solicitar la palabra
    Entonces el sistema retorna "Error al ver la palabra, la palabra no existe."
    Y status code "400"