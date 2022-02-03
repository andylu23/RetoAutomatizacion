package com.bdd.step;


import com.bdd.page.PerurailPage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;
import web.com.bdd.lib.WebDriverManager;

public class PerurailStep {

    public PerurailPage perurailPage;

    @Step
    public void cargarWeb(String browse) {
        System.out.println("Iniciando Chrome");
        perurailPage.setDriver(WebDriverManager.setWebDriver(browse));
        perurailPage.open();
        perurailPage.getDriver().manage().window().maximize();
        perurailPage.cargarPagina();
        System.out.println("Cargo Pagina Peru rail");
    }

    @Step
    public void seleccionodestino(String cusco) {
        System.out.println("selecciono destino");
        perurailPage.seleccionodestino();
        System.out.println("Valido que selecciono destino CUSCO");
    }

    @Step
    public void seleccionaRuta(String punoycusco) {
        System.out.println("seleccionar ruta");
        perurailPage.seleccionaruta();
    }

    @Step
    public void seleccionoServicio(String belmond) {
        System.out.println("Seleciono el servicio");
        perurailPage.seleccionoServicio();
    }

    @Step
    public void clicbuscar() {
        System.out.println("Buscar train");
        perurailPage.buscarTrain();
    }

    @Step
    public void continuamos() {
        System.out.println("Continuamos siguiente paso");
        perurailPage.continuamos();
    }


    @Step
    public void mensaje() {
        Assert.assertTrue(perurailPage.mensaje());
    }

    @Step
    public void destinohappy(String OllantayTambo) {
        System.out.println("selecciono destino OllantayTambo");
        perurailPage.destinohappy();
        System.out.println("Valido que selecciono destino OllantayTambo");
    }
    public void cantidad(String uno) {
        System.out.println("Selecciona cantidad");
        perurailPage.cantidad();
        System.out.println("Se selecciona 1 pasajero");
    }
    @Step
    public void rutahappy(String OllantayTambo) {
        System.out.println("seleccionar ruta");
        perurailPage.rutaHappy();
    }

    @Step
    public void pasajero1() {
        perurailPage.pasajero1();

    }

    @Step
    public void pasajero2() {
    }

    @Step
    public void Route() {
        System.out.println("Escojo el route del servicio");
        perurailPage.route();
    }

    @Step
    public void botonbuscar() {
        perurailPage.botonbuscar();
    }

    @Step
    public void seleccionoDiaIda() {
        perurailPage.seleccionoDiaIda();
        System.out.println("Se selecciono el dia e ida");
    }

    @Step
    public void seleccionaida() {
        perurailPage.seleccionarDiaRegreso();
        System.out.println("Se selecciono el dia");
    }

    @Step
    public void botoncontinuar() {
        perurailPage.botoncontinuar();
    }
}
