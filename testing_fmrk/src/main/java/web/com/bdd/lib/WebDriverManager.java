package web.com.bdd.lib;

import net.thucydides.core.util.EnvironmentVariables;
import org.eclipse.jetty.util.URIUtil;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import web.com.bdd.generic.HelperOS;
import web.com.bdd.generic.HelperProperties;
import web.com.bdd.generic.Parameters;
import web.com.bdd.util.UtilWeb;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;

import static web.com.bdd.util.Errors.*;

public final class WebDriverManager {

    private WebDriverManager() {
    }

    private static final String USER_DIR = "user.dir";
    private static final String OS_NAME = "os.name";
    private static final String MSJ_OS = "Sistema Opertivo:";

    public static WebDriver setWebDriver(String browser) {

        UtilWeb.logger(WebDriverManager.class).log(Level.INFO, "Ejecutando en: {0}", browser);
        HashMap<String, Object> chromePrefs = new HashMap<>();
        WebDriver webDriver;

        if (browser.equalsIgnoreCase("chrome") || browser.equalsIgnoreCase("background")) {

            String basePath = System.getProperty(USER_DIR);
            String so = System.getProperty(OS_NAME);

            chromePrefs.put("profile.default_content_settings.popups", 0);
            chromePrefs.put("download.default_directory", basePath + "\\descargas\\");

            ChromeOptions options = new ChromeOptions();
            if (browser.equalsIgnoreCase("background")) {
                options.addArguments("--headless");
                UtilWeb.logger(WebDriverManager.class).info("Modo Background: " + ANSI_GREEN + "SI" + ANSI_RESET);
            } else {
                UtilWeb.logger(WebDriverManager.class).info("Modo Background: " + ANSI_GREEN + "NO" + ANSI_RESET);
            }
            if (so.contains("Mac") || so.contains("Linux")) {
                basePath = basePath + "/drivers/chromedriver";
            } else {
                basePath = basePath + "/drivers/chromedriver.exe";
                options.setExperimentalOption("prefs", chromePrefs);
                options.setExperimentalOption("useAutomationExtension", false);
            }
            UtilWeb.logger(WebDriverManager.class).log(Level.INFO, "Las configuraciones se estan iniciando en {0}",
                    ANSI_BLUE + so + ANSI_RESET);
            System.setProperty("webdriver.chrome.driver", basePath);
            webDriver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("ie")) {
            String basePath = System.getProperty(USER_DIR) + URIUtil.SLASH;
            basePath = basePath + "drivers/IEDriverServer32.exe";
            System.setProperty("webdriver.ie.driver", basePath);
            webDriver = new InternetExplorerDriver();
        } else {
            throw new IllegalArgumentException("Browser type not supported: " + browser);
        }
        webDriver.manage().window().maximize();
        return webDriver;

    }

    public static WebDriver setWebDriverFromEnvironment(EnvironmentVariables environmentVariables)
            throws MalformedURLException {

        HelperProperties helperProperties = new HelperProperties(environmentVariables);
        String browser = helperProperties.getWebCustomBrowser().toLowerCase();
        String headless = helperProperties.getBrowserHeadless();

        WebDriver webDriver = null;
        UtilWeb.logger(WebDriverManager.class).log(Level.INFO, "Browser: {0}", browser);
        UtilWeb.logger(WebDriverManager.class).log(Level.INFO, "Headless: {0}", headless);
        HelperOS helperOS = new HelperOS();
        if (helperOS.isLinux()) {
            DesiredCapabilities capabilities = DesiredCapabilities.chrome();
            capabilities.setPlatform(Platform.LINUX);
            UtilWeb.logger(WebDriverManager.class).log(Level.INFO, MSJ_OS + " {0}", Platform.LINUX);
            webDriver = new RemoteWebDriver(new URL(helperProperties.getWebRemoteHub()), capabilities);
            webDriver.manage().window().maximize();
        } else if (helperOS.isWindows()) {
            if (browser.equalsIgnoreCase(Parameters.CHROME_DRIVER)) {
                String basePath = System.getProperty(USER_DIR);
                ChromeOptions options = new ChromeOptions();
                UtilWeb.logger(WebDriverManager.class).log(Level.INFO, MSJ_OS + " {0}", Platform.WINDOWS);
                if (Boolean.parseBoolean(headless)) options.addArguments("--headless");
                if (System.getProperty(OS_NAME).contains("Mac")) {
                    basePath = basePath + "/drivers/chromedriver";
                } else {
                    basePath = basePath + "/drivers/chromedriver.exe";
                    options.setExperimentalOption("useAutomationExtension", false);
                }
                System.setProperty("webdriver.chrome.driver", basePath);
                webDriver = new ChromeDriver(options);
            } else if (browser.equalsIgnoreCase(Parameters.IE_DRIVER)) {
                String basePath = System.getProperty(USER_DIR) + URIUtil.SLASH;
                basePath = basePath + "drivers/IEDriverServer32.exe";
                System.setProperty("webdriver.ie.driver", basePath);
                webDriver = new InternetExplorerDriver();
            } else {
                throw new IllegalArgumentException("Browser: " + browser + " no soportado");
            }
            UtilWeb.logger(WebDriverManager.class).log(Level.INFO, MSJ_OS + " {0}", Platform.WINDOWS);
            webDriver.manage().window().maximize();
        }
        return webDriver;
    }

}