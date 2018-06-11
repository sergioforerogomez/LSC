# language: es

Característica: Iniciar sesion

  Como usuario anónimo
  Quiero iniciar sesión con mis credenciales
  Para poder acceder a la aplicación móvil.

  Escenario: 01 Iniciar sesion con datos existentes - Positivo
    Dado que Sergio quiere iniciar sesion con datos existentes
    Cuando realiza una peticion para iniciar sesion
    Entonces el sistema retorna un token
    Y el id del perfil
    Y status code "200"

  Escenario: 02 Iniciar sesion con correo inexistente - Negativo
    Dado que Sergio quiere iniciar sesion con un correo inexistente
    Cuando realiza una peticion para iniciar sesion
    Entonces el sistema retorna "Error al iniciar sesion, el correo no existe."
    Y status code "200"

  Escenario: 03 Iniciar sesion con credenciales invalidas - Negativo
    Dado que Sergio quiere iniciar sesion con datos existentes
    Y contraseña incorrecta
    Cuando realiza una peticion para iniciar sesion
    Entonces el sistema retorna "Error al iniciar sesion, el correo y la contraseña no coinciden."
    Y status code "200"