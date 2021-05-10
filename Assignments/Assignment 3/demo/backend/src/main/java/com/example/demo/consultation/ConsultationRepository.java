package com.example.demo.consultation;

import com.example.demo.consultation.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;


public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    Consultation findByDoctorIdAndAndDateAndAndTime(Long id, Date date, Long time);

}
