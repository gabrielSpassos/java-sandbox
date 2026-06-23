package com.gabrielspassos.config;

import com.gabrielspassos.domain.City;
import com.gabrielspassos.domain.Company;
import com.gabrielspassos.domain.Person;
import com.gabrielspassos.repository.CityRepository;
import com.gabrielspassos.repository.CompanyRepository;
import com.gabrielspassos.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;
    private final CityRepository cityRepository;

    public DataLoader(
            PersonRepository personRepository,
            CompanyRepository companyRepository,
            CityRepository cityRepository) {
        this.personRepository = personRepository;
        this.companyRepository = companyRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(String... args) {
        personRepository.deleteAll();
        companyRepository.deleteAll();
        cityRepository.deleteAll();

        City sf = cityRepository.save(new City("San Francisco"));
        City ny = cityRepository.save(new City("New York"));

        Company techCorp = new Company("TechCorp");
        techCorp.setCity(sf);

        techCorp = companyRepository.save(techCorp);

        Person alice = new Person("Alice", 30);
        Person bob = new Person("Bob", 28);
        Person charlie = new Person("Charlie", 35);

        alice.setCompany(techCorp);
        bob.setCompany(techCorp);

        alice.setCity(sf);
        bob.setCity(ny);
        charlie.setCity(sf);

        alice.addFriend(bob);
        bob.addFriend(charlie);

        personRepository.save(alice);
        personRepository.save(bob);
        personRepository.save(charlie);
    }

}