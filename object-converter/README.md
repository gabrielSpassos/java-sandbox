# Objects converter

- This project creates an util class that is capable to convert an object to a other object, using as param the instance of object and the destiny object class

### Usage

* Create a [singleton](https://refactoring.guru/design-patterns/singleton) `ObjectConverterMapper` instance

```java
ObjectConverterMapper objectConverterMapper = ObjectConverterMapper.getObjectConverterMapper();
```

* Convert an `originObject` (class `OriginClassDTO`) to other `convertedObject` (class `DestinyClassDTO`)

```java
// any a instance of OriginClassDTO
OriginClassDTO originObject = new OriginClassDTO();

// instance of DestinyClassDTO with all common attributes names of two classes filled
DestinyClassDTO convertedObject = objectConverterMapper.convert(originObject, DestinyClassDTO.class);
```

* Change ObjectConverterMapper to **not** use cache. _Obs:_ the **default** is to use the cache.
_Obs 2:_ this feature is based on [strategy](https://refactoring.guru/design-patterns/strategy/java/example) pattern

```java
objectConverterMapper.setShouldCacheClassInfo(Boolean.FALSE);
```

* Map attribute with different names you can you the annotation `AttributeSynonym`.

```java
// normal POJO and the attribute name has is a synonym of `species` and `firstName`
public class PersonDTO { 
    
    @AttributeSynonym(synonyms = {"species", "firstName"}) 
    private String name;
    
    // getters and setters
}

/*
normal POJO that extends `PersonDTO`. So in this case `name` and `firstName`
will be converted with the same value, when you use the code:
EmployeeDTO employee = objectConverterMapper.convert(personDTO, EmployeeDTO.class)
*/
public class EmployeeDTO extends PersonDTO {

    private String firstName;

    // getters and setters
}

/*
normal POJO that has the attribute name `species`.
When convert the attribute `species` will have the same value of the `name` from person.
AnimalDTO animal = objectConverterMapper.convert(personDTO, AnimalDTO.class)
*/
public class AnimalDTO {

    private String species;

    // getters and setters
}
```

### Comparing Solutions

| # of conversions | Reflections without Cache Converter | Reflections with Cache Converter | In Memory Converter |
|:----------------:|:-----------------------------------:|:--------------------------------:|:-------------------:|
|        1         |                  2                  |                0                 |         256         |
|        10        |                  1                  |                1                 |          1          |
|       100        |                  5                  |                2                 |          2          |
|        1K        |                 15                  |                10                |          4          |
|       10K        |                 99                  |                71                |         19          |
|        1M        |                2907                 |               2361               |        1082         |
|       10M        |                28248                |              23670               |        10791        |
|       30M        |                84626                |              71669               |        33195        |

_Obs: the numbers on the solutions columns are the time spent to complete the all the conversions in milliseconds (ms)_

### What is pending on this project

- [X] Rename to ObjectConverterMapper
- [X] Include cache on the getFieldMethod
- [X] Add annotation to set fields based on attributeName + annotation
- [X] performance test with more conversions
- [X] create class with cache
- [X] load class in memory
- [X] use class that is already on the class loader
- [X] in memory solution first check if class (converter) exists, if not create it
- [X] in memory solution create map destiny class -> converter
- [X] segregate solutions
- [X] segregate unit tests
- [X] move performance test to be on the same test
- [X] test performance with scenario (loop with 1, 1000, 10000, 10000000)
- [X] create performance analysis output
- [X] redo reflections logic to try use bubble sort complexity
- [X] two conversions inside each iteration
- [X] three asserts inside each iteration
- [X] complete separate objects
- [ ] implement converter with java unsafe

- https://www.baeldung.com/java-string-compile-execute-code
- https://www.javassist.org/
