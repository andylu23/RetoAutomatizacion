package web.com.bdd.base;

import cucumber.api.Scenario;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.com.bdd.generic.WebDriverDOM;
import web.com.bdd.util.UtilWeb;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;

import static web.com.bdd.util.Errors.*;
import static web.com.bdd.util.UtilWeb.guardarTextoEnPortapapeles;

public class Middleware extends WebDriverDOM {

    private String documentQuerySelector = "return document.querySelector(";

    /**
     * Busca un elemento
     *
     * @param appBase elemento app-base del shadow root
     * @param pathCss ruta del elemento
     * @return elemento
     */
    public WebElement getElementoFromJavaScript(WebElement appBase, String pathCss) {
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        String[] parts = String.valueOf(appBase).split(":");
        String elementBase = parts[2].replace("//", "").replace("]", "").trim();
        String query = shadowRootQuerySelector(elementBase, pathCss);
        UtilWeb.logger(this.getClass()).log(Level.INFO, "Se buscara el elemento desde JS con query: {0}", ANSI_YELLOW + query + ANSI_RESET);
        WebElement element = (WebElement) ex.executeScript(query);
        if (element == null)
            UtilWeb.logger(this.getClass()).log(Level.INFO, "elemento " + ANSI_RED + "no encontrado" + ANSI_RESET);
        else
            UtilWeb.logger(this.getClass()).log(Level.INFO, "elemento " + ANSI_GREEN + "encontrado" + ANSI_RESET);
        return element;
    }

    /**
     * Busca una lista de elementos
     *
     * @param appBase elemento app-base del shadow root
     * @param pathCss ruta del elemento
     * @return una lista de elementos
     */
    public List<WebElement> getElementosFromJavaScript(WebElement appBase, String pathCss) {
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        String[] parts = String.valueOf(appBase).split(":");
        String elementBase = parts[2].replace("//", "").replace("]", "").trim();
        String query = shadowRootQuerySelectorAll(elementBase, pathCss);
        return (List<WebElement>) ex.executeScript(query);
    }

    /**
     * Valida si el elemento es visible durante un tiempo maximo establecido.
     *
     * @param pathCss          elemento de tipo de CSS String
     * @param timeOutOnSeconds tiempo en segundos, expresado en numeros enteros
     * @return verdadero, si el elemento esperado no esta visible
     */
    public boolean waitElementFromJavaScript(WebElement appBase, String pathCss, int timeOutOnSeconds) {
        boolean found = false;
        for (int c = 0; c <= timeOutOnSeconds; c++) {
            try {
                WebElement element = getElementoFromJavaScript(appBase, pathCss);
                if (element == null) {
                    Thread.sleep(1000);
                } else {
                    found = true;
                    break;
                }
            } catch (Exception e) {
                UtilWeb.logger(this.getClass()).throwing(this.getClass().getName(), "waitElementFromJavaScript", e);
            }
        }
        return found;
    }

    /**
     * Valida si el elemento ya no es visible durante un tiempo maximo establecido.
     *
     * @param timeOutOnSeconds tiempo en segundos, expresado en numeros enteros
     * @return verdadero, si el elemento esperado no esta visible
     */
    public boolean waitElementIsNotVisibleFromJavaScript(WebElement appBase, String pathCss, int timeOutOnSeconds) {
        boolean found = false;
        for (int c = 0; c <= timeOutOnSeconds; c++) {
            try {
                WebElement element = getElementoFromJavaScript(appBase, pathCss);
                if (element == null) {
                    found = true;
                    break;
                } else {
                    if (c == timeOutOnSeconds)
                        UtilWeb.logger(this.getClass()).info("Tiempo vencido, no se espero que se oculte el elemento");
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                UtilWeb.logger(this.getClass()).throwing(this.getClass().getName(), "waitElementIsNotVisibleFromJavaScript", e);
            }
        }
        return found;
    }

    /**
     * Valida si el elemento ya no es visible durante un tiempo maximo establecido.
     *
     * @param appBase elemento disponible por xpath
     * @param pathCss elemento de tipo de CSS String
     * @param key     action con el componente
     * @return verdadero, si el elemento esperado no esta visible
     */
    public boolean getKeyElementFromJavaScript(WebElement appBase, String pathCss, String key) {
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        String[] parts = String.valueOf(appBase).split(":");
        String elementBase = parts[2].replace("//", "").replace("]", "").trim();
        String query = shadowRootQuerySelector(elementBase, pathCss) + key;
        UtilWeb.logger(this.getClass()).log(Level.INFO, "Se buscara el elemento desde JS con query: {0}", ANSI_YELLOW + query + ANSI_RESET );
        return (boolean) ex.executeScript(query);
    }

    /**
     * Valida si el elemento ya no es visible durante un tiempo maximo establecido.
     *
     * @param appBase elemento disponible por xpath
     * @param sXpath  elemento de tipo de CSS String
     * @param sTexto  texto a enviar
     */
    public void sendKeys(WebElement appBase, String sTexto, String sXpath) {
        WebElement elemento = null;
        waitElementFromJavaScript(appBase, sXpath, 30);
        elemento = getElementoFromJavaScript(appBase, sXpath);
        elemento.clear();
        elemento.sendKeys(sTexto);
    }

    /**
     * Valida si el elemento ya no es visible durante un tiempo maximo establecido.
     *
     * @param appBase elemento disponible por xpath
     * @param sXpath  elemento de tipo de CSS String
     */
    public void clickElementFromJavaScript(WebElement appBase, String sXpath) {
        WebElement elemento = null;
        waitElementFromJavaScript(appBase, sXpath, 30);
        elemento = getElementoFromJavaScript(appBase, sXpath);
        elemento.click();
    }

    /**
     * Valida si el elemento ya no es visible durante un tiempo maximo establecido.
     *
     * @param appBase elemento disponible por xpath
     * @param pathCss elemento de tipo de CSS String
     * @param indice  indice del elemento en la lista
     */
    public void scrollFromElements(WebElement appBase, String pathCss, int indice) {
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        String[] parts = String.valueOf(appBase).split(":");
        String elementBase = parts[2].replace("//", "").replace("]", "").trim();
        String query = shadowRootQuerySelectorAll(elementBase, pathCss) + "[" + indice + "].scrollIntoView()";
        ex.executeScript(query);
    }

    /**
     * Valida si el elemento ya no es visible durante un tiempo maximo establecido.
     *
     * @param incrementador incrementador del scroll
     */
    public void addScrollFromJavascript(int incrementador) {
        Dimension di = getDriver().manage().window().getSize();
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        int actual = di.height + incrementador;
        String query = "window.scrollTo(0," + actual + "" + ")";
        UtilWeb.logger(this.getClass()).log(Level.INFO, "Query scroll: {0}", ANSI_YELLOW + query + ANSI_RESET);
        ex.executeScript(query);
    }

    public void waitUntilOfficebankigLoadingIsNotVisible() {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(getDriver(), 60L);
            webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//ntlc-loader")));
        } catch (Exception e) {
            UtilWeb.logger(this.getClass()).throwing(this.getClass().getName(), "waitUntilOfficebankigLoadingIsNotVisible", e);
        }
    }

    public WebElement waitElement(WebElement element, int timeOnSeconds) {
        return (new WebDriverWait(getDriver(), (long) timeOnSeconds)).until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitElementIsClickeable(WebElement element, int timeOnSeconds) {
        return (new WebDriverWait(getDriver(), (long) timeOnSeconds)).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Setea el valor a un atributo de DOM
     *
     * @param appBase elemento app-base del shadow root
     * @param pathCss ruta del elemento
     * @return texto
     */
    public void setElementoFromJavascript(WebElement appBase, String pathCss, String key, String value) {
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        String[] parts = String.valueOf(appBase).split(":");
        String elementBase = parts[2].replace("//", "").replace("]", "").trim();
        String query = "document.querySelector(\"" + elementBase + "\").shadowRoot.querySelector(\"" + pathCss + "\")." + key + "=\"" + value + "\"";
        UtilWeb.logger(this.getClass()).log(Level.INFO, "Se seteará el elemento desde JS con query: {0}", ANSI_YELLOW + query + ANSI_RESET);
        ex.executeScript(query);
    }

    /***
     * Se agrega al elemento un atributo y un valor nuevo, en caso el atributo ya existe solo reemplazara su valor
     * @param appBase elemento base antes del shadowRoot
     * @param pathCss ruta del elemento en ccSelector
     * @param attribute atributo que se agrega al elemento
     * @param value valor que se agrega al elemento
     */
    public void setAttributeJS(WebElement appBase, String pathCss, String attribute, String value) {
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        String[] parts = String.valueOf(appBase).split(":");
        String elementBase = parts[2].replace("//", "").replace("]", "").trim();
        String query = "document.querySelector(\"" + elementBase + "\").shadowRoot.querySelector('" + pathCss + "').setAttribute('" + attribute + "','" + value + "')";
        UtilWeb.logger(this.getClass()).log(Level.INFO, "Se agrego el elemento desde JS con query: {0}", ANSI_YELLOW + query + ANSI_RESET);
        ex.executeScript(query);
    }

    /***
     * Generar evidencia desde un scenario
     * @param scenario variable para guardar evidencias
     */
    public void generarEvidencia(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }


    /***
     * Clic derecho en el elemento
     * @param elemento elemento a interactuar
     */
    public void clicDerechoEnElemento(WebElement elemento) {
        Actions acciones = new Actions(getDriver());
        acciones.contextClick(elemento);
        acciones.perform();

    }

    /***
     * Pega un texto desde el portapapeles
     * @param elemento elemento a interactuar
     * @param texto a pegar
     */
    public void pegarTextoconClicDerecho(String texto, WebElement elemento) throws AWTException, InterruptedException {
        guardarTextoEnPortapapeles(texto);
        elemento.clear();
        clicDerechoEnElemento(elemento);
        String sOperativo = System.getProperty("os.name").toLowerCase();
        UtilWeb.logger(this.getClass()).log(Level.INFO, "Se copiará el texto en el sistema operativo de {0}", sOperativo);
        Robot varRobot = new Robot();
        if (sOperativo.contains("windows")) {
            for (Integer i = 0; i < 3; i++) {
                Thread.sleep(500);
                varRobot.keyPress(KeyEvent.VK_DOWN);
            }
        }
        varRobot.keyPress(KeyEvent.VK_ENTER);

    }

    /***
     * Obtiene un elemento desde JS
     * @param pathCss elemento a obtener
     */
    public WebElement getElemento(String pathCss) {
        JavascriptExecutor ex = (JavascriptExecutor) this.getDriver();
        String query = documentQuerySelector+"\"" + pathCss + "\")";
        UtilWeb.logger(this.getClass()).log(Level.INFO, "Se buscara el elemento desde JS con query: \u001b[33m {0} \u001b[0m", query);
        WebElement element = (WebElement) ex.executeScript(query, new Object[0]);
        if (element == null) {
            UtilWeb.logger(this.getClass()).log(Level.INFO, "elemento \u001b[31mno encontrado\u001b[0m");
        } else {
            UtilWeb.logger(this.getClass()).log(Level.INFO, "elemento \u001b[32mencontrado\u001b[0m");
        }

        return element;
    }

    /***
     * Obtiene elementos desde JS
     * @param pathCss elementos a obtener
     */
    public List<WebElement> getElementos(String pathCss) {
        JavascriptExecutor ex = (JavascriptExecutor) this.getDriver();
        String query = "return document.querySelectorAll(\"" + pathCss + "\")";
        return (List<WebElement>) ex.executeScript(query, new Object[0]);
    }

    /***
     * Obtiene un elemento desde JS
     * @param pathCss elemento a obtener
     * @param timeOutOnSeconds tiempo a esperar el elemento
     */
    public boolean waitElemento(String pathCss, int timeOutOnSeconds) {
        boolean found = false;

        for (int c = 0; c <= timeOutOnSeconds; ++c) {
            try {
                WebElement element = getElemento(pathCss);
                if (element != null) {
                    found = true;
                    break;
                }

                Thread.sleep(1000L);
            } catch (Exception e) {
                UtilWeb.logger(this.getClass()).throwing(this.getClass().getName(), "waitElemento", e);
            }
        }

        return found;
    }

    private String shadowRootQuerySelector(String elementBase, String pathCss) {
        return documentQuerySelector+"\"" + elementBase + "\").shadowRoot.querySelector(\"" + pathCss + "\")";
    }

    private String shadowRootQuerySelectorAll(String elementBase, String pathCss) {
        return documentQuerySelector+"\"" + elementBase + "\").shadowRoot.querySelectorAll(\"" + pathCss + "\")";
    }




}