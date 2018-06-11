# language: es

Característica: Crear cuenta

  Como usuario anónimo
  Quiero crear una cuenta ingresando mis credenciales
  Para poder convertirme en usuario nuevo, guardar registro de mi perfil y acceder a la aplicación móvil.

  Escenario: 01 Crear cuenta con datos validos - Positivo
    Dado que Sergio quiere crear una cuenta con datos validos
    Cuando realiza una peticion para crear la cuenta
    Entonces el sistema retorna un token
    Y el id del perfil
    Y status code "200"

  Esquema del escenario: 02 Crear cuenta con datos invalidos - Negativo
    Dado que Sergio quiere crear una cuenta con "<email>", "<password>", "<confirmPassword>" y "<name>"
    Cuando realiza una peticion para crear la cuenta
    Entonces el sistema retorna "<errorMessage>"
    Y status code "200"

    Ejemplos:
      | email            | password | confirmPassword | name   | errorMessage                                                                                                  |
      | email            | Qwerty12 | Qwerty12        | Sergio | Error al crear la cuenta, el correo es invalido.                                                              |
      | email@domain.com | password | password        | Sergio | Error al crear la cuenta, la contraseña debe contener al menos una letra mayúscula, un número y 8 caracteres. |
      | email@domain.com | Qwerty12 | Qwerty1         | Sergio | Error al crear la cuenta, las contraseñas no coinciden.                                                       |

  Escenario: 03 Crear cuenta con datos existentes - Negativo
    Dado que Sergio quiere crear una cuenta con datos existentes
    Cuando realiza una peticion para crear la cuenta
    Entonces el sistema retorna "Error al crear la cuenta, el correo ya existe."
    Y status code "200"