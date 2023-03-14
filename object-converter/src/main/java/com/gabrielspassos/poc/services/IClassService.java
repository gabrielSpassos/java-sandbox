package com.gabrielspassos.poc.services;

import java.lang.reflect.Field;
import java.util.Map;

public interface IClassService {

    Map<String, Object> getAttributesNamesAndValuesFromObject(Object object);

    Field getAccessibleFieldByName(Object object, String fieldName) throws NoSuchFieldException;
}
