package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.border.*;

public class Login extends JFrame {
    Login() {
        super("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 400);
        JPanel p = new JPanel();
        p.setLayout(null);
        JLabel login_label = new JLabel("Login:");
        JLabel password_label = new JLabel("Password:");
        JTextField login_textField = new JTextField(40);
        JPasswordField password_textField = new JPasswordField(40);
        JButton login_button = new JButton("Login");
        JButton Register_button = new JButton("Login");

        login_label.setBounds(80, 100, 36,17);
        password_label.setBounds(56, 117, 60,17);
        login_textField.setBounds(120, 100, 120, 17);
        password_textField.setBounds(120, 117, 120, 17);

        p.add(login_label);
        p.add(password_label);
        p.add(login_textField);
        p.add(password_textField);
        add(p);

    }
}
