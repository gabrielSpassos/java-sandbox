package com.gabrielspassos.dtos;

public class Person {

    private Long id;
    private String name;
    private Integer age;
    private Double height;
    private Double weight;
    private Long personalNumber;
    private Long professionalNumber;

    public Person(Long id, String name, Integer age, Double height, Double weight, Long personalNumber, Long professionalNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.personalNumber = personalNumber;
        this.professionalNumber = professionalNumber;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(Long personalNumber) {
        this.personalNumber = personalNumber;
    }

    public Long getProfessionalNumber() {
        return professionalNumber;
    }

    public void setProfessionalNumber(Long professionalNumber) {
        this.professionalNumber = professionalNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", personalNumber=" + personalNumber +
                ", professionalNumber=" + professionalNumber +
                '}';
    }
}
