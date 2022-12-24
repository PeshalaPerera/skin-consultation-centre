package com.company;

import java.util.Date;

public class Person {
    private String name;
    private String surname;
    private String mobileNo;
    private String dob;

    public Person(String name, String surname, String mobileNo, String dob) {
        this.name = name;
        this.surname = surname;
        this.mobileNo = mobileNo;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
