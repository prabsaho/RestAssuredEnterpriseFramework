package eventhub.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CreateEventDataPojo extends BasePojo{
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
