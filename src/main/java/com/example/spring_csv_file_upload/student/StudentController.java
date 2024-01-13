package com.example.spring_csv_file_upload.student;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    //form upload does not take in request mapping but rather multipart/form-data
    @PostMapping(path = "/upload", consumes = {"multipart/form-data"})
    ResponseEntity<Integer> uploadStudents(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(studentService.uploadStudents(file));
    }

}
