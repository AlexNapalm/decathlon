package ru.krasnopolsky.util;

/**
 * Constants for proccessing CSV data.
 */
public class CsvConstants {

    public static final int ATHLETE_NAME = 0;
    public static final int SPRINT_100_INDEX = 1;
    public static final int SPRINT_400_INDEX = 5;
    public static final int SPRINT_HURDLES_INDEX = 6;
    public static final int SPRINT_1500_INDEX = 10;

    public static final int MALE_LONG_JUMP_INDEX = 2;
    public static final int FEMALE_DISCUS_THROW_INDEX = MALE_LONG_JUMP_INDEX;

    public static final int MALE_SHOT_PUT_INDEX = 3;
    public static final int FEMALE_POLE_VAULT_INDEX = MALE_SHOT_PUT_INDEX;

    public static final int MALE_HIGH_JUMP_INDEX = 4;
    public static final int FEMALE_JAVELIN_THROW_INDEX = MALE_HIGH_JUMP_INDEX;

    public static final int MALE_DISCUS_THROW_INDEX = 7;
    public static final int FEMALE_LONG_JUMP_INDEX = MALE_DISCUS_THROW_INDEX;

    public static final int MALE_POLE_VAULT_INDEX = 8;
    public static final int FEMALE_SHOT_PUT_INDEX = MALE_POLE_VAULT_INDEX;

    public static final int MALE_JAVELIN_THROW_INDEX = 9;
    public static final int FEMALE_HIGH_JUMP_INDEX = MALE_JAVELIN_THROW_INDEX;

    public static final int CSV_NUMBER_OF_COLUMNS = 11;

    private CsvConstants() {
    }
}
