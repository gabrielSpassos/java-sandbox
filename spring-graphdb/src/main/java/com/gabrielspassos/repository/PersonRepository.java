package com.gabrielspassos.repository;

import com.gabrielspassos.domain.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Optional<Person> findByName(String name);

    @Query("""
                MATCH (p:Person)-[:KNOWS]->(friend:Person)
                WHERE p.name = $name
                RETURN friend
            """)
    List<Person> findFriends(String name);

    @Query("""
                MATCH (p:Person)-[:KNOWS]->(:Person)-[:KNOWS]->(friend)
                WHERE p.name = $name
                RETURN DISTINCT friend
            """)
    List<Person> findFriendsOfFriends(String name);

    @Query("""
                MATCH (p:Person)-[:WORKS_AT]->(c:Company)
                WHERE c.name = $company
                RETURN p
            """)
    List<Person> findEmployees(String company);
}