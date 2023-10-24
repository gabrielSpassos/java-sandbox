package org.gabrielspassos.task;

import org.gabrielspassos.dto.PersonDTO;
import org.gabrielspassos.external.Task;

import java.time.LocalDate;

public class TaskB implements Task<PersonDTO> {

    @Override
    public PersonDTO execute() {
        PersonDTO personDTO = new PersonDTO("Julio", LocalDate.parse("1992-05-19"));
        System.out.println("Task result: " + personDTO);
        return personDTO;
    }

}
