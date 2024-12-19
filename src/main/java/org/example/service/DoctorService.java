package org.example.service;

import org.example.dao.UserDAO;
import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class DoctorService {
    private final Scanner scanner = new Scanner(System.in);

    public void doctorMenu(String doctorName) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Doctor Menu ---");
            System.out.println("1. View Patient Records");
            System.out.println("2. View Appointments");
            System.out.println("3. Add Medical History");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewPatientRecords();
                case 2 -> viewAppointments(doctorName);
                case 3 -> addMedicalHistory(doctorName);
                case 4 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewPatientRecords() {
        String query = "SELECT * FROM Patients";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No patient records found.");
                return;
            }

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Health Condition: " + rs.getString("health_condition"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching patient records: " + e.getMessage());
        }
    }

    private void viewAppointments(String doctorName) {
        String query = "SELECT * FROM Appointments WHERE doctor_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, doctorName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("No appointments found.");
                    return;
                }

                while (rs.next()) {
                    System.out.println("Patient: " + rs.getString("patient_name"));
                    System.out.println("Date: " + rs.getDate("appointment_date"));
                    System.out.println("Time: " + rs.getTime("appointment_time"));
                    System.out.println("------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching appointments: " + e.getMessage());
        }
    }

    private void addMedicalHistory(String doctorName) {
        System.out.print("Enter patient's name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter visit date (YYYY-MM-DD): ");
        String visitDate = scanner.nextLine();
        System.out.print("Enter diagnosis: ");
        String diagnosis = scanner.nextLine();
        System.out.print("Enter prescription: ");
        String prescription = scanner.nextLine();

        String query = "INSERT INTO medicalhistory (patient_name, visit_date, doctor_name, diagnosis, prescription) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, patientName);
            stmt.setString(2, visitDate);
            stmt.setString(3, doctorName);
            stmt.setString(4, diagnosis);
            stmt.setString(5, prescription);
            stmt.executeUpdate();
            System.out.println("Medical history added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding medical history: " + e.getMessage());
        }
    }
}
