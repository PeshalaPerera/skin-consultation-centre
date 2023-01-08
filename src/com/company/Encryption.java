package com.company;

import javax.swing.*;
import java.util.Base64;

public class Encryption {
    public static void main(String[] args) {
        String originalInput = "test input";

//        JTextField textField = new JTextField("Image Uploaded to ...");
//        String textFieldVal = textField.getText();
//        String encodedString = Base64.getEncoder().encodeToString(textFieldVal.getBytes());

        String encodedString = String.valueOf("Tm90ZSBub3RlICAgVXBsb2FkZWQgSW1hZ2UgUGF0aDogRDpceGdlblxhd3NcQm93ZW5zXGhvbWUtYmcuanBn");
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);

//        System.out.println(originalInput + " | " + encodedString + " | "+ decodedString);
        System.out.println(decodedString);
    }
}
