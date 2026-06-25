package com.gabrielspassos.controller;

import com.gabrielspassos.TestcontainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GraphControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnAllPeople() throws Exception {
        mockMvc.perform(get("/v1/graph/people"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Alice")))
                .andExpect(jsonPath("$[1].name", is("Bob")))
                .andExpect(jsonPath("$[2].name", is("Charlie")));

    }

    @Test
    void shouldReturnAlice() throws Exception {
        mockMvc.perform(get("/v1/graph/people/Alice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"))
                .andExpect(jsonPath("$.age").value(30));

    }

    @Test
    void shouldReturnFriendsOfAlice() throws Exception {
        mockMvc.perform(get("/v1/graph/people/Alice/friends"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Bob"));

    }

    @Test
    void shouldReturnFriendsOfFriends() throws Exception {
        mockMvc.perform(get("/v1/graph/people/Alice/friends-of-friends"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Charlie"));

    }

    @Test
    void shouldReturnEmployees() throws Exception {
        mockMvc.perform(get("/v1/graph/companies/TechCorp/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[1].name").value("Bob"));
    }

    @Test
    void shouldReturnCompanies() throws Exception {
        mockMvc.perform(get("/v1/graph/cities/San Francisco/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("BigTechCorp"))
                .andExpect(jsonPath("$[1].name").value("TechCorp"));
    }

    @Test
    void shouldReturnEmptyCompanies() throws Exception {
        mockMvc.perform(get("/v1/graph/cities/New York/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void shouldReturn404WhenPersonDoesNotExist() throws Exception {
        mockMvc.perform(get("/v1/graph/people/John"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Person not found: John"))
                .andExpect(jsonPath("$.code").value("PERSON_NOT_FOUND"));
    }
}