package eventhub;
import eventhub.pojos.EventHub;
import utils.DateUtils;
import utils.RandomDataGen;
import utils.RandomDataTypeNames;

import java.util.HashMap;
import java.util.Map;

public class Payloads {
    public static String getCreateEventPayload(String title,String description,String category,String venue,
                                            String city,String eventDate,String price,String totalSeats,String imageUrl){
        String payLoad="{\n" +
                "  \"title\": \""+title+"\",\n" +
                "  \"description\": \""+description+"\",\n" +
                "  \"category\": \""+category+"\",\n" +
                "  \"venue\": \""+venue+"\",\n" +
                "  \"city\": \""+city+"\",\n" +
                "  \"eventDate\": \""+eventDate+"\",\n" +
                "  \"price\": "+price+",\n" +
                "  \"totalSeats\": "+totalSeats+",\n" +
                "  \"imageUrl\": \""+imageUrl+"\"\n" +
                "}";
        return  payLoad;
    }
    public static Map<String,Object> getCreateEventPayloadFromMap(String title,String description,String category,String venue,
                                               String city,String eventDate,String price,String totalSeats,String imageUrl){
        Map<String,Object> payLoad= new HashMap<>();
        payLoad.put("title",title);
        payLoad.put("description",description);
        payLoad.put("category",category);
        payLoad.put("venue",venue);
        payLoad.put("city",city);
        payLoad.put("eventDate",eventDate);
        payLoad.put("price",price);
        payLoad.put("totalSeats",totalSeats);
        payLoad.put("imageUrl",imageUrl);
        return  payLoad;
    }
    public static Map<String,Object> getCreateEventFakerPayloadFromMap(){
        Map<String,Object> payLoad= new HashMap<>();
        payLoad.put("title", RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.TITLE));
        payLoad.put("description", RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.DESCRIPTION));
        payLoad.put("category", RandomDataGen.getRandomAlphabeticString(10));
        payLoad.put("venue", RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.VENUE));
        payLoad.put("city", RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.CITY));
        payLoad.put("eventDate", DateUtils.getFutureDate(10));
        payLoad.put("price", RandomDataGen.getRandomNumberInRange(100,5000));
        payLoad.put("totalSeats", RandomDataGen.getRandomNumberInRange(50,500));
        payLoad.put("imageUrl", RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.IMAGEURL));
        return  payLoad;
    }
    public static EventHub getCreateEventPayloadFromPojo(){
        return EventHub.builder()
                .title(RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.TITLE))
                .description(RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.DESCRIPTION))
                .category(RandomDataGen.getRandomAlphabeticString(10))
                .venue(RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.VENUE))
                .city(RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.CITY))
                .eventDate(DateUtils.getFutureDate(10))
                .price(Integer.parseInt(RandomDataGen.getRandomNumberInRange(100, 5000)))
                .totalSeats(Integer.parseInt(RandomDataGen.getRandomNumberInRange(50, 500)))
                .imageUrl(RandomDataGen.createRandomDataFor(RandomDataTypeNames.Type.IMAGEURL))
                .build();
    }
}
