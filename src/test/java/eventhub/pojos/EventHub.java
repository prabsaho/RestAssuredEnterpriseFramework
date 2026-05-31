package eventhub.pojos;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventHub {
    private String title;
    private String description;
    private String category;
    private String venue;
    private String city;
    private String eventDate;
    private int price;
    private int totalSeats;
    private String imageUrl;
}
