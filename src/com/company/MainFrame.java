package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class MainFrame extends JFrame {
    private JButton btnExit;
    private JPanel MainPanel;
    private JTextField txtDoctorName;
    private JTextField txtDoctorSpeciality;
    private JButton refreshButton;
    private JTextField txtConsultationId;
    private JTextField txtPatientName;
    private JTextField txtPatientSurname;
    private JTextField txtPatientDOB;
    private JTextField txtPatientMobileNo;
    private JTextField txtPatientNIC;
    private JTextField doctorsListTextField;
    private JTextField availableSlotsTextField;
    private JTextField txtCnsultationPatientName;
    private JTextField txtCnsultationDoctorName;
    private JTextField txtCnsultationCost;
    private JTextField txtCnsultationNotes;
    private JButton addPatientButton;
    private JButton resetBtnPatient;
    private JButton addConsultationButton;
    private JButton resetBtnDoctorAvailability;
    private JButton saveConsultationButton;
    private JButton button2;
    private JButton resetBtnConsultation;
    private JTable showTable;
    private JTextField txtCnsultationHours;
    private JLabel lblSkinConsultationCentre;

    public MainFrame() {
        JFrame frame = new JFrame("Skin Consultation Centre");

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(MainPanel,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        resetBtnPatient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtPatientName.setText("");
                txtPatientSurname.setText("");
                txtPatientDOB.setText("");
                txtPatientMobileNo.setText("");
                txtPatientNIC.setText("");
            }
        });
        resetBtnDoctorAvailability.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtDoctorName.setText("");
                txtDoctorSpeciality.setText("");
            }
        });
        resetBtnConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtConsultationId.setText("");
                txtCnsultationPatientName.setText("");
                txtCnsultationDoctorName.setText("");
                txtCnsultationHours.setText("");
                txtCnsultationCost.setText("");
                txtCnsultationNotes.setText("");
            }
        });
    }

    public void start() {
        setContentPane(MainPanel);
        setTitle("Skin Consultation Centre");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        createTable();
    }

    private void createTable() {
//        Object[][] doctors = new ArrayList<>();
        try {
            File myObj = new File("src/list.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
//                Object c1 = new Object("Apple","i5", 50000, "IOS");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        Object[][] data = {
                {"sam", "miller", "200/10/11", "0718138414", "8888", "sss"},
                {"den", "miller", "188/10/11", "0714785414", "7777", "eee"}
        };
        showTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Name", "Surname", "DOB", "MobileNumber", "Medical Licence Number", "Specialization"}
        ));
        TableColumnModel columns = showTable.getColumnModel();
        columns.getColumn(0).setMinWidth(200);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.LEFT);
        columns.getColumn(0).setCellRenderer(centerRenderer);
        columns.getColumn(1).setCellRenderer(centerRenderer);
        columns.getColumn(2).setCellRenderer(centerRenderer);
        columns.getColumn(3).setCellRenderer(centerRenderer);
        columns.getColumn(4).setCellRenderer(centerRenderer);
        columns.getColumn(5).setCellRenderer(centerRenderer);
    }
}
