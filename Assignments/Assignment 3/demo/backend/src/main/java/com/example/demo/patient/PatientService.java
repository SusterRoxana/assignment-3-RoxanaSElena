package com.example.demo.patient;

import com.example.demo.patient.model.Patient;
import com.example.demo.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    private Patient findById(Long id)
    {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found: " + id));
    }

    public List<PatientDTO> findAll()
    {
        return patientRepository.findAll().stream()
                .map(patientMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDTO create(PatientDTO patient)
    {
        return patientMapper.toDto(patientRepository.save(patientMapper.fromDto(patient)));

    }

    public void delete(Long id)
    {
        patientRepository.deleteById(id);
    }

    public PatientDTO edit(Long id, PatientDTO patient)
    {
        Patient actPatient = findById(id);
        actPatient.setName(patient.getName());
        actPatient.setCardNumber(patient.getCardNumber());
        actPatient.setAddress(patient.getAddress());
        return patientMapper.toDto(patientRepository.save(actPatient));
    }

}
