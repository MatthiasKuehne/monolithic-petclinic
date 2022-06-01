package org.springframework.samples.petclinic.vets.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.vets.dto.VetDto;
import org.springframework.samples.petclinic.vets.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GetVetsController {

    private final VetService vetService;

    @Autowired
    public GetVetsController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/api/vets")
    public ResponseEntity<List<VetDto>> getVets() {
        return ResponseEntity.ok(this.vetService.allVets());
    }

}
