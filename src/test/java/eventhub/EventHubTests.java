package eventhub;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eventhub.pojos.EventHub;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AssertionUtils;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.*;

public class EventHubTests extends EventHubApis {
    /*
    //Demo of using Map style payload
    @Test (description = "Creating a Map Style payload")
    public  void testCreateEvent() throws IOException {
        Map<String,String> headers=Map.of("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExNTQ4LCJlbWFpbCI6InByYWJzNDRAZ21haWwuY29tIiwiaWF0IjoxNzgwMTI4MzI1LCJleHAiOjE3ODA3MzMxMjV9.kIDWzK7fKPCYqP9_X-4MYD0X4vwELGRLX-BMgZo3pxU");
        Map<String,Object> payLoad=Payloads.getCreateEventFakerPayloadFromMap();
        System.out.println(payLoad);
        Response response= createEvent(payLoad,headers);


        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),201);
    }
   */

/*
    @Test (description = "Creating request payload ith default data in different ways for POJO")
    public  void testCreateEvent() throws IOException {
        Map<String,String> headers=Map.of("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExNTQ4LCJlbWFpbCI6InByYWJzNDRAZ21haWwuY29tIiwiaWF0IjoxNzgwMTI4MzI1LCJleHAiOjE3ODA3MzMxMjV9.kIDWzK7fKPCYqP9_X-4MYD0X4vwELGRLX-BMgZo3pxU");
//        EventHub payLoad=Payloads.getCreateEventPayloadFromPojo();  // Getting payload from payload.java
//        EventHub payLoad=new EventHub();  //directly creating object without help of builder
//        EventHub payLoad= new EventHub().toBuilder().title("Zwigato Food Fest Chennai").build();  // getting default values from EventHub class with toBuilder()
        EventHub payLoad= new EventHub().toBuilder().build();
        System.out.println(payLoad);
        Response response= createEvent(payLoad,headers);


        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(),201);
    }
*/

//    @Test(description = "validate the request payload & response payload values are same ignoring the extra fields")
//    public  void createEventAndVerifyResponse() throws IOException {
//        Map<String,String> headers=Map.of("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExNTQ4LCJlbWFpbCI6InByYWJzNDRAZ21haWwuY29tIiwiaWF0IjoxNzgwMTI4MzI1LCJleHAiOjE3ODA3MzMxMjV9.kIDWzK7fKPCYqP9_X-4MYD0X4vwELGRLX-BMgZo3pxU");
//        EventHub payLoad= new EventHub();
//        Response response = createEvent(payLoad, headers);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode root = objectMapper.readTree(response.getBody().asString());
//        EventHub responseAsPojo = objectMapper.treeToValue(root.get("data"), EventHub.class);
//
//        Assert.assertEquals(responseAsPojo, payLoad);
//        Assert.assertEquals(response.getStatusCode(), 201);
//    }

//    @Test(description = "validate each value of the response payload")
//    public void createEventAndVerifyResponse() {
//                Map<String,String> headers=Map.of("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExNTQ4LCJlbWFpbCI6InByYWJzNDRAZ21haWwuY29tIiwiaWF0IjoxNzgwMTI4MzI1LCJleHAiOjE3ODA3MzMxMjV9.kIDWzK7fKPCYqP9_X-4MYD0X4vwELGRLX-BMgZo3pxU");
//                EventHub payLoad=Payloads.getCreateEventPayloadFromPojo();
//                Response response = createEvent(payLoad, headers);
//
//                Map<String, Object> expectedValueMap = new HashMap<>();
//                expectedValueMap.put("data.title", payLoad.getTitle());
//                expectedValueMap.put("data.description", payLoad.getDescription());
//                expectedValueMap.put("data.category", payLoad.getCategory());
//                expectedValueMap.put("data.venue", payLoad.getVenue());
//                expectedValueMap.put("data.city", payLoad.getCity());
//                expectedValueMap.put("data.eventDate", payLoad.getEventDate());
//                expectedValueMap.put("data.price", String.valueOf(payLoad.getPrice()));
//                expectedValueMap.put("data.totalSeats", payLoad.getTotalSeats());
//                expectedValueMap.put("data.imageUrl", payLoad.getImageUrl());
//
//                AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);
//        }

        @Test(dataProvider = "createEventDataProvider",description = "take hard coded data from excel & execute set of test cases")
        public void createEventAndVerifyResponse(EventHub eventData) {
            Map<String,String> headers=Map.of("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExNTQ4LCJlbWFpbCI6InByYWJzNDRAZ21haWwuY29tIiwiaWF0IjoxNzgwMTI4MzI1LCJleHAiOjE3ODA3MzMxMjV9.kIDWzK7fKPCYqP9_X-4MYD0X4vwELGRLX-BMgZo3pxU");
            Response response=createEvent(eventData, headers);
            Map<String, Object> expectedValueMap = new HashMap<>();
                expectedValueMap.put("data.title", eventData.getTitle());
                expectedValueMap.put("data.description", eventData.getDescription());
                expectedValueMap.put("data.category", eventData.getCategory());
                expectedValueMap.put("data.venue", eventData.getVenue());
                expectedValueMap.put("data.city", eventData.getCity());
                expectedValueMap.put("data.eventDate", eventData.getEventDate());
                expectedValueMap.put("data.price", String.valueOf(eventData.getPrice()));
                expectedValueMap.put("data.totalSeats", eventData.getTotalSeats());
                expectedValueMap.put("data.imageUrl", eventData.getImageUrl());
                AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);
        }
        @DataProvider(name = "createEventDataProvider")
        public Iterator<EventHub>getCreateEventData() throws IOException {
            List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap("CreateEventHubTestData", "Sheet1");
            List<EventHub> eventHubData = new ArrayList<>();
            for(LinkedHashMap<String,String> data : excelDataAsListOfMap) {
                EventHub event = EventHub.builder()
                        .title(data.get("Title"))
                        .description(data.get("Description"))
                        .category(data.get("Category"))
                        .venue(data.get("Venue"))
                        .city(data.get("City"))
                        .eventDate(data.get("EventDate"))
                        .price(Integer.parseInt(data.get("Price")))
                        .totalSeats(Integer.parseInt(data.get("TotalSeats")))
                        .imageUrl(data.get("ImageUrl"))
                        .build();
                eventHubData.add(event);
            }
            return eventHubData.iterator();
        }

}