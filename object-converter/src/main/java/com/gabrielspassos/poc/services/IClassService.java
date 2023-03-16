package com.gabrielspassos.poc.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface IClassService {

    Map<Field, Object> getAttributesAndValuesFromObject(Object object);

    List<Field> getAccessibleFieldsFromObjectByOrigin(Object object, Field originField) throws NoSuchFieldException;

    Field getFieldFromObjectByName(Object object, String originField) throws NoSuchFieldException;
}
