package org.springframework.samples.petclinic.vets;

import org.springframework.samples.petclinic.db.OwnerRepository;
import org.springframework.samples.petclinic.db.PetRepository;
import org.springframework.samples.petclinic.db.RevenueRepository;
import org.springframework.samples.petclinic.db.VisitRepository;
import org.springframework.samples.petclinic.model.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class VetService {

    private final VetRepository vets;

    public VetService(
        VetRepository vets
    ) {
        this.vets = vets;
    }

    public Collection<Vet> allVets() {
        return this.vets.findAll();
    }
}
