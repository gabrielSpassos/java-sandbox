package com.gabrielspassos;

public class UnsafeCounter extends Counter {

    @Override
    public void increment() {
        var value = this.getValue();
        var newValue = value + 1;
        this.setValue(newValue);
    }

}
