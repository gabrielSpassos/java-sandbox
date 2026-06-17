package com.gabrielspassos.repository;

import com.gabrielspassos.domain.SensorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorEventRepository extends CassandraRepository<SensorEvent, String> {
}
