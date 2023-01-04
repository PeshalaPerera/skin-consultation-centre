package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class SkinConsultationCentre extends JFrame {
    JPanel mainPanel = new JPanel();
    ArrayList<Patient> patientList = new ArrayList<>();
    ArrayList<Doctor> doctorList = new ArrayList<>();
    ArrayList<Consultation> consultations = new ArrayList<>();
    JPanel tabbedPanel = new JPanel();
    Date date = new Date();
    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    public static void start() {
        SkinConsultationCentre skinConsultationCentre = new SkinConsultationCentre();
        skinConsultationCentre.setVisible(true);
        skinConsultationCentre.setSize(1300, 700);
        skinConsultationCentre.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
        //actionPanel.setBackground(Color.BLUE);
        mainPanel.add(actionPanel);

        JPanel consultantsPanel = consultantsPanel();
        JTabbedPane tabbedPane = tabbedPanel();
        tabbedPane.setFont(new Font("Cooper Black", Font.PLAIN, 25));
        UIManager.put("TabbedPane.selected", new Color(178, 52, 178));
        tabbedPane.setForeground(Color.WHITE);
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));

        tabbedPane.setBorder(BorderFactory.createEmptyBorder(20, 5, 0, 5));
        consultantsPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        actionPanel.add(tabbedPane);
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

        JScrollPane sp = new JScrollPane(consultantsTable());

        BorderLayout layoutBl = new BorderLayout();
        mainPanel.setLayout(layoutBl);

        mainPanel.add(heading(), BorderLayout.NORTH);
        mainPanel.add(sp, BorderLayout.CENTER);
        //consultantsActionPanel().setLayout(new GridLayout(1, 1));
        mainPanel.add(consultantsActionPanel(), BorderLayout.SOUTH);

        return mainPanel;
    }

    private JTabbedPane tabbedPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Add Consultation   ", addConsultation());
        tabbedPane.add("Doctor Availability", doctorAvailability());
        tabbedPane.add("Add Patient                  ", addPatientPanel());
        tabbedPane.add("Saved Consultation            ", savedConsultations());

        tabbedPane.setBackground(Color.BLUE);
        tabbedPanel.setBackground(Color.YELLOW);
        tabbedPanel.add(tabbedPane);
        return tabbedPane;
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
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(550, 450));
        table.setAutoCreateRowSorter(true);
        return table;
    }

    private JPanel consultantsActionPanel() {
        JPanel panel = new JPanel();
        JButton refresh = button("Refresh");
        JButton exit = button("   Exit   ");

        panel.add(refresh);
        panel.add(exit);

        exit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(mainPanel,
                    "Are you sure you want to close this window?", "Close Window?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        refresh.addActionListener(e -> {
            dispose();
            start();
        });

        return panel;
    }

    private JButton button(String text) {
        JButton btn = new JButton(text);
        btn.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(178, 52, 178));
        return btn;
    }

    private JPanel addPatientPanel() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel lblPatientName = label("Name");
        JTextField txtPatientName = textField();
        topPanel.add(lblPatientName);
        topPanel.add(txtPatientName);

        JLabel lblPatientSurname = label("Surname");
        JTextField txtPatientSurname = textField();
        topPanel.add(lblPatientSurname);
        topPanel.add(txtPatientSurname);

        JLabel lblPatientDOB = label("Date Of Birth");
        JLabel lblPatientDOBVal = new JLabel();
        JButton btnDate = new JButton("Date");
        topPanel.add(lblPatientDOB);
        topPanel.add(lblPatientDOBVal);
        topPanel.add(btnDate);

        JLabel lblPatientMobileNo = label("Mobile Number");
        JTextField txtPatientMobileNo = textField();
        topPanel.add(lblPatientMobileNo);
        topPanel.add(txtPatientMobileNo);

        JLabel lblPatientNIC = label("NIC");
        JTextField txtPatientNIC = textField();
        topPanel.add(lblPatientNIC);
        topPanel.add(txtPatientNIC);

        JLabel lblMessage = new JLabel();
        topPanel.add(lblMessage);
        lblMessage.setInputVerifier(txtPatientNIC.getInputVerifier());

        topPanel.setLayout(new GridLayout(6, 1));

        JButton btnReset = button("Reset");
        JButton btnAdd = button("Add Patient");

        btnDate.addActionListener(e -> {
            DatePicker datePicker = new DatePicker(this);
            lblPatientDOBVal.setText(datePicker.setPickedDate());
        });

        bottomPanel.add(btnReset);
        bottomPanel.add(btnAdd);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(ae -> {
            if (JOptionPane.showConfirmDialog(mainPanel,
                    "Are you sure you want to save patient details?", "Select an Option?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                String status = initPatient();
                if (status.equals("error")) {
                    showErrorMessageDialog();
                }

                boolean valid = true;
                if (!txtPatientMobileNo.getText().matches("\\d+(\\.\\d*)?")) {
                    txtPatientMobileNo.setText(" ");
                    valid = false;
                } else if (!txtPatientNIC.getText().matches("\\d+(\\.\\d*)?")) {
                    txtPatientNIC.setText(" ");
                    valid = false;
                }

                if (valid) {
                    String txtPatientNameValue = txtPatientName.getText();
                    String txtPatientSurnameValue = txtPatientSurname.getText();
                    String txtPatientDOBValue = lblPatientDOBVal.getText();
                    String txtPatientMobileNoValue = txtPatientMobileNo.getText();
                    int txtPatientNICValue = parseInt(txtPatientNIC.getText());

                    Patient patient = new Patient(txtPatientNameValue, txtPatientSurnameValue, txtPatientMobileNoValue, txtPatientDOBValue, txtPatientNICValue);
                    patientList.add(patient);

                    String message = savePatient("src/patientsList.txt");
                    if (message.equals("success")) {
                        showMessageDialog("Patient Details Added Successfully...");
                        btnReset.doClick();
                    } else {
                        showErrorMessageDialog();
                    }
                } else {
                    lblMessage.setText("Invalid Input");
                    lblMessage.setSize(1000, 300);
                    lblMessage.setForeground(new Color(255, 0, 0, 255));
                    lblMessage.setFont(new Font("Arial", Font.BOLD, 20));
                }
            }
        });

        btnReset.addActionListener(e -> {
            txtPatientName.setText("");
            txtPatientSurname.setText("");
            lblPatientDOBVal.setText("");
            txtPatientMobileNo.setText("");
            txtPatientNIC.setText("");
            lblMessage.setText("");
        });

        return panel;
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

    private String saveConsultation(String fileName) {
        String message;
        try {
            Formatter formatter = new Formatter(fileName);
            if (consultations.size() > 0) {
                for (Consultation consultation : consultations) {
                    formatter.format("%s", consultation.toString());
                }
            }
            formatter.close();
            message = "success";
        } catch (Exception exception) {
            message = "error";
        }
        return message;
    }

    private String initPatient() {
        String status;
        try {
            File myObj = new File("src/patientsList.txt");
            Scanner myReader = new Scanner(myObj);
            patientList.clear();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                Patient initPatient = new Patient(arr[0], arr[1], arr[2], arr[3], parseInt(arr[4]));
                patientList.add(initPatient);
            }
            System.out.println(patientList);
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
        JPanel bottomPanel = new JPanel();
        JPanel subPanel = new JPanel();
        JPanel miniPanel = new JPanel();
        JPanel firstPanel = new JPanel();
        JPanel secondPanel = new JPanel();
        JPanel thirdPanel = new JPanel();
        JPanel fourthPanel = new JPanel();

        JLabel lblTop = new JLabel("Add Doctor Available Slots", JLabel.CENTER);
        lblTop.setFont(new Font("Cooper Black", Font.PLAIN, 22));
        lblTop.setForeground(Color.GRAY);

        topPanel.add(lblTop);

        JLabel lblDoctorName1 = label("Doctor Name");
        String[] doctorNamesList1 = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
        JComboBox cbDoctorNames1 = new JComboBox(doctorNamesList1);
        firstPanel.add(lblDoctorName1);
        firstPanel.add(cbDoctorNames1);

        JLabel test3 = label("Available Times");
        String[] test = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
        JComboBox test2 = new JComboBox(test);
        firstPanel.add(test3);
        firstPanel.add(test2);

        JButton btnReset1 = button("Reset");
        secondPanel.add(btnReset1);

        JLabel lbl = new JLabel();
        secondPanel.add(lbl);

        JButton update = button("Update");
        secondPanel.add(update);

        subPanel.setLayout(new GridLayout(2, 1));
        miniPanel.setLayout(new GridLayout(2, 1));
        firstPanel.setLayout(new GridLayout(5, 1));
        thirdPanel.setLayout(new GridLayout(5, 1));
        subPanel.add(firstPanel);
        subPanel.add(secondPanel);
        topPanel.add(subPanel);

        JLabel lblBottom = new JLabel("Check Doctor Availability", JLabel.CENTER);
        lblBottom.setFont(new Font("Cooper Black", Font.PLAIN, 22));
        lblBottom.setForeground(Color.GRAY);

        bottomPanel.add(lblBottom);

        JLabel lblDoctor = label("Doctor Name");
        String[] doctors = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
        JComboBox cbDoctor = new JComboBox(doctors);
        thirdPanel.add(lblDoctor);
        thirdPanel.add(cbDoctor);

        JLabel times = label("Available Time Slots");
//        String[] doctors2 = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
        String[] doctors2 = doctorDropdown();
        JComboBox cbTimes = new JComboBox(doctors2);

        thirdPanel.add(times);
        thirdPanel.add(cbTimes);

        JButton refresh = button("Refresh");
        fourthPanel.add(refresh);

        miniPanel.add(thirdPanel);
        miniPanel.add(fourthPanel);
        bottomPanel.add(miniPanel);

        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));

        bottomPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.magenta));

        panel.setLayout(new GridLayout(1, 2));
        panel.add(topPanel);
        panel.add(bottomPanel);

        btnReset1.addActionListener(e -> {
            cbDoctorNames1.removeAllItems();
            test2.removeAllItems();
        });

        refresh.addActionListener(e -> {
            dispose();
            start();
        });

        return panel;
    }

    private JPanel addConsultation() {
        JPanel panel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        JLabel lblPatientName = label("Patient Name");
        JTextField txtPatientName = textField();
        topPanel.add(lblPatientName);
        topPanel.add(txtPatientName);

        JLabel lblDoctorName = label("Doctor Name");
        JTextField txtDoctorName = textField();
        topPanel.add(lblDoctorName);
        topPanel.add(txtDoctorName);

        JLabel lblConsultationHours = label("Consultation Hours");
        JTextField txtConsultationHours = textField();
        topPanel.add(lblConsultationHours);
        topPanel.add(txtConsultationHours);

        JTextField txtCost = textField();

        JLabel lblNotes = label("Notes");
        JTextField txtNotes = textField();
        topPanel.add(lblNotes);
        topPanel.add(txtNotes);

        JButton upload = new JButton("Upload");
        JLabel jLabelImage = new JLabel();
        topPanel.add(upload);
        topPanel.add(jLabelImage);

        upload.addActionListener(ae -> {
            // TODO add your handling code here:
            JFileChooser browseImageFile = new JFileChooser();
            //Filter image extensions
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
            browseImageFile.addChoosableFileFilter(fnef);
            int showOpenDialogue = browseImageFile.showOpenDialog(null);

            if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
                File selectedImageFile = browseImageFile.getSelectedFile();
                String selectedImagePath = selectedImageFile.getAbsolutePath();
                JOptionPane.showMessageDialog(null, selectedImagePath);
                //Display image on jlable
                ImageIcon ii = new ImageIcon(selectedImagePath);
                //Resize image to fit jlabel
                Image image = ii.getImage().getScaledInstance(jLabelImage.getWidth(), jLabelImage.getHeight(), Image.SCALE_SMOOTH);

                jLabelImage.setIcon(new ImageIcon(image));
            }
        });

        JLabel lblMessage = new JLabel();
        topPanel.add(lblMessage);

        topPanel.setLayout(new GridLayout(6, 1));

        JButton btnReset = button("Reset");
        JButton btnRandom = button("Assign Doctor");
        JButton btnAdd = button("Add Consultation");
        JButton btnAddNew = button("Add Another Consultation");
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
                String status = initConsultations("src/consultations.txt");
                if (status == "error") {
                    lblMessage.setText("An Error Occurred!!!");
                }

                String txtDoctorNameValue = txtDoctorName.getText();
                String txtPatientNameValue = txtPatientName.getText();
                Integer txtConsultationHoursValue = Integer.parseInt(txtConsultationHours.getText());
                Double consultationCost = costCalculator(txtConsultationHoursValue, txtPatientNameValue);
                txtCost.setText(String.valueOf(consultationCost));
                double txtCostValue = Double.parseDouble(txtCost.getText());
                String txtNotesValue = txtNotes.getText();

                Consultation consultation = new Consultation(getDoctorByName(txtDoctorNameValue, doctorList), getPatientByName(txtPatientNameValue, patientList), new Date(), txtCostValue, txtNotesValue);
                consultations.add(consultation);

                String message = saveConsultation("src/consultations.txt");
                if (message.equals("success")) {
                    showMessageDialog("Consultation Added Successfully...");
                    btnReset.doClick();
                    btnAdd.setVisible(false);
                    btnAddNew.setVisible(true);
                } else {
                    showErrorMessageDialog();
                }
            }
        });

        btnReset.addActionListener(e -> {
            txtPatientName.setText("");
            txtDoctorName.setText("");
            txtConsultationHours.setText("");
            txtCost.setText("");
            txtNotes.setText("");
        });

        btnRandom.addActionListener(e -> {
            initDoctor();
            /*String[] doctorNamesList = {"Doctor4", "Doctor2", "Doctor3", "Doctor4", "Doctor5", "Doctor6"};
            ArrayList indexArr = new ArrayList();
            for (Doctor doctor:doctorList) {

            }*/
            System.out.println(doctorList);
            if (doctorList.isEmpty()) {
                System.out.println("Error");
            } else {
                int randomIndex = (int) (Math.random() * doctorList.size());
                System.out.println("Random Doctor: " + doctorList.get(randomIndex));
                /*txtDoctorName.setText(doctorNamesList[1]);*/
            }

        });

        btnAddNew.addActionListener(e -> {
            btnReset.doClick();
            btnAddNew.setVisible(false);
            btnAdd.setVisible(true);
            btnAdd.setText("Save Consultation");
        });

        return panel;
    }

    private Double costCalculator(Integer hours, String name) {
        Integer cost;
        Patient patientAvailable = getPatientByName(name, patientList);
        if (null == patientAvailable) {
            cost = hours * 15;
        } else {
            cost = hours * 25;
        }
        return Double.parseDouble(String.valueOf(cost));
    }

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
                //Consultation initConsultation = new Consultation(arr[0], arr[1], arr[2], arr[3], arr[4]);
                //consultations.add(initConsultation);
            }
            myReader.close();
            status = "success";
            initDoctor();
            initPatient();
        } catch (Exception exception) {
            status = "error";
        }
        return status;
    }


    public JPanel savedConsultations() {
        JPanel panel = new JPanel();

        ArrayList<String[]> savedConsultations = getFileContent("src/consultations.txt");
        //String[][] data = savedConsultations.toArray(String[][]::new);
        String[][] data = {
                {"Consultation Id", "Doctor", "Patient Name", "Patient Surname", "Patient Mobile Number", "Patient DOB", "Patient Id", "Date/Time", "Cost", "Notes"},
                {"Consultation Id", "Doctor", "Patient Name", "Patient Surname", "Patient Mobile Number", "Patient DOB", "Patient Id", "Date/Time", "Cost", "Notes"},
        };
        String[] column = {
                "Consultation Id", "Doctor", "Patient Name", "Patient Surname", "Patient Mobile Number", "Patient DOB", "Patient Id", "Date/Time", "Cost", "Notes"
        };
        JTable table = new JTable(data, column);
        table.setFillsViewportHeight(true);
        table.setFont(new Font("Arial", Font.PLAIN, 20));
        table.setRowHeight(50);
        table.setPreferredScrollableViewportSize(new Dimension(550, 550));
        table.setAutoCreateRowSorter(true);

        JScrollPane sp = new JScrollPane(table);
        sp.setFont(new Font("Arial", Font.PLAIN, 20));

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        panel.add(sp);

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

    private void showMessageDialog(String message) {
        JLabel lblMessage = new JLabel();
        lblMessage.setText(message);
        lblMessage.setSize(1000, 300);
        lblMessage.setForeground(new Color(4, 88, 2, 255));
        lblMessage.setFont(new Font("Arial", Font.BOLD, 25));
        JOptionPane.showMessageDialog(mainPanel, lblMessage);
    }

    private void showErrorMessageDialog() {
        JLabel lblMessage = new JLabel();
        lblMessage.setText("An Error Occurred!!!");
        lblMessage.setSize(1000, 300);
        lblMessage.setForeground(new Color(255, 0, 0, 255));
        lblMessage.setFont(new Font("Arial", Font.BOLD, 25));
        JOptionPane.showMessageDialog(mainPanel, lblMessage,
                "Error", JOptionPane.WARNING_MESSAGE);
    }

    private Patient getPatientByName(String name, ArrayList<Patient> patientList) {
        for (Patient patient : patientList) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

    private Doctor getDoctorByName(String name, ArrayList<Doctor> doctorList) {
        for (Doctor doctor : doctorList) {
            if (doctor.getName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }

    private void initDoctor() {
        try {
            File myObj = new File("src/doctorsList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                Doctor doctor = new Doctor(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), arr[5]);
                doctorList.add(doctor);
            }
            System.out.println(doctorList);
            myReader.close();
            System.out.println("Doctors List Initialised!\n");
        } catch (Exception exception) {
            System.out.println("Could not load the data!!!");
        }
    }

    private String[] doctorDropdown() {
        String[] names = doctorList.toArray(new String[1]);
        return names;
    }

}
