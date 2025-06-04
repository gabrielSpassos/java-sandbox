package com.gabrielspassos.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ConfigManager {

    @Value("${my.config.keyOne}")
    private String keyOne;

    @Value("${my.config.keyTwo:defaultValueTwo}")
    private String keyTwo;

    @Value("${my.config.keyThree:defaultValueThree}")
    private String keyThree;

    @Value("${my.config.keyFour}")
    private String keyFour;

    @Value("${KEY_FIVE}")
    private String keyFive;

    @Value("${KEY_SIX}")
    private String keySix;

    public String getKeyOne() {
        return keyOne;
    }

    public String getKeyTwo() {
        return keyTwo;
    }

    public String getKeyThree() {
        return keyThree;
    }

    public String getKeyFour() {
        return keyFour;
    }

    public String getKeyFive() {
        return keyFive;
    }

    public String getKeySix() {
        return keySix;
    }

    public List<String> getConfigs() {
        return Arrays.asList(keyOne, keyTwo, keyThree, keyFour, keyFive, keySix);
    }
}
