package com.gabrielspassos.api;

import com.gabrielspassos.api.request.CreatePersonRequest;
import com.gabrielspassos.api.request.UpdatePersonRequest;
import com.gabrielspassos.dto.PersonDTO;
import com.gabrielspassos.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody CreatePersonRequest request) {
        PersonDTO personDTO = personService.createPerson(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(personDTO);
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findPeople() {
        List<PersonDTO> people = personService.findPeople();
        return ResponseEntity.ok(people);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id) {
        PersonDTO personDTO = personService.findPersonById(id);
        return ResponseEntity.ok(personDTO);
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<PersonDTO> findByUUID(@PathVariable UUID uuid) {
        PersonDTO personDTO = personService.findPersonByUUID(uuid);
        return ResponseEntity.ok(personDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody UpdatePersonRequest request) {
        PersonDTO personDTO = personService.updatePerson(id, request);
        return ResponseEntity.ok(personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonDTO> deletePerson(@PathVariable Long id) {
        PersonDTO personDTO = personService.deletePerson(id);
        return ResponseEntity.ok(personDTO);
    }

}
