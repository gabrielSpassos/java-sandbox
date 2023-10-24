package org.gabrielspassos.task;

import org.gabrielspassos.dto.PersonDTO;
import org.gabrielspassos.external.Task;

import java.time.LocalDate;

public class TaskA implements Task<PersonDTO> {

    @Override
    public PersonDTO execute() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("Error "+ e);
        }
        PersonDTO personDTO = new PersonDTO("Josh", LocalDate.parse("1996-03-27"));
        System.out.println("Task result: " + personDTO);
        return personDTO;
    }

}
