package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.border.*;

public class Register extends JFrame{
    public JLabel name_label = new JLabel("Name:");
    public JLabel login_label = new JLabel("Login:");
    public JLabel empty_login_label = new JLabel("Enter the login");
    public JLabel wrong_login_label = new JLabel("Login already exists");
    public JLabel password_label = new JLabel("Password:");
    public JLabel empty_password_label = new JLabel("Enter the password");
    public JLabel wrong_password_label = new JLabel("Passwords are different");
    public JLabel repeat_password_label = new JLabel("Repeat password:");
    public JTextField name_textField = new JTextField(40);
    public JTextField login_textField = new JTextField(40);
    public JPasswordField password_textField = new JPasswordField(40);
    public JPasswordField repeat_password_textField = new JPasswordField(40);
    public JButton register_button = new JButton("Register");
    public JButton login_button = new JButton("Login");
    public JPanel p = new JPanel();

    Register() {
        super("Register");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 350);
        p.setLayout(null);

        p.add(name_label);
        p.add(login_label);
        p.add(password_label);
        p.add(repeat_password_label);
        p.add(name_textField);
        p.add(login_textField);
        p.add(password_textField);
        p.add(repeat_password_textField);
        p.add(register_button);
        p.add(login_button);
        add(p);

        name_label.setBounds(80, 100, 36,17);
        login_label.setBounds(80, 117, 36,17);
        password_label.setBounds(56, 134, 60,17);
        repeat_password_label.setBounds(15, 151, 110,17);
        name_textField.setBounds(120, 100, 120, 17);
        login_textField.setBounds(120, 117, 120, 17);
        password_textField.setBounds(120, 134, 120, 17);
        repeat_password_textField.setBounds(120, 151, 120, 17);
        register_button.setBounds(120, 168, 120, 17);
        login_button.setBounds(120, 185, 120, 17);

        ActionListener actionListener = new RegistrationListener();
        register_button.addActionListener(actionListener);

        ActionListener loginListener = new LoginListener();
        login_button.addActionListener(loginListener);

        Insets insets = this.getInsets();
        this.setBounds(500, 300, 350 + insets.left + insets.right, 350 + insets.top + insets.bottom);
    }

    public class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Main.log.login_textField.setText("");
            Main.log.password_textField.setText("");
            Main.log.setVisible(true);
        }
    }

    public class RegistrationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            addUserProcess();
            setVisible(false);
            Main.log.setVisible(true);
        }
    }

    private User addUser(String name, String login, char[] password, String role) throws Exception {
        if (!Main.getUsers().isEmpty()) {
            for (User user: Main.getUsers()) {
                if (user.login.equals(login)) {
                    System.out.println("0");
                    throw new Exception("Login already exists");
                }
            }
        }

        if (role.equals("admin")) {
            Admin user = new Admin(name, login, password);
            List<User> curUsers = Main.getUsers();
            curUsers.add(user);
            Main.setUsers(curUsers);
            return user;
        } else if (role.equals("elector")) {
            Elector user = new Elector(name, login, password);
            List<User> curUsers = Main.getUsers();
            curUsers.add(user);
            Main.setUsers(curUsers);
            return user;
        }
        throw new Exception("Wrong role!");
    }

    private void addUserProcess() {
        String name = name_textField.getText();
        String login = login_textField.getText();
        char[] password1 = password_textField.getPassword();
        char[] password2 = repeat_password_textField.getPassword();
        System.out.println(login_textField.getText());
        System.out.println(password_textField.getPassword());
        System.out.println(repeat_password_textField.getPassword());
        System.out.println();
        if (Arrays.equals(password1, password2)) {
            try {
                addUser(name, login, password1, "elector");
                System.out.println("New elector registered");
                System.out.println();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Passwords are different");
        }
    }
}
