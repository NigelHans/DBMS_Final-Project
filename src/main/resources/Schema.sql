USE medicaredb;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS doctors;
DROP TABLE IF EXISTS patients;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS medicalhistory;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    identity_type ENUM('Doctor', 'Patient') NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Insert initial data into users table
INSERT INTO users (identity_type, username, password) VALUES
    ('Doctor', 'Nigel', 'nigel1234'),
    ('Patient', 'Anjo', 'anjo1234'),
    ('Patient', 'Lenard', 'lenard1234'),
    ('Doctor', 'Chynna', 'chynna1234'),
    ('Doctor', 'Jerzha', 'jerzha1234'),
    ('Patient', 'kiarra', 'kiarra1234');

CREATE TABLE doctors (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    specialty VARCHAR(100) NOT NULL
);

INSERT INTO doctors (name, age, specialty) VALUES
    ('Dr. Nigel', 45, 'Cardiology'),
    ('Dr. Chynna', 44, 'Neurology'),
    ('Dr. Jerzha', 38, 'Dermatology');

CREATE TABLE patients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    health_condition VARCHAR(100) NOT NULL
);

INSERT INTO patients (name, age, health_condition) VALUES
    ('Anjo', 19, 'Asthma'),
    ('Lenard', 20, 'Diabetes'),
    ('Kiarra', 19, 'Hypertension');

CREATE TABLE appointments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    doctor_name VARCHAR(100) NOT NULL,
    patient_name VARCHAR(100) NOT NULL,
    appointment_date DATE NOT NULL,
    appointment_time TIME NOT NULL
);

INSERT INTO appointments (doctor_name, patient_name, appointment_date, appointment_time) VALUES
    ('Dr. Nigel', 'Anjo', '2024-12-20', '10:00:00'),
    ('Dr. Jerzha', 'Kiarra', '2024-12-21', '11:00:00'),
    ('Dr. Chynna', 'Lenard', '2024-12-22', '14:00:00');

CREATE TABLE medicalhistory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(100) NOT NULL,
    visit_date DATE NOT NULL,
    doctor_name VARCHAR(100) NOT NULL,
    diagnosis VARCHAR(255),
    prescription VARCHAR(255)
);

INSERT INTO medicalhistory (patient_name, visit_date, doctor_name, diagnosis, prescription) VALUES
    ('Anjo', '2024-11-15', 'Dr. Nigel', 'Asthma exacerbation', 'Inhaler'),
    ('Kiarra', '2024-10-10', 'Dr. Jerzha', 'Hypertension', 'Metformin'),
    ('Lenard', '2024-09-05', 'Dr. Chynna', 'Type 2 Diabetes', 'Lisinopril');
