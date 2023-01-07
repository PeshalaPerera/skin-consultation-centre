package com.company;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    private final Scanner scan;
    ArrayList<Doctor> doctorList = new ArrayList<>();
    ArrayList<Doctor> deletedList = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public WestminsterSkinConsultationManager(InputStream source) {
        scan = new Scanner(source);
        initialise();
    }

    @Override
    public Doctor addDoctor() {
        Doctor doctor = null;
        try {
            if (doctorList.size() < 10) {
                String name = getStringInput("Enter the Name");
                String surname = getStringInput("Enter the Surname");
                String mobileNo = getStringInput("Enter the Mobile Number");
                String dob = getStringInput("Enter the date of birth");
                int medicalLicenceNo = getIntInput("Enter the Medical Licence Number");
                String specialization = getStringInput("Enter the Specialization");

                doctor = new Doctor(name, surname, mobileNo, dob, medicalLicenceNo, specialization);

                doctorList.add(doctor);

                System.out.println("Successfully added doctor!\n");

            } else {
                System.out.println("You can not add more doctors.");
                System.out.println("Maximum number of doctors limit exceeded!!!\n");
            }
        } catch (Exception exception) {
            System.out.println("Invalid Input!!!\n");
        }

        return doctor;
    }

    @Override
    public void deleteDoctor() {
        int medicalLicenceNumber = getIntInput("Enter doctor medical licence number");
        checkNo(medicalLicenceNumber);
        try {
            boolean isDeleted = false;
            for (Doctor doctor : doctorList) {
                if (doctor.getMedicalLicenceNo() == medicalLicenceNumber) {
                    doctorList.remove(doctor);
                    deletedList.add(doctor);
                    isDeleted = true;
                    break;
                }
            }
            if (isDeleted) {
                System.out.println("Successfully deleted doctor!\n");
                showStatus();
            }
        } catch (Exception exception) {
            System.out.println("Invalid doctor medical licence number!!!\n");
        }
    }

    @Override
    public void printList() {
        try {
            if (doctorList.size() > 0) {
                doctorList.sort(Comparator.comparing(d -> d.getSurname().toLowerCase()));
                for (Doctor doctor : doctorList) {
                    System.out.println(doctor);
                }
            } else {
                System.out.println("Doctors list is empty...\n");
            }
        } catch (Exception exception) {
            System.out.println("Could not find doctors list!!!\n");
        }
    }

    @Override
    public void saveFile() {
        try {
            Formatter formatter = new Formatter("doctorsList.txt");
            if (doctorList.size() > 0) {
                for (Doctor doctor : doctorList) {
                    formatter.format("%s", doctor.toFormattedString());
                }
            } else {
                formatter.format("Doctors list is empty...");
            }
            formatter.close();
            System.out.println("Successfully saved to file!\n");
        } catch (Exception exception) {
            System.out.println("Could not store the data!!!\n");
        }
    }

    @Override
    public void viewGui() {
        Gui.start();
    }

    private void initialise() {
        try {
            File myObj = new File("doctorsList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                Doctor doctor = new Doctor(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), arr[5]);
                doctorList.add(doctor);
            }
            myReader.close();
            System.out.println("Doctors List Initialised!\n");
        } catch (Exception exception) {
            System.out.println("Could not load the data!!!");
        }
    }

    private void checkNo(Integer number) {
        int getMedicalLicenceNo = number;
        try {
            boolean available = true;
            for (Doctor doctor : doctorList) {
                if (doctor.getMedicalLicenceNo() == getMedicalLicenceNo) {
                    available = false;
                    break;
                }
            }
            if (available) {
                System.out.println("Doctor holds the medical license number "
                        + getMedicalLicenceNo +
                        " not in the system !!!\n");
            }
        } catch (Exception exception) {
            System.out.println("Invalid doctor medical license number!!!\n");
        }
    }

    private void showStatus() {
        try {
            System.out.println("Total Number of doctors in the centre : " + doctorList.size());
            System.out.println("Deleted doctor details...");
            for (Doctor doctor : deletedList) {
                System.out.println(doctor);
            }
        } catch (Exception exception) {
            System.out.println("Could not find Doctor Details!!!");
        }
    }

    private String getStringInput(String message) {
        System.out.println("*** " + message + " : ");
        return scan.next().toLowerCase();
    }

/*
    private String getDateInput() {
        String date = new Date();
        boolean isDate;
        do {
            System.out.println("*** " + "Enter the Date Of Birth" + " : ");
            String dob = scan.next();
            try {
                date = dateFormat.parse(dob);
                isDate = true;
            } catch (ParseException e) {
                System.out.println(dob + " is Invalid Date format!!! (hint:YYYY/MM/DD, DD/MM/YYYY)");
                isDate = false;
            }
        } while (!(isDate));

        return date;
    }
*/

    private Integer getIntInput(String message) {
        int input = 0;
        boolean isNumber = false;
        do {
            System.out.println("*** " + message + " : ");
            if (scan.hasNextInt()) {
                input = scan.nextInt();
                isNumber = true;
            } else if (!(scan.hasNextInt())) {
                System.out.println("Invalid Input Type!!!");
                isNumber = false;
                scan.next();
            }
        } while (!(isNumber));

        return input;
    }
}
