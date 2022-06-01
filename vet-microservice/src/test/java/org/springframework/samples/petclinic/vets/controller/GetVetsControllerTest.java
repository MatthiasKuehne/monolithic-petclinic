package org.springframework.samples.petclinic.vets.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.vets.dto.VetDto;
import org.springframework.samples.petclinic.vets.service.VetService;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GetVetsController.class)
class GetVetsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VetService vetService;

    @Test
    void testGetVets() throws Exception {
        List<VetDto> expectedVets = new ArrayList<>();

        VetDto expectedVetDto = new VetDto();
        expectedVetDto.setFirstName("first");
        expectedVetDto.setLastName("last");
        expectedVetDto.setId(1);
        expectedVetDto.setSpecialties(new ArrayList<>());
        expectedVets.add(expectedVetDto);

        doReturn(expectedVets).when(vetService).allVets();

        String expectedJson = "[{\"id\":1,\"specialties\":[],\"firstName\":\"first\",\"lastName\":\"last\",\"nrOfSpecialties\":0}]";


        mockMvc.perform(get("/api/vets"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

}