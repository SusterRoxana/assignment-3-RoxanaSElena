package com.example.demo.patient.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PatientDTO {

    private Long id;
    private String name;
    private Long cardNumber;
    private String cnp;
    private Date birthDate;
    private String address;
}
