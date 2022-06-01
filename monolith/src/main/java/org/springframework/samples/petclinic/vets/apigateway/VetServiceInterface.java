package org.springframework.samples.petclinic.vets.apigateway;

import org.springframework.samples.petclinic.vets.VetDto;

import java.util.List;

public interface VetServiceInterface {

    List<VetDto> allVets();

}
