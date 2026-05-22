CREATE TABLE orders (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_name VARCHAR(255),
    amount NUMERIC(10,2),
    created_at TIMESTAMP DEFAULT NOW()
);
