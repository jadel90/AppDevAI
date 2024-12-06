CREATE TABLE IF NOT EXISTS myusers (
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    county VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    enabled BOOLEAN NOT NULL,
    account_non_expired BOOLEAN NOT NULL,
    credentials_non_expired BOOLEAN NOT NULL,
    account_non_locked BOOLEAN NOT NULL
    );

CREATE TABLE IF NOT EXISTS household (
    eircode VARCHAR(8) PRIMARY KEY,
    number_of_occupants INT NOT NULL,
    max_number_of_occupants INT NOT NULL,
    owner_occupied BIT NOT NULL
    );

CREATE TABLE IF NOT EXISTS pet (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    animal_type VARCHAR(50) NOT NULL,
    breed VARCHAR(255),
    age INT NOT NULL,
    household_eircode VARCHAR(8) ,  -- Foreign key column
    FOREIGN KEY (household_eircode) REFERENCES household(eircode)  -- Foreign key constraint
    );

