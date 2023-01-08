package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Formatter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Gui extends JFrame {
    JPanel mainPanel = new JPanel();
    JPanel welcomePanel = new JPanel();
    JPanel addConsultationPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    JPanel doctorAvailabilityJPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    JPanel savedConsultationsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    ArrayList<Patient> patientList = new ArrayList<>();
    ArrayList<Consultation> consultations = new ArrayList<>();
    ArrayList<Doctor> doctorList = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();

    public Gui() {
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

    public static void start() {
        Gui gui = new Gui();
        gui.setVisible(true);
        gui.setSize(1300, 700);
        gui.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public static void main(String[] args) {
        start();
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
            start();
        });

        bottomPanel.add(refresh);
        bottomPanel.add(exit);
        mainConsultantsPanel.add(sp, BorderLayout.CENTER);
        mainConsultantsPanel.add(bottomPanel, BorderLayout.SOUTH);

        return mainConsultantsPanel;
    }

    private JPanel menuPanel() {
        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JPanel instructionsPanel = new JPanel();

        welcomePanel.setBorder(BorderFactory.createLineBorder(new Color(144, 224, 239), 10));
        welcomePanel.setBackground(new Color(0, 119, 182));

        JLabel lbl = mainHeader();

        JLabel lbl2 = details("1. Add Consultation");
        JLabel lbl3 = details("2. Doctor Availability");
        JLabel lbl4 = details("3. Saved Consultations");

        JPanel subPanel = new JPanel();

        ImageIcon icon1 = new ImageIcon("assets/images/img1.jpg");
        Image img1 = icon1.getImage();
        Image newImg1 = img1.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        icon1 = new ImageIcon(newImg1);
        JButton btn1 = new JButton(icon1);
        btn1.setToolTipText("Add Consultations");
        btn1.setPreferredSize(new Dimension(150, 150));
        subPanel.add(new JPanel().add(btn1), BorderLayout.LINE_START);

        ImageIcon icon2 = new ImageIcon("assets/images/img2.jpg");
        Image img2 = icon2.getImage();
        Image newImg2 = img2.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        icon2 = new ImageIcon(newImg2);
        JButton btn2 = new JButton(icon2);
        btn2.setToolTipText("Check Doctor Availability");
        btn2.setPreferredSize(new Dimension(150, 150));
        subPanel.add(new JPanel().add(btn2), BorderLayout.CENTER);

        ImageIcon icon3 = new ImageIcon("assets/images/img3.jpg");
        Image img3 = icon3.getImage();
        Image newImg3 = img3.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        icon3 = new ImageIcon(newImg3);
        JButton btn3 = new JButton(icon3);
        btn3.setToolTipText("Saved Consultations");
        btn3.setPreferredSize(new Dimension(150, 150));
        subPanel.add(new JPanel().add(btn3), BorderLayout.LINE_END);

        subPanel.setBackground(new Color(0, 119, 182));

        instructionsPanel.add(lbl2);
        instructionsPanel.add(lbl3);
        instructionsPanel.add(lbl4);
        instructionsPanel.setBackground(new Color(0, 119, 182));
        instructionsPanel.setLayout(new GridLayout(3, 1));

        welcomePanel.add(lbl, BorderLayout.NORTH);
        welcomePanel.add(instructionsPanel, BorderLayout.CENTER);
        welcomePanel.add(subPanel, BorderLayout.SOUTH);

        mainPanel.setLayout(new GridLayout(1, 1));

        mainPanel.add(welcomePanel);

        btn1.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setTitle("Add Consultation");
            frame.add(addConsultation());
            frame.setSize(500, 600);
            frame.setVisible(true);
        });

        btn2.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setTitle("Doctor Availability");
            frame.add(doctorAvailability());
            frame.setSize(500, 600);
            frame.setVisible(true);
        });

        btn3.addActionListener(e -> {
            JFrame frame = new JFrame();
            frame.setTitle("Saved Consultations");
            frame.add(savedConsultations());
            frame.setSize(1300, 600);
            frame.setVisible(true);
        });

        return mainPanel;
    }

    private JPanel addConsultation() {
        JPanel mainAddConsultantPanel = addConsultationsPanel();
        mainAddConsultantPanel.setBackground(new Color(255, 255, 255));
        return mainAddConsultantPanel;
    }

    private JPanel doctorAvailability() {
        JPanel mainDoctorPanel = doctorAvailabilityPanel();
        mainDoctorPanel.setBackground(new Color(255, 255, 255));
        return mainDoctorPanel;
    }

    private JPanel savedConsultations() {
        savedConsultationsPanel.setBackground(new Color(255, 255, 255));
        JPanel headerPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        topPanel.setBorder(new EmptyBorder(0, 50, 0, 50));

        JLabel mainHeading = mainHeading("Saved Consultations");
        headerPanel.add(mainHeading);

        ArrayList<String[]> consultationsList = getSavedConsultationsContent();
        String[][] data = consultationsList.toArray(String[][]::new);
        String[] column = {
                "Medical License Number", "NIC", "Patient Name", "Patient Surname", "DOB", "Mobile Number", "Time", "Cost", "Notes"
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
        table.setPreferredScrollableViewportSize(new Dimension(1200, 400));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(200);
        columnModel.getColumn(1).setMinWidth(100);
        columnModel.getColumn(2).setMinWidth(150);
        columnModel.getColumn(3).setMinWidth(150);
        columnModel.getColumn(4).setMinWidth(100);
        columnModel.getColumn(5).setMinWidth(150);
        columnModel.getColumn(6).setMinWidth(100);
        columnModel.getColumn(7).setMinWidth(100);
        columnModel.getColumn(8).setMinWidth(150);

        JScrollPane sp = new JScrollPane(table);
        topPanel.add(sp);

        BorderLayout layout = new BorderLayout();
        savedConsultationsPanel.setLayout(layout);

        headerPanel.setLayout(new GridLayout(2, 1));

        savedConsultationsPanel.add(headerPanel, BorderLayout.NORTH);
        savedConsultationsPanel.add(topPanel, BorderLayout.CENTER);
        savedConsultationsPanel.add(bottomPanel, BorderLayout.SOUTH);

        return savedConsultationsPanel;
    }

    private JTable consultantsTable() {
        ArrayList<String[]> doctorList = getDoctorFileContent();
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
        table.setPreferredScrollableViewportSize(new Dimension(700, 700));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(100);
        columnModel.getColumn(1).setMinWidth(100);
        columnModel.getColumn(2).setMinWidth(80);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(150);
        return table;
    }

    private ArrayList<String[]> getDoctorFileContent() {
        File myObj = new File("assets/files/doctorsList.txt");
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

    private ArrayList<String[]> getSavedConsultationsContent() {
        File myObj = new File("assets/files/consultations.txt");
        ArrayList<String[]> savedConsultationsList = new ArrayList<>();
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
            savedConsultationsList.add(arr);
        }
        myReader.close();
        return savedConsultationsList;
    }

    private ArrayList<String[]> getTimesFileContent() {
        File myObj = new File("assets/files/doctorAvailableTimes.txt");
        ArrayList<String[]> timesList = new ArrayList<>();
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
            timesList.add(arr);
        }
        myReader.close();
        return timesList;
    }

    private JPanel addConsultationsPanel() {
        addConsultationPanel.setBackground(new Color(255, 255, 255));
        JPanel headerPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        topPanel.setBorder(new EmptyBorder(50, 50, 0, 50));

        JLabel mainHeading = mainHeading("Add Consultation");
        JLabel subHeading = subHeading("Add Patient/Doctor/Consultation Details");

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
        JLabel lblPatientDOBVal = new JLabel();
        JButton btnDate = dateButton("Date");

        JPanel actionDatePanel = new JPanel();
        actionDatePanel.add(lblPatientDOBVal);
        actionDatePanel.add(btnDate);

        topPanel.add(lblPatientDOB);
        topPanel.add(actionDatePanel);

        btnDate.addActionListener(e -> {
            DatePicker datePicker = new DatePicker(this);
            lblPatientDOBVal.setText(datePicker.setPickedDate());
        });

        JLabel lblPatientMobileNo = label("Mobile Number");
        JTextField txtPatientMobileNo = textField();
        topPanel.add(lblPatientMobileNo);
        topPanel.add(txtPatientMobileNo);

        JLabel lblPatientNIC = label("NIC");
        JTextField txtPatientNIC = textField();
        topPanel.add(lblPatientNIC);
        topPanel.add(txtPatientNIC);

        initDoctor();
        JLabel lblDoctorName = label("Doctor Name");
        JComboBox<String> cbDoctorNames = new JComboBox<>(doctorDropdown(doctorList));
        topPanel.add(lblDoctorName);
        topPanel.add(cbDoctorNames);

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
            FileNameExtensionFilter fileNameExtensionFilter = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
            browseImageFile.addChoosableFileFilter(fileNameExtensionFilter);
            int showOpenDialogue = browseImageFile.showOpenDialog(null);

            if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
                File selectedImageFile = browseImageFile.getSelectedFile();
                String selectedImagePath = selectedImageFile.getAbsolutePath();
                JOptionPane.showMessageDialog(null, selectedImagePath);
                //Display image on JLabel
                ImageIcon ii = new ImageIcon(selectedImagePath);
                //Resize image to fit JLabel
                Image image = ii.getImage().getScaledInstance(jLabelImage.getWidth(), jLabelImage.getHeight(), Image.SCALE_SMOOTH);

                jLabelImage.setIcon(new ImageIcon(image));
            }
        });

        JLabel lblMessage = new JLabel();
        topPanel.add(lblMessage);

        GridLayout formLayout = new GridLayout(10, 2);
        formLayout.setHgap(5);
        formLayout.setVgap(5);

        topPanel.setLayout(formLayout);

        JButton btnReset = button("Reset");
        JButton btnRandom = button("Assign Doctor");
        JButton btnAdd = button("Add Consultation");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnRandom);
        bottomPanel.add(btnAdd);

        BorderLayout layout = new BorderLayout();
        addConsultationPanel.setLayout(layout);

        headerPanel.setLayout(new GridLayout(2, 1));

        addConsultationPanel.add(headerPanel, BorderLayout.NORTH);
        addConsultationPanel.add(topPanel, BorderLayout.CENTER);
        addConsultationPanel.add(bottomPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(ae -> {
            if ((txtPatientName.getText().trim().length() > 0) &&
                    (txtPatientSurname.getText().trim().length() > 0) &&
                    (lblPatientDOBVal.getText().trim().length() > 0) &&
                    (txtPatientMobileNo.getText().trim().length() > 0) &&
                    (txtPatientNIC.getText().trim().length() > 0) &&
                    (txtConsultationHours.getText().trim().length() > 0) &&
                    (txtNotes.getText().trim().length() > 0)) {
                if (JOptionPane.showConfirmDialog(addConsultationPanel,
                        "Are you sure you want to save consultation?", "Select an Option?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    String status = initConsultations();
                    if (status.equals("error")) {
                        System.out.println(consultations);
                        lblMessage.setText("Could not load the data!!!");
                    }

                    initDoctor();
                    initPatient();
                    initConsultations();

                    String txtPatientNameValue = txtPatientName.getText();
                    String txtPatientSurnameValue = txtPatientSurname.getText();
                    String txtPatientDOBValue = lblPatientDOBVal.getText();
                    String txtPatientMobileNoValue = txtPatientMobileNo.getText();
                    int txtPatientNICValue = parseInt(txtPatientNIC.getText());

                    Patient patient = new Patient(txtPatientNameValue, txtPatientSurnameValue, txtPatientMobileNoValue, txtPatientDOBValue, txtPatientNICValue);
                    patientList.add(patient);                    ;
                    if(savePatients()) {
                        String txtDoctorNameValue = (String) cbDoctorNames.getSelectedItem();
                        Integer txtConsultationHoursValue = Integer.parseInt(txtConsultationHours.getText());
                        Double consultationCost = costCalculator(txtConsultationHoursValue, txtPatientNameValue);
                        txtCost.setText(String.valueOf(consultationCost));
                        double txtCostValue = Double.parseDouble(txtCost.getText());
                        String txtNotesValue = Base64.getEncoder().encodeToString(txtNotes.getText().getBytes());


                        Consultation consultation = new Consultation(getDoctorByName(txtDoctorNameValue, doctorList), patient, LocalDate.now(), txtCostValue, txtNotesValue);
                        consultations.add(consultation);

                        String message = saveConsultation();
                        if (message.equals("success")) {
                            showMessageDialog("Consultation Added Successfully...", addConsultationPanel);
                            btnReset.doClick();
                        } else {
                            System.out.println("If message is not success");
                            showErrorMessageDialog(addConsultationPanel);
                        }
                    } else {
                        showErrorMessageDialog(addConsultationPanel);
                    }
                }
            } else {
                showWarningDialog("All the fields should be required", addConsultationPanel);
            }
        });

        btnReset.addActionListener(e -> {
            txtPatientName.setText("");
            txtPatientSurname.setText("");
            lblPatientDOBVal.setText("");
            txtPatientMobileNo.setText("");
            txtPatientNIC.setText("");
            cbDoctorNames.setSelectedIndex(0);
            txtConsultationHours.setText("");
            txtCost.setText("");
            txtNotes.setText("");
            jLabelImage.removeAll();
        });

        btnRandom.addActionListener(e -> {
            initDoctor();
            if (doctorList.isEmpty()) {
                System.out.println("Error");
            } else {
                int randomIndex = (int) (Math.random() * cbDoctorNames.getItemCount());
                cbDoctorNames.setSelectedIndex(randomIndex);
            }
        });

        return addConsultationPanel;
    }

    private JPanel doctorAvailabilityPanel() {
        JScrollPane scrollPane = new JScrollPane();
        doctorAvailabilityJPanel.setBackground(new Color(255, 255, 255));
        JPanel headerPanel = new JPanel();
        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        topPanel.setBorder(new EmptyBorder(50, 50, 0, 50));

        JLabel mainHeading = mainHeading("Doctor Availability");
        JLabel subHeading = subHeading("Check Doctor/ Add Available Times");

        headerPanel.add(mainHeading);
        headerPanel.add(subHeading);

        initDoctor();
        JLabel lblDoctorName = label("Doctor Name");
        JComboBox<String> cbDoctorNames = new JComboBox<>(doctorDropdown(doctorList));
        topPanel.add(lblDoctorName);
        topPanel.add(cbDoctorNames);

        JLabel lblDoctorAvailableTimeFrom = new JLabel();
        JLabel lblDoctorAvailableTimeTo = new JLabel();
        JButton btnFromDate = dateButton("Date From");
        JButton btnToDate = dateButton("Date To");
        JPanel dateFromPanel = new JPanel();
        JPanel dateToPanel = new JPanel();

        dateFromPanel.add(lblDoctorAvailableTimeFrom);
        dateFromPanel.add(btnFromDate);

        dateToPanel.add(lblDoctorAvailableTimeTo);
        dateToPanel.add(btnToDate);

        topPanel.add(dateFromPanel);
        topPanel.add(dateToPanel);

        btnFromDate.addActionListener(e -> {
            DatePicker datePicker = new DatePicker(this);
            lblDoctorAvailableTimeFrom.setText(datePicker.setPickedDate());
        });

        btnToDate.addActionListener(e -> {
            DatePicker datePicker = new DatePicker(this);
            lblDoctorAvailableTimeTo.setText(datePicker.setPickedDate());
        });

        ArrayList<String[]> timesList = getTimesFileContent();

        String[][] data = timesList.toArray(String[][]::new);
        String[] column = {"Medical Licence Number", "Date From", "Date To"};
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
        table.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setMinWidth(200);
        columnModel.getColumn(1).setMinWidth(100);
        columnModel.getColumn(2).setMinWidth(100);

        JScrollPane sp = new JScrollPane(table);
        JPanel doctorAvailabilityTimes = new JPanel();

        doctorAvailabilityTimes.add(sp);

        topPanel.add(doctorAvailabilityTimes);

        JButton btnReset = button("Reset");
        JButton btnUpdate = button("Update");
        bottomPanel.add(btnReset);
        bottomPanel.add(btnUpdate);

        BorderLayout layout = new BorderLayout();
        doctorAvailabilityJPanel.setLayout(layout);

        headerPanel.setLayout(new GridLayout(2, 1));

        doctorAvailabilityJPanel.add(headerPanel, BorderLayout.NORTH);
        doctorAvailabilityJPanel.add(topPanel, BorderLayout.CENTER);
        doctorAvailabilityJPanel.add(bottomPanel, BorderLayout.SOUTH);

        btnUpdate.addActionListener(ae -> {
            if ((lblDoctorAvailableTimeFrom.getText().trim().length() > 0) &&
                    (lblDoctorAvailableTimeTo.getText().trim().length() > 0)) {
                if (JOptionPane.showConfirmDialog(doctorAvailabilityJPanel,
                        "Are you sure?", "Select an Option?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    String status = initAvailableTimes();
                    String message;
                    if (status.equals("success")) {
                        try {
                            FileWriter fileWriter = new FileWriter("assets/files/doctorAvailableTimes.txt", true);
                            Formatter formatter = new Formatter(fileWriter);
                            formatter.format("%s\n", cbDoctorNames.getSelectedItem() + "," + lblDoctorAvailableTimeFrom.getText() + "," + lblDoctorAvailableTimeTo.getText());
                            formatter.close();
                            message = "success";
                        } catch (Exception exception) {
                            message = "error";
                        }

                        if (message.equals("success")) {
                            showMessageDialog("Updated Successfully...", doctorAvailabilityJPanel);
                            btnReset.doClick();
                        } else {
                            showErrorMessageDialog(doctorAvailabilityJPanel);
                        }
                    } else {
                        showErrorMessageDialog(doctorAvailabilityJPanel);
                    }
                }
            } else {
                showWarningDialog("All the fields should be required", doctorAvailabilityJPanel);
            }
        });

        btnReset.addActionListener(e -> {
            cbDoctorNames.setSelectedIndex(0);
            lblDoctorAvailableTimeFrom.setText("");
            lblDoctorAvailableTimeTo.setText("");
        });

        scrollPane.add(doctorAvailabilityJPanel);

        return doctorAvailabilityJPanel;
    }

    private String initConsultations() {
        String status;
        try {
            File myObj = new File("assets/files/consultations.txt");
            Scanner myReader = new Scanner(myObj);
            consultations.clear();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                int medicalLicenseNumber = Integer.parseInt(arr[0]);
                int patientId = Integer.parseInt(arr[1]);
                LocalDate date = LocalDate.parse(arr[2]);
                byte[] decodedBytes = Base64.getDecoder().decode(arr[4]);
                String decodedString = new String(decodedBytes);
                Consultation initConsultation = new Consultation(getDoctorByMedicalLicenceNo(medicalLicenseNumber, doctorList), getPatientById(patientId, patientList), date, Double.parseDouble(arr[3]), decodedString);
                consultations.add(initConsultation);
            }
            myReader.close();
            status = "success";
        } catch (Exception exception) {
            status = "error";
        }
        return status;
    }

    private void initDoctor() {
        try {
            File myObj = new File("assets/files/doctorsList.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                Doctor doctor = new Doctor(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]), arr[5]);
                doctorList.add(doctor);
            }
            myReader.close();
            System.out.println("Doctors List Initialised!\n");
        } catch (Exception exception) {
            System.out.println("Could not load the data!!!");
        }
    }

    private void initPatient() {
        try {
            File myObj = new File("assets/files/patientsList.txt");
            Scanner myReader = new Scanner(myObj);
            this.patientList.clear();

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                Patient initPatient = new Patient(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]));
                this.patientList.add(initPatient);
            }

            myReader.close();
        } catch (Exception exception) {
            showErrorMessageDialog(mainPanel);
        }
    }

    private String initAvailableTimes() {
        String status;
        try {
            File myObj = new File("assets/files/doctorAvailableTimes.txt");
            Scanner myReader = new Scanner(myObj);
            times.clear();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(",");
                times.add(arr[0]);
                times.add(arr[1]);
                times.add(arr[2]);
            }
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

    private Patient getPatientById(int id, ArrayList<Patient> patientList) {
        for (Patient patient : patientList) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }

    private Doctor getDoctorByMedicalLicenceNo(int medicalLicenceNo, ArrayList<Doctor> doctorList) {
        for (Doctor doctor : doctorList) {
            if (doctor.getMedicalLicenceNo() == medicalLicenceNo) {
                return doctor;
            }
        }
        return null;
    }

    private String saveConsultation() {
        String message;
        try {
            Formatter formatter = new Formatter("assets/files/consultations.txt");
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

    private boolean savePatients() {
        boolean status = false;
        try {
            Formatter formatter = new Formatter("assets/files/patientslist.txt");
            if (patientList.size() > 0) {
                for (Patient patient : patientList) {
                    formatter.format("%s", patient.toFormattedString());
                }
            }
            formatter.close();
            status = true;
        } catch (Exception exception) {
        }
        return status;
    }

    private String[] doctorDropdown(ArrayList<Doctor> doctorList) {
        return doctorList.stream().map(doctor -> doctor.getName()).toArray(String[]::new);
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

    private JLabel mainHeader() {
        JLabel label = new JLabel("SKIN CONSULTATION CENTRE");
        label.setForeground(new Color(2, 48, 71));
        label.setFont(new Font("Calibre", Font.BOLD, 32));
        label.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20));
        return label;
    }

    private JLabel details(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(222, 236, 239));
        label.setFont(new Font("Calibre", Font.PLAIN, 28));
        label.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        return label;
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
        txtField.setForeground(new Color(0, 0, 0));
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

    private JButton dateButton(String text) {
        JButton btn = new JButton(text);
        btn.setForeground(new Color(144, 224, 239));
        btn.setBackground(new Color(3, 4, 94));
        return btn;
    }

    private void showMessageDialog(String message, JPanel panel) {
        JLabel lblMessage = new JLabel();
        lblMessage.setText(message);
        lblMessage.setForeground(new Color(3, 4, 94, 255));
        lblMessage.setFont(new Font("Arial", Font.BOLD, 17));
        JOptionPane.showMessageDialog(mainPanel, lblMessage);
    }

    private void showWarningDialog(String message, JPanel panel) {
        JLabel lblMessage = new JLabel();
        lblMessage.setText(message);
        lblMessage.setForeground(new Color(33, 232, 45, 255));
        lblMessage.setFont(new Font("Arial", Font.BOLD, 17));
        JOptionPane.showMessageDialog(panel, lblMessage, "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    private void showErrorMessageDialog(JPanel panel) {
        JLabel lblMessage = new JLabel();
        lblMessage.setText("An Error Occurred!!!");
        lblMessage.setForeground(new Color(227, 4, 69));
        lblMessage.setFont(new Font("Arial", Font.BOLD, 17));
        JOptionPane.showMessageDialog(mainPanel, lblMessage,
                "Error", JOptionPane.WARNING_MESSAGE);
    }

    private void jTextFieldKeyPressed(java.awt.event.KeyEvent evt) {
        JLabel lbl = new JLabel("");
        JTextField aa = new JTextField();
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            aa.setEditable(false);
            lbl.setText("Numbers Only");
        } else {
            aa.setEditable(true);
        }
    }
}
