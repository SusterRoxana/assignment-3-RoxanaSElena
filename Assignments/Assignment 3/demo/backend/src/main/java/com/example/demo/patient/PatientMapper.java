package com.example.demo.patient;

import com.example.demo.patient.model.Patient;
import com.example.demo.patient.model.dto.PatientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientDTO toDto(Patient patient);
    Patient fromDto(PatientDTO patientDTO);



}
