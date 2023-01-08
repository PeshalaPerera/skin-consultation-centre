package com.company;

import java.util.Scanner;

/**
 * @author peshala
 * @version (Main)
 */

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager(System.in);
        String choice;
        do {
            showMenu();
            System.out.print("\nYour Choice : ");
            choice = input.next().toUpperCase();
            switch (choice) {
                case "A": {
                    manager.addDoctor();
                    break;
                }
                case "B": {
                    manager.deleteDoctor();
                    break;
                }
                case "C": {
                    manager.printList();
                    break;
                }
                case "D": {
                    manager.saveFile();
                    break;
                }
                case "E": {
                    manager.viewGui();
                    break;
                }
                case "X": {
                    System.out.println("Program Ended! \nThank you!");
                    break;
                }
                default: {
                    System.out.println("Invalid Input! Try Again!");
                    break;
                }
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
