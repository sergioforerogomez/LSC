# language: es

Característica: Editar perfil

  Como usuario
  Quiero actualizar los datos de mi perfil
  Para poder modificar mi correo, nombre, contraseña y lecciones realizadas.

  Escenario: 01 Editar correo - Positivo
    Dado que Sergio quiere editar su "email"
    Cuando realiza una peticion para editar
    Entonces el sistema retorna "email" actualizado
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

  Esquema del escenario: 04 Editar perfil con datos invalidos y contraseña actual valida - Negativo
    Dado que Sergio quiere editar su perfil con "<email>", "<password>", "<confirmPassword>" y contraseña actual valida
    Cuando realiza una peticion para editar
    Entonces el sistema retorna "<errorMessage>"
    Y status code "200"

    Ejemplos:
      | email            | password | confirmPassword | errorMessage                                                                                                   |
      | email            | Qwerty12 | Qwerty12        | Error al editar el perfil, el correo es invalido.                                                              |
      | email@domain.com | password | password        | Error al editar el perfil, la contraseña debe contener al menos una letra mayúscula, un número y 8 caracteres. |
      | email@domain.com | Qwerty12 | Qwerty1         | Error al editar el perfil, las contraseñas no coinciden.                                                       |

  Esquema del escenario: 05 Editar perfil con datos invalidos y contraseña actual invalida - Negativo
    Dado que Sergio quiere editar su perfil con "<password>", "<confirmPassword>" y contraseña actual invalida
    Cuando realiza una peticion para editar
    Entonces el sistema retorna "Error al editar el perfil, la contraseña actual no coincide."
    Y status code "200"

    Ejemplos:
      | password | confirmPassword |
      | Qwerty12 | Qwerty12        |
      | password | password        |
      | Qwerty12 | Qwerty1         |

  Escenario: 06 Editar perfil con datos existentes - Negativo
    Dado que Sergio quiere editar su perfil con datos existentes
    Cuando realiza una peticion para editar
    Entonces el sistema retorna "Error al editar el perfil, el correo ya existe."
    Y status code "200"