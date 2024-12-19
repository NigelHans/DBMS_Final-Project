package org.example.model;

public class Doctor extends Person {
    private String specialty;

    public Doctor(String name, int age, String specialty) {
        super(name, age);
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void showPersonDetails() {
        super.showPersonDetails();
        System.out.println("Specialty: " + specialty);
    }
}
