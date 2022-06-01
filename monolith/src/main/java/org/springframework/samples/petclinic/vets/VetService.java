package org.springframework.samples.petclinic.vets;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class VetService {

    private final VetRepository vets;

    public VetService(VetRepository vets) {
        this.vets = vets;
    }

    public Collection<VetDto> allVets() {
        Collection<Vet> vets = this.vets.findAll();

        return vets.stream().map(this::toVetDto).collect(Collectors.toList());
    }


    private VetDto toVetDto(Vet source) {
        VetDto vetDto = new VetDto();

        vetDto.setId(source.getId());
        vetDto.setFirstName(source.getFirstName());
        vetDto.setLastName(source.getLastName());
        vetDto.setSpecialtiesInternal(source.getSpecialties().stream()
                .map(this::toSpecialtyDto)
                .collect(Collectors.toSet()));
        return vetDto;
    }

    private SpecialtyDto toSpecialtyDto(Specialty source) {
        SpecialtyDto specialtyDto = new SpecialtyDto();

        specialtyDto.setName(source.getName());

        return specialtyDto;
    }
}
