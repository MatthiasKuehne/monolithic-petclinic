package org.springframework.samples.petclinic.apigateway;

import org.springframework.samples.petclinic.vets.VetDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class VetMicroServiceAdapter {

    private final RestTemplate restTemplate;

    private String baseUrl = "http://localhost:8081";

    public VetMicroServiceAdapter() {
        restTemplate = new RestTemplate();
    }

    public VetMicroServiceAdapter(String baseUrl) {
        restTemplate = new RestTemplate();
        this.baseUrl = baseUrl;
    }

    public List<VetDto> allVets() {
        VetDto[] vets = restTemplate
                .getForObject(baseUrl + "/api/vets", VetDto[].class);

        return Arrays.asList(vets);
    }

}
