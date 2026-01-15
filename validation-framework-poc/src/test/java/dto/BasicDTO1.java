package dto;

import java.util.Objects;

public class BasicDTO1 {

    private String name;
    private int age;

    public BasicDTO1() {
    }

    public BasicDTO1(String name, int age) {
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BasicDTO1 basicDTO1 = (BasicDTO1) o;
        return age == basicDTO1.age && Objects.equals(name, basicDTO1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "BasicDTO1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
