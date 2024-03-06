package jp.tokyo.leon.hera.util;

import jp.tokyo.leon.hera.util.data.CsvData;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author leon
 * @date 2024/3/6 22:37
 */
public class CsvFileTypeHandler implements FileTypeHandler<String[][], String>{

    @Override
    public String transferFileType(String[][] strings) {
        return Arrays.stream(strings)
                .map(row -> String.join(",", row))
                .collect(Collectors.joining("\n"));
    }
}
