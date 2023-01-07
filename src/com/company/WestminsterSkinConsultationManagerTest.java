package com.company;

import org.junit.jupiter.api.Test;

class WestminsterSkinConsultationManagerTest {
    WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

    @Test
    void addDoctor() {
        manager.addDoctor();
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