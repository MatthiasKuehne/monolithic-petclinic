package org.springframework.samples.petclinic.vets;

import java.util.Objects;

public class SpecialtyDto {

    private String name;

    public SpecialtyDto() {
    }

    public SpecialtyDto(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecialtyDto that = (SpecialtyDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
