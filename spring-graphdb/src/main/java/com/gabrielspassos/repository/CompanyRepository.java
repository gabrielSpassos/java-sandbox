package com.gabrielspassos.repository;

import com.gabrielspassos.domain.Company;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface CompanyRepository extends Neo4jRepository<Company, Long> {

    @Query("""
                MATCH (company:Company)-[:LOCATED_IN]->(city:City)
                WHERE city.name = $name
                RETURN company
            """)
    List<Company> findCompaniesByCityName(String name);

}
