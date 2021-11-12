package ru.krasnopolsky.parser;

import java.util.List;

public interface FileParser {

    List<List<String>> parse(String path) throws Exception;
}
