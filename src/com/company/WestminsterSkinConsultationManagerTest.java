package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

class WestminsterSkinConsultationManagerTest {

    @Test
    void addDoctor() {
        String input = "bhavan\n" +
                "bhavan1\n" +
                "0778421262\n" +
                "2000/10/10\n" +
                "100\n" +
                "sample";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(inputStream);
        Doctor doctor = manager.addDoctor();
        Assertions.assertEquals(100, doctor.getMedicalLicenceNo());
        Assertions.assertEquals("bhavan", doctor.getName());
        Assertions.assertEquals("bhavan1", doctor.getSurname());
        Assertions.assertEquals("sample", doctor.getSpecialization());
    }

    @Test
    void deleteDoctor() {
    }

    @Test
    void printList() {
    }

    @Test
    void saveFile() {
    }

    @Test
    void viewGui() {
        Gui.start();
    }
}