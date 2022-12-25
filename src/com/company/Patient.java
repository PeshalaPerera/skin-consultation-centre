package com.company;

import java.util.Date;

public class Patient extends Person {
    private int id;

    public Patient(String name, String surname, String mobileNo, String dob, int id) {
        super(name, surname, mobileNo, dob);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toFormattedString() {
        return getName() + "," + getSurname() + "," + getMobileNo() + ","
                + getDob() + "," + getId() + "\n";
    }
}
