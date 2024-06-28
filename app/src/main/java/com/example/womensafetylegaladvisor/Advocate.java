package com.example.womensafetylegaladvisor;

public class Advocate {
    private String name;
    private String specialization;
    private int experience;
    private String phone;
    private String email;

    public Advocate(String name, String specialization, int experience, String phone, String email) {
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getExperience() {
        return experience;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}