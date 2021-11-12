package ru.krasnopolsky.parser;

import ru.krasnopolsky.exception.CsvParsingException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.krasnopolsky.util.CsvConstants.CSV_NUMBER_OF_COLUMNS;

public class CSVParser implements FileParser {

    private static final String CSV_SEPARATOR = ";";
    public static final String INCORRECT_DATA_MSG_TEMPLATE =
            "Error while parsing CSV: row %d has %d columns, but should %d";

    /**
     * Parses the file using given path.
     * @return list of lists (rows), every which contains splited strings by separator.
     * For example, string 'name;value1;value2;value3' will be represented as list {name, value1, value2, value3}
     */
    public List<List<String>> parse(String path) throws IOException {
        List<List<String>> result = new ArrayList<>();

        List<String> fileLines = Files.readAllLines(Paths.get(path));
        for (int i = 0; i < fileLines.size(); i++) {
            String[] splitedText = fileLines.get(i).split(CSV_SEPARATOR);
            List<String> row = Arrays.asList(splitedText);
            if (row.size() != CSV_NUMBER_OF_COLUMNS) {
                int rowNumber = i + 1;
                int numberOfColumns = row.size();
                throw new CsvParsingException(
                        String.format(INCORRECT_DATA_MSG_TEMPLATE, rowNumber, numberOfColumns, CSV_NUMBER_OF_COLUMNS)
                );
            }
            result.add(row);
        }
        return result;
    }
}
