package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SkinConsultationCentre extends JFrame {
    JPanel mainPanel = new JPanel();

    public SkinConsultationCentre() {
        super("Skin Consultation Centre");
        JLabel lblSkinConsultationCentre = title();

        BorderLayout layout = new BorderLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        mainPanel.setLayout(layout);
        mainPanel.getInsets();
        mainPanel.add(lblSkinConsultationCentre, BorderLayout.NORTH);

        /*JPanel subPanel = new JPanel();
        subPanel.setBackground(Color.RED);
        mainPanel.add(subPanel, BorderLayout.CENTER);*/

        JPanel actionPanel = new JPanel();
        GridLayout gridLayout = new GridLayout();
        gridLayout.setVgap(0);
        gridLayout.setHgap(0);
        actionPanel.setLayout(gridLayout);
        //actionPanel.setBounds(subPanel.getBounds());
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
        //subPanel.setMaximumSize(getMaximumSize());
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

        JPanel tabbedPanel = new JPanel();
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
        String[][] data = {
                {"sam", "miller", "200/10/11", "0718138414", "8888", "sss"},
                {"den", "miller", "188/10/11", "0714785414", "7777", "eee"}
        };
        String[] column = {
                "Name", "Surname", "DOB", "MobileNumber", "Medical Licence Number", "Specialization"
        };
        JTable table = new JTable(data, column);
        //table.setGridColor(Color.BLACK);
        table.setBackground(Color.green);
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
        TextField txtPatientName = new TextField("Name");
        topPanel.add(lblPatientName);
        topPanel.add(txtPatientName);

        JLabel lblPatientSurname = new JLabel("Surname");
        TextField txtPatientSurname = new TextField("Surname");
        topPanel.add(lblPatientSurname);
        topPanel.add(txtPatientSurname);

        JLabel lblPatientDOB = new JLabel("Date Of Birth");
        TextField txtPatientDOB = new TextField("Date Of Birth");
        topPanel.add(lblPatientDOB);
        topPanel.add(txtPatientDOB);

        JLabel lblPatientMobileNo = new JLabel("Mobile Number");
        TextField txtPatientMobileNo = new TextField("Mobile Number");
        topPanel.add(lblPatientMobileNo);
        topPanel.add(txtPatientMobileNo);

        JLabel lblPatientNIC = new JLabel("NIC");
        TextField txtPatientNIC = new TextField("NIC");
        topPanel.add(lblPatientNIC);
        topPanel.add(txtPatientNIC);

        JLabel lblDoctor = new JLabel("Doctor");
        TextField txtDoctor = new TextField("Doctor");
        topPanel.add(lblDoctor);
        topPanel.add(txtDoctor);

        topPanel.setLayout(new GridLayout(6,1));

        JButton btnReset = new JButton("Reset");
        JButton btnAdd = new JButton("Add Patient");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnAdd);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnReset.addActionListener(e -> {
            txtPatientName.setText("");
            txtPatientSurname.setText("");
            txtPatientDOB.setText("");
            txtPatientMobileNo.setText("");
            txtPatientNIC.setText("");
            txtDoctor.setText("");
        });

        return panel;
    }

    private JPanel doctorAvailability() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel lblDoctorName = new JLabel("Doctor Name");
        String[] doctorNamesList ={"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
        JComboBox cbDoctorNames=new JComboBox(doctorNamesList);
        topPanel.add(lblDoctorName);
        topPanel.add(cbDoctorNames);

        JLabel lblSpeciality = new JLabel("Speciality");
        TextField txtSpeciality = new TextField("Speciality");
        topPanel.add(lblSpeciality);
        topPanel.add(txtSpeciality);

        JLabel lblAvailableSlot = new JLabel("Available Slots");
        JTextArea taAvailableSlot = new JTextArea("Available Slots");
        topPanel.add(lblAvailableSlot);
        topPanel.add(taAvailableSlot);

        topPanel.setLayout(new GridLayout(6,1));

        JButton btnReset = new JButton("Reset");
        JButton btnAdd = new JButton("Add Consultation");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnAdd);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnReset.addActionListener(e -> {
            cbDoctorNames.removeAllItems();
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
        TextField txtConsultationId = new TextField("Consultation Id");
        topPanel.add(lblConsultationId);
        topPanel.add(txtConsultationId);

        JLabel lblPatientName = new JLabel("Patient Name");
        TextField txtPatientName = new TextField("Patient Name");
        topPanel.add(lblPatientName);
        topPanel.add(txtPatientName);

        JLabel lblDoctorName = new JLabel("Doctor Name");
        TextField txtDoctorName = new TextField("Doctor Name");
        topPanel.add(lblDoctorName);
        topPanel.add(txtDoctorName);

        JLabel lblConsultationHours = new JLabel("Consultation Hours");
        TextField txtConsultationHours = new TextField("Consultation Hours");
        topPanel.add(lblConsultationHours);
        topPanel.add(txtConsultationHours);

        JLabel lblCost = new JLabel("Cost");
        TextField txtCost = new TextField("Cost");
        topPanel.add(lblCost);
        topPanel.add(txtCost);

        JLabel lblNotes = new JLabel("Notes");
        TextField txtNotes = new TextField("Notes");
        topPanel.add(lblNotes);
        topPanel.add(txtNotes);

        topPanel.setLayout(new GridLayout(6,1));

        JButton btnReset = new JButton("Reset");
        JButton btnRandom = new JButton("Assign Doctor");
        JButton btnAdd = new JButton("Save Consultation");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnRandom);
        bottomPanel.add(btnAdd);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnReset.addActionListener(e -> {
            txtConsultationId.setText("");
            txtPatientName.setText("");
            txtDoctorName.setText("");
            txtConsultationHours.setText("");
            txtCost.setText("");
            txtNotes.setText("");
        });


        btnRandom.addActionListener(e -> {
            String[] doctorNamesList ={"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
            ArrayList indexArr = new ArrayList();
            for (int i = 0; i<doctorNamesList.length; i++) {
                indexArr.add(i);
            }
            txtDoctorName.setText(doctorNamesList[1]);
        });



        return panel;
    }

    public JPanel savedConsultations() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        panel.setBackground(Color.BLUE);

        String[][] data = {
                {"sam", "miller", "200/10/11", "0718138414", "8888", "sss"},
                {"den", "miller", "188/10/11", "0714785414", "7777", "eee"}
        };
        String[] column = {
                "Consultation Id", "Doctor", "Patient Name", "MobileNumber", "Medical Licence Number", "Specialization"
        };
        JTable table = new JTable(data, column);
        table.setBounds(30, 40, 200, 300);

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

    public static void main(String[] args) {
        SkinConsultationCentre skinConsultationCentre = new SkinConsultationCentre();
        skinConsultationCentre.setVisible(true);
        skinConsultationCentre.setSize(1200, 700);
        skinConsultationCentre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
