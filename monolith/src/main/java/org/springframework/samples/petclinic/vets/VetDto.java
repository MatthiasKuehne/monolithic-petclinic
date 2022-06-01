package org.springframework.samples.petclinic.vets;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.validation.constraints.NotNull;
import java.util.*;

public class VetDto {

    private Integer id;
    @NotNull
    private List<SpecialtyDto> specialties;
    private String firstName;
    private String lastName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    protected List<SpecialtyDto> getSpecialtiesInternal() {
        return this.specialties;
    }

    public void setSpecialties(List<SpecialtyDto> specialties) {
        this.specialties = specialties;
    }

    public List<SpecialtyDto> getSpecialties() {
        List<SpecialtyDto> sortedSpecs = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecs, new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedSpecs);
    }

    public int getNrOfSpecialties() {
        return getSpecialtiesInternal().size();
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VetDto vetDto = (VetDto) o;
        return Objects.equals(id, vetDto.id) && Objects.equals(specialties, vetDto.specialties) && Objects.equals(firstName, vetDto.firstName) && Objects.equals(lastName, vetDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specialties, firstName, lastName);
    }
}
