package ru.krasnopolsky.export;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.krasnopolsky.exception.FileExportException;
import ru.krasnopolsky.service.model.AthleteRank;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XmlExporter implements Exporter {

    public static final String INCORRECT_DATA_MSG = "Incorrect data: nothing to export";

    /**
     * Exports given data to XML file on the filesystem.
     */
    @Override
    public void exportToFile(List<AthleteRank> athletes, String outputPath) {
        if (athletes == null || athletes.isEmpty()) {
            throw new IllegalArgumentException(INCORRECT_DATA_MSG);
        }
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("athletes");
            doc.appendChild(rootElement);

            for (AthleteRank rank : athletes) {
                Element athlete = doc.createElement("athlete");
                rootElement.appendChild(athlete);

                athlete.appendChild(createElement(doc, "name", rank.getPerformance().getName()));
                athlete.appendChild(createElement(doc, "rank", rank.getRank()));
                athlete.appendChild(createElement(doc, "score", String.valueOf(rank.getPerformance().getTotalScore())));

                Element performanceElement = createPerformanceElement(doc, rank);
                athlete.appendChild(performanceElement);
            }

            saveFile(doc, outputPath);

        } catch (Exception e) {
            throw new FileExportException(e.getMessage());
        }
    }

    private Element createElement(Document doc, String name, String value) {
        Element element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return element;
    }

    /**
     * Creates 'performance' section inside 'athlete' element
     */
    private Element createPerformanceElement(Document doc, AthleteRank rank) {
        Element element = doc.createElement("performance");
        element.appendChild(createElement(doc, "sprint100Meters", rank.getPerformance().getSprint100().toString()));
        element.appendChild(createElement(doc, "longJump", rank.getPerformance().getLongJump().toString()));
        element.appendChild(createElement(doc, "shotPut", rank.getPerformance().getShotPut().toString()));
        element.appendChild(createElement(doc, "highJump", rank.getPerformance().getHighJump().toString()));
        element.appendChild(createElement(doc, "sprint400Meters", rank.getPerformance().getSprint100().toString()));
        element.appendChild(createElement(doc, "sprintHurdles", rank.getPerformance().getSprintHurdles().toString()));
        element.appendChild(createElement(doc, "discusThrow", rank.getPerformance().getDiscusThrow().toString()));
        element.appendChild(createElement(doc, "poleVault", rank.getPerformance().getPoleVault().toString()));
        element.appendChild(createElement(doc, "javelinThrow", rank.getPerformance().getJavelinThrow().toString()));
        element.appendChild(createElement(doc, "sprint1500Meters", rank.getPerformance().getSprint1500().toString()));
        return element;
    }

    /**
     * Saves given document to filesystem.
     */
    private void saveFile(Document document, String outputPath) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(document);

        File file = new File(outputPath);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }
}
