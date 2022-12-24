package com.company;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    private JPanel mainPanel;
    private JLabel lblSkinConsultationCentre;

    public Calculator(){
        super("Skin Consultation Centre");
        // Panel mainPanel for the numbers
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 3, -1, -1));
        mainPanel.setBackground(Color.blue);
//        mainPanel.add(lblSkinConsultationCentre);

        for (int i =1; i <= 9; i ++){
            JButton button = new JButton (Integer.toString(i));
            //set the color for the buttons
            button.setBackground(Color.RED);
            // if you use MAC
            button.setOpaque(true);
            button.setBorderPainted(false);

            //set the color and chenge the font
            button.setForeground(Color.blue);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            mainPanel.add(button);
        }

        JButton zeroButton = new JButton("0");
        mainPanel.add(zeroButton);
        JButton dotButton = new JButton(".");
        mainPanel.add (dotButton);

        // set color for "zero" and "."
        zeroButton.setBackground(Color.RED);
        zeroButton.setOpaque(true);
        zeroButton.setBorderPainted(false);
        zeroButton.setForeground(Color.blue);
        zeroButton.setFont(new Font("Arial", Font.BOLD, 20));
        dotButton.setBackground(Color.RED);
        dotButton.setOpaque(true);
        dotButton.setBorderPainted(false);
        dotButton.setForeground(Color.blue);
        dotButton.setFont(new Font("Arial", Font.BOLD, 20));


        //panel p2 for the operations
        JPanel p2 = new JPanel();
        p2.setBackground(Color.blue);
        p2.setLayout(new GridLayout(4, 1, 2, 2));

        JButton plusButton = new JButton("+");
        p2.add(plusButton);
        JButton minusButton = new JButton("-");
        p2.add(minusButton);
        JButton clearButton = new JButton("Clear");
        p2.add(clearButton);

        // set color for "+", "-" and "Clear"
        plusButton.setBackground(Color.green);
        plusButton.setOpaque(true);
        plusButton.setBorderPainted(false);
        plusButton.setForeground(Color.blue);
        plusButton.setFont(new Font("Arial", Font.BOLD, 20));
        minusButton.setBackground(Color.green);
        minusButton.setOpaque(true);
        minusButton.setBorderPainted(false);
        minusButton.setForeground(Color.blue);
        minusButton.setFont(new Font("Arial", Font.BOLD, 20));
        clearButton.setBackground(Color.green);
        clearButton.setOpaque(true);
        clearButton.setBorderPainted(false);
        clearButton.setForeground(Color.blue);
        clearButton.setFont(new Font("Arial", Font.BOLD, 20));

        //panel p3 that combines mainPanel and p2
        JPanel p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 2));
        p3.add(mainPanel);
        p3.add(p2);

        // in the content frame (Which has by default a Border layout) we combined p3 and the textfiled
        JTextField txtField = new JTextField();
        txtField.setBackground(Color.yellow);
        //clearButton.setOpaque(true);
        //clearButton.setBorderPainted(false);
        txtField.setForeground(Color.blue);
        txtField.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(p3, BorderLayout.CENTER);
        this.add(txtField, BorderLayout.NORTH);



    }


    public static void main(String[] args) {
        // TODO code application logic here
        Calculator skinConsultationCentre = new Calculator();
        skinConsultationCentre.setVisible(true);
        skinConsultationCentre.setSize(1200, 700);
        skinConsultationCentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
