package com.example.patient;

import com.example.BaseControllerTest;
import com.example.TestCreationFactory;
import com.example.demo.patient.PatientController;
import com.example.demo.patient.PatientService;
import com.example.demo.patient.model.dto.PatientDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.List;

import static com.example.TestCreationFactory.*;
import static com.example.demo.UrlMapping.ENTITY;
import static com.example.demo.UrlMapping.PATIENT;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PatientControllerTest extends BaseControllerTest {

    @InjectMocks
    private PatientController controller;

    @Mock
    private PatientService patientService;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        MockitoAnnotations.openMocks(this);
        controller = new PatientController(patientService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void allPatients() throws Exception{
        List<PatientDTO> patientDTOList = TestCreationFactory.listOf(PatientDTO.class);
        when(patientService.findAll()).thenReturn(patientDTOList);
        ResultActions result = mockMvc.perform(get(PATIENT));
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTOList));
    }

    @Test
    void create() throws Exception{
        PatientDTO patientDTO = PatientDTO.builder()
                .id(randomLong())
                .name(randomString())
                .cardNumber(randomLong())
                .cnp(randomString())
                .birthDate(Date.valueOf("1999-09-09"))
                .address(randomString())
                .build();
        when(patientService.create(patientDTO)).thenReturn(patientDTO);
        ResultActions result = performPostWithRequestBody(PATIENT, patientDTO);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));
    }

    @Test
    void edit() throws Exception
    {
        PatientDTO patientDTO = PatientDTO.builder()
                .id(randomLong())
                .name(randomString())
                .cardNumber(randomLong())
                .cnp(randomString())
                .birthDate(Date.valueOf("1999-09-09"))
                .address(randomString())
                .build();
        when(patientService.edit(patientDTO.getId(), patientDTO)).thenReturn(patientDTO);
        ResultActions result = performPutWithRequestBodyAndPathVariable(PATIENT + ENTITY,patientDTO, patientDTO.getId());
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(patientDTO));
    }

    @Test
    void delete() throws Exception{
        PatientDTO patientDTO = PatientDTO.builder()
                .id(randomLong())
                .name(randomString())
                .cardNumber(randomLong())
                .cnp(randomString())
                .birthDate(Date.valueOf("1999-09-09"))
                .address(randomString())
                .build();
        ResultActions result = performDeleteWIthPathVariable(PATIENT+ENTITY, patientDTO.getId().toString());
        result.andExpect(status().isOk());
    }
}
