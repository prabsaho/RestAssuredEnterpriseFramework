package eventhub;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;
import utils.RestUtils;

import java.io.IOException;
import java.util.Map;

public class EventHubTests extends EventHubApis {
    @Test
    public  void testCreateEvent() throws IOException {
        Map<String,String> headers=Map.of("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExNTQ4LCJlbWFpbCI6InByYWJzNDRAZ21haWwuY29tIiwiaWF0IjoxNzgwMTI4MzI1LCJleHAiOjE3ODA3MzMxMjV9.kIDWzK7fKPCYqP9_X-4MYD0X4vwELGRLX-BMgZo3pxU");
        Map<String,Object> payLoad=Payloads.getCreateEventPayloadFromMap("Tech Summit 2026","A premier technology conference.","Conference","Bangalore International Centre",
                "Bangalore","2026-06-15T09:00:00.000Z","1500","500","https://example.com/banner.jpg");
        Response response= createEvent(payLoad,headers);


        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),201);
    }
}
