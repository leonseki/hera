package jp.tokyo.leon.hera.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author leon
 * @date 2024/3/6 22:27
 */
public class FileProcessor {
    public File csvFileProcessor(String fileName, String[][] source, CsvFileTypeHandler csvFileTypeHandler) {
        String csvData = csvFileTypeHandler.transferFileType(source);

        File csvFile = null;
        try {
            csvFile = File.createTempFile(fileName, ".csv");
            try (FileWriter writer = new FileWriter(csvFile)) {
                writer.write(csvData);
                System.out.println("Content has been written to the temporary file.");
                return csvFile;
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating or writing to the temporary file: " + e.getMessage());
        }
        return csvFile;
    }
}
