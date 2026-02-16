package com.gabrielspassos.poc.controller.response;

import java.util.Objects;

public class CalculatorResponse {

    private int result;

    public CalculatorResponse() {
    }

    public CalculatorResponse(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalculatorResponse that = (CalculatorResponse) o;
        return result == that.result;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(result);
    }

    @Override
    public String toString() {
        return "CalculatorResponse{" +
                "result=" + result +
                '}';
    }
}
