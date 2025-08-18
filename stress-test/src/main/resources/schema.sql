create table if not exists game(
    id uuid not null primary key default gen_random_uuid(),
    execution_id text not null,
    dungeon text not null,
    minimal_health integer not null,
    execution_time timestamptz not null
);
