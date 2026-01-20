package com.gabrielspassos.dto;

import com.gabrielspassos.annotations.NotEmpty;

import java.util.List;
import java.util.Objects;

public class BasicDTO2 {

    @NotEmpty
    private String a;

    @NotEmpty
    private List<Double> b;

    @NotEmpty
    private String c;

    public BasicDTO2() {
    }

    public BasicDTO2(String a, List<Double> b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public List<Double> getB() {
        return b;
    }

    public void setB(List<Double> b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BasicDTO2 basicDTO2 = (BasicDTO2) o;
        return Objects.equals(a, basicDTO2.a) && Objects.equals(b, basicDTO2.b) && Objects.equals(c, basicDTO2.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "BasicDTO2{" +
                "a='" + a + '\'' +
                ", b=" + b +
                ", c='" + c + '\'' +
                '}';
    }
}
