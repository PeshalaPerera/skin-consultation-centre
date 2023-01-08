package com.company;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WestminsterSkinConsultationManagerTest {

    @Test
    void addDoctor() {
        String input = "peshala\n" +
                "perera\n" +
                "0718258411\n" +
                "2000/10/25\n" +
                "100\n" +
                "sample";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(inputStream);
        Doctor doctor = manager.addDoctor();
        assertEquals(100, doctor.getMedicalLicenceNo());
        assertEquals("0718258411", doctor.getMobileNo());
        assertEquals("2000/10/25", doctor.getDob());
        assertEquals("peshala", doctor.getName());
        assertEquals("perera", doctor.getSurname());
        assertEquals("sample", doctor.getSpecialization());
    }

    @Test
    void deleteDoctor() {
        String input = "44\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(inputStream);
        Doctor doctor = manager.deleteDoctor();
        assertEquals(44, doctor.getMedicalLicenceNo());
    }

    @Test
    void printList() {
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(inputStream);
        manager.printList();
    }

    @Test
    void saveFile() {
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(inputStream);
        manager.saveFile();
    }

    @Test
    void viewGui() {
        Gui.start();
    }
}