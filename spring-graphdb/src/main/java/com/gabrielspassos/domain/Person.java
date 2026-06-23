package com.gabrielspassos.domain;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Node("Person")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer age;

    @Relationship(type = "KNOWS")
    private Set<Person> friends = new HashSet<>();

    @Relationship(type = "WORKS_AT")
    private Company company;

    @Relationship(type = "LIVES_IN")
    private City city;

    public Person() {
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public void addFriend(Person friend) {
        friends.add(friend);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Set<Person> getFriends() {
        return friends;
    }

    public Company getCompany() {
        return company;
    }

    public City getCity() {
        return city;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person other)) return false;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
