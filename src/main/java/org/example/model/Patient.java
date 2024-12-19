package org.example.model;

public class Patient extends Person {
    private String healthCondition;

    public Patient(String name, int age, String healthCondition) {
        super(name, age);
        this.healthCondition = healthCondition;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public void showPersonDetails() {
        super.showPersonDetails();
        System.out.println("Health Condition: " + healthCondition);
    }
}
