package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class SkinConsultationCentre extends JFrame {
    JPanel mainPanel = new JPanel();
    ArrayList<Patient> patientList = new ArrayList<>();
    ArrayList<Consultation> consultations = new ArrayList<>();
    JPanel tabbedPanel = new JPanel();

    public static void start(ArrayList<Doctor> doctorList) {
        SkinConsultationCentre skinConsultationCentre = new SkinConsultationCentre();
        skinConsultationCentre.setVisible(true);
        skinConsultationCentre.setSize(1200, 700);
        skinConsultationCentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public SkinConsultationCentre() {
        super("Skin Consultation Centre");
        JLabel lblSkinConsultationCentre = title();

        BorderLayout layout = new BorderLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        mainPanel.setLayout(layout);
        mainPanel.getInsets();
        mainPanel.add(lblSkinConsultationCentre, BorderLayout.NORTH);

        JPanel actionPanel = new JPanel();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setVgap(0);
        gridLayout.setHgap(0);
        actionPanel.setLayout(gridLayout);
        actionPanel.setBackground(Color.BLACK);
        mainPanel.add(actionPanel);

        JPanel consultantsPanel = consultantsPanel();
        JPanel tabbedPanel = tabbedPanel();

        actionPanel.add(tabbedPanel);
        actionPanel.add(consultantsPanel);

        add(mainPanel);
    }

    private JLabel title() {
        JLabel lbl = new JLabel("Skin Consultation Centre", JLabel.CENTER);
        lbl.setFont(new Font("Cooper Black", Font.PLAIN, 48));
        lbl.setForeground(new Color(178, 52, 178));
        return lbl;
    }

    private JPanel consultantsPanel() {
        JPanel mainPanel = new JPanel();
        JPanel subPanel = new JPanel();
        JPanel subActionPanel = consultantsActionPanel();
        JLabel label = heading();

        JScrollPane sp = new JScrollPane(consultantsTable());
        subPanel.add(sp);
        subPanel.setVisible(true);
        subPanel.setBackground(Color.cyan);

        BorderLayout layoutBl = new BorderLayout();
        mainPanel.setLayout(layoutBl);

        mainPanel.setBackground(Color.BLUE);
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(subPanel, BorderLayout.CENTER);
        mainPanel.add(subActionPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JPanel tabbedPanel() {
        JPanel doctorAvailability = doctorAvailability();
        JPanel addConsultation = addConsultation();
        JPanel addPatientPanel = addPatientPanel();
        JPanel savedConsultations = savedConsultations();


        JTabbedPane tabbedPane = new JTabbedPane();
        //tabbedPane.setBounds(tabbedPanel.getBounds());
        tabbedPane.add("Doctor Availability", doctorAvailability);
        tabbedPane.add("Add Consultation", addConsultation);
        tabbedPane.add("Add Patient", addPatientPanel);
        tabbedPane.add("Saved Consultation", savedConsultations);

        tabbedPane.setBackground(Color.RED);
        tabbedPanel.setBackground(Color.YELLOW);
        tabbedPanel.setSize(30000, 80000);
        //tabbedPanel.setMaximumSize(getMaximumSize());
        tabbedPanel.add(tabbedPane);
        return tabbedPanel;
    }

    private JLabel heading() {
        JLabel lbl = new JLabel("Consultants", JLabel.CENTER);
        lbl.setFont(new Font("Cooper Black", Font.PLAIN, 38));
        lbl.setForeground(new Color(23, 51, 194));
        return lbl;
    }

    private JTable consultantsTable() {
        ArrayList<String[]> doctorList = getFileContent("src/doctorsList.txt");
        String[][] data = doctorList.toArray(String[][]::new);
        String[] column = {
                "Name", "Surname", "DOB", "MobileNumber", "Medical Licence Number", "Specialization"
        };
        JTable table = new JTable(data, column);
        table.setBackground(Color.green);
        table.setAutoCreateRowSorter(true);
        return table;
    }

    private JPanel consultantsActionPanel() {
        JPanel panel = new JPanel();
        JButton exit = new JButton("Exit");
        panel.add(new JButton("Refresh"));
        panel.add(exit);

        exit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(mainPanel,
                    "Are you sure you want to close this window?", "Close Window?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        return panel;
    }

    private JPanel addPatientPanel() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        panel.setBackground(Color.MAGENTA);

        JLabel lblPatientName = new JLabel("Name");
        TextField txtPatientName = new TextField();
        topPanel.add(lblPatientName);
        topPanel.add(txtPatientName);

        JLabel lblPatientSurname = new JLabel("Surname");
        TextField txtPatientSurname = new TextField();
        topPanel.add(lblPatientSurname);
        topPanel.add(txtPatientSurname);

        JLabel lblPatientDOB = new JLabel("Date Of Birth");
        TextField txtPatientDOB = new TextField();
        topPanel.add(lblPatientDOB);
        topPanel.add(txtPatientDOB);

        JLabel lblPatientMobileNo = new JLabel("Mobile Number");
        TextField txtPatientMobileNo = new TextField();
        topPanel.add(lblPatientMobileNo);
        topPanel.add(txtPatientMobileNo);

        JLabel lblPatientNIC = new JLabel("NIC");
        TextField txtPatientNIC = new TextField();
        topPanel.add(lblPatientNIC);
        topPanel.add(txtPatientNIC);

        JLabel lblMessage = new JLabel();
        topPanel.add(lblMessage);

        topPanel.setLayout(new GridLayout(6, 1));

        JButton btnReset = new JButton("Reset");
        JButton btnAdd = new JButton("Add Patient");
        JButton btnAddNew = new JButton("Add Another Patient");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnAdd);
        bottomPanel.add(btnAddNew);

        btnAddNew.setVisible(false);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(ae -> {
            if (JOptionPane.showConfirmDialog(mainPanel,
                    "Are you sure you want to save patient details?", "Select an Option?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                String status = initPatient("src/patientsList.txt");
                if (status == "error") {
                    lblMessage.setText("An Error Occurred!!!");
                }

                String txtPatientNameValue = txtPatientName.getText();
                String txtPatientSurnameValue = txtPatientSurname.getText();
                String txtPatientDOBValue = txtPatientDOB.getText();
                String txtPatientMobileNoValue = txtPatientMobileNo.getText();
                int txtPatientNICValue = Integer.parseInt(txtPatientNIC.getText());

                Patient patient = new Patient(txtPatientNameValue, txtPatientSurnameValue, txtPatientMobileNoValue, txtPatientDOBValue, txtPatientNICValue);
                patientList.add(patient);

                String message = savePatient("src/patientsList.txt");
                if (message == "success") {
                    lblMessage.setText("Patient Details Added Successfully...");
                    btnAdd.setVisible(false);
                    btnAddNew.setVisible(true);
                } else {
                    lblMessage.setText("An Error Occurred!!!");
                }
            }
        });

        btnReset.addActionListener(e -> {
            txtPatientName.setText("");
            txtPatientSurname.setText("");
            txtPatientDOB.setText("");
            txtPatientMobileNo.setText("");
            txtPatientNIC.setText("");
            lblMessage.setText("");
        });

        btnAddNew.addActionListener(e -> {
            btnReset.doClick();
            btnAddNew.setVisible(false);
            btnAdd.setVisible(true);
            btnAdd.setText("Save Patient");
        });

        return panel;
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

    private String initPatient(String pathName) {
        String status;
        try {
            File myObj = new File(pathName);
            Scanner myReader = null;
            try {
                myReader = new Scanner(myObj);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                Patient initPatient = new Patient(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]));
                patientList.add(initPatient);
            }
            myReader.close();
            status = "success";
        } catch (Exception exception) {
            status = "error";
        }
        return status;
    }

    private JPanel doctorAvailability() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel lblTop = new JLabel("Add Doctor Available Slots", JLabel.CENTER);
        lblTop.setFont(new Font("Cooper Black", Font.PLAIN, 20));

        JLabel lblDoctorName1 = new JLabel("Doctor Name");
        String[] doctorNamesList1 = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
        JComboBox cbDoctorNames1 = new JComboBox(doctorNamesList1);
        topPanel.add(lblTop);
        topPanel.add(lblDoctorName1);
        topPanel.add(cbDoctorNames1);

        JLabel test3 = new JLabel("Available Times");
        String[] test = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
        JComboBox test2 = new JComboBox(test);
        topPanel.add(test3);
        topPanel.add(test2);

        JLabel lblMiddle = new JLabel("Check Doctor Availability", JLabel.CENTER);
        lblMiddle.setFont(new Font("Cooper Black", Font.PLAIN, 20));
        middlePanel.add(lblMiddle);

        JLabel lblDoctorName2 = new JLabel("Doctor Name");
        String[] doctorNamesList2 = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
        JComboBox cbDoctorNames2 = new JComboBox(doctorNamesList2);
        middlePanel.add(lblDoctorName2);
        middlePanel.add(cbDoctorNames2);

        JLabel lblSpeciality = new JLabel("Speciality");
        TextField txtSpeciality = new TextField();
        middlePanel.add(lblSpeciality);
        middlePanel.add(txtSpeciality);

        JLabel lblAvailableSlot = new JLabel("Available Slots");
        JTextArea taAvailableSlot = new JTextArea();
        middlePanel.add(lblAvailableSlot);
        middlePanel.add(taAvailableSlot);

        JButton btnReset = new JButton("Reset");
        JButton btnAdd = new JButton("Add Consultation");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnAdd);

        topPanel.setLayout(new GridLayout(6, 1));
        middlePanel.setLayout(new GridLayout(6, 1));

        BorderLayout layout = new BorderLayout(5, 5);
        panel.setLayout(layout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(middlePanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnReset.addActionListener(e -> {
            cbDoctorNames1.removeAllItems();
            txtSpeciality.setText("");
            taAvailableSlot.setText("");
        });

        panel.setBackground(Color.CYAN);

        return panel;
    }

    private JPanel addConsultation() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        panel.setBackground(Color.GREEN);
        JLabel lblConsultationId = new JLabel("Consultation Id");
        TextField txtConsultationId = new TextField();
        topPanel.add(lblConsultationId);
        topPanel.add(txtConsultationId);

        JLabel lblPatientName = new JLabel("Patient Name");
        TextField txtPatientName = new TextField();
        topPanel.add(lblPatientName);
        topPanel.add(txtPatientName);

        JLabel lblDoctorName = new JLabel("Doctor Name");
        TextField txtDoctorName = new TextField();
        topPanel.add(lblDoctorName);
        topPanel.add(txtDoctorName);

        JLabel lblConsultationHours = new JLabel("Consultation Hours");
        TextField txtConsultationHours = new TextField();
        topPanel.add(lblConsultationHours);
        topPanel.add(txtConsultationHours);

        JLabel lblCost = new JLabel("Cost");
        TextField txtCost = new TextField();
        topPanel.add(lblCost);
        topPanel.add(txtCost);

        JLabel lblNotes = new JLabel("Notes");
        TextField txtNotes = new TextField();
        topPanel.add(lblNotes);
        topPanel.add(txtNotes);

        JLabel lblMessage = new JLabel("Consultation Hours");

        topPanel.setLayout(new GridLayout(6, 1));

        JButton btnReset = new JButton("Reset");
        JButton btnRandom = new JButton("Assign Doctor");
        JButton btnAdd = new JButton("Add Consultation");
        JButton btnAddNew = new JButton("Add Another Consultation");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnRandom);
        bottomPanel.add(btnAdd);

        btnAddNew.setVisible(false);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(ae -> {
            if (JOptionPane.showConfirmDialog(mainPanel,
                    "Are you sure you want to save consultation?", "Select an Option?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                //String status = initConsultations("src/consultations.txt");
                /*if (status == "error") {
                    lblMessage.setText("An Error Occurred!!!");
                }*/

                String txtConsultationIdValue = txtConsultationId.getText();
                String txtDoctorNameValue = txtDoctorName.getText();
                String txtPatientNameValue = txtPatientName.getText();
                String txtConsultationHoursValue = txtConsultationHours.getText();
                //Double consultationCost = costCalculator(txtConsultationHoursValue);
                //txtCost.setText(String.valueOf(consultationCost));
                double txtCostValue = Double.parseDouble(txtCost.getText());
                String txtNotesValue = txtNotes.getText();



                //Consultation consultation = new Consultation(txtDoctorNameValue, txtPatientNameValue, txtConsultationHoursValue, txtCostValue, txtNotesValue);
                //consultations.add(consultation);

                String message = savePatient("src/consultations.txt");
                if (message == "success") {
                    lblMessage.setText("Consultation Added Successfully...");
                    btnAdd.setVisible(false);
                    btnAddNew.setVisible(true);
                } else {
                    lblMessage.setText("An Error Occurred!!!");
                }
            }
        });

        btnReset.addActionListener(e -> {
            txtConsultationId.setText("");
            txtPatientName.setText("");
            txtDoctorName.setText("");
            txtConsultationHours.setText("");
            txtCost.setText("");
            txtNotes.setText("");
        });

        btnRandom.addActionListener(e -> {
            String[] doctorNamesList = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
            ArrayList indexArr = new ArrayList();
            for (int i = 0; i < doctorNamesList.length; i++) {
                indexArr.add(i);
            }
            txtDoctorName.setText(doctorNamesList[1]);
        });

        btnAddNew.addActionListener(e -> {
            btnReset.doClick();
            btnAddNew.setVisible(false);
            btnAdd.setVisible(true);
            btnAdd.setText("Save Consultation");
        });

        return panel;
    }

    /*private Double costCalculator(String hours) {
        Integer cost;
        if (!patient) {
            cost = Integer.parseInt(hours) * 15;
        } else {
            cost = Integer.parseInt(hours) * 25;
        }
        return Double.parseDouble(String.valueOf(cost));
    }*/

/*
    private String initConsultations(String pathName) {
        String status;
        try {
            File myObj = new File(pathName);
            Scanner myReader = null;
            try {
                myReader = new Scanner(myObj);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                Consultation initConsultation = new Consultation(arr[0], arr[1], arr[2], arr[3], arr[4]);
                consultations.add(initConsultation);
            }
            myReader.close();
            status = "success";
        } catch (Exception exception) {
            status = "error";
        }
        return status;
    }
*/

    public JPanel savedConsultations() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        panel.setBackground(Color.BLUE);

        ArrayList<String[]> savedConsultations = getFileContent("src/doctorsList.txt");
        String[][] data = savedConsultations.toArray(String[][]::new);
        String[] column = {
                "Consultation Id", "Doctor", "Patient Name", "MobileNumber", "Medical Licence Number", "Specialization"
        };
        JTable table = new JTable(data, column);
        table.setBounds(30, 40, 200, 300);
        table.setAutoCreateRowSorter(true);

        JScrollPane sp = new JScrollPane(table);
        topPanel.add(sp);
        topPanel.setVisible(true);

        JButton btnAddNew = new JButton("Add New Consultation");

        bottomPanel.add(btnAddNew);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        return panel;
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
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] arr = data.split(",");
            doctorList.add(arr);
        }
        myReader.close();
        return doctorList;
    }

}
