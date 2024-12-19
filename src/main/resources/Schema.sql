USE medicaredb;

DROP TABLE users;
DROP TABLE doctors;
DROP TABLE patients;
DROP TABLE appointments;
DROP TABLE medicalhistory;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identity_type ENUM('Doctor', 'Patient') NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    specialty VARCHAR(100) NOT NULL
);

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    health_condition VARCHAR(100) NOT NULL
);

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_name VARCHAR(100) NOT NULL,
    patient_name VARCHAR(100) NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL
);

CREATE TABLE medicalhistory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(100) NOT NULL,
    visit_date DATE NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    diagnosis VARCHAR(255),
    prescription VARCHAR(255)
);