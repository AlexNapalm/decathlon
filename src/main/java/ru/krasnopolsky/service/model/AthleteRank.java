package ru.krasnopolsky.service.model;

public class AthleteRank {

    private String rank;
    private AthletePerformance performance;

    public AthleteRank(AthletePerformance performance) {
        this.performance = performance;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public AthletePerformance getPerformance() {
        return performance;
    }
}
