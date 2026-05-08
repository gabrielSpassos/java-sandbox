CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    description TEXT,
    created_at TIMESTAMP
);

CREATE TABLE outbox (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    aggregate_type VARCHAR(255),
    aggregate_id UUID,
    event_type VARCHAR(255),
    payload TEXT,
    created_at TIMESTAMP,
    processed BOOLEAN DEFAULT FALSE
);