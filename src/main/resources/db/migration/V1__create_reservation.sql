CREATE TABLE reservation (
    id BIGINT PRIMARY KEY,
    customer_name VARCHAR(36) NOT NULL,
    reservation_time TIMESTAMP NOT NULL,
    number_of_people INT NOT NULL
);
 
CREATE INDEX idx_reservation_customer_id ON reservation(customer_name);
CREATE INDEX idx_reservation_customer_date ON reservation(customer_name, reservation_time);
