package org.springframework.samples.petclinic.vets.apigateway;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.samples.petclinic.vets.vets.VetDto;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class VetMicroServiceAdapterTest {

    private final WireMockServer wireMock = new WireMockServer(options().port(8089));
    private VetMicroServiceAdapter vetMicroServiceAdapter;

    @BeforeEach
    void startWireMock() {
        wireMock.start();
    }

    @BeforeEach
    void init() {
        this.vetMicroServiceAdapter = new VetMicroServiceAdapter("http://localhost:8089");
    }

    @AfterEach
    void stopWireMock() {
        wireMock.stop();
    }

    @Test
    void testAllVets() {
        wireMock.stubFor(get(urlEqualTo("/api/vets"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\":1,\"specialties\":[],\"firstName\":\"first\",\"lastName\":\"last\",\"nrOfSpecialties\":0}]")));


        List<VetDto> receivedVets = vetMicroServiceAdapter.allVets();


        List<VetDto> expectedVets = new ArrayList<>();

        VetDto expectedVetDto = new VetDto();
        expectedVetDto.setFirstName("first");
        expectedVetDto.setLastName("last");
        expectedVetDto.setId(1);
        expectedVetDto.setSpecialties(new ArrayList<>());
        expectedVets.add(expectedVetDto);

        assertEquals(expectedVets, receivedVets);
    }

}