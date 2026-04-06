package com.gabrielspassos.queue;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedQueueTest {

    @Test
    void shouldEnqueue() {
        IQueue<Integer> queue = new LinkedQueue<>();

        assertTrue(queue.enqueue(1));
        assertTrue(queue.enqueue(2));
        assertTrue(queue.enqueue(3));
        assertEquals(3, queue.size());
    }

    @Test
    void shouldDequeue() {
        IQueue<Integer> queue = new LinkedQueue<>();

        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        assertEquals(3, queue.size());

        Optional<Integer> dequeue1 = queue.dequeue();
        assertTrue(dequeue1.isPresent());
        assertEquals(4, dequeue1.get());
        assertEquals(2, queue.size());

        Optional<Integer> dequeue2 = queue.dequeue();
        assertTrue(dequeue2.isPresent());
        assertEquals(5, dequeue2.get());
        assertEquals(1, queue.size());

        Optional<Integer> dequeue3 = queue.dequeue();
        assertTrue(dequeue3.isPresent());
        assertEquals(6, dequeue3.get());
        assertEquals(0, queue.size());
    }

    @Test
    void shouldDequeueEmpty() {
        IQueue<Integer> queue = new LinkedQueue<>();

        Optional<Integer> dequeue = queue.dequeue();
        assertTrue(dequeue.isEmpty());
        assertEquals(0, queue.size());
    }

}