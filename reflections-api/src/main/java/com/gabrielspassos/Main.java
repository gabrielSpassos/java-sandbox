package com.gabrielspassos;

import com.gabrielspassos.service.ReflectionsService;

public class Main {
    static void main() {
        IO.println("Reflections API POC!");

        var reflectionsService = new ReflectionsService();
        reflectionsService.testReflections();
    }
}
