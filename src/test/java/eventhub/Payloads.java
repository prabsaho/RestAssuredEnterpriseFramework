package eventhub;

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
}
