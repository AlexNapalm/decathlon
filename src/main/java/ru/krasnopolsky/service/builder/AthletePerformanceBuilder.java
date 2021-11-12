package ru.krasnopolsky.service.builder;

import ru.krasnopolsky.enums.Discipline;
import ru.krasnopolsky.enums.Gender;
import ru.krasnopolsky.service.model.AthletePerformance;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.krasnopolsky.util.CsvConstants.*;
import static ru.krasnopolsky.util.StringUtils.timeToSeconds;
import static ru.krasnopolsky.util.StringUtils.toDouble;

public class AthletePerformanceBuilder {

    /**
     * Builds list of {@link AthletePerformance} using parsed data.
     * It maps values from parsed string, using predefined indexes, and calculates total score for each athlete.
     * @param performanceRows
     * @param gender gender of athlete to map values correctly, as men and women have different order of disciplines in decathlon.
     * @return list of athlete performances sorted by totalScore.
     */
    public List<AthletePerformance> buildAthletePerformanceList(List<List<String>> performanceRows, Gender gender) {
        return performanceRows.stream()
                .map((List<String> resultRow) -> toAthletePerformance(resultRow, gender))
                .sorted(Comparator.comparing(AthletePerformance::getTotalScore).reversed())
                .collect(Collectors.toList());
    }

    private AthletePerformance toAthletePerformance(List<String> resultRow, Gender gender) {
        AthletePerformance athletePerformance = new AthletePerformance();
        athletePerformance.setName(resultRow.get(ATHLETE_NAME));
        athletePerformance.setSprint100(toDouble(resultRow.get(SPRINT_100_INDEX)));
        athletePerformance.setSprint400(toDouble(resultRow.get(SPRINT_400_INDEX)));
        athletePerformance.setSprintHurdles(toDouble(resultRow.get(SPRINT_HURDLES_INDEX)));
        athletePerformance.setSprint1500(timeToSeconds(resultRow.get(SPRINT_1500_INDEX)));

        athletePerformance.setLongJump(toDouble(resultRow.get(Gender.MALE == gender ? MALE_LONG_JUMP_INDEX : FEMALE_LONG_JUMP_INDEX)));
        athletePerformance.setShotPut(toDouble(resultRow.get(Gender.MALE == gender ? MALE_SHOT_PUT_INDEX : FEMALE_SHOT_PUT_INDEX)));
        athletePerformance.setHighJump(toDouble(resultRow.get(Gender.MALE == gender ? MALE_HIGH_JUMP_INDEX : FEMALE_HIGH_JUMP_INDEX)));
        athletePerformance.setDiscusThrow(toDouble(resultRow.get(Gender.MALE == gender ? MALE_DISCUS_THROW_INDEX : FEMALE_DISCUS_THROW_INDEX)));
        athletePerformance.setPoleVault(toDouble(resultRow.get(Gender.MALE == gender ? MALE_POLE_VAULT_INDEX : FEMALE_POLE_VAULT_INDEX)));
        athletePerformance.setJavelinThrow(toDouble(resultRow.get(Gender.MALE == gender ? MALE_JAVELIN_THROW_INDEX : FEMALE_JAVELIN_THROW_INDEX)));

        athletePerformance.setTotalScore(calculateAthleteTotalScore(athletePerformance));
        return athletePerformance;
    }

    /**
     * Calculates athlete scores according to athlete's performance for each disciplines
     * and returns the sum of scores.
     */
    private int calculateAthleteTotalScore(AthletePerformance athletePerformance) {
        return getSprint100Score(athletePerformance.getSprint100()) +
                getLongJumpScore(athletePerformance.getLongJump()) +
                getShotPutScore(athletePerformance.getShotPut()) +
                getHighJumpScore(athletePerformance.getHighJump()) +
                getSprint400Score(athletePerformance.getSprint400()) +
                getSprintHurdlesScore(athletePerformance.getSprintHurdles()) +
                getDiscusThrowScore(athletePerformance.getDiscusThrow()) +
                getPoleVaultScore(athletePerformance.getPoleVault()) +
                getJavelinThrowScore(athletePerformance.getJavelinThrow()) +
                getSprint1500Score(athletePerformance.getSprint1500());
    }

    private int getSprint100Score(Double athletePerformance) {
        return Discipline.SPRINT_100.calculateScore(athletePerformance);
    }

    private int getLongJumpScore(Double athletePerformance) {
        return Discipline.LONG_JUMP.calculateScore(athletePerformance);
    }

    private int getShotPutScore(Double athletePerformance) {
        return Discipline.SHOT_PUT.calculateScore(athletePerformance);
    }

    private int getHighJumpScore(Double athletePerformance) {
        return Discipline.HIGH_JUMP.calculateScore(athletePerformance);
    }

    private int getSprint400Score(Double athletePerformance) {
        return Discipline.SPRINT_400.calculateScore(athletePerformance);
    }

    private int getSprintHurdlesScore(Double athletePerformance) {
        return Discipline.SPRINT_HURDLES.calculateScore(athletePerformance);
    }

    private int getDiscusThrowScore(Double athletePerformance) {
        return Discipline.DISCUS_THROW.calculateScore(athletePerformance);
    }

    private int getPoleVaultScore(Double athletePerformance) {
        return Discipline.POLE_VAULT.calculateScore(athletePerformance);
    }

    private int getJavelinThrowScore(Double athletePerformance) {
        return Discipline.JAVELIN_THROW.calculateScore(athletePerformance);
    }

    private int getSprint1500Score(Double athletePerformance) {
        return Discipline.SPRINT_1500.calculateScore(athletePerformance);
    }
}
