package ru.krasnopolsky.export;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.krasnopolsky.export.XmlExporter.INCORRECT_DATA_MSG;

public class XmlExporterTest {

    private final Exporter exporter = new XmlExporter();

    @Test
    void exportEmptyDataTest() {
        Exception exception =
                assertThrows(IllegalArgumentException.class, () -> exporter.exportToFile(null, "somePath"));
        assertEquals(INCORRECT_DATA_MSG, exception.getMessage());
    }
}
