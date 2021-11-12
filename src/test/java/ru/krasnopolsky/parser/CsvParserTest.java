package ru.krasnopolsky.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.krasnopolsky.exception.CsvParsingException;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.krasnopolsky.util.CsvConstants.*;
import static ru.krasnopolsky.parser.CSVParser.INCORRECT_DATA_MSG_TEMPLATE;

public class CsvParserTest {

    private final CSVParser parser = new CSVParser();

    @Test
    void incorrectNumOfColumnsCsvParseTest() {
        CsvParsingException exception =
                assertThrows(CsvParsingException.class, () -> parser.parse("src/test/resources/incorrect.csv"));
        int actualRowNumber = 1;
        int actualNumberOfColumns = 2;
        assertEquals(String.format(INCORRECT_DATA_MSG_TEMPLATE, actualRowNumber, actualNumberOfColumns, CSV_NUMBER_OF_COLUMNS), exception.getMessage());
    }

    @Test
    void correctCsvParseTest() throws IOException {
        List<List<String>> fileParseResult = parser.parse("src/test/resources/test.csv");
        assertEquals(3, fileParseResult.size());

        List<String> parsedRow = fileParseResult.get(0);
        assertEquals(11, parsedRow.size());
        assertEquals("Test Test", parsedRow.get(ATHLETE_NAME));
        assertEquals("10.395", parsedRow.get(SPRINT_100_INDEX));
        assertEquals("7.76", parsedRow.get(MALE_LONG_JUMP_INDEX));
        assertEquals("18.4", parsedRow.get(MALE_SHOT_PUT_INDEX));
        assertEquals("2.20", parsedRow.get(MALE_HIGH_JUMP_INDEX));
        assertEquals("46.17", parsedRow.get(SPRINT_400_INDEX));
        assertEquals("13.8", parsedRow.get(SPRINT_HURDLES_INDEX));
        assertEquals("56.17", parsedRow.get(MALE_DISCUS_THROW_INDEX));
        assertEquals("5.28", parsedRow.get(MALE_POLE_VAULT_INDEX));
        assertEquals("77.19", parsedRow.get(MALE_JAVELIN_THROW_INDEX));
        assertEquals("3:53.79", parsedRow.get(SPRINT_1500_INDEX));
    }
}
