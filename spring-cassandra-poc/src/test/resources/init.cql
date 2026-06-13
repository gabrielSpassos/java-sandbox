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
