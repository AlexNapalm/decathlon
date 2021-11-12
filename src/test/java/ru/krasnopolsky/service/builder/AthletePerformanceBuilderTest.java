package ru.krasnopolsky.service.builder;

import org.junit.jupiter.api.Test;
import ru.krasnopolsky.enums.Gender;
import ru.krasnopolsky.service.model.AthletePerformance;
import ru.krasnopolsky.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AthletePerformanceBuilderTest {

    private static final String NAME = "TestName";
    //official values to get 1000 points are used here
    //see https://en.wikipedia.org/wiki/Decathlon#Men's_decathlon
    private static final String SPRINT_100 = "10.395";
    private static final String LONG_JUMP = "7.76";
    private static final String SHOT_PUT = "18.4";
    private static final String HIGH_JUMP = "2.21";
    private static final String SPRINT_400 = "46.17";
    private static final String SPRINT_HURDLES = "13.8";
    private static final String DISCUS_THROW = "56.17";
    private static final String POLE_VAULT = "5.28";
    private static final String JAVELIN_THROW = "77.19";
    private static final String SPRINT_1500 = "3:53.79";

    private AthletePerformanceBuilder builder = new AthletePerformanceBuilder();

    @Test
    void buildMaleAthletePerformanceTest() {
        List<String> performanceRow = getPerformanceRow(Gender.MALE);

        List<List<String>> parsedPerformance = new ArrayList<>();
        parsedPerformance.add(performanceRow);

        List<AthletePerformance> athletePerformances = builder.buildAthletePerformanceList(parsedPerformance, Gender.MALE);
        assertEquals(1, athletePerformances.size());

        AthletePerformance result = athletePerformances.get(0);
        assertResult(result);
    }

    @Test
    void buildFemaleAthletePerformanceTest() {
        List<String> performanceRow = getPerformanceRow(Gender.FEMALE);

        List<List<String>> parsedPerformance = new ArrayList<>();
        parsedPerformance.add(performanceRow);

        List<AthletePerformance> athletePerformances = builder.buildAthletePerformanceList(parsedPerformance, Gender.FEMALE);
        assertEquals(1, athletePerformances.size());

        AthletePerformance result = athletePerformances.get(0);
        assertResult(result);
    }

    private List<String> getPerformanceRow(Gender gender) {
        List<String> performanceRow = new ArrayList<>();
        performanceRow.add(NAME);
        performanceRow.add(SPRINT_100);
        performanceRow.add(gender == Gender.MALE ? LONG_JUMP : DISCUS_THROW);
        performanceRow.add(gender == Gender.MALE ?SHOT_PUT : POLE_VAULT);
        performanceRow.add(gender == Gender.MALE ?HIGH_JUMP : JAVELIN_THROW);
        performanceRow.add(SPRINT_400);
        performanceRow.add(SPRINT_HURDLES);
        performanceRow.add(gender == Gender.MALE ?DISCUS_THROW : LONG_JUMP);
        performanceRow.add(gender == Gender.MALE ?POLE_VAULT : SHOT_PUT);
        performanceRow.add(gender == Gender.MALE ?JAVELIN_THROW : HIGH_JUMP);
        performanceRow.add(SPRINT_1500);
        return performanceRow;
    }

    private void assertResult(AthletePerformance result) {
        assertEquals(NAME, result.getName());
        assertEquals(Double.parseDouble(SPRINT_100), result.getSprint100());
        assertEquals(Double.parseDouble(LONG_JUMP), result.getLongJump());
        assertEquals(Double.parseDouble(SHOT_PUT), result.getShotPut());
        assertEquals(Double.parseDouble(HIGH_JUMP), result.getHighJump());
        assertEquals(Double.parseDouble(SPRINT_400), result.getSprint400());
        assertEquals(Double.parseDouble(SPRINT_HURDLES), result.getSprintHurdles());
        assertEquals(Double.parseDouble(DISCUS_THROW), result.getDiscusThrow());
        assertEquals(Double.parseDouble(POLE_VAULT), result.getPoleVault());
        assertEquals(Double.parseDouble(JAVELIN_THROW), result.getJavelinThrow());
        assertEquals(StringUtils.timeToSeconds(SPRINT_1500), result.getSprint1500());
        assertEquals(10000, result.getTotalScore());
    }
}
