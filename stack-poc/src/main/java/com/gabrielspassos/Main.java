package com.gabrielspassos;

public class Main {
    static void main() {
        IO.println("Stack POC!");

        var stack = new Stack<String>();
        stack.push("foo");
        stack.push("gabriel");

        IO.println(stack.pop());
        IO.println(stack.pop());
    }
}
