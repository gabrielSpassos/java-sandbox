package com.gabrielspassos.poc.controller.request;

import java.util.Objects;

public class CalculatorRequest {

    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalculatorRequest that = (CalculatorRequest) o;
        return a == that.a && b == that.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "CalculatorRequest{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }
}
