package com.gabrielspassos.poc.mappers;

import com.gabrielspassos.poc.dto.EmployeeDTO;
import com.gabrielspassos.poc.dto.PersonDTO;
import com.gabrielspassos.poc.loaders.MyClassLoader;

public class Principal extends ClassLoader {

    public static void main(String[] args) {
        try {
            PersonDTO personDTO = new PersonDTO("John", 38);
            ClassLoader parentClassLoader = MyClassLoader.class.getClassLoader();
            MyClassLoader classLoader = new MyClassLoader(parentClassLoader);
            Class myObjectClass = classLoader.customLoadClass(personDTO, EmployeeDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
