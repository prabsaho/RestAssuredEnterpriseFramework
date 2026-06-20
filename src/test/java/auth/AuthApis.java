package auth;

import BaseClass.Base;
import io.restassured.response.Response;
import org.testng.Assert;
import utils.RestUtils;
import utils.ConfigManager;

import java.util.Map;

public class AuthApis {
    public static Response AuthLogin() {
        String endPoint = (String) Base.loginDataFromJsonFile.get("authLoginApiEndPoint");
        Map<String,Object> createEventPayload = Map.of(
                "email", Base.loginDataFromJsonFile.get("email"),
                "password", Base.loginDataFromJsonFile.get("password")
        );

        Response authResponse = RestUtils.performPost(endPoint, createEventPayload);
        Assert.assertEquals(authResponse.getStatusCode(), 200);

        String token = authResponse.jsonPath().getString("token");

        // Save token globally in properties file
        ConfigManager.setProperty("authToken", token);

        return authResponse;
    }

    public static String getToken() {
        AuthApis.AuthLogin();
        return ConfigManager.getProperty("authToken");
    }
}
