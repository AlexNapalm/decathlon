package ru.krasnopolsky.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static ru.krasnopolsky.util.CommonConstants.*;

public class StringUtils {

    private StringUtils() {
    }

    /**
     * Converts string to double.
     * @param string
     * @return parsed {@link Double} from string or Double.NaN if parsing failed.
     */
    public static Double toDouble(String string) {
        try {
            return Double.valueOf(string);
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    /**
     * Converts given time string to {@link Double} of seconds.
     * The method is using predefined "mm:ss.SS" time format for parsing.
     * For example, for input string "1:02.45" it will return 60 + 02.45 = 62.45 value
     * @return parsed {@link Double} from string or Double.NaN if parsing failed.
     */
    public static Double timeToSeconds(String string) {
        DateFormat formatter = new SimpleDateFormat(DECATHLON_TIME_FORMAT);
        try {
            Date dt = formatter.parse(string);
            LocalDateTime localDateTime = Instant.ofEpochMilli(dt.getTime())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            int minute = localDateTime.getMinute();
            int seconds = localDateTime.getSecond();
            int nanos = localDateTime.getNano();
            return (minute * SECONDS_IN_MINUTE) + seconds + (double) nanos / (NANOS_IN_MILLIS * 100);
        } catch (ParseException e) {
            return Double.NaN;
        }
    }
}
