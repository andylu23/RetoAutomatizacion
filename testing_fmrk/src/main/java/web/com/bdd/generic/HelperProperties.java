package web.com.bdd.generic;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

public class HelperProperties {

    private EnvironmentVariables environmentVariables;

    public HelperProperties(EnvironmentVariables environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public String getBrowserHeadless() {
        return EnvironmentSpecificConfiguration.from(this.environmentVariables)
                .getProperty(Parameters.BROWSER_HEADLESS);
    }

    public String getWebRemoteHub() {
        return EnvironmentSpecificConfiguration.from(this.environmentVariables)
                .getProperty(Parameters.REMOTE_HUB);
    }

    public String getWebCustomBrowser() {
        return EnvironmentSpecificConfiguration.from(this.environmentVariables)
                .getProperty(Parameters.WEB_CUSTOM_BROWSER);
    }
}
