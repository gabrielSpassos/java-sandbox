package com.gabrielspassos.poc.loaders;

public interface InMemoryConverterClass<I, O> {

    O convert(I input);

}
