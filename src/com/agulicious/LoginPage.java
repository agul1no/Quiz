package com.agulicious;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Log in");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("User ID");
    JLabel userPasswordLabel = new JLabel("Password");
    JLabel messageLabel = new JLabel();


    public LoginPage(){

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setTitle("Quiz");
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(54, 54, 54, 255));

        userIDField.setBounds(150,100,50,40);
        userIDField.setBackground(Color.WHITE);
        userIDField.setForeground(Color.BLACK);
        userIDField.setText("sdfsdfsdf");
        userIDField.setBorder(BorderFactory.createBevelBorder(1));
        userIDField.setHorizontalAlignment(JTextField.CENTER);

        frame.add(userIDField);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
