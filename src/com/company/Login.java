package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.border.*;

public class Login extends JFrame {
    public static JTextField login_textField = new JTextField(40);
    public static JPasswordField password_textField = new JPasswordField(40);
    public static JLabel login_label = new JLabel("Login:");
    public static JLabel password_label = new JLabel("Password:");
    public static JButton login_button = new JButton("Login");
    public static JButton register_button = new JButton("Register");
    public static JPanel p = new JPanel();

    Login() {
        super("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 300);
        p.setLayout(null);

        login_label.setBounds(80, 100, 36,17);
        password_label.setBounds(56, 117, 60,17);
        login_textField.setBounds(120, 100, 120, 17);
        password_textField.setBounds(120, 117, 120, 17);
        login_button.setBounds(120, 134, 120, 17);
        register_button.setBounds(120, 151, 120, 17);

        ActionListener registrationListener = new RegistrationListener();
        register_button.addActionListener(registrationListener);

        ActionListener loginListener = new LoginListener();
        login_button.addActionListener(loginListener);

        p.add(login_label);
        p.add(password_label);
        p.add(login_textField);
        p.add(password_textField);
        p.add(login_button);
        p.add(register_button);
        add(p);
    }

    public class RegistrationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Register reg = new Register();
            reg.setVisible(true);
        }
    }

    public class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            findUserProcess();
        }
    }

    private static User findUser(String login, char[] password) throws Exception {
        if (!Main.getUsers().isEmpty()) {
            for (User user: Main.getUsers()) {
                if (user.login.equals(login)) {
                    if (Arrays.equals(user.password, password)) {
                        return user;
                    } else {
                        throw new Exception("Wrong password!");
                    }
                }
            }
        }
        throw new Exception("Login not found!");
    }

    private static void findUserProcess() {
        String login = login_textField.getText();
        char[] password1 = password_textField.getPassword();
        try {
            Main.setCurrentUser(findUser(login, password1));
            if (Main.getCurrentUser().access == 1) {
                MainWindow.user_label.setBounds(80, 117, 36,17);
                MainWindow.current_election_label.setBounds(80, 100, 36,17);
                MainWindow.candidates_label.setBounds(56, 134, 60,17);
                MainWindow.votes_label.setBounds(56, 134, 60,17);
                MainWindow.new_candidate_label.setBounds(56, 134, 60,17);
                MainWindow.new_candidate_textField.setBounds(15, 151, 110,17);
                MainWindow.candidates_select_list.setBounds(120, 117, 120, 17);
                MainWindow.new_election_button.setBounds(120, 100, 120, 17);
                MainWindow.new_candidate_button.setBounds(120, 168, 120, 17);
                MainWindow.election_results_button.setBounds(120, 168, 120, 17);
                MainWindow.vote_button.setBounds(120, 168, 120, 17);
                MainWindow.exit_button.setBounds(120, 168, 120, 17);

                MainWindow.p.add(MainWindow.user_label);
                MainWindow.p.add(MainWindow.current_election_label);
                MainWindow.p.add(MainWindow.candidates_label);
                MainWindow.p.add(MainWindow.votes_label);
                MainWindow.p.add(MainWindow.new_candidate_label);
                MainWindow.p.add(MainWindow.new_candidate_textField);
                MainWindow.p.add(MainWindow.candidates_select_list);
                MainWindow.p.add(MainWindow.new_election_button);
                MainWindow.p.add(MainWindow.new_candidate_button);
                MainWindow.p.add(MainWindow.election_results_button);
                MainWindow.p.add(MainWindow.vote_button);
                MainWindow.p.add(MainWindow.exit_button);
                Main.mw.add(MainWindow.p);
                Main.mw.setVisible(true);
            } else {
                MainWindow.user_label.setBounds(80, 117, 36,17);
                MainWindow.current_election_label.setBounds(80, 100, 36,17);
                MainWindow.candidates_label.setBounds(56, 134, 60,17);
                MainWindow.votes_label.setBounds(56, 134, 60,17);
                MainWindow.new_candidate_label.setBounds(56, 134, 60,17);
                MainWindow.new_candidate_textField.setBounds(15, 151, 110,17);
                MainWindow.candidates_select_list.setBounds(120, 117, 120, 17);
                MainWindow.new_election_button.setBounds(120, 100, 120, 17);
                MainWindow.new_candidate_button.setBounds(120, 168, 120, 17);
                MainWindow.election_results_button.setBounds(120, 168, 120, 17);
                MainWindow.vote_button.setBounds(120, 168, 120, 17);
                MainWindow.exit_button.setBounds(120, 168, 120, 17);

                MainWindow.p.add(MainWindow.user_label);
                MainWindow.p.add(MainWindow.current_election_label);
                MainWindow.p.add(MainWindow.candidates_label);
                MainWindow.p.add(MainWindow.votes_label);
                MainWindow.p.add(MainWindow.new_candidate_label);
                MainWindow.p.add(MainWindow.new_candidate_textField);
                MainWindow.p.add(MainWindow.candidates_select_list);
                MainWindow.p.add(MainWindow.new_election_button);
                MainWindow.p.add(MainWindow.new_candidate_button);
                MainWindow.p.add(MainWindow.election_results_button);
                MainWindow.p.add(MainWindow.vote_button);
                MainWindow.p.add(MainWindow.exit_button);
                Main.mw.add(MainWindow.p);
                Main.mw.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
