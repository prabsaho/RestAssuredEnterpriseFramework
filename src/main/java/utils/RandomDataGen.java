package utils;

import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class RandomDataGen {
    public static Faker faker = new Faker();

    public static String createRandomDataFor(RandomDataTypeNames.Type type) {
        switch (type) {
            case TITLE:
                return faker.book().title();
            case DESCRIPTION:
                return faker.lorem().sentence();
            case CATEGORY:
                return faker.book().genre();
            case VENUE:
                return faker.address().streetAddress();
            case CITY:
                return faker.address().city();
            case DATE:
                return DateUtils.getFutureDate(10);
            case PRICE:
                return String.valueOf(faker.number().numberBetween(100, 5000));
            case NUMBER:
                return String.valueOf(faker.number().numberBetween(50, 500));
            case IMAGEURL:
                return faker.internet().url();
            case GENDER:
                return Stream.of("Male","Female","Others").findAny().get();
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
    public static String getRandomNumberInRange(int min, int max) {
        return String.valueOf(faker.number().numberBetween(min, max));
    }
    public static String getRandomAlphabeticString(int characterCount){
        return faker.lorem().characters(characterCount);
    }
}