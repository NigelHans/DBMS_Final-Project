package org.example.dao;

import org.example.model.Doctor;
import org.example.model.Patient;
import org.example.util.DatabaseConnection;

import java.sql.*;

public class UserDAO {
    public void saveUserData(String identity, String username, String password) {
        String query = "INSERT INTO Users (identity_type, username, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, identity);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveDoctorData(Doctor doctor) {
        String query = "INSERT INTO Doctors (name, age, specialty) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, doctor.getName());
            stmt.setInt(2, doctor.getAge());
            stmt.setString(3, doctor.getSpecialty());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void savePatientData(Patient patient) {
        String query = "INSERT INTO Patients (name, age, health_condition) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getHealthCondition());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateCredentials(String identity, String username, String password) {
        String query = "SELECT * FROM Users WHERE identity_type = ? AND username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, identity);
            stmt.setString(2, username);
            stmt.setString(3, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
