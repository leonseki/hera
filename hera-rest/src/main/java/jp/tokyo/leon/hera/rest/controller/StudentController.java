package jp.tokyo.leon.hera.rest.controller;

import jp.tokyo.leon.hera.util.CsvFileTypeHandler;
import jp.tokyo.leon.hera.util.data.CsvData;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author leon
 * @date 2024/3/6 23:08
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @GetMapping("/download-csv")
    public ResponseEntity<InputStreamResource> downloadCsv() {


        String csvContent = new CsvFileTypeHandler().transferFileType(CsvData.STUDENT_INFO);
        String data = CsvData.STUDENT_INFO_TITLE + "\n" + csvContent;

        byte[] csvBytes = data.getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.setContentDispositionFormData("attachment", "data.csv");
        headers.setContentLength(csvBytes.length);

        InputStream inputStream = new ByteArrayInputStream(csvBytes);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
    }
}
