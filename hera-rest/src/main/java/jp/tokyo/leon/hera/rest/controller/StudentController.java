package jp.tokyo.leon.hera.rest.controller;

import jp.tokyo.leon.hera.common.api.ResponseResult;
import jp.tokyo.leon.hera.dao.entity.jpa.Student;
import jp.tokyo.leon.hera.service.StudentService;
import jp.tokyo.leon.hera.util.CsvFileTypeHandler;
import jp.tokyo.leon.hera.util.data.CsvData;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author leon
 * @date 2024/3/6 23:08
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    private final CsvFileTypeHandler csvFileTypeHandler = new CsvFileTypeHandler();

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get-by-name")
    public ResponseResult<Student> getByName(@RequestParam(name = "name") String name) {
        Student student = studentService.findByName(name);
        return ResponseResult.ok(student);
    }

    @GetMapping("/download-csv")
    public ResponseEntity<InputStreamResource> downloadCsv() {


        String csvContent = csvFileTypeHandler.transferFileType(CsvData.STUDENT_INFO);
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

    @GetMapping("/download-zip")
    public ResponseEntity<InputStreamResource> downloadZip() throws IOException {

        byte[] data1 = (CsvData.STUDENT_INFO_TITLE + "\n" + csvFileTypeHandler.transferFileType(CsvData.STUDENT_INFO)).getBytes();

        byte[] zipBytes = createZipFile(data1, data1);

        InputStream inputStream = new ByteArrayInputStream(zipBytes);
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "data.zip");

        return ResponseEntity.ok()
                .headers(headers)
                .body(inputStreamResource);
    }

    private byte[] createZipFile(byte[]... data) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
            for (int i = 0; i < data.length; i++) {
                String fileName = "data" + (i + 1) + ".csv";
                byte[] csvBytes = data[i];
                zipOutputStream.putNextEntry(new ZipEntry(fileName));
                zipOutputStream.write(csvBytes, 0, csvBytes.length);
                zipOutputStream.closeEntry();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }


}
