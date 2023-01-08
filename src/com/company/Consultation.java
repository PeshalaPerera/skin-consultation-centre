package com.company;

import java.time.LocalDate;

public class Consultation {
    private Doctor doctor;
    private Patient patient;
    private LocalDate time;
    private double cost;
    private String notes;

    public Consultation(Doctor doctor, Patient patient, LocalDate time, double cost, String notes) {
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

    public LocalDate getTime() {
        return LocalDate.from(time);
    }

    public void setTime(LocalDate time) {
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

    @Override
    public String toString() {
        return doctor.getMedicalLicenceNo() + "," +
                patient.getId() + "," +
                patient.getName() + "," +
                patient.getSurname() + "," +
                patient.getDob() + "," +
                patient.getMobileNo() + "," +
                time + "," +
                cost + "," +
                notes + "\n";
    }

    public String toFormattedString() {
        return getDoctor() + "," + getPatient() + "," + getTime() + ","
                + getCost() + "," + getNotes() + "\n";
    }
}
