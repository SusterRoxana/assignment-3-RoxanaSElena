package com.example.demo.consultation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ConsultationDTO {

    private Long id;
    private String text;
    private Long doctor_id;
    private Long patient_id;
    private Date date;
    private Long time;
}
