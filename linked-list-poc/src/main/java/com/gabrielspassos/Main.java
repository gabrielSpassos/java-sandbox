package com.gabrielspassos;


import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>() {{
            add(1);
            add(2);
            add(3);
        }};
        System.out.println(linkedList);

        linkedList.addFirst(4);
        System.out.println("Last " + linkedList.getLast());
        linkedList.add(5);
        linkedList.addLast(6);
        System.out.println("First " + linkedList.getFirst());

        System.out.println(linkedList);

        Integer first = linkedList.removeFirst();
        System.out.println("Removed first " + first);

        Integer last = linkedList.removeLast();
        System.out.println("Removed last " + last);

        System.out.println(linkedList);
    }

}