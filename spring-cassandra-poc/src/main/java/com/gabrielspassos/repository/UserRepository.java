package com.gabrielspassos.repository;

import com.gabrielspassos.domain.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CassandraRepository<User, String> {

    Optional<User> findByName(String name);
}
