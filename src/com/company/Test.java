package com.company;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Test {
    public static void showMessageDialog() {
        JFrame optionFrame = new JFrame();
        JOptionPane.showMessageDialog(optionFrame, "Hello, Welcome to Javatpoint.");
    }

    public static void showWarningDialog() {
        JFrame warningOptionFrame = new JFrame();
        JOptionPane.showMessageDialog(warningOptionFrame, "Successfully Updated.", "Alert", JOptionPane.WARNING_MESSAGE);
    }

    public static void showInputDialog() {
        JFrame inputOptionFrame = new JFrame();
        String name = JOptionPane.showInputDialog(inputOptionFrame, "Enter Name");
    }

    public static void main(String[] args) {
        //creating instance of JFrame
        JFrame frame = new JFrame("Skin Consultation Centre");

        TitledBorder border = new TitledBorder("This is my title");
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitlePosition(TitledBorder.TOP);

        JPanel panel = new JPanel();
        panel.setBorder(border);

        frame.add(panel);

        //creating instance of JTable
        String data[][] = {{"101", "Amit", "670000"}, {"102", "Jai", "780000"}, {"101", "Sachin", "700000"}};
        String column[] = {"ID", "NAME", "SALARY"};
        JTable table = new JTable(data, column);
        table.setBounds(100, 80, 200, 300);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);

        //creating instance of JComboBox
        String country[] = {"India", "Aus", "U.S.A", "England", "Newzealand"};
        JComboBox comboBox = new JComboBox(country);
        comboBox.setBounds(50, 250, 90, 20);
        frame.add(comboBox);

        //creating instance of JFrame
        JRadioButton radioButton1 = new JRadioButton("A) Male");
        JRadioButton radioButton2 = new JRadioButton("B) Female");
        radioButton1.setBounds(75, 270, 100, 30);
        radioButton2.setBounds(75, 180, 100, 30);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        frame.add(radioButton1);
        frame.add(radioButton2);

        //creating instance of JCheckBox
        JCheckBox checkBox = new JCheckBox("C++");
        checkBox.setBounds(80, 500, 50, 50);
        frame.add(checkBox);

        //creating instance of JTextField
        JTextField textField = new JTextField("Welcome to Javatpoint.");
        textField.setBounds(50, 100, 200, 30);
        textField.setBounds(50, 150, 200, 30);
        frame.add(textField);

        //creating instance of JTextArea
        JTextArea textArea = new JTextArea("Welcome to javatpoint");
        textArea.setBounds(10, 30, 200, 20);
        frame.add(textArea);

        //creating instance of JLabel
        JLabel label = new JLabel("Skin Consultation Centre");
        label.setBounds(130, 30, 500, 80);
        frame.add(label);

        //creating instance of JButton
        JButton button = new JButton("click");
        //x axis, y axis, width, height
        button.setBounds(130, 100, 100, 40);
        //adding button in JFrame
        frame.add(button);

        //800 width and 600 height
        frame.setSize(1200, 700);
        //using no layout managers
        frame.setLayout(null);
        //making the frame visible
        frame.setVisible(true);

        showMessageDialog();
        showWarningDialog();
        showInputDialog();
    }
}
