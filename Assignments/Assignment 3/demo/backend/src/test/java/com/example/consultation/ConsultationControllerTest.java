package com.example.consultation;

import com.example.BaseControllerTest;
import com.example.demo.consultation.ConsultationController;
import com.example.demo.consultation.ConsultationService;
import com.example.demo.consultation.model.dto.ConsultationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;

import static com.example.TestCreationFactory.*;
import static com.example.demo.UrlMapping.CONSULTATION;
import static com.example.demo.UrlMapping.ENTITY;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ConsultationControllerTest extends BaseControllerTest {

    @InjectMocks
    ConsultationController controller;

    @Mock
    private ConsultationService consultationService;

     @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new ConsultationController(consultationService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void create() throws Exception{
        ConsultationDTO consultationDTO = ConsultationDTO.builder()
                .id(randomLong())
                .text(randomString())
                .doctor_id(randomLong())
                .patient_id(randomLong())
                .date(Date.valueOf("1999-11-09"))
                .build();
        when(consultationService.create(consultationDTO)).thenReturn(consultationDTO);
        ResultActions result = performPostWithRequestBody(CONSULTATION, consultationDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDTO));
    }

    @Test
    void edit() throws Exception
    {
        ConsultationDTO consultationDTO = ConsultationDTO.builder()
                .id(randomLong())
                .text(randomString())
                .doctor_id(randomLong())
                .patient_id(randomLong())
                .date(Date.valueOf("1999-11-09"))
                .build();
        when(consultationService.edit(consultationDTO.getId(),consultationDTO)).thenReturn(consultationDTO);
        ResultActions result = performPutWithRequestBodyAndPathVariable(CONSULTATION + ENTITY, consultationDTO, consultationDTO.getId());
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(consultationDTO));
    }
    @Test
    void delete() throws Exception
    {
        ConsultationDTO consultationDTO = ConsultationDTO.builder()
                .id(randomLong())
                .text(randomString())
                .doctor_id(randomLong())
                .patient_id(randomLong())
                .date(Date.valueOf("1999-11-09"))
                .build();
        ResultActions result = performDeleteWIthPathVariable(CONSULTATION+ENTITY, consultationDTO.getId().toString());
        result.andExpect(status().isOk());
    }
}
