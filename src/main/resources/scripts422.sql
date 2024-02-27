CREATE TABLE Person (
    id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    has_driver_license BOOLEAN
);

CREATE TABLE Car (
    id INT PRIMARY KEY,
    brand VARCHAR(50),
    model VARCHAR(50),
    price DECIMAL(10, 2)
);

CREATE TABLE PersonCar (
    person_id INT,
    car_id INT,
    PRIMARY KEY (person_id, car_id),
    FOREIGN KEY (person_id) REFERENCES Person(id),
    FOREIGN KEY (car_id) REFERENCES Car(id)
);

