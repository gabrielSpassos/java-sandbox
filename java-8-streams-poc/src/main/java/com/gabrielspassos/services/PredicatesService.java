package com.gabrielspassos.services;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicatesService {

    public void testPredicates() {
        List<String> strings = Arrays.asList("a", "", "c", null, "e", "f");
        List<String> validStrings = strings.stream()
                .filter(isNotNullOrEmpty())
                .collect(Collectors.toList());
        System.out.println("Valid strings: " + validStrings);

        List<Integer> integers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> evenNumbers = integers.stream()
                .filter(value -> isEven().test(value))
                .collect(Collectors.toList());
        System.out.println("Even numbers: " + evenNumbers);

        List<Double> doubles = Arrays.asList(5d, 34d, 99d, 100d, 132d, 434d);
        List<Double> doublesBiggerThanOneHundred = doubles.stream()
                .filter(value -> isBiggerThanOneHundred().test(value))
                .collect(Collectors.toList());
        System.out.println("Numbers bigger than 100: " + doublesBiggerThanOneHundred);

        List<Long> longs = Arrays.asList(5L, 2147483647L, 2147483648L, 774343254312L);
        List<Long> longNumbers = longs.stream()
                .filter(value -> isNotIntegerParsable().test(value))
                .collect(Collectors.toList());
        System.out.println("Long numbers that is not integer parsable: " + longNumbers);
    }

    private Predicate<String> isNotNullOrEmpty() {
        return string -> Objects.nonNull(string) && !"".equals(string);
    }

    private IntPredicate isEven() {
        return number -> number % 2 == 0;
    }

    private DoublePredicate isBiggerThanOneHundred() {
        return number -> number > 100;
    }

    private LongPredicate isNotIntegerParsable() {
        return number -> {
          try {
              Integer.parseInt(Long.toString(number));
              return false;
          } catch (Exception e) {
              return true;
          }
        };
    }
}
