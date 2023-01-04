package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

public class Test extends JFrame {
    JPanel mainPanel = new JPanel();
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

        BorderLayout layout = new BorderLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        mainPanel.setLayout(layout);
        mainPanel.getInsets();

        JPanel actionPanel = new JPanel();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setVgap(0);
        gridLayout.setHgap(0);
        actionPanel.setLayout(gridLayout);
        mainPanel.add(actionPanel);

        JPanel consultantsPanel = consultantsPanel();
        JPanel menuPanel = menuPanel();

        actionPanel.add(menuPanel);
        actionPanel.add(consultantsPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel consultantsPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));

        JScrollPane sp = new JScrollPane(consultantsTable());

        BorderLayout layoutBl = new BorderLayout();
        mainPanel.setLayout(layoutBl);

        //mainPanel.add(heading(), BorderLayout.NORTH);
        mainPanel.add(sp, BorderLayout.CENTER);

        return mainPanel;
    }

    private JPanel menuPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel welcomePanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
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
        subPanel.add(btn1, BorderLayout.LINE_START);

        JButton btn2  = new JButton("Doctor Availability");
        subPanel.add(btn2, BorderLayout.CENTER);

        JButton btn3  = new JButton("Add consultation");
        subPanel.add(btn3, BorderLayout.LINE_END);

        welcomePanel.add(lbl, BorderLayout.NORTH);
        welcomePanel.add(lbl2, BorderLayout.CENTER);
        welcomePanel.add(subPanel, BorderLayout.SOUTH);

        //getContentPane().add(mainPanel);
        //getContentPane().add(welcomePanel);
        //welcomePanel.setBounds(mainPanel.getBounds());

        /*mainPanel.setBounds(0, 0, 100, 100000);
        welcomePanel.setBounds(0, 0, 100, 100000);

        getContentPane().add(mainPanel);
        getContentPane().add(welcomePanel);*/

        mainPanel.add(welcomePanel);



        /*btn1.addActionListener(e -> {
            mainPanel.add(panel1);
        });

        btn2.addActionListener(e -> {
            mainPanel.add(panel2);
        });

        btn3.addActionListener(e -> {
            mainPanel.add(panel3);
        });*/

        return welcomePanel;
    }

    private JLabel heading() {
        JLabel lbl = new JLabel("Consultants", JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 38));
        lbl.setForeground(new Color(0, 119, 182));
        return lbl;
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

    private JButton button(String text) {
        JButton btn = new JButton(text);
        btn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setForeground(new Color(0, 202, 240, 248));
        btn.setBackground(new Color(178, 52, 178));
        return btn;
    }

    private JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setBounds(100, 100, 300, 30);
        label.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        return label;
    }

    private JTextField textField() {
        JTextField txtField = new JTextField();
        txtField.setBackground(Color.pink);
        txtField.setForeground(Color.blue);
        txtField.setFont(new Font("Arial", Font.BOLD, 20));
        txtField.setMargin(new Insets(10, 10, 10, 10));
        return txtField;
    }

    private String savePatient(String fileName) {
        String message;
        try {
            Formatter formatter = new Formatter(fileName);
            if (patientList.size() > 0) {
                for (Patient p : patientList) {
                    formatter.format("%s", p.toFormattedString());
                }
            }
            formatter.close();
            message = "success";
        } catch (Exception exception) {
            message = "error";
        }
        return message;
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
