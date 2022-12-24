package com.company;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Gui {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Skin Consultation Centre");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLayout(new GridLayout(3, 2));

        JPanel mainPanel = new JPanel();
        frame.add(mainPanel);
        frame.setVisible(true);

        //JFrame.setDefaultLookAndFeelDecorated(true);
//        TitledBorder border = new TitledBorder("Skin Consultation Centre");
//        border.setTitleJustification(TitledBorder.CENTER);
//        border.setTitlePosition(TitledBorder.TOP);
//
//        Font newFont = new Font (Font.SERIF, Font.BOLD, 40);
//        border.setTitleFont(newFont);
//        border.setTitleColor(new Color(0,76,153));
//
//        JPanel panel = new JPanel();
//        panel.setBorder(border);
//
//        frame.add(panel);
//
//        frame.setVisible(true);
    }
}
