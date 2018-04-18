# language: es

Característica: Editar perfil

  Como usuario
  Quiero actualizar los datos de mi perfil
  Para poder modificar mi imagen de perfil, el nombre y la contraseña.

  Escenario: 01 Editar imagen de perfil - Positivo
    Dado que Sergio quiere editar su "profileImage"
    Cuando realiza una peticion para editar
    Entonces el sistema retorna "profileImage" actualizado
    Y status code "200"

  Escenario: 02 Editar nombre - Positivo
    Dado que Sergio quiere editar su "name"
    Cuando realiza una peticion para editar
    Entonces el sistema retorna "name" actualizado
    Y status code "200"

  Escenario: 03 Editar contraseña con datos validos - Positivo
    Dado que Sergio quiere editar su "password"
    Cuando realiza una peticion para editar
    Entonces el sistema retorna status code "200"