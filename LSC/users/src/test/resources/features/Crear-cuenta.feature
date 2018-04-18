# language: es

Característica: Crear cuenta

  Como usuario anónimo
  Quiero crear una cuenta ingresando mis credenciales
  Para poder convertirme en usuario nuevo, guardar registro de mis credenciales y acceder a la aplicación móvil.

  Escenario: 01 Crear cuenta con datos validos - Positivo
    Dado que Sergio quiere crear una cuenta con datos validos
    Cuando realiza una peticion para crear la cuenta
    Entonces el sistema retorna un token
    Y el id del perfil
    Y status code "200"

  Esquema del escenario: 02 Crear cuenta con datos invalidos - Negativo
    Dado que Sergio quiere crear una cuenta con "<email>", "<password>" y "<confirmPassword>"
    Cuando realiza una peticion para crear la cuenta
    Entonces el sistema retorna "<errorMessage>"
    Y status code "400"

    Ejemplos:
      | email           | password | confirmPassword | errorMessage                                            |
      | name            | abcdefg0 | abcdefg0        | Error al crear la cuenta, correo invalido.              |
      | name@domain.com | abcdefgh | abcdefgh        | Error al crear la cuenta, contraseña invalida.          |
      | name@domain.com | abcdefg0 | abcdefgh        | Error al crear la cuenta, las contraseñas no coinciden. |

  Escenario: 03 Crear cuenta con datos existentes - Negativo
    Dado que Sergio quiere crear una cuenta con datos existentes
    Cuando realiza una peticion para crear la cuenta
    Entonces el sistema retorna "Error al crear la cuenta, el correo ya existe."
    Y status code "400"