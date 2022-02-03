#language: es

@PERU_RAIL
Característica: AUTOMATIZACIÓN de reservas de tickets en www.perurail.com

  @PERU_RAIL_NOOK
  Escenario: Reservar viajes en tren
    Dado que carga la pagina perurail "chrome"
    Cuando selecciono el destino "cusco"
    Y selecciono la ruta "ruta"
    Y ejecuto boton Find Train
    Entonces valido mensaje


  @PERU_RAIL_OK
  Escenario: Reservar viajes en tren
    Dado que carga la pagina perurail "chrome"
    Cuando selecciono el destino de "OllantayTambo"
    Y selecciono la ruta de "ruta"
    Y ejecuto boton Find Train
    Y escojo route
    Y ejecuto boton buscar
    Y selecciono dia de ida
    Y selecciona dia de regreso
    Y selecciono boton continuar
    Entonces valido mensaje



