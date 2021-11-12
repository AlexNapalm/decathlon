package ru.krasnopolsky.service;

import org.junit.jupiter.api.Test;
import ru.krasnopolsky.service.model.AthletePerformance;
import ru.krasnopolsky.service.model.AthleteRank;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AthleteServiceTest {

    private final AthleteService athleteService = new AthleteService();

    @Test
    void getAthleteRanksByPerformancesTest() {
        AthletePerformance performance1 = new AthletePerformance();
        performance1.setName("name1");
        performance1.setTotalScore(1000);

        AthletePerformance performance2 = new AthletePerformance();
        performance2.setName("name2");
        performance2.setTotalScore(800);

        AthletePerformance performance3 = new AthletePerformance();
        performance3.setName("name3");
        performance3.setTotalScore(800);

        AthletePerformance performance4 = new AthletePerformance();
        performance4.setName("name4");
        performance4.setTotalScore(500);

        List<AthletePerformance> performances = new ArrayList<>();
        performances.add(performance1);
        performances.add(performance2);
        performances.add(performance3);
        performances.add(performance4);

        List<AthleteRank> athleteRanksByPerformances = athleteService.getAthleteRanksByPerformances(performances);
        assertEquals(4, athleteRanksByPerformances.size());

        assertEquals("1", athleteRanksByPerformances.get(0).getRank());
        assertEquals("name1", athleteRanksByPerformances.get(0).getPerformance().getName());
        assertEquals(1000, athleteRanksByPerformances.get(0).getPerformance().getTotalScore());

        assertEquals("2-3", athleteRanksByPerformances.get(1).getRank());
        assertEquals("name2", athleteRanksByPerformances.get(1).getPerformance().getName());
        assertEquals(800, athleteRanksByPerformances.get(1).getPerformance().getTotalScore());

        assertEquals("2-3", athleteRanksByPerformances.get(2).getRank());
        assertEquals("name3", athleteRanksByPerformances.get(2).getPerformance().getName());
        assertEquals(800, athleteRanksByPerformances.get(2).getPerformance().getTotalScore());

        assertEquals("4", athleteRanksByPerformances.get(3).getRank());
        assertEquals("name4", athleteRanksByPerformances.get(3).getPerformance().getName());
        assertEquals(500, athleteRanksByPerformances.get(3).getPerformance().getTotalScore());
    }
}
