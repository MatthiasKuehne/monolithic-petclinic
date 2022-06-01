/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.apigateway.VetMicroServiceAdapter;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@SpringBootTest
@AutoConfigureMockMvc
class VetControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    VetMicroServiceAdapter vetMicroServiceAdapter;

    @BeforeEach
    void mockAdapter() {
        List<VetDto> expectedVets = new ArrayList<>();

        VetDto expectedVetDto = new VetDto();
        expectedVetDto.setFirstName("James");
        expectedVetDto.setLastName("Carter");
        expectedVetDto.setId(1);
        expectedVetDto.setSpecialties(new ArrayList<>());
        expectedVets.add(expectedVetDto);

        VetDto expectedVetDto2 = new VetDto();
        expectedVetDto2.setFirstName("Linda");
        expectedVetDto2.setLastName("Douglas");
        expectedVetDto2.setId(1);
        expectedVetDto2.setSpecialties(Arrays.asList(new SpecialtyDto[]{new SpecialtyDto("dentistry"), new SpecialtyDto("surgery")}));
        expectedVets.add(expectedVetDto2);

        VetDto expectedVetDto2x = new VetDto();
        expectedVetDto2x.setFirstName("Helen");
        expectedVetDto2x.setLastName("E");
        expectedVetDto2x.setId(1);
        expectedVetDto2x.setSpecialties(new ArrayList<>());
        expectedVets.add(expectedVetDto2x);

        VetDto expectedVetDto3 = new VetDto();
        expectedVetDto3.setFirstName("Helen");
        expectedVetDto3.setLastName("Leary");
        expectedVetDto3.setId(1);
        expectedVetDto3.setSpecialties(new ArrayList<>());
        expectedVets.add(expectedVetDto3);


        doReturn(expectedVets).when(vetMicroServiceAdapter).allVets();
    }

    @Test
    void testShowVetListHtml() throws Exception {
        mockMvc.perform(get("/vets"))
            .andExpect(status().isOk())
            .andExpect(xpath("//table[@id='vets']").exists())
            .andExpect(xpath("//table[@id='vets']/tbody/tr").nodeCount(4))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=1]/td[position()=1]").string("James Carter"))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=1]/td[position()=2]/span").string("none"))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=2]/td[position()=1]").string("Linda Douglas"))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=2]/td[position()=2]/span").nodeCount(2))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=2]/td[position()=2]/span[position()=1]").string("dentistry "))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=2]/td[position()=2]/span[position()=2]").string("surgery "))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=4]/td[position()=1]").string("Helen Leary"))
            .andExpect(xpath("//table[@id='vets']/tbody/tr[position()=4]/td[position()=2]/span").string("radiology "))

        ;
    }

}
