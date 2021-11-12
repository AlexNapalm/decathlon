package ru.krasnopolsky.export;

import ru.krasnopolsky.service.model.AthleteRank;

import java.util.List;

public interface Exporter {

    /**
     * Export athlete results to file.
     * @param athletes list of athletes calculated scores and performances
     * @param outputPath path where to save exported file.
     */
    void exportToFile(List<AthleteRank> athletes, String outputPath);
}
