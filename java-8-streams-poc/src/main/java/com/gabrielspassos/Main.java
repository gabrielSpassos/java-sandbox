package com.gabrielspassos;

import com.gabrielspassos.services.BinaryOperatorService;
import com.gabrielspassos.services.ConsumerService;
import com.gabrielspassos.services.PredicatesService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Java 8 Streams POC!");
        System.out.println("Java Streams Consumer test");
        ConsumerService consumerService = new ConsumerService();
        consumerService.testConsumers();

        System.out.println("Java Streams Predicates test");
        PredicatesService predicatesService = new PredicatesService();
        predicatesService.testPredicates();

        System.out.println("Java Streams Binary Operator test");
        BinaryOperatorService binaryOperatorService = new BinaryOperatorService();
        binaryOperatorService.testBinaryOperator();
    }
}