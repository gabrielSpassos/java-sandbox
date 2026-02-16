package com.gabrielspassos;

import java.util.concurrent.locks.ReentrantLock;

public class LockCounter extends Counter {

    private final ReentrantLock lock = new ReentrantLock();

    @Override
    public void increment() {
        lock.lock();
        try {
            var value = this.getValue();
            this.setValue(value + 1);
        } finally {
            lock.unlock();
        }
    }

}
