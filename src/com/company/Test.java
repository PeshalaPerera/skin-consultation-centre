package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Test extends JFrame {
    JPanel mainPanel = new JPanel();
    JPanel welcomePanel = new JPanel();
    ArrayList<Patient> patientList = new ArrayList<>();
    ArrayList<Consultation> consultations = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");

    public static void main(String[] args) {
        Test skinConsultationCentre = new Test();
        skinConsultationCentre.setVisible(true);
        skinConsultationCentre.setSize(1300, 700);
        skinConsultationCentre.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public Test() {
        super("Skin Consultation Centre");
        JPanel mainTestPanel = new JPanel();
        BorderLayout layout = new BorderLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        mainTestPanel.setLayout(layout);
        mainTestPanel.getInsets();

        JPanel actionPanel = new JPanel();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setVgap(0);
        gridLayout.setHgap(0);
        actionPanel.setLayout(gridLayout);
        mainTestPanel.add(actionPanel);

        JPanel consultantsPanel = consultantsPanel();
        JPanel menuPanel = menuPanel();

        actionPanel.add(menuPanel);
        actionPanel.add(consultantsPanel);

        add(mainTestPanel, BorderLayout.CENTER);
    }

    private JPanel consultantsPanel() {
        JPanel mainConsultantsPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        mainConsultantsPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));

        JScrollPane sp = new JScrollPane(consultantsTable());

        BorderLayout layoutBl = new BorderLayout();
        mainConsultantsPanel.setLayout(layoutBl);

        JButton exit = new JButton("Exit");
        exit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(mainPanel,
                    "Are you sure you want to close this window?", "Close Window?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(e -> {
            dispose();
            //main();
        });

        bottomPanel.add(refresh, BorderLayout.WEST);
        bottomPanel.add(exit, BorderLayout.EAST);

        mainConsultantsPanel.add(sp, BorderLayout.CENTER);
        mainConsultantsPanel.add(exit, BorderLayout.SOUTH);

        return mainConsultantsPanel;
    }

    private JButton menu() {
        JButton menu = new JButton("MENU");
        menu.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            mainPanel.add(welcomePanel);
            mainPanel.repaint();
            mainPanel.revalidate();
        });

        return menu;
    }

    private JPanel menuPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));

        panel1.setBorder(BorderFactory.createLineBorder(new Color(0, 119, 182), 20));
        panel1.setBackground(new Color(255, 255, 255));

        panel2.setBorder(BorderFactory.createLineBorder(new Color(0, 119, 182), 20));
        panel2.setBackground(new Color(64, 182, 0));

        panel3.setBorder(BorderFactory.createLineBorder(new Color(0, 119, 182), 20));
        panel3.setBackground(new Color(227, 4, 69));
        panel3.add(new JButton());

        welcomePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
        welcomePanel.setBackground(new Color(0, 119, 182));

        JLabel lbl = new JLabel("SKIN CONSULTATION CENTRE");
        lbl.setForeground(new Color(202, 240, 248));
        lbl.setFont(new Font("Calibri", Font.BOLD, 42));

        JLabel lbl2 = new JLabel("SKIN CONSULTATION CENTRE");
        lbl2.setForeground(new Color(202, 240, 248));
        lbl2.setFont(new Font("Calibri", Font.BOLD, 38));

        JPanel subPanel = new JPanel();
        JButton btn1  = new JButton("Consultations");
        subPanel.add(new JPanel().add(btn1), BorderLayout.LINE_START);

        JButton btn2  = new JButton("Doctor Availability");
        subPanel.add(new JPanel().add(btn2), BorderLayout.CENTER);

        JButton btn3  = new JButton("Add consultation");
        subPanel.add(new JPanel().add(btn3), BorderLayout.LINE_END);

        welcomePanel.add(lbl, BorderLayout.NORTH);
        welcomePanel.add(lbl2, BorderLayout.CENTER);
        welcomePanel.add(subPanel, BorderLayout.SOUTH);

        mainPanel.setLayout(new GridLayout(1, 1));

        JButton menuBtn = new JButton("Menu");
        menuBtn.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            mainPanel.add(welcomePanel);
            mainPanel.repaint();
            mainPanel.revalidate();
        });

        panel1.add(menuBtn);
        panel2.add(menuBtn);
        panel3.add(menuBtn);
        mainPanel.add(welcomePanel);

        btn1.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            mainPanel.add(panel1);
            mainPanel.repaint();
            mainPanel.revalidate();

            JPanel addConsultation = addConsultation();
            panel1.add(addConsultation);
        });

        btn2.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            mainPanel.add(panel2);
            mainPanel.repaint();
            mainPanel.revalidate();

            JPanel doctorAvailability = doctorAvailability();
            panel2.add(doctorAvailability);
        });

        btn3.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            mainPanel.add(panel3);
            mainPanel.repaint();
            mainPanel.revalidate();

            JPanel savedConsultations = savedConsultations();
            panel3.add(savedConsultations());
        });

        return mainPanel;
    }

    private JPanel addConsultation() {
        JPanel mainAddConsultantPanel = new JPanel();

        return mainAddConsultantPanel;
    }

    private JPanel doctorAvailability() {
        JPanel mainDoctorPanel = new JPanel();

        return mainDoctorPanel;
    }

    private JPanel savedConsultations() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        mainPanel.setBackground(Color.cyan);

        ArrayList<String[]> doctorList = getFileContent("src/consultations.txt");
        String[][] data = doctorList.toArray(String[][]::new);
        String[] column = {
                "Name", "Surname", "DOB", "MobileNumber", "Medical Licence Number", "Specialization"
        };
        JTable table = new JTable(data, column);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0, 119, 182));
        table.getTableHeader().setForeground(new Color(202, 240, 248));
        table.getTableHeader().setSize(40, 40);
        table.setSelectionBackground(new Color(0, 180, 216));
        table.setGridColor(new Color(2, 62, 138, 255));
        table.setRowHeight(40);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setAutoCreateRowSorter(true);
        table.setBackground(new Color(202, 240, 248));
        table.setShowVerticalLines(true);

        JScrollPane sp = new JScrollPane(table);
        mainPanel.add(sp);

        return mainPanel;
    }

    private JTable consultantsTable() {
        ArrayList<String[]> doctorList = getFileContent("src/doctorsList.txt");
        String[][] data = doctorList.toArray(String[][]::new);
        String[] column = {
                "Name", "Surname", "DOB", "MobileNumber", "Medical Licence Number", "Specialization"
        };
        JTable table = new JTable(data, column);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 17));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0, 119, 182));
        table.getTableHeader().setForeground(new Color(202, 240, 248));
        table.getTableHeader().setSize(40, 40);
        table.setSelectionBackground(new Color(0, 180, 216));
        table.setGridColor(new Color(2, 62, 138, 255));
        table.setRowHeight(40);
        table.setFont(new Font("Arial", Font.PLAIN, 15));
        table.setAutoCreateRowSorter(true);
        table.setBackground(new Color(202, 240, 248));
        table.setShowVerticalLines(true);
        return table;
    }

    private ArrayList<String[]> getFileContent(String pathName) {
        File myObj = new File(pathName);
        ArrayList<String[]> doctorList = new ArrayList<>();
        Scanner myReader = null;
        try {
            myReader = new Scanner(myObj);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            assert myReader != null;
            if (!myReader.hasNextLine()) break;
            String data = myReader.nextLine();
            String[] arr = data.split(",");
            doctorList.add(arr);
        }
        myReader.close();
        return doctorList;
    }
}
