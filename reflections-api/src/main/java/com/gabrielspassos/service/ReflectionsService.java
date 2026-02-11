package com.gabrielspassos.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionsService {

    public void testReflections() {
        try {
            Class<?> clazz = Class.forName("com.gabrielspassos.dto.UserDTO");

            for (Constructor<?> c : clazz.getConstructors()) {
                IO.println("Constructor: " + c);
            }

            for (Field f: clazz.getDeclaredFields()) {
                IO.println("Field: " + f);
            }

            for (Method m: clazz.getDeclaredMethods()) {
                IO.println("Method: " + m);
            }

            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
            var user = constructor.newInstance("Gabriel", 28);
            IO.println("User instance by reflections: " + user);

            Method isAdult = clazz.getMethod("isAdult");
            Method greet = clazz.getMethod("greet", String.class);
            Method canDrink = clazz.getMethod("canDrink", String.class, int.class);

            IO.println("isAdult reflections invoke: " + isAdult.invoke(user));
            IO.println("greet reflections invoke: " + greet.invoke(user, "Hi"));
            IO.println("canDrink in USA reflections invoke: " + canDrink.invoke(user, "USA", 21));
            IO.println("canDrink in Brazil reflections invoke: " + canDrink.invoke(user, "Brazil", 18));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
