package com.bdd.stepdefinition;
import com.bdd.step.PerurailStep;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.thucydides.core.annotations.Steps;

public class PerurailStepDefinition {

    @Steps
    public PerurailStep perurailStep;

    @Dado("^que carga la pagina perurail \"([^\"]*)\"$")
    public void que_carga_la_pagina_perurail(String browse) {
        perurailStep.cargarWeb(browse);
    }

    @Cuando("^selecciono el destino \"([^\"]*)\"$")
    public void selecciono_el_destino(String cusco) {
        perurailStep.seleccionodestino(cusco);
    }

    @Cuando("^selecciono la ruta \"([^\"]*)\"$")
    public void selecciono_la_ruta(String punoycusco) {
        perurailStep.seleccionaRuta(punoycusco);
    }

    @Cuando("^selecciono el servicio \"([^\"]*)\"$")
    public void selecciono_el_servicio(String belmond) {
        perurailStep.seleccionoServicio(belmond);
    }

    @Cuando("^ejecuto boton Find Train$")
    public void ejecuto_boton_Find_Train() {
        perurailStep.clicbuscar();

    }

    @Cuando("^continuamos al siguiente paso$")
    public void continuamos_al_siguiente_paso() {
        perurailStep.continuamos();
    }

    @Entonces("^valido mensaje$")
    public void valido_mensaje() {
        perurailStep.mensaje();
        System.out.println("Valido mensaje: The departures date entered is not valid, check your date selected");
    }


    @Cuando("^selecciono el destino de \"([^\"]*)\"$")
    public void selecciono_el_destino_de(String OllantayTambo) {
        perurailStep.destinohappy(OllantayTambo);
    }

    @Cuando("^selecciono la ruta de \"([^\"]*)\"$")
    public void selecciono_la_ruta_de(String OllantayTambo) {
        perurailStep.rutahappy(OllantayTambo);
    }

    @Cuando("^selecciona cantidad  \"([^\"]*)\"$")
    public void selecciona_cantidad(String uno) {
        perurailStep.cantidad(uno);
    }

    @Cuando("^selecciono outbound pasajero (\\d+)$")
    public void selecciono_outbound_pasajero(int arg1) {
        perurailStep.pasajero1();
    }

    @Cuando("^selecciono outbound(\\d+)$")
    public void selecciono_outbound(int arg1) {
        perurailStep.pasajero2();
    }

    @Cuando("^escojo route$")
    public void escojo_route() {
        perurailStep.Route();
    }

    @Cuando("^ejecuto boton buscar$")
    public void ejecuto_boton_buscar() {
        perurailStep.botonbuscar();
    }

    @Cuando("^selecciono dia de ida$")
    public void selecciono_dia_de_ida() {
        perurailStep.seleccionoDiaIda();
    }

    @Cuando("^selecciona dia de regreso$")
    public void selecciona_dia_de_regreso() {
        perurailStep.seleccionaida();
    }

    @Cuando("^selecciono boton continuar$")
    public void selecciono_boton_continuar() {
        perurailStep.botoncontinuar();
    }

}
