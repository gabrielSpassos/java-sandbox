# Spring Property File Runtime Reader POC

## Automated Test

1. [ConfigManagerTest](https://github.com/gabrielSpassos/java-sandbox/blob/master/runtime-property-loader-poc/src/test/java/com/gabrielspassos/config/ConfigManagerTest.java)

## Manual Test

1. Execute curl --location 'http://localhost:8080/configs'
2. Expected output
```json
[
    "value1",
    "defaultValueTwo",
    "value3",
    "value4",
    "value5",
    "value6"
]
```

## Code

```java
@Component
public class OutsideConfigLoader {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        var resource = new FileSystemResource("conf/outside-config.properties");
        propertySourcesPlaceholderConfigurer.setLocations( resource );
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders( true );
        return propertySourcesPlaceholderConfigurer;
    }
}
```