package com.gabrielspassos.repository;

import com.gabrielspassos.domain.Company;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {
}
