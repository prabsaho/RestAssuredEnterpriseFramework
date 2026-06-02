package eventhub;

import com.aventstack.extentreports.ExtentTest;
import eventhub.pojos.CreateEventDataPojo;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import reporting.SetupReport;
import utils.AssertionUtils;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.*;

public class EventHubTestsWithScenarios extends EventHubApis{
    @Test(dataProvider = "createEventDataProvider",description = "test event hub api for different scenarios with data driven approach using excel")
    public void createEventAndVerifyResponse(CreateEventDataPojo eventData) {

        ExtentTest test = SetupReport.extentReports.createTest("Test Name - " + eventData.getScenarioId(),
                eventData.getScenarioDesc());
        SetupReport.extentTest.set(test);
        System.out.println("Executing scenario Prabin Sahoo: " + eventData.getScenarioId() + " - " + eventData.getScenarioDesc());
        Map<String,String> headers=Map.of("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExNTQ4LCJlbWFpbCI6InByYWJzNDRAZ21haWwuY29tIiwiaWF0IjoxNzgwMTI4MzI1LCJleHAiOjE3ODA3MzMxMjV9.kIDWzK7fKPCYqP9_X-4MYD0X4vwELGRLX-BMgZo3pxU");
        Response response=createEvent(eventData, headers);

        if(eventData.getExpectedStatusCode() != 201){
            if(eventData.getScenarioId().equalsIgnoreCase("CreateEvent_InvalidDate")||eventData.getScenarioId().equalsIgnoreCase("CreateEvent_EmptyPayload")) {
                response = createEvent(eventData, headers);
                Assert.assertEquals(response.getStatusCode(), eventData.getExpectedStatusCode());
                Assert.assertEquals(response.jsonPath().getString("error"), eventData.getExpectedErrorMessage());
            }
            if(eventData.getScenarioId().equalsIgnoreCase("CreateEvent_WithoutTitle")) {
                response = createEvent(eventData, headers);
                Assert.assertEquals(response.getStatusCode(), eventData.getExpectedStatusCode());
                Assert.assertEquals(response.jsonPath().getString("details[0].message"), eventData.getExpectedErrorMessage());
            }
        }
        else {
            Map<String,Object> expectedValueMap = new HashMap<>();
            expectedValueMap.put("data.title", eventData.getTitle());
            expectedValueMap.put("data.description", eventData.getDescription());
            expectedValueMap.put("data.category", eventData.getCategory());
            expectedValueMap.put("data.venue", eventData.getVenue());
            expectedValueMap.put("data.city", eventData.getCity());
            expectedValueMap.put("data.eventDate", eventData.getEventDate());
            expectedValueMap.put("data.price", String.valueOf(eventData.getPrice()));
            expectedValueMap.put("data.totalSeats", eventData.getTotalSeats());
            expectedValueMap.put("data.imageUrl", eventData.getImageUrl());

            if(eventData.getScenarioId().equalsIgnoreCase("CreateEvent_WithoutDescription")) {
                expectedValueMap.remove("data.description");
            }
            AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);
        }

//        Map<String,String> headers=Map.of("Authorization","Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjExNTQ4LCJlbWFpbCI6InByYWJzNDRAZ21haWwuY29tIiwiaWF0IjoxNzgwMTI4MzI1LCJleHAiOjE3ODA3MzMxMjV9.kIDWzK7fKPCYqP9_X-4MYD0X4vwELGRLX-BMgZo3pxU");
//        Response response=createEvent(eventData, headers);
//        Map<String, Object> expectedValueMap = new HashMap<>();
//        expectedValueMap.put("data.title", eventData.getTitle());
//        expectedValueMap.put("data.description", eventData.getDescription());
//        expectedValueMap.put("data.category", eventData.getCategory());
//        expectedValueMap.put("data.venue", eventData.getVenue());
//        expectedValueMap.put("data.city", eventData.getCity());
//        expectedValueMap.put("data.eventDate", eventData.getEventDate());
//        expectedValueMap.put("data.price", String.valueOf(eventData.getPrice()));
//        expectedValueMap.put("data.totalSeats", eventData.getTotalSeats());
//        expectedValueMap.put("data.imageUrl", eventData.getImageUrl());
//        AssertionUtils.assertExpectedValuesWithJsonPath(response, expectedValueMap);
    }
    @DataProvider(name = "createEventDataProvider")
    public Iterator<CreateEventDataPojo> getCreateEventData() throws IOException {
        List<LinkedHashMap<String, String>> excelDataAsListOfMap = ExcelUtils.getExcelDataAsListOfMap("CreateEventHubTestDataScenarios", "Sheet1");
        List<CreateEventDataPojo> createEventData = new ArrayList<>();
        for(LinkedHashMap<String,String> data : excelDataAsListOfMap) {
            CreateEventDataPojo event = getCreateEventCustomizedData(data);
            createEventData.add(event);
        }
        return createEventData.iterator();
    }
    private CreateEventDataPojo getCreateEventCustomizedData(LinkedHashMap<String,String>data){
        CreateEventDataPojo createEventDataPojo=new CreateEventDataPojo();
        createEventDataPojo.setScenarioId(data.get("ScenarioId"));
        createEventDataPojo.setScenarioDesc(data.get("ScenarioDesc"));
        createEventDataPojo.setExpectedStatusCode(Integer.parseInt(data.get("ExpectedStatusCode")));
        if(!data.get("ExpectedErrorMessage").equals("NO_DATA")){
            createEventDataPojo.setExpectedErrorMessage(data.get("ExpectedErrorMessage"));
        }
        if (!data.get("Title").equals("NO_DATA")){
            createEventDataPojo.setTitle(data.get("Title"));
        }
        if (!data.get("Description").equals("NO_DATA")){
            createEventDataPojo.setDescription(data.get("Description"));
        }
        if (!data.get("Category").equals("NO_DATA")){
            createEventDataPojo.setCategory(data.get("Category"));
        }
        if (!data.get("Venue").equals("NO_DATA")){
            createEventDataPojo.setVenue(data.get("Venue"));
        }
        if (!data.get("City").equals("NO_DATA")){
            createEventDataPojo.setCity(data.get("City"));
        }
        if (!data.get("EventDate").equals("NO_DATA")){
            createEventDataPojo.setEventDate(data.get("EventDate"));
        }
        if (!data.get("Price").equals("NO_DATA")){
            createEventDataPojo.setPrice(Integer.parseInt(data.get("Price")));
        }
        if (!data.get("TotalSeats").equals("NO_DATA")){
            createEventDataPojo.setTotalSeats(Integer.parseInt(data.get("TotalSeats")));
        }
        if (!data.get("ImageUrl").equals("NO_DATA")){
            createEventDataPojo.setImageUrl(data.get("ImageUrl"));
        }
        return createEventDataPojo;
    }
}
