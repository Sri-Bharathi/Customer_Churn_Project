CREATE TABLE customers (
    customer_id INTEGER PRIMARY KEY,
    name TEXT,
    email TEXT,
    join_date TEXT,
    region TEXT,
    status TEXT  -- 'Active' or 'Churned'
);

CREATE TABLE transactions (
    transaction_id INTEGER PRIMARY KEY,
    customer_id INTEGER,
    amount REAL,
    transaction_date TEXT,
    FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);
