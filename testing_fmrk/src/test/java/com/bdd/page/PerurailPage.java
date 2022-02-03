package com.bdd.page;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.support.FindBy;
import web.com.bdd.generic.WebDriverDOM;

import java.util.List;

@DefaultUrl("https://www.perurail.com/")
public class PerurailPage extends WebDriverDOM {

    @FindBy(xpath = "//*[@alt='PeruRaillogo']")
    private WebElementFacade elementovisible;

    @FindBy(xpath = "//*[@id='destinoSelect']//option[@value='3']")
    private List<WebElementFacade> seleccionaDestino;

    @FindBy(xpath = "//*[@id='rutaSelect']//option[@value='35']")
    private List<WebElementFacade> seleccionaRuta;

    @FindBy(xpath = "//*[@id='cbTrenSelect']//option[@value='2']")
    private List<WebElementFacade> seleccionoservicio;

    @FindBy(xpath = "//*[@id='btn_search']")
    private WebElementFacade buscarTrain;

    @FindBy(xpath = "//*[@class=' undefined ui-datepicker-current-day']//a[@class='ui-state-default ui-state-active']")
    private List<WebElementFacade> seleccionafecha;

    @FindBy(xpath = "//*[@id='continuar_bae']")
    private WebElementFacade continuar;

    @FindBy(xpath = "//*[@class='sb-content']")
    private WebElementFacade mensaje;

    @FindBy(xpath = "//*[@class='validado']")
    private WebElementFacade mensajes;

    @FindBy(xpath = "//*[@id='destinoSelect']//option[@value='1']")
    private List<WebElementFacade> destinoHappy;

    @FindBy(xpath = "//*[@id='rutaSelect']//option[@value='61']")
    private List<WebElementFacade> rutaHappy;

    @FindBy(xpath = "//select[@id='numCupoAdulto']//option[@value='2']")
    private List<WebElementFacade> cantidad;

    @FindBy(xpath = "//*[@id='div_2020009266_46']")
    private WebElementFacade outbound1;

    @FindBy(xpath = "//*[@id='idRuta']//option[@value='43']")
    private List<WebElementFacade> route;

    @FindBy(xpath = "//*[@id='text_btn_search']")
    private WebElementFacade botonbuscar;

    @FindBy(xpath = "/div[@id='viajeIda']//div[@class='content-viajes viaje-jorge']//div[1]")
    private WebElementFacade seleccionoDiaIda2;



    // @FindBy(xpath = "//*[@class='bx-clone active'][./span[text()='10 DEC']]")
    @FindBy(xpath = "//*[@id='viajeIdaCapa']/div[3]/div[2]/div[1]/div/div[3]/span")
    private WebElementFacade seleccionarDia;




    @FindBy(xpath = "//*[@id='div_2020020217_20']")
    private WebElementFacade seleccionaida;

    @FindBy(xpath = "//*[@value='Continue']")
    private WebElementFacade botoncontinuar;

    public boolean cargarPagina() {
        return isElementPresent(elementovisible);

    }

    public void seleccionodestino() {
        clickElementInAList(seleccionaDestino, "CUSCO");

    }

    public void seleccionaruta() {
        clickElementInAList(seleccionaRuta, "AREQUIPA > PUNO > CUSCO");
    }

    public void seleccionoServicio() {
        clickElementInAList(seleccionoservicio, "ANDEAN EXPLORER, A BELMOND TRAIN");
    }

    public void buscarTrain() {
        clickElement(buscarTrain);
    }


    public void continuamos() {
        clickElement(continuar);
    }

    public boolean mensaje() {
        return isElementPresent(mensajes);
    }

    public void destinohappy() {
        clickElementInAList(destinoHappy, "MACHU PICCHU");
    }

    public void rutaHappy() {
        clickElementInAList(rutaHappy, "URUBAMBA > MACHU PICCHU");
    }

    public void cantidad() {
         clickElementInAList(cantidad, "1");
    }

    public void pasajero1() {
        clickElement(outbound1);
    }

    public void route() {
        clickElementInAList(route, "Ollantaytambo > Machu Picchu");
    }

    public void botonbuscar() {
        clickElement(botonbuscar);
    }
    public void seleccionoDiaIda2(){
        clickElement(seleccionoDiaIda2);
    }

    public void seleccionoDiaIda() {
        isElementClickable(seleccionarDia);
    }

    public void seleccionarDiaRegreso() {
        isElementClickable(seleccionaida);
    }

    public void botoncontinuar() {
        clickElement(botoncontinuar);
    }
}
