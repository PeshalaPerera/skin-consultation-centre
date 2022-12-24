package com.company;

import java.util.Date;

public class Consultation {
    private Doctor doctor;
    private Patient patient;
    private Date time;
    private double cost;
    private String notes;

    public Consultation(Doctor doctor, Patient patient, Date time, double cost, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.time = time;
        this.cost = cost;
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
