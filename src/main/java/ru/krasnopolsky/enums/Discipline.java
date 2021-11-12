package ru.krasnopolsky.enums;

/**
 * Discipline type with values to use in formula
 */
public enum Discipline {
    SPRINT_100( 25.4348, 18, 1.81),
    LONG_JUMP( 90.5674, 2.2, 1.4),
    SHOT_PUT( 51.39, 1.5, 1.05),
    HIGH_JUMP( 585.64, 0.75, 1.42),
    SPRINT_400( 1.53775, 82, 1.81),
    SPRINT_HURDLES( 5.74354, 28.5, 1.92),
    DISCUS_THROW( 12.91, 4, 1.1),
    POLE_VAULT( 140.182, 1, 1.35),
    JAVELIN_THROW( 10.14, 7, 1.08),
    SPRINT_1500( 0.03768, 480, 1.85);

    Discipline(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private final double a;
    private final double b;
    private final double c;

    /**
     * Calculates score with given performance.
     * Score is calculated using formula: score = a * abs(athletePerformance - b)^c
     */
    public int calculateScore(Double athletePerformance) {
        if (athletePerformance == null) {
            return 0;
        }
        double result = this.a * Math.pow(Math.abs(athletePerformance - this.b), this.c);
        return (int) result;
    }
}
