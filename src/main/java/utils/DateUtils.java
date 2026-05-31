package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    // --- YEAR HELPERS ---
    public static int getCurrentYear() {
        return LocalDate.now(ZoneOffset.UTC).getYear();
    }

    public static int getFutureYear(int diff) {
        return LocalDate.now(ZoneOffset.UTC).plusYears(diff).getYear();
    }

    public static int getPastYear(int diff) {
        return LocalDate.now(ZoneOffset.UTC).minusYears(diff).getYear();
    }

    // --- DATE HELPERS ---
    public static String getCurrentDate() {
        return formatDate(LocalDateTime.now(ZoneOffset.UTC));
    }

    public static String getFutureDate(int daysAhead) {
        return formatDate(LocalDateTime.now(ZoneOffset.UTC).plusDays(daysAhead));
    }

    public static String getPastDate(int daysBack) {
        return formatDate(LocalDateTime.now(ZoneOffset.UTC).minusDays(daysBack));
    }

    // --- TIME HELPERS ---
    public static String getCurrentTime() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.now(ZoneOffset.UTC).format(timeFormatter);
    }

    public static String getCurrentDateTime() {
        return formatDate(LocalDateTime.now(ZoneOffset.UTC));
    }

    public static String getFutureDateTime(int daysAhead, int hoursAhead) {
        return formatDate(LocalDateTime.now(ZoneOffset.UTC)
                .plusDays(daysAhead)
                .plusHours(hoursAhead));
    }

    public static String getPastDateTime(int daysBack, int hoursBack) {
        return formatDate(LocalDateTime.now(ZoneOffset.UTC)
                .minusDays(daysBack)
                .minusHours(hoursBack));
    }

    // --- MONTH HELPERS ---
    public static String getCurrentMonthName() {
        return LocalDate.now(ZoneOffset.UTC).getMonth().name();
    }

    public static String getFutureMonthName(int diff) {
        return LocalDate.now(ZoneOffset.UTC).plusMonths(diff).getMonth().name();
    }

    public static String getPastMonthName(int diff) {
        return LocalDate.now(ZoneOffset.UTC).minusMonths(diff).getMonth().name();
    }

    // --- PRIVATE FORMATTER ---
    private static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

}
