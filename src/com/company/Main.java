package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        String choice;
        do {
            showMenu();
            System.out.print("\nYour Choice : ");
            choice = input.next().toUpperCase();
            switch (choice) {
                case "A" -> {
                    manager.addDoctor();
                }
                case "B" -> {
                    manager.deleteDoctor();
                }
                case "C" -> {
                    manager.printList();
                }
                case "D" -> {
                    manager.saveFile();
                }
                case "E" -> {
                    manager.viewGui();
                }
                case "X" -> System.out.println("Program Ended! \nThank you!");
                default -> System.out.println("Invalid Input! Try Again!");
            }
        } while (!choice.equals("X"));

    }

    private static void showMenu() {
        System.out.println(
                "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -\n" +
                        "*-----------------------------------------------------*\n" +
                        "|          SKIN CONSULTATION CENTRE                   |\n" +
                        "*-----------------------------------------------------*\n" +
                        "|            A. ADD A NEW DOCTOR                      |\n" +
                        "|            B. DELETE A DOCTOR                       |\n" +
                        "|            C. PRINT THE LIST OF DOCTORS             |\n" +
                        "|            D. SAVE IN A FILE                        |\n" +
                        "|            E. GUI                                   |\n" +
                        "|            X. EXIT                                  |\n" +
                        "*-----------------------------------------------------*"
        );
    }
}
