#language: es

Característica: como usuario de invictus realizo un login



  @loginExitoso
  Escenario: loguearse en invictus
    Dado el usuario ingresa a invictus
    Cuando  ingresa usuario y contraseña
    Entonces  el sistema muestra mensaje del home