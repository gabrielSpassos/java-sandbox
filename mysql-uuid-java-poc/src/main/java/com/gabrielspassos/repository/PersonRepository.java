package com.gabrielspassos.repository;

import com.gabrielspassos.domain.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByUuid(UUID uuid);

}
