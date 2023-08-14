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

| # of conversions | Reflections without Cache Converter | Reflections with Cache Converter | In Memory Converter | Unsafe Converter  |
|:----------------:|:-----------------------------------:|:--------------------------------:|:-------------------:|:-----------------:|
|        1         |                  3                  |                0                 |         283         |         0         |
|        10        |                  1                  |                1                 |          0          |         1         |
|       100        |                  4                  |                3                 |          1          |         1         |
|        1K        |                 15                  |                8                 |          4          |         5         |
|       10K        |                 108                 |                74                |         18          |        42         |
|        1M        |           2915 - (2,915s)           |         2399 - (2,399s)          |   1071 - (1,071s)   |  1294 - (1,294s)  |
|       10M        |          27901 - (27,901s)          |        23726 - (23,726s)         |  10659 - (10,659s)  | 13144 - (13,144s) |
|       30M        |          84656 - (1,41min)          |        71791  - (1,19min)        |  32297 - (32,297s)  | 39234 - (39,234s) |

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
- [X] implement converter with java unsafe
  - (https://howtodoinjava.com/java-examples/usage-of-class-sun-misc-unsafe/)
  - (https://stackoverflow.com/questions/54142600/defineclass-method-throws-java-lang-classformaterror-incompatible-magic-value-1)

- https://www.baeldung.com/java-string-compile-execute-code
- https://www.javassist.org/
