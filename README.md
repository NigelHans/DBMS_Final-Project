# DBMS Project
Medicare System
This project is a Database Management System (DBMS) designed to manage the data and operations for a Medicare system. It supports user registration and login, appointment scheduling, and medical history management.

# 📋Objective
To develop a digital system that securely manages user accounts and healthcare data, fostering seamless interactions between patients and doctors while ensuring efficient record-keeping and prescription management.

# ✨ Key Features
⧠ Console-based database management system.

⧠ Backend logic implemented in Java.

⧠ Database operations powered by MySQL.

⧠ Modular programming principles for clean and maintainable code.

# 📝Project Structure Overview
⧠ main package: Contains the entry point (Medicare_DBMS).

⧠ model package: Defines core data structures like Person, Doctor, and Patient.

⧠ service package: Handles business logic and user interactions through classes like UserService, DoctorService, and PatientService.

⧠ dao package: Manages database operations via UserDAO.

⧠ util package: Provides utility classes such as DBConnection for database connectivity.

# 👨🏻‍💻User Roles
⧠ Doctor

  ⧠ View patient records and medical history.
  
  ⧠ Add new medical history for patients.
  
  ⧠ View and manage appointments.
  
⧠ Patient

  ⧠ View personal health profiles.
  
  ⧠ View a list of available doctors.
  
  ⧠ Schedule and manage appointments.
  
  ⧠ Access medical history.

# 🛢Database Structure
⧠ The application connects to a MySQL database (medicaredb) and uses the following tables:

⧠ Users: Stores credentials and roles (Doctor/Patient).

⧠ Doctors: Holds doctor details such as name, age, and specialty.

⧠ Patients: Stores patient information like name, age, and health conditions.

⧠ Appointments: Tracks doctor-patient appointments.

⧠ Medical History: Maintains patient medical records.
