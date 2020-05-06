package com.asgarov.avlbaum.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileReader {
    /**
     * Utility method that reads the file
     * @param fileName
     * @return list of lines read from the file
     * @throws IOException
     */
    public static List<String> readFile(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName)).collect(Collectors.toList());
    }
}
