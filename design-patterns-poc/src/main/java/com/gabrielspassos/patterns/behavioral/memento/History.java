package com.gabrielspassos.patterns.behavioral.memento;

import java.util.Stack;

public class History {

    private final Stack<EditorMemento> history = new Stack<>();

    public boolean push(EditorMemento memento) {
        history.push(memento);
        return true;
    }

    public EditorMemento pop() {
        return history.pop();
    }
}
