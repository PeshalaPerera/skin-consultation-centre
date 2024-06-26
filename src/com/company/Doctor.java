package com.company;

import java.util.Date;

public class Doctor extends Person {
    private int medicalLicenceNo;
    private String specialization;

    public Doctor(String name, String surname, String mobileNo, String dob, int medicalLicenceNo, String specialization) {
        super(name, surname, mobileNo, dob);
        this.medicalLicenceNo = medicalLicenceNo;
        this.specialization = specialization;
    }

    public int getMedicalLicenceNo() {
        return medicalLicenceNo;
    }

    public void setMedicalLicenceNo(int medicalLicenceNo) {
        this.medicalLicenceNo = medicalLicenceNo;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", dob='" + getDob() + '\'' +
                ", mobileNo='" + getMobileNo() + '\'' +
                " medicalLicenceNo=" + medicalLicenceNo +
                ", specialization='" + specialization + '\'' +
                '}';
    }

    public String toFormattedString() {
        return getName() + "," + getSurname() + "," + getDob() + ","
                + getMobileNo() + "," + getMedicalLicenceNo() + "," +  getSpecialization() + "\n";
    }

    /*public String identity() {
        String[] names = namesList.toArray(new String[0]);
        return names;
    }*/

//    {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"}

}
