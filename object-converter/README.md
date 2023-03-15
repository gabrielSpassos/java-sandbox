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
```java
objectConverterMapper.setShouldCacheClassInfo(Boolean.FALSE);
```

### What is pending on this project

- [X] Rename to ObjectConverterMapper
- [X] Include cache on the getFieldMethod
- [X] Add annotation to set fields based on attributeName + annotation
