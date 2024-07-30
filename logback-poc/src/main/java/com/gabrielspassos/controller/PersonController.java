package com.gabrielspassos.controller;

import com.gabrielspassos.controller.response.PersonResponse;
import com.gabrielspassos.exception.TestErrorPurposeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/people")
    public ResponseEntity<PersonResponse> createPerson() {
        var personResponse = new PersonResponse("Gabriel", "Passos");
        logger.info("Created person {}", personResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
    }

    @GetMapping("/people")
    public ResponseEntity<PersonResponse> getPeople() {
        try {
            throw new TestErrorPurposeException(HttpStatus.EXPECTATION_FAILED, "Test purpose error to get people", "ERR-001");
        } catch (TestErrorPurposeException exception) {
            logger.error("Error to get people", exception);
            throw exception;
        }
    }
}
