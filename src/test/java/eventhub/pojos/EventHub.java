package eventhub.pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import utils.DateUtils;
import utils.RandomDataGen;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventHub {
    private String title= RandomDataGen.createRandomDataFor(utils.RandomDataTypeNames.Type.TITLE);
    private String description=RandomDataGen.createRandomDataFor(utils.RandomDataTypeNames.Type.DESCRIPTION);
    private String category=RandomDataGen.createRandomDataFor(utils.RandomDataTypeNames.Type.CATEGORY);
    private String venue=RandomDataGen.createRandomDataFor(utils.RandomDataTypeNames.Type.VENUE);
    private String city=RandomDataGen.createRandomDataFor(utils.RandomDataTypeNames.Type.CITY);
    private String eventDate= DateUtils.getFutureDate(10);
    private int price=Integer.parseInt(RandomDataGen.getRandomNumberInRange(100, 5000));
    private int totalSeats=Integer.parseInt(RandomDataGen.getRandomNumberInRange(50, 500));
    private String imageUrl=RandomDataGen.createRandomDataFor(utils.RandomDataTypeNames.Type.IMAGEURL);
}
