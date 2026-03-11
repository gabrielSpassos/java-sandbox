package com.gabrielspassos.patterns.behavioral.memento;

public class Editor {

    private String content;

    public boolean write(String text) {
        content = null == content ? text : content + text;
        return true;
    }

    public String getContent() {
        return content;
    }

    public EditorMemento save() {
        return new EditorMemento(content);
    }

    public boolean restore(EditorMemento memento) {
        content = memento.getContent();
        return true;
    }
}
