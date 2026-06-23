package com.gabrielspassos.repository;

import com.gabrielspassos.domain.City;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CityRepository extends Neo4jRepository<City, Long> {
}
