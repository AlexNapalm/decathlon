package ru.krasnopolsky.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    void toDoubleTest() {
        String stringDouble = "5.5";
        Double result = StringUtils.toDouble(stringDouble);
        assertEquals(Double.parseDouble(stringDouble), result);
    }

    @Test
    void toDoubleIncorrectInputTest() {
        String stringDouble = "abc";
        Double result = StringUtils.toDouble(stringDouble);
        assertEquals(Double.NaN, result);
    }

    @Test
    void timeToSecondsTest() {
        String time = "5:25.72";
        Double result = StringUtils.timeToSeconds(time);
        assertEquals(Double.parseDouble("325.72"), result);
    }

    @Test
    void timeToSecondsIncorrectFormatTest() {
        String time = "10-10-10";
        Double result = StringUtils.timeToSeconds(time);
        assertEquals(Double.NaN, result);
    }
}
