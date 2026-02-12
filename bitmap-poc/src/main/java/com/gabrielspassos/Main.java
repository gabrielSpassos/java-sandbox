package com.gabrielspassos;

public class Main {
    static void main(String[] args) {
        IO.println("BitMap POC!");

        BitMap bm = new BitMap(128);

        bm.set(3);
        bm.set(7);
        bm.set(64);
        bm.toggle(3);

        System.out.println(bm.get(3));
        System.out.println(bm.get(7));
        System.out.println(bm.get(64));
        System.out.println(bm.get(70));
        System.out.println(bm.cardinality());

        bm.forEachSetBit(System.out::println);

        bm.clear(7);
        System.out.println(bm.get(7));
    }
}
