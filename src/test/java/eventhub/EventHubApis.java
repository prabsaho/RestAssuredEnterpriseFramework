package eventhub;

import eventhub.pojos.EventHub;
import io.restassured.response.Response;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;


public class EventHubApis {
    public Response createEvent(Map<String,Object> createEventPayload,Map<String,String> headers){
        String endPoint =(String) Base.dataFromJsonFile.get("eventHubAPIEndPoint");
        return RestUtils.performPost(endPoint, createEventPayload, headers);
    }
    public Response createEvent(EventHub createEventPayload, Map<String,String> headers){
        String endPoint =(String) Base.dataFromJsonFile.get("eventHubAPIEndPoint");
        return RestUtils.performPost(endPoint, createEventPayload, headers);
    }
}
