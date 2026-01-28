package com.gabrielspassos.challenge15;

import java.util.*;
import java.util.stream.Collectors;

/*
Create a class that can represent a person. The person should have a name, age and a list of friends.

person = new Person("John", 30)
person.addFriend("Paul")
person.addFriend("George")
person.addFriend("Ringo")

The person should have a method that can return the name of the person and the list of friends.

person.getFriends() -> ["Paul", "George", "Ringo"]

The person should have a method that can return the age.

person.getAge() -> 30

The person should have a method that can return the name of the person.

person.getName() -> "John"

Refactoring time:

    Now could you refactor the code and move the list of friends to a separate class?
    Refactor the code so you dont allow the same friend to be added twice.
    Refactor the code so you can remove a friend from the list.

More Refactoring:

    In one of your classes, could you create a method that tell who is the person with more friends?
    In one of your classes, could you create a method that tell who is the person with less friends?
    In one of your classes, could you create a method that tell who is the person with the oldest friend?

*/
public class Person {

    private String name;
    private Integer age;
    private Set<Person> friends;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.friends = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getFriendsNames() {
        return friends.stream().map(Person::getName).collect(Collectors.toList());
    }

    public Set<Person> getFriends() {
        return friends;
    }

    public Boolean addFriend(Person friend) {
        return friends.add(friend);
    }

    public Boolean removeFriend(Person friend) {
        return friends.remove(friend);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name)
                && Objects.equals(age, person.age)
                && Objects.equals(friends, person.friends);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, friends);
    }

}
