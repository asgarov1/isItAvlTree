package com.asgarov.avlbaum.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    /**
     * Utility method that reads the file from resources
     * @param fileName
     * @return list of lines read from the file
     * @throws IOException
     */
    public static List<String> readFile(String fileName) throws IOException {
        String pathToFile = "src/resources/" + fileName;
        return Files.lines(Paths.get(pathToFile)).collect(Collectors.toList());
    }
}
