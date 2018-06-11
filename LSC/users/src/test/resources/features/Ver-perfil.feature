# language: es

Característica: Ver perfil

  Como usuario
  Quiero consultar los datos de mi perfil
  Para poder detallar mi nombre, correo, nivel, progreso general, mis logros y mi avance en los niveles.

  Escenario: 01 Ver datos del perfil - Positivo
    Dado que Sergio quiere ver los datos de su perfil
    Cuando realiza una peticion para ver el perfil
    Entonces el sistema retorna status code "200"
    Y "profileId" del perfil
    Y "email" del perfil
    Y "name" del perfil
    Y "level" del perfil
    Y "generalProgress" del perfil
    Y "reachedAchievements" del perfil
    Y "completedLessons" del perfil

  Escenario: 02 Ver todos los datos del perfil con datos invalidos - Negativo
    Dado que Sergio quiere ver los datos de su perfil con un id invalido
    Cuando realiza una peticion para ver el perfil
    Entonces el sistema retorna "Error al ver el perfil, el perfil no existe."
    Y status code "200"

  Escenario: 03 Ver informacion de todos los logros - Positivo
    Dado que Sergio quiere ver los datos de todos los logros
    Cuando realiza una peticion para ver logros
    Entonces el sistema retorna los logros
    Y status code "200"

  Escenario: 04 Ver informacion de un logro con datos invalidos - Negativo
    Dado que Sergio quiere ver los datos de un logro con un id invalido
    Cuando realiza una peticion para ver logros
    Entonces el sistema retorna "Error al ver el logro, el logro no existe."
    Y status code "200"