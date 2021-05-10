package com.example.demo.patient;

import com.example.demo.patient.model.dto.PatientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.UrlMapping.ENTITY;
import static com.example.demo.UrlMapping.PATIENT;
import java.util.List;

@RestController
@RequestMapping(PATIENT)
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public List<PatientDTO> allPatients() { return patientService.findAll();}

    @PostMapping
    public PatientDTO create (@RequestBody PatientDTO patient){
        System.out.println("buna");
        return patientService.create(patient);}

    @PutMapping(ENTITY)
    public PatientDTO edit(@PathVariable Long id, @RequestBody PatientDTO patient) {return patientService.edit(id,patient);}

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {patientService.delete(id);}
}
