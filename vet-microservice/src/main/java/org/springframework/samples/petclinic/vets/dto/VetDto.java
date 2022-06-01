package org.springframework.samples.petclinic.vets.dto;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

public class VetDto implements Serializable {

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

}
