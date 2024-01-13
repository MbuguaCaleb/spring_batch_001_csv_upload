package com.example.spring_csv_file_upload.student;

import com.opencsv.bean.CsvBindByName;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCSVReprestation {

    @CsvBindByName(column = "firstname")
    private String fname;

    @CsvBindByName(column = "lastname")
    private String lname;

    @CsvBindByName(column = "age")
    private int age;

}
