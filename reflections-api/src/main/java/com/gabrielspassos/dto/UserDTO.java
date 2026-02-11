package com.gabrielspassos.dto;

import java.util.Objects;

public class UserDTO {

    private String name;
    private int age;

    public UserDTO() {
        this("Unknow", 0);
    }

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isAdult() {
        return this.age >= 18;
    }

    public String greet(String prefix) {
        return String.format("%s, %s", prefix, this.name);
    }

    public boolean canDrink(String country, int minAge) {
        if ("Brazil".equalsIgnoreCase(country)) {
            return isAdult();
        }

        return this.age >= minAge;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return age == userDTO.age && Objects.equals(name, userDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
