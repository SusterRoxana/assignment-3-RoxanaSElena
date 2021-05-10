package com.example.demo.patient;

import com.example.demo.patient.model.Patient;
import org.springframework.data.jpa.domain.Specification;

public class PatientSpecification {

    public static Specification<Patient> similarNames(String name) {
        return (root, query, cb) -> cb.like(root.get("name"),  name);
    }
}
