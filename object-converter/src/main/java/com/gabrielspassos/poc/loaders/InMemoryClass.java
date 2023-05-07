package com.gabrielspassos.poc.loaders;

public interface InMemoryClass<I, O> {

    O convert(I input);

}
