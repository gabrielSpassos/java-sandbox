package com.gabrielspassos.poc.dto;

import com.gabrielspassos.poc.common.AttributeSynonym;

public class PersonDTO {

    @AttributeSynonym(synonyms = {"species", "firstName"})
    private String name;

    private Integer age;

    public PersonDTO() {
    }

    public PersonDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
