

INSERT INTO myusers (email, password, first_name, last_name, county, role, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES
    ('user@example.com', '$2a$10$7QciS9tqAaOk6F3iAG4UruEnOHlFFQeWrycM0CZfLl2AKQCbUMy.e', 'John', 'Doe', 'Cork', 'ROLE_USER', true, true, true, true),
    ('admin@example.com', '$2a$10$uQ7peIfhROGuGmCY7vmYhOpU1mtdYpoiUZmNxM8KfLCugndIhNH5K', 'Jane', 'Smith', 'Kerry', 'ROLE_ADMIN', true, true, true, true);

-- Insert sample data into the Household table
INSERT INTO household (eircode, number_of_occupants, max_number_of_occupants, owner_occupied) VALUES
                                                                                                  ('D01F123', 3, 5, TRUE),
                                                                                                  ('D02X456', 2, 4, FALSE),
                                                                                                  ('D03Y789', 1, 3, TRUE),
                                                                                                  ('D04Z012', 4, 4, TRUE),
                                                                                                  ('D05A345', 0, 2, FALSE);

-- Insert sample data into the Pet table
INSERT INTO pet (name, animal_type, breed, age, household_eircode) VALUES
('Buddy', 'Dog', 'Golden Retriever', 5, 'D01F123'),
('Luna', 'Cat', 'Siamese', 3, 'D02X456'),
('Charlie', 'Dog', 'Beagle', 2, 'D03Y789'),
('Bella', 'Rabbit', 'Holland Lop', 1, 'D04Z012'),
('Max', 'Parrot', 'African Grey', 4, 'D01F123');
