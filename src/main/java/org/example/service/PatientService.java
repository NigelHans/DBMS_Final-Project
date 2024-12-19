package org.example.service;

import org.example.util.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class PatientService {
    private final Scanner scanner = new Scanner(System.in);

    public void patientMenu(String patientName) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Patient Menu ---");
            System.out.println("1. View Health Profile");
            System.out.println("2. View Doctors");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. View Medical History");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewHealthProfile(patientName);
                case 2 -> viewDoctors();
                case 3 -> scheduleAppointment(patientName);
                case 4 -> viewAppointments(patientName);
                case 5 -> viewMedicalHistory(patientName);
                case 6 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void viewHealthProfile(String patientName) {
        String query = "SELECT * FROM Patients WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, patientName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Health Condition: " + rs.getString("health_condition"));
            } else {
                System.out.println("No health profile found.");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching health profile: " + e.getMessage());
        }
    }

    private void viewDoctors() {
        String query = "SELECT * FROM Doctors";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Specialty: " + rs.getString("specialty"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching doctors: " + e.getMessage());
        }
    }

    private void scheduleAppointment(String patientName) {
        System.out.print("Enter doctor's name: ");
        String doctorName = scanner.nextLine();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter appointment time (HH:MM): ");
        String time = scanner.nextLine();

        String query = "INSERT INTO Appointments (doctor_name, patient_name, appointment_date, appointment_time) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, doctorName);
            stmt.setString(2, patientName);
            stmt.setString(3, date);
            stmt.setString(4, time);
            stmt.executeUpdate();
            System.out.println("Appointment scheduled successfully!");
        } catch (SQLException e) {
            System.out.println("Error scheduling appointment: " + e.getMessage());
        }
    }

    private void viewAppointments(String patientName) {
        String query = "SELECT * FROM Appointments WHERE patient_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, patientName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Doctor: " + rs.getString("doctor_name"));
                System.out.println("Date: " + rs.getDate("appointment_date"));
                System.out.println("Time: " + rs.getTime("appointment_time"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching appointments: " + e.getMessage());
        }
    }

    private void viewMedicalHistory(String patientName) {
        String query = "SELECT * FROM MedicalHistory WHERE patient_name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, patientName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println("Visit Date: " + rs.getDate("visit_date"));
                System.out.println("Doctor: " + rs.getString("doctor_name"));
                System.out.println("Diagnosis: " + rs.getString("diagnosis"));
                System.out.println("Prescription: " + rs.getString("prescription"));
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching medical history: " + e.getMessage());
        }
    }
}
