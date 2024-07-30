package com.gabrielspassos.service;

import com.gabrielspassos.api.request.CreatePersonRequest;
import com.gabrielspassos.api.request.UpdatePersonRequest;
import com.gabrielspassos.domain.PersonEntity;
import com.gabrielspassos.dto.PersonDTO;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.gabrielspassos.constant.ErrorsCodes.PERSON_NOT_FOUND;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO createPerson(CreatePersonRequest request) {
        var personEntity = new PersonEntity();
        personEntity.setUuid(UUID.randomUUID().toString());
        personEntity.setFirstName(request.getFirstName());
        personEntity.setLastName(request.getLastName());
        personEntity.setCreatedAt(Timestamp.from(ZonedDateTime.now().toInstant()));

        var savedPerson = personRepository.save(personEntity);
        return new PersonDTO(savedPerson);
    }

    public List<PersonDTO> findPeople() {
        return StreamSupport.stream(personRepository.findAll().spliterator(), false)
                .map(PersonDTO::new)
                .collect(Collectors.toList());
    }

    public PersonDTO findPersonById(Long id) {
        var personEntity = findById(id);
        return new PersonDTO(personEntity);
    }

    public PersonDTO findPersonByUUID(UUID uuid) {
        return personRepository.findByUuid(uuid.toString())
                .map(PersonDTO::new)
                .orElseThrow(() -> new NotFoundException(PERSON_NOT_FOUND, String.format("person not found with uuid: %s", uuid)));
    }

    public PersonDTO updatePerson(Long id, UpdatePersonRequest request) {
        var personEntity = findById(id);

        personEntity.setUuid(request.getUuid().toString());
        personEntity.setFirstName(request.getFirstName());
        personEntity.setLastName(request.getLastName());

        var updatedPerson = personRepository.save(personEntity);
        return new PersonDTO(updatedPerson);
    }

    public PersonDTO deletePerson(Long id) {
        var personEntity = findById(id);
        personRepository.delete(personEntity);
        return new PersonDTO(personEntity);
    }

    private PersonEntity findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(PERSON_NOT_FOUND, String.format("person not found with id: %s", id)));
    }
}
