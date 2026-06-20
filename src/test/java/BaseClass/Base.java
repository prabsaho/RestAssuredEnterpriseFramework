package BaseClass;

import utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class Base {
    public static Map<String,Object> dataFromJsonFile;
    public static Map<String,Object> loginDataFromJsonFile;
    static {
        String env=System.getProperty("env")==null?"qa":System.getProperty("env");
        try {
            dataFromJsonFile = JsonUtils.getJsonDataAsMap("eventhub/"+env+"/eventHubApiData.json");
            loginDataFromJsonFile=JsonUtils.getJsonDataAsMap("authLogin/"+env+"/authLoginApiData.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
