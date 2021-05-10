package com.example.demo.consultation;
import com.example.demo.consultation.model.dto.ConsultationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.UrlMapping.*;

@RestController
@RequestMapping(CONSULTATION)
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    @GetMapping
    public List<ConsultationDTO> findAll() {
        return consultationService.findAll();
    }

    @PostMapping
    public ConsultationDTO create(@RequestBody ConsultationDTO consultation) {
        return consultationService.create(consultation);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable Long id) {
        consultationService.delete(id);
    }
    
    @PutMapping(ENTITY)
    public ConsultationDTO edit(@PathVariable Long id, @RequestBody ConsultationDTO consultation) {return  consultationService.edit(id,consultation);}
}
