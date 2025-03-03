package org.springframework.samples.petclinic.vets;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VetServiceTest {

    @Autowired
    VetService service;

    @Test
    void shouldFindVets() {
        Collection<VetDto> vets = service.allVets();

        Assertions.assertThat(vets)
                .filteredOn(vet -> vet.getId() == 3)
                .hasSize(1)
                .first()
                .hasFieldOrPropertyWithValue("lastName", "Douglas")
                .hasFieldOrPropertyWithValue("nrOfSpecialties", 2)
                .extracting(VetDto::getSpecialties).asList()
                .extracting("name")
                .containsExactly("dentistry", "surgery");
    }
}