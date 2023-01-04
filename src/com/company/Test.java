package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

public class Test extends JFrame {
    JPanel mainPanel = new JPanel();
    JPanel welcomePanel = new JPanel();
    ArrayList<Patient> patientList = new ArrayList<>();
    ArrayList<Consultation> consultations = new ArrayList<>();
    ArrayList<Doctor> doctorList = new ArrayList<>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");

    public static void main(String[] args) {
        Test skinConsultationCentre = new Test();
        skinConsultationCentre.setVisible(true);
        skinConsultationCentre.setSize(1100, 700);
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

      mainConsultantsPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 180, 216), 10));

        JScrollPane sp = new JScrollPane(consultantsTable());

        BorderLayout layoutBl = new BorderLayout();
        mainConsultantsPanel.setLayout(layoutBl);

        JButton exit = button("Exit");
        exit.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(mainPanel,
                    "Are you sure you want to close this window?", "Close Window?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        JButton refresh = button("Refresh");
        refresh.addActionListener(e -> {
            dispose();
            //main();
        });

        JButton mainMenu = button("Menu");

        bottomPanel.add(mainMenu);
        bottomPanel.add(refresh);
        bottomPanel.add(exit);
;
        mainConsultantsPanel.add(sp, BorderLayout.CENTER);
        mainConsultantsPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainConsultantsPanel;
    }

    private JPanel menuPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        panel1.setBorder(BorderFactory.createLineBorder(new Color(0, 119, 182), 10));
        panel1.setBackground(new Color(255, 255, 255));

        panel2.setBorder(BorderFactory.createLineBorder(new Color(0, 119, 182), 10));
        panel2.setBackground(new Color(64, 182, 0));

        panel3.setBorder(BorderFactory.createLineBorder(new Color(0, 119, 182), 10));
        panel3.setBackground(new Color(227, 4, 69));
        panel3.add(new JButton());

        welcomePanel.setBorder(BorderFactory.createLineBorder(new Color(144, 224, 239), 10));
        welcomePanel.setBackground(new Color(0, 119, 182));

        JLabel lbl = new JLabel("SKIN CONSULTATION CENTRE");
        lbl.setForeground(new Color(202, 240, 248));
        lbl.setFont(new Font("Calibri", Font.BOLD, 42));

        JLabel lbl2 = new JLabel("SKIN CONSULTATION CENTRE");
        lbl2.setForeground(new Color(202, 240, 248));
        lbl2.setFont(new Font("Calibri", Font.BOLD, 38));

        JPanel subPanel = new JPanel();
        Icon icon1 = new ImageIcon("src/assets/img1.PNG");
        JButton btn1  = new JButton(icon1);
        btn1.setBorder(new EmptyBorder(50, 50, 50, 50));
        subPanel.add(new JPanel().add(btn1), BorderLayout.LINE_START);

        Icon icon2 = new ImageIcon("src/assets/img2.PNG");
        JButton btn2  = new JButton(icon2);
        subPanel.add(new JPanel().add(btn2), BorderLayout.CENTER);

        Icon icon3 = new ImageIcon("src/assets/img3.PNG");
        JButton btn3  = new JButton(icon3);
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
        JPanel mainAddConsultantPanel = addConsultationPanel();
        mainAddConsultantPanel.setBackground(new Color(255, 255, 255));
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

    private JPanel addConsultationPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel.setBackground(new Color(255, 255, 255));
        JPanel headerPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        topPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        JLabel mainHeading = mainHeading("Add Consultation");
        JLabel subHeading = subHeading("Add Patient");

        headerPanel.add(mainHeading);
        headerPanel.add(subHeading);

        JLabel lblPatientName = label("Patient Name");
        JTextField txtPatientName = textField();
        topPanel.add(lblPatientName);
        topPanel.add(txtPatientName);

        JLabel lblPatientSurname = label("Patient Surname");
        JTextField txtPatientSurname = textField();
        topPanel.add(lblPatientSurname);
        topPanel.add(txtPatientSurname);

        JLabel lblPatientDOB = label("Date Of Birth");
        //JLabel lblPatientDOBVal = new JLabel();
        JButton btnDate = new JButton("Date");
        topPanel.add(lblPatientDOB);
        //topPanel.add(lblPatientDOBVal);
        topPanel.add(btnDate);

        JLabel lblPatientMobileNo = label("Mobile Number");
        JTextField txtPatientMobileNo = textField();
        topPanel.add(lblPatientMobileNo);
        topPanel.add(txtPatientMobileNo);

        JLabel lblPatientNIC = label("NIC");
        JTextField txtPatientNIC = textField();
        topPanel.add(lblPatientNIC);
        topPanel.add(txtPatientNIC);

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

        JLabel images = label("Images");
        JLabel jLabelImage = new JLabel();
        topPanel.add(images);
        topPanel.add(jLabelImage);

        JButton upload = button("Upload");

        jLabelImage.setBounds(0, 0, 10, 20);
        bottomPanel.add(upload);


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

        topPanel.setLayout(new GridLayout(10, 2));

        JButton btnReset = button("Reset");
        JButton btnRandom = button("Assign Doctor");
        JButton btnAdd = button("Add Consultation");
        JButton btnAddNew = button("Add Another Consultation");
        JButton menuBtn = button("Menu");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnRandom);
        bottomPanel.add(btnAdd);
        bottomPanel.add(menuBtn);

        btnAddNew.setVisible(false);

        BorderLayout layout = new BorderLayout();
        panel.setLayout(layout);

        headerPanel.setLayout(new GridLayout(2, 1));

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(topPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        panel.setSize(500,1000);

        menuBtn.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.repaint();
            mainPanel.revalidate();

            mainPanel.add(welcomePanel);
            mainPanel.repaint();
            mainPanel.revalidate();
        });

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
            jLabelImage.removeAll();
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

    private JLabel mainHeading(String message) {
        JLabel lbl = new JLabel(message, JLabel.LEFT);
        lbl.setFont(new Font(null, Font.BOLD, 25));
        lbl.setForeground(new Color(0, 0, 0));
        lbl.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 5));
        return lbl;
    }

    private JLabel subHeading(String message) {
        JLabel lbl = new JLabel(message, JLabel.LEFT);
        lbl.setFont(new Font(null, Font.BOLD, 22));
        lbl.setForeground(new Color(104, 116, 149));
        lbl.setBorder(BorderFactory.createEmptyBorder(5, 25, 5, 10));
        return lbl;
    }

    private JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font(null, Font.BOLD, 15));
        label.setForeground(new Color(0, 119, 182));
        label.setBorder(new EmptyBorder(10, 0, 10, 0));
        return label;
    }

    private JTextField textField() {
        JTextField txtField = new JTextField();
        txtField.setFont(new Font(null, Font.PLAIN, 15));
        txtField.setForeground(new Color(2, 62, 138));
        return txtField;
    }

    private JButton button(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font(null, Font.BOLD, 15));
        btn.setForeground(new Color(2, 62, 138));
        btn.setBackground(new Color(72, 202, 228));
        btn.setBorder(new EmptyBorder(10, 10, 10, 10));
        return btn;
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
}
