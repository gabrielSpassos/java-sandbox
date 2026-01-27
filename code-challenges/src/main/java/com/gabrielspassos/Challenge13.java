package com.gabrielspassos;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/*
Create a function that can return a list of numbers from 1 to 100. For multiples of three return “Fizz” instead of the number and for the multiples of five return “Buzz”. For numbers which are multiples of both three and five return “FizzBuzz”.

fizzbuzz() -> [1,2,"Fizz",4,"Buzz","Fizz",7,8,"Fizz","Buzz",11,"Fizz",13,14,"FizzBuzz",16,17,"Fizz",19,"Buzz","Fizz",22,23,"Fizz","Buzz",26,"Fizz",28,29,"FizzBuzz",31,32,"Fizz",34,"Buzz","Fizz",37,38,"Fizz","Buzz",41,"Fizz",43,44,"FizzBuzz",46,47,"Fizz",49,"Buzz","Fizz",52,53,"Fizz","Buzz",56,"Fizz",58,59,"FizzBuzz",61,62,"Fizz",64,"Buzz","Fizz",67,68,"Fizz","Buzz",71,"Fizz",73,74,"FizzBuzz",76,77,"Fizz",79,"Buzz","Fizz",82,83,"Fizz","Buzz",86,"Fizz",88,89,"FizzBuzz",91,92,"Fizz",94,"Buzz","Fizz",97,98,"Fizz","Buzz"]

Refactor the code to receive by parameter how many numbers you want to generate.

fizzbuzz(10) -> [1,2,"Fizz",4,"Buzz","Fizz",7,8,"Fizz","Buzz"]
*/
public class Challenge13 {

    private static final Integer DEFAULT_LIMIT = 100;

    public static List<Object> fizzbuzz() {
        return fizzbuzz(DEFAULT_LIMIT);
    }

    public static List<Object> fizzbuzz(Integer limit) {
        var result = new ArrayList<>();

        for (int i = 1; i <= limit ; i++) {
            Predicate<Integer> multipleOf3 = (x) -> x % 3 == 0;
            Predicate<Integer> multipleOf5 = (x) -> x % 5 == 0;

            var isMultipleOf3 = multipleOf3.test(i);
            var isMultipleOf5 = multipleOf5.test(i);

            if (isMultipleOf3 && isMultipleOf5){
                result.add("FizzBuzz");
            } else if (isMultipleOf3) {
                result.add("Fizz");
            } else if (isMultipleOf5) {
                result.add("Buzz");
            } else {
                result.add(i);
            }
        }

        return result;
    }


}
