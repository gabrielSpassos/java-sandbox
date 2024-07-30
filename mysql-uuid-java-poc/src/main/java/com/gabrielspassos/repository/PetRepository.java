package com.gabrielspassos.repository;

import com.gabrielspassos.domain.PetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, String> {
}
