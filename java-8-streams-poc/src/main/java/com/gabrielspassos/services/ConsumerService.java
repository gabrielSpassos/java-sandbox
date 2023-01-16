package com.gabrielspassos.services;

import com.gabrielspassos.dtos.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;

public class ConsumerService {

    public void testConsumers() {
        List<Person> people = new ArrayList<Person>() {{
            add(new Person(1L, "Joao", 25, 1.83, 85.5, 51987654321L, 11912345678L));
        }};

        people.forEach(person -> {
            printId().accept(person.getId());
            printName().accept(person.getName());
            printAge().accept(person.getAge());
            System.out.println("Height: " + String.format("%,.2f", person.getHeight()));
            printWeight().accept(person.getWeight());
            printIMC().accept(person.getHeight(), person.getWeight());
            printContactNumbers().accept(person.getPersonalNumber(), person.getProfessionalNumber());
            printNextAge().accept(person.getName(), person.getAge());
            printHeightAsFeet().accept(person.getName(), person.getHeight());
        });
    }

    private LongConsumer printId() {
        return id -> System.out.println("Id: " + id);
    }

    private Consumer<String> printName() {
        return name -> System.out.println("Name: " + name);
    }

    private IntConsumer printAge() {
        return age -> System.out.println("Age: " + age);
    }

    private DoubleConsumer printWeight() {
        return weight -> System.out.println("Weight: " + String.format("%,.2f", weight));
    }

    private BiConsumer<Double, Double> printIMC() {
        return (height, weight) -> {
            double imc = weight / (height * height);
            System.out.println("IMC: " + String.format("%,.2f", imc));
        };
    }

    private ObjDoubleConsumer<String> printHeightAsFeet() {
        return (name, heightOnMeters) -> {
            double heightOnFeet = heightOnMeters * 3.281;
            System.out.println(name + " weight on feet: " + String.format("%,.2f", heightOnFeet));
        };
    }

    private ObjIntConsumer<String> printNextAge() {
        return (name, currentAge) -> {
            Integer nextAge = currentAge + 1;
            System.out.println(name + " will be with " + nextAge + " next year");
        };
    }

    private ObjLongConsumer<Long> printContactNumbers() {
        return (personalNumber, professionalNumber) -> {
            System.out.println("Personal number: " + personalNumber);
            System.out.println("ProfessionalNumber: " + professionalNumber);
        };
    }
}
