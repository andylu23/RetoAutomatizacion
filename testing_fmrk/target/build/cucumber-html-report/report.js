$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/web/perurail.feature");
formatter.feature({
  "name": "AUTOMATIZACIÓN de reservas de tickets en www.perurail.com",
  "description": "",
  "keyword": "Característica",
  "tags": [
    {
      "name": "@PERU_RAIL"
    }
  ]
});
formatter.scenario({
  "name": "Reservar viajes en tren",
  "description": "",
  "keyword": "Escenario",
  "tags": [
    {
      "name": "@PERU_RAIL"
    },
    {
      "name": "@PERU_RAIL_OK"
    }
  ]
});
formatter.step({
  "name": "que carga la pagina perurail \"chrome\"",
  "keyword": "Dado "
});
formatter.match({
  "location": "PerurailStepDefinition.que_carga_la_pagina_perurail(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "selecciono el destino de \"OllantayTambo\"",
  "keyword": "Cuando "
});
formatter.match({
  "location": "PerurailStepDefinition.selecciono_el_destino_de(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "selecciono la ruta de \"ruta\"",
  "keyword": "Y "
});
formatter.match({
  "location": "PerurailStepDefinition.selecciono_la_ruta_de(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "ejecuto boton Find Train",
  "keyword": "Y "
});
formatter.match({
  "location": "PerurailStepDefinition.ejecuto_boton_Find_Train()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "escojo route",
  "keyword": "Y "
});
formatter.match({
  "location": "PerurailStepDefinition.escojo_route()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "ejecuto boton buscar",
  "keyword": "Y "
});
formatter.match({
  "location": "PerurailStepDefinition.ejecuto_boton_buscar()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "selecciono dia de ida",
  "keyword": "Y "
});
formatter.match({
  "location": "PerurailStepDefinition.selecciono_dia_de_ida()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "selecciona dia de regreso",
  "keyword": "Y "
});
formatter.match({
  "location": "PerurailStepDefinition.selecciona_dia_de_regreso()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "selecciono boton continuar",
  "keyword": "Y "
});
formatter.match({
  "location": "PerurailStepDefinition.selecciono_boton_continuar()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "valido mensaje",
  "keyword": "Entonces "
});
formatter.match({
  "location": "PerurailStepDefinition.valido_mensaje()"
});
formatter.result({
  "status": "passed"
});
});