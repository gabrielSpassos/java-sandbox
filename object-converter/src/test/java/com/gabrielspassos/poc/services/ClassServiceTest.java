package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.reflections.services.ClassService;
import org.junit.jupiter.api.BeforeEach;

class ClassServiceTest {

    private ClassService classService;

    @BeforeEach
    void initService() {
        classService = ClassService.getClassService();
    }

}