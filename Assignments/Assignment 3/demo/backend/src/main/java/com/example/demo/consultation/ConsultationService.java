package com.example.demo.consultation;

import com.example.demo.consultation.model.Consultation;
import com.example.demo.consultation.model.dto.ConsultationDTO;
import com.example.demo.patient.PatientRepository;
import com.example.demo.patient.model.Patient;
import com.example.demo.user.UserRepository;
import com.example.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final ConsultationMapper consultationMapper;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public List<ConsultationDTO> findAll()
    {
        return consultationRepository.findAll().stream()
                .map(consultationMapper::toDto)
                .collect(Collectors.toList());
    }

    private Consultation findById(Long id)
    {
        return consultationRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Consultation not found: " + id));
    }

    private Consultation createConsultation (ConsultationDTO consultationDTO)
    {
        User doctor = userRepository.findById(consultationDTO.getDoctor_id()).orElse(null);
        Patient patient = patientRepository.findById(consultationDTO.getPatient_id()).orElse(null);
        Consultation consultation = Consultation.builder()
                .doctor(doctor)
                .patient(patient)
                .date(consultationDTO.getDate())
                .text(consultationDTO.getText())
                .time(consultationDTO.getTime())
                .build();
        return consultation;
    }

    public ConsultationDTO create (ConsultationDTO consultation)  {

        Consultation consultationAlreadyExists = consultationRepository.findByDoctorIdAndAndDateAndAndTime(consultation.getDoctor_id(), consultation.getDate(), consultation.getTime());
        if (consultationAlreadyExists == null) {
            Consultation c = createConsultation(consultation);
            return consultationMapper.toDto(consultationRepository.save(c));
        }
        else return null;
    }

    public void delete (Long id) {consultationRepository.deleteById(id);}

    public ConsultationDTO edit (Long id, ConsultationDTO consultation)
    {
        Consultation actConsultation = findById(id);
        actConsultation.setText(consultation.getText());
        return consultationMapper.toDto(consultationRepository.save(actConsultation));
    }



}
