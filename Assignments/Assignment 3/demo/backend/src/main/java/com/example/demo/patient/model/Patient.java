package com.example.demo.patient.model;

import com.example.demo.consultation.model.Consultation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long cardNumber;

    @Column(length = 13, nullable = false)
    private String cnp;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "patient")
    private List<Consultation> consultations;




}
