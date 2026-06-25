package com.gabrielspassos.controller;

import com.gabrielspassos.dto.CompanyDto;
import com.gabrielspassos.dto.CompanyMapper;
import com.gabrielspassos.dto.PersonDto;
import com.gabrielspassos.dto.PersonMapper;
import com.gabrielspassos.service.GraphService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/graph")
public class GraphController {

    private final GraphService graphService;

    public GraphController(GraphService graphService) {
        this.graphService = graphService;
    }

    @GetMapping("/people")
    public List<PersonDto> allPeople() {
        return graphService.findAllPeople()
                .stream()
                .map(PersonMapper::toDto)
                .toList();
    }

    @GetMapping("/people/{name}")
    public PersonDto person(@PathVariable String name) {
        return PersonMapper.toDto(graphService.findPerson(name));
    }

    @GetMapping("/people/{name}/friends")
    public List<PersonDto> friends(@PathVariable String name) {
        return graphService.findFriends(name)
                .stream()
                .map(PersonMapper::toDto)
                .toList();
    }

    @GetMapping("/people/{name}/friends-of-friends")
    public List<PersonDto> friendsOfFriends(@PathVariable String name) {
        return graphService.findFriendsOfFriends(name)
                .stream()
                .map(PersonMapper::toDto)
                .toList();
    }

    @GetMapping("/companies/{company}/employees")
    public List<PersonDto> employees(@PathVariable String company) {
        return graphService.findEmployees(company)
                .stream()
                .map(PersonMapper::toDto)
                .toList();
    }

    @GetMapping("/cities/{cityName}/companies")
    public List<CompanyDto> companiesByCityName(@PathVariable String cityName) {
        return graphService.findCompaniesByCityName(cityName)
                .stream()
                .map(CompanyMapper::toDto)
                .toList();
    }
}
