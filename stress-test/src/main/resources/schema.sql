create table if not exists stress_test (
    id uuid not null primary key default gen_random_uuid(),
    execution id text not null,
    execution_time timestampz not null
);
