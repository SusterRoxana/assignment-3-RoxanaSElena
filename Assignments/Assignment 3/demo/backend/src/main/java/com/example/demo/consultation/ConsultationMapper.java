package com.example.demo.consultation;

import com.example.demo.consultation.model.Consultation;
import com.example.demo.consultation.model.dto.ConsultationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsultationMapper {

    ConsultationDTO toDto(Consultation consultation);
    Consultation fromDto(ConsultationDTO consultationDTO);
}
