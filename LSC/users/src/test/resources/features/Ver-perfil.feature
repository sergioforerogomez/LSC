# language: es

Característica: Ver perfil

  Como usuario
  Quiero consultar los datos de mi perfil
  Para poder detallar mi nivel, mi progreso general, mis logros y mi avance en los niveles.

  Escenario: 01 Ver datos del perfil - Positivo
    Dado que Sergio quiere ver los datos de su perfil
    Cuando realiza una peticion para ver el perfil
    Entonces el sistema retorna status code "200"
    Y "profileId" del perfil
    Y "profileImage" del perfil
    Y "name" del perfil
    Y "level" del perfil
    Y "generalProgress" del perfil
    Y "reachedAchievements" del perfil
    Y "completedLevels" del perfil

  Escenario: 02 Ver todos los datos del perfil con datos invalidos - Negativo
    Dado que Sergio quiere ver los datos de su perfil con un id invalido
    Cuando realiza una peticion para ver el perfil
    Entonces el sistema retorna "Error al ver el perfil, el id es invalido."
    Y status code "400"

  Escenario: 03 Ver informacion de un logro con datos invalidos - Negativo
    Dado que Sergio quiere ver los datos de un logro con un id invalido
    Cuando realiza una peticion para ver el logro
    Entonces el sistema retorna "Error al ver el logro, el id es invalido."
    Y status code "400"

  Escenario: 04 Ver informacion de todos los logros - Positivo
    Dado que Sergio quiere ver los datos de todos los logros
    Cuando realiza una peticion para ver los logros
    Entonces el sistema retorna los logros
    Y status code "200"