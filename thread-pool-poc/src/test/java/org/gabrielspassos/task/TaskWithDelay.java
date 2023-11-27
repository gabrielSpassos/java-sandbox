package org.gabrielspassos.task;

import org.gabrielspassos.dto.PersonDTO;
import org.gabrielspassos.external.Task;

import java.time.LocalDate;

public class TaskWithDelay implements Task<PersonDTO> {

    @Override
    public PersonDTO execute() {
        try {
            Thread.sleep((int) ((Math.random() * (4000 - 10)) + 10));
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }
        PersonDTO personDTO = new PersonDTO("Josh", LocalDate.parse("1996-03-27"));
        System.out.println("Task result: " + personDTO);
        return personDTO;
    }

}
