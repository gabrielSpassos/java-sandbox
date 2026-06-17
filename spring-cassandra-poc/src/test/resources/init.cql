CREATE KEYSPACE IF NOT EXISTS test_keyspace
    WITH replication = {
        'class':'SimpleStrategy',
        'replication_factor':1
        };

USE test_keyspace;

CREATE TABLE IF NOT EXISTS users (
    id text PRIMARY KEY,
    name text,
    birth_date date
);

CREATE INDEX IF NOT EXISTS users_name_idx ON users (name);

CREATE TABLE IF NOT EXISTS sensor_events (
    sensor_id text,
    timestamp timestamp,
    value double,
    PRIMARY KEY (sensor_id, timestamp)
);