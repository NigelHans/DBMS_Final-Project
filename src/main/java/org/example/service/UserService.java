package org.example.service;

import org.example.dao.UserDAO;
import org.example.model.Doctor;
import org.example.model.Patient;
import java.util.Scanner;

public class UserService {
    private final Scanner scanner = new Scanner(System.in);
    private final UserDAO userDAO = new UserDAO();
    private final DoctorService doctorService = new DoctorService();
    private final PatientService patientService = new PatientService();

    public void start() {
        boolean running = true;
        while (running) {
            printHeader("MEDICARE USER MANAGER");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            printSeparator();
            System.out.print("Enter your choice: ");

            int choice = getValidatedInput();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> {
                    running = false;
                    System.out.println("\nThank you for using Medicare. Goodbye!");
                }
                default -> System.out.println("\nInvalid choice. Please try again.");
            }
        }
    }

    private void register() {
        printHeader("REGISTER USER");
        System.out.println("1. Register as Doctor");
        System.out.println("2. Register as Patient");
        printSeparator();
        System.out.print("Enter your choice: ");

        int choice = getValidatedInput();

        if (choice == 1) {
            registerDoctor();
        } else if (choice == 2) {
            registerPatient();
        } else {
            System.out.println("\nInvalid choice. Returning to main menu.");
        }
    }

    private void registerDoctor() {
        printHeader("DOCTOR REGISTRATION");
        System.out.print("Enter doctor's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = getValidatedInput();
        System.out.print("Enter specialty: ");
        String specialty = scanner.nextLine();
        System.out.print("Set a password: ");
        String password = scanner.nextLine();

        Doctor doctor = new Doctor(name, age, specialty);
        userDAO.saveUserData("Doctor", doctor.getName(), password);
        userDAO.saveDoctorData(doctor);

        System.out.println("\nDoctor registered successfully!");
        printSeparator();
        doctor.showPersonDetails();
    }

    private void registerPatient() {
        printHeader("PATIENT REGISTRATION");
        System.out.print("Enter patient's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = getValidatedInput();
        System.out.print("Enter health condition: ");
        String condition = scanner.nextLine();
        System.out.print("Set a password: ");
        String password = scanner.nextLine();

        Patient patient = new Patient(name, age, condition);
        userDAO.saveUserData("Patient", patient.getName(), password);
        userDAO.savePatientData(patient);

        System.out.println("\nPatient registered successfully!");
        printSeparator();
        patient.showPersonDetails();
    }

    private void login() {
        printHeader("USER LOGIN");
        System.out.println("1. Login as Doctor");
        System.out.println("2. Login as Patient");
        printSeparator();
        System.out.print("Enter your choice: ");

        int choice = getValidatedInput();
        String identity = choice == 1 ? "Doctor" : choice == 2 ? "Patient" : null;

        if (identity == null) {
            System.out.println("\nInvalid choice. Returning to main menu.");
            return;
        }

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (userDAO.validateCredentials(identity, username, password)) {
            System.out.println("\nLogin successful! Welcome, " + username + ".");
            if ("Doctor".equals(identity)) {
                doctorService.doctorMenu(username);
            } else {
                patientService.patientMenu(username);
            }
        } else {
            System.out.println("\nInvalid username or password. Please try again.");
        }
    }

    private int getValidatedInput() {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                return choice;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Enter a number: ");
            }
        }
    }

    private void printHeader(String title) {
        System.out.println("\n==== " + title.toUpperCase() + " ====");
    }

    private void printSeparator() {
        System.out.println("-------------------------------");
    }
}
