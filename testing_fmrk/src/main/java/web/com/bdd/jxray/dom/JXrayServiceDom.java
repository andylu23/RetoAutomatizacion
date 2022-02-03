package web.com.bdd.jxray.dom;

import io.restassured.http.Header;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;

public class JXrayServiceDom {

    public Response importTestResultExecution(String pathResource, String pathJsonFile, String user, String password) {
        Response response = SerenityRest
                .given()
                .header(new Header("Content-Type", "application/json"))
                .body(new File(pathJsonFile))
                .auth().preemptive().basic(user, password)
                .when().log().all()
                .post(pathResource + "/rest/raven/1.0/import/execution/cucumber");

        response.prettyPeek();
        return response;
    }


}
