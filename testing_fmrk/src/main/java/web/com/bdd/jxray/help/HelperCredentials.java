package web.com.bdd.jxray.help;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.util.EnvironmentVariables;

public class HelperCredentials {

    private EnvironmentVariables environmentVariables;

    public HelperCredentials(EnvironmentVariables environmentVariables) {
        this.environmentVariables = environmentVariables;
    }

    public String getJXrayUser() {
        return EnvironmentSpecificConfiguration.from(this.environmentVariables)
                .getProperty(JiraParameters.USUARIO);
    }

    public String getJXrayPassword() {
        return EnvironmentSpecificConfiguration.from(this.environmentVariables)
                .getProperty(JiraParameters.CONTRASENA);
    }

    public String getPathResource() {
        return EnvironmentSpecificConfiguration.from(this.environmentVariables)
                .getProperty(JiraParameters.BASE_URL);
    }

}
