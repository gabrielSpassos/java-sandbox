package com.gabrielspassos.patterns.behavioral.memento;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MementoTest {

    @Test
    void shouldCreateSnapshot() {
        Editor editor = new Editor();
        History history = new History();

        assertTrue(editor.write("textA"));
        assertTrue(editor.write("textB"));
        assertEquals("textAtextB", editor.getContent());

        EditorMemento memento = editor.save();
        assertNotNull(memento);
        assertEquals("textAtextB", memento.getContent());

        assertTrue(history.push(memento));

        EditorMemento mementoPop = history.pop();
        assertNotNull(mementoPop);
        assertEquals(memento.getContent(), mementoPop.getContent());
    }

    @Test
    void shouldCreateMultipleSnapshot() {
        Editor editor = new Editor();
        History history = new History();

        editor.write("textA");
        editor.write("textB");
        assertEquals("textAtextB", editor.getContent());

        EditorMemento memento1 = editor.save();
        assertNotNull(memento1);
        assertEquals("textAtextB", memento1.getContent());
        assertTrue(history.push(memento1));

        editor.write("textC");
        assertEquals("textAtextBtextC", editor.getContent());

        EditorMemento memento2 = editor.save();
        assertNotNull(memento2);
        assertEquals("textAtextBtextC", memento2.getContent());
        assertTrue(history.push(memento2));

        EditorMemento memento2pop = history.pop();
        assertEquals("textAtextBtextC", memento2pop.getContent());
        EditorMemento memento1pop = history.pop();
        assertEquals("textAtextB", memento1pop.getContent());
    }
}