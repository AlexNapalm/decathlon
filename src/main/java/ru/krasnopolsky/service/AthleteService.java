package ru.krasnopolsky.service;

import ru.krasnopolsky.service.model.AthletePerformance;
import ru.krasnopolsky.service.model.AthleteRank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AthleteService {

    /**
     * Returns list with final rankings.
     * If any athletes have the same score, so their ranking will be shared (for example, 3-5 instead of 3, 4 and 5).
     */
    public List<AthleteRank> getAthleteRanksByPerformances(List<AthletePerformance> performances) {
        List<AthleteRank> resultRanks = new ArrayList<>();

        for (int i = 0; i < performances.size(); i++) {
            AthletePerformance athletePerformance = performances.get(i);

            List<AthletePerformance> duplicates = checkDuplicates(athletePerformance.getTotalScore(), performances);
            if (duplicates.size() < 2) {
                AthleteRank rank = new AthleteRank(athletePerformance);
                rank.setRank(String.valueOf(i + 1));
                resultRanks.add(rank);
            } else {
                String sharedRank = buildSharedRankString(i + 1, i + duplicates.size());
                duplicates.forEach(perfWithDuplScore -> {
                    AthleteRank rank = new AthleteRank(perfWithDuplScore);
                    rank.setRank(sharedRank);
                    resultRanks.add(rank);
                });
                //jump over checked athletes with duplicated score to proceed with unchecked ones.
                i = i + duplicates.size() - 1;
            }
        }
        return resultRanks;
    }

    private String buildSharedRankString(int from, int to) {
        return String.format("%d-%d", from, to);
    }

    /**
     * Returns sublist of athletes with the given score.
     */
    private List<AthletePerformance> checkDuplicates(int scoreToCheck, List<AthletePerformance> performances) {
        return performances.stream()
                .filter(perf -> perf.getTotalScore() == scoreToCheck)
                .collect(Collectors.toList());
    }

}
