package com.gabrielspassos.poc.memory.loaders;

public interface InMemoryConverterClass<I, O> {

    O convert(I input);

}
