package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {
    Doctor d = new Doctor("Peshala", "wer", "0718138414", "2000/10/25", 14582, "sdfg");

    @Test
    void getMedicalLicenceNo() {
        assertEquals(14582, d.getMedicalLicenceNo());
    }

    @Test
    void getSpecialization() {
        assertEquals("sdfg", d.getSpecialization());
    }

    @Test
    void testToString() {

    }

    @Test
    void toFormattedString() {
    }
}