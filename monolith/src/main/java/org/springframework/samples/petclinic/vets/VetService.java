package org.springframework.samples.petclinic.vets;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VetService {

    private final VetRepository vets;

    public VetService(VetRepository vets) {
        this.vets = vets;
    }

    public List<VetDto> allVets() {
        Collection<Vet> vets = this.vets.findAll();

        return vets.stream().map(this::toVetDto)
                .sorted(Comparator.comparing(VetDto::getLastName)
                    .thenComparing(VetDto::getFirstName)
                    .thenComparing(VetDto::getId))
                .collect(Collectors.toList());
    }


    private VetDto toVetDto(Vet source) {
        VetDto vetDto = new VetDto();

        vetDto.setId(source.getId());
        vetDto.setFirstName(source.getFirstName());
        vetDto.setLastName(source.getLastName());
        vetDto.setSpecialties(source.getSpecialties().stream()
                .map(this::toSpecialtyDto)
                .collect(Collectors.toList()));
        return vetDto;
    }

    private SpecialtyDto toSpecialtyDto(Specialty source) {
        SpecialtyDto specialtyDto = new SpecialtyDto();

        specialtyDto.setName(source.getName());

        return specialtyDto;
    }
}
