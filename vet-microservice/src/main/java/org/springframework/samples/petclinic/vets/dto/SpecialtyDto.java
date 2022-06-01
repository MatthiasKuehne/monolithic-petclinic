package org.springframework.samples.petclinic.vets.dto;

import java.io.Serializable;

public class SpecialtyDto implements Serializable {

    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
