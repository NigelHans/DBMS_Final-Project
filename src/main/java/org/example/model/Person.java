package org.example.model;

public abstract class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void showPersonDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

