# language: es

Característica: Cerrar sesion

  Como usuario
  Quiero salir de mi sesión en la aplicación
  Para poder establecerme como usuario anónimo y dejar la sesión inactiva.

  Escenario: 01 Cerrar sesion con un token valido - Positivo
    Dado que Sergio tiene un token valido
    Cuando realiza una peticion para cerrar sesion
    Entonces el sistema retorna status code "200"