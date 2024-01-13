package com.example.spring_csv_file_upload.student;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;


    public Integer uploadStudents(MultipartFile file) throws IOException {

        //My Tasks
        //One-->Read the File
        //Two-->Upload it to the Database.
        //The method known as parseCSV will read the file
        Set<Student> students = parseCsv(file);
        studentRepository.saveAll(students);
        return students.size();
    }

    private Set<Student> parseCsv(MultipartFile file) throws IOException {

        //Reading the CSV File.
        //Java has methods of reading files
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            //When i am reading a CSV File one of the easiest way to read is through the header
            HeaderColumnNameMappingStrategy<StudentCSVReprestation> strategy = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(StudentCSVReprestation.class);

            //Defines how i want to read my CSV
            CsvToBean<StudentCSVReprestation> csvToBean =
                    new CsvToBeanBuilder<StudentCSVReprestation>(reader)
                            .withMappingStrategy(strategy)
                            .withIgnoreEmptyLine(true)
                            .withIgnoreLeadingWhiteSpace(true)
                            .build();

            return csvToBean.parse()
                    .stream()
                    .map(csvLine ->
                            Student.builder()
                                    .firstname(csvLine.getFname())
                                    .lastname(csvLine.getLname())
                                    .age(csvLine.getAge())
                                    .build()
                    ).collect(Collectors.toSet());
        }

    }
}
