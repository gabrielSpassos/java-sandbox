package com.gabrielspassos;

import com.gabrielspassos.queue.LinkedQueue;

import java.util.Arrays;
import java.util.Optional;

public class Main {
    static void main() {
        IO.println("Queue POC!");

        var queue = new LinkedQueue<Integer>();

        var values = Arrays.asList(1, 2, 3);

        values.forEach(value -> {
            IO.println("Enqueuing value " + value);
            queue.enqueue(value);
        });
        IO.println("Queue Size: " + queue.size());

        var dequeue1 = queue.dequeue();
        var dequeue2 = queue.dequeue();
        var dequeue3 = queue.dequeue();
        var dequeue4 = queue.dequeue();
        logDequeue(dequeue1);
        IO.println("Queue Size: " + queue.size());
        logDequeue(dequeue2);
        IO.println("Queue Size: " + queue.size());
        logDequeue(dequeue3);
        IO.println("Queue Size: " + queue.size());
        logDequeue(dequeue4);
        IO.println("Queue Size: " + queue.size());
    }

    private static void logDequeue(Optional<Integer> dequeue) {
        var isPresent = dequeue.isPresent();
        IO.print("Is dequeue present? " + isPresent);
        if (isPresent) {
            IO.print(" value: " + dequeue.get());
        }
        IO.println();
    }
}
