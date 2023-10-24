package org.gabrielspassos.dto;

import java.time.LocalDate;

public class PersonDTO {

    private String name;

    private LocalDate birthdate;

    public PersonDTO() {
    }

    public PersonDTO(String name, LocalDate birthdate) {
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
