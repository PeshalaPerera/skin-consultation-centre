package com.company;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author peshala
 * @version (WestminsterSkinConsultationManagerTest)
 */

class WestminsterSkinConsultationManagerTest {

    /**
     * junit testing
     * add a doctor to the system
     */
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

    /**
     * junit testing
     * delete a doctor from the system
     */
    @Test
    void deleteDoctor() {
        String input = "44\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(inputStream);
        Doctor doctor = manager.deleteDoctor();
        assertEquals(44, doctor.getMedicalLicenceNo());
    }

    /**
     * junit testing
     * print the list of doctors in the system
     */
    @Test
    void printList() {
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(inputStream);
        manager.printList();
    }

    /**
     * junit testing
     * save the doctor arraylist to the text file
     */
    @Test
    void saveFile() {
        String input = "";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(inputStream);
        manager.saveFile();
    }

    /**
     * junit testing
     * start java swing gui
     */
    @Test
    void viewGui() {
        Gui.start();
    }
}