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

            Method getName = clazz.getMethod("getName");
            Method getAge = clazz.getMethod("getAge");
            Method isAdult = clazz.getMethod("isAdult");
            Method greet = clazz.getMethod("greet", String.class);
            Method canDrink = clazz.getMethod("canDrink", String.class, int.class);

            IO.println(String.format("User with age %s isAdult reflections invoke: %s",
                    getAge.invoke(user), isAdult.invoke(user)));
            IO.println(String.format("User with name %s greet reflections invoke: %s",
                    getName.invoke(user), greet.invoke(user, "Hi")));
            IO.println(String.format("User with age %s canDrink in USA reflections invoke: %s",
                    getAge.invoke(user), canDrink.invoke(user, "USA", 21)));
            IO.println(String.format("User with age %s canDrink in Brazil reflections invoke: %s",
                    getAge.invoke(user), canDrink.invoke(user, "Brazil", 18)));

            Method hiddenMethod = clazz.getDeclaredMethod("hiddenMethod");
            hiddenMethod.setAccessible(true);
            IO.println("private hiddenMethod reflections invoke: " + hiddenMethod.invoke(user));

            Field age = clazz.getDeclaredField("age");
            age.setAccessible(true);
            age.set(user, 18);

            Field name = clazz.getDeclaredField("name");
            name.setAccessible(true);
            name.set(user, "ReflectionName");

            IO.println(String.format("User with age %s isAdult reflections invoke: %s",
                    getAge.invoke(user), isAdult.invoke(user)));
            IO.println(String.format("User with name %s greet reflections invoke: %s",
                    getName.invoke(user), greet.invoke(user, "Hello")));
            IO.println(String.format("User with age %s canDrink in USA reflections invoke: %s",
                    getAge.invoke(user), canDrink.invoke(user, "USA", 21)));
            IO.println(String.format("User with age %s canDrink in Brazil reflections invoke: %s",
                    getAge.invoke(user), canDrink.invoke(user, "Brazil", 18)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
