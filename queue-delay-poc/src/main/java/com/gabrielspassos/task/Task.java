package com.gabrielspassos.task;

public interface Task<T> {

    boolean execute(T input);

}
