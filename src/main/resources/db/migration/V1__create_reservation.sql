CREATE TABLE reservation (
    id BIGINT PRIMARY KEY,
    customer_id VARCHAR(36) NOT NULL,
    date_time TIMESTAMP NOT NULL,
    number_of_people INT NOT NULL, 
    index idx_customer_id (customer_id),
    index idx_date_time (date_time)
);