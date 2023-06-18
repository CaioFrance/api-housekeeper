CREATE TABLE IF NOT EXISTS housekeepers (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cellphone VARCHAR(25) NOT NULL,
    telephone VARCHAR(25),
    email VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    experience TEXT NOT NULL,
    price_per_hour DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    street VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255) NOT NULL,
    zip_code VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    complement TEXT,
    number VARCHAR(100)
)