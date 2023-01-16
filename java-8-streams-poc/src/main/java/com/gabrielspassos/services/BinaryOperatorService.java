package com.gabrielspassos.services;

import com.gabrielspassos.dtos.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

public class BinaryOperatorService {

    public void testBinaryOperator() {
        BiFunction<Integer, Integer, Integer> function = (x, y) -> x * y;
        Integer functionResult = function.apply(2, 7);
        System.out.println("BiFunction result: " + functionResult);

        BinaryOperator<Integer> operator = (x, y) -> x * y;
        Integer operatorResult = operator.apply(2, 7);
        System.out.println("BinaryOperator result: " + operatorResult);

        IntBinaryOperator divide = divide();
        int powResult = divide.applyAsInt(10, 5);
        System.out.println("IntBinaryOperator result " + powResult);

        Person person1 = new Person(1L,
                "Joao",
                25,
                1.83,
                85.5,
                51987654321L,
                11912345678L);

        Person person2 = new Person(2L,
                "Maria",
                22,
                1.67,
                52.4,
                51994344342L,
                51954346887L);

        Person person3 = new Person(3L,
                "Felipe",
                44,
                1.97,
                120.4,
                6927568740L,
                69984558822L);
        List<Person> people = Arrays.asList(person1, person2, person3);

        Comparator<Person> comparatorHeight = Comparator.comparing(Person::getHeight);
        BinaryOperator<Person> personMaxHeight = BinaryOperator.maxBy(comparatorHeight);
        BinaryOperator<Person> personMinHeight = BinaryOperator.minBy(comparatorHeight);
        System.out.println("Max Height: " + findPerson(people, personMaxHeight));
        System.out.println("Min Height: " + findPerson(people, personMinHeight));
    }

    private IntBinaryOperator divide() {
        return (x, y) -> x / y;
    }

    private Person findPerson(List<Person> people, BinaryOperator<Person> operator) {
        Person result = null;
        for (Person p : people) {
            if (result == null) {
                result = p;
            } else {
                result = operator.apply(result, p);
            }
        }
        return result;
    }
}
