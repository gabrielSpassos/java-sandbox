package com.gabrielspassos.service;

import com.gabrielspassos.domain.Company;
import com.gabrielspassos.domain.Person;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.repository.CompanyRepository;
import com.gabrielspassos.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphService {

    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    public GraphService(PersonRepository personRepository, CompanyRepository companyRepository) {
        this.personRepository = personRepository;
        this.companyRepository = companyRepository;
    }

    public List<Person> findAllPeople() {
        return personRepository.findAll();
    }

    public Person findPerson(String name) {
        return personRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Person not found: " + name, "PERSON_NOT_FOUND"));
    }

    public List<Person> findFriends(String name) {
        return personRepository.findFriends(name);
    }

    public List<Person> findFriendsOfFriends(String name) {
        return personRepository.findFriendsOfFriends(name);
    }

    public List<Person> findEmployees(String company) {
        return personRepository.findEmployees(company);
    }

    public List<Company> findCompaniesByCityName(String cityName) {
        return companyRepository.findCompaniesByCityName(cityName);
    }

}
