package com.gabrielspassos.patterns.creational.builder;

import java.util.Objects;

public class PersonDTO {

    private final String name;
    private final Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    private PersonDTO(PersonDTOBuilder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(name, personDTO.name) && Objects.equals(age, personDTO.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static class PersonDTOBuilder {
        private String name;
        private Integer age;

        public static PersonDTOBuilder builder() {
            return new PersonDTOBuilder();
        }

        public PersonDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public PersonDTOBuilder withAge(Integer age) {
            this.age = age;
            return this;
        }

        public PersonDTO build() {
            return new PersonDTO(this);
        }
    }
}
