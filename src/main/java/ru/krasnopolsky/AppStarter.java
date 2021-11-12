package ru.krasnopolsky;

import ru.krasnopolsky.enums.Gender;
import ru.krasnopolsky.export.Exporter;
import ru.krasnopolsky.export.XmlExporter;
import ru.krasnopolsky.parser.CSVParser;
import ru.krasnopolsky.parser.FileParser;
import ru.krasnopolsky.service.AthleteService;
import ru.krasnopolsky.service.builder.AthletePerformanceBuilder;
import ru.krasnopolsky.service.model.AthletePerformance;
import ru.krasnopolsky.service.model.AthleteRank;

import java.util.List;

public class AppStarter {

    public static final String WRONG_NUMBER_OF_ARGS_MSG = "Wrong number of arguments. Please, run with 3 arguments: inputPath, outputPath and gender (m/f)";
    public static final String WRONG_3RD_ARG_MSG = "Wrong 3rd argument: should be 'male' or 'female'";

    private static final int INPUT_PATH_ARG_INDEX = 0;
    private static final int OUTPUT_PATH_ARG_INDEX = 1;
    private static final int GENDER_ARG_INDEX = 2;
    private static final int NUMBER_OF_ARGS_LIMIT = 3;


    /**
     * Starts the logic of application.
     */
    public void start(String[] args) throws Exception {
        validateArgs(args);

        String inputPath = args[INPUT_PATH_ARG_INDEX];
        String outputPath = args[OUTPUT_PATH_ARG_INDEX];
        Gender gender = Gender.MALE.getValue().equalsIgnoreCase(args[GENDER_ARG_INDEX]) ? Gender.MALE : Gender.FEMALE;

        FileParser parser = new CSVParser();
        List<List<String>> parsedCsv = parser.parse(inputPath);

        AthletePerformanceBuilder performanceBuilder = new AthletePerformanceBuilder();
        List<AthletePerformance> athletePerformances = performanceBuilder.buildAthletePerformanceList(parsedCsv, gender);

        AthleteService athleteService = new AthleteService();
        List<AthleteRank> athleteRanks = athleteService.getAthleteRanksByPerformances(athletePerformances);

        Exporter exporter = new XmlExporter();
        exporter.exportToFile(athleteRanks, outputPath);
    }

    /**
     * Checks for input arguments validity.
     */
    private static void validateArgs(String[] args) {
        if (args == null || args.length != NUMBER_OF_ARGS_LIMIT) {
            throw new IllegalArgumentException(WRONG_NUMBER_OF_ARGS_MSG);
        }
        if (!Gender.MALE.getValue().equalsIgnoreCase(args[GENDER_ARG_INDEX]) && !Gender.FEMALE.getValue().equalsIgnoreCase(args[GENDER_ARG_INDEX])) {
            throw new IllegalArgumentException(WRONG_3RD_ARG_MSG);
        }
    }
}
