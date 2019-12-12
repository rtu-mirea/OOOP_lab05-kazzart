package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import javax.swing.border.*;

public class Login extends JFrame {
    public JTextField login_textField = new JTextField(40);
    public JPasswordField password_textField = new JPasswordField(40);
    public JLabel login_label = new JLabel("Login:");
    public JLabel empty_login_label = new JLabel("Enter the login");
    public JLabel wrong_login_label = new JLabel("Unregistered login");
    public JLabel password_label = new JLabel("Password:");
    public JLabel empty_password_label = new JLabel("Enter the password");
    public JLabel wrong_password_label = new JLabel("Wrong password");
    public JButton login_button = new JButton("Login");
    public JButton register_button = new JButton("Register");
    public JPanel p = new JPanel();

    Login() {
        super("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);
        p.setLayout(null);

        login_textField.setText("admin");
        password_textField.setText("admin");

        p.add(login_label);
        p.add(empty_login_label);
        p.add(wrong_login_label);
        p.add(password_label);
        p.add(empty_password_label);
        p.add(wrong_password_label);
        p.add(login_textField);
        p.add(password_textField);
        p.add(login_button);
        p.add(register_button);
        add(p);

//        JTextField login_textField = new JTextField(40);
//        JPasswordField password_textField = new JPasswordField(40);
//        JLabel login_label = new JLabel("Login:");
//        JLabel password_label = new JLabel("Password:");
//        JButton login_button = new JButton("Login");
//        JButton register_button = new JButton("Register");
//        JPanel p = new JPanel();


        login_label.setBounds(100, 100, 36,17);
        empty_login_label.setBounds(260, 100, 150,17);
        wrong_login_label.setBounds(260, 100, 150,17);
        password_label.setBounds(76, 117, 60,17);
        empty_password_label.setBounds(260, 117, 150,17);
        wrong_password_label.setBounds(260, 117, 150,17);
        login_textField.setBounds(140, 100, 120, 17);
        password_textField.setBounds(140, 117, 120, 17);
        login_button.setBounds(140, 134, 120, 17);
        register_button.setBounds(140, 151, 120, 17);

        ActionListener loginListener = new LoginListener();
        login_button.addActionListener(loginListener);

        ActionListener registrationListener = new RegistrationListener();
        register_button.addActionListener(registrationListener);

        empty_login_label.setVisible(false);
        wrong_login_label.setVisible(false);
        empty_password_label.setVisible(false);
        wrong_password_label.setVisible(false);

        Insets insets = this.getInsets();
        this.setBounds(500, 300, 400 + insets.left + insets.right, 300 + insets.top + insets.bottom);
    }

    public class RegistrationListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Main.reg.name_textField.setText("");
            Main.reg.login_textField.setText("");
            Main.reg.password_textField.setText("");
            Main.reg.repeat_password_textField.setText("");
            Main.reg.setVisible(true);
        }
    }

    public class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            setVisible(false);
            findUserProcess();
        }
    }

    private User findUser(String login, char[] password) throws Exception {
        empty_login_label.setVisible(false);
        wrong_login_label.setVisible(false);
        empty_password_label.setVisible(false);
        wrong_password_label.setVisible(false);
        if (!login.isEmpty()) {
            if (!Main.getUsers().isEmpty()) {
                for (User user : Main.getUsers()) {
                    if (user.login.equals(login)) {
                        if (Arrays.equals(user.password, password)) {
                            return user;
                        } else {
                            if (password.length <= 0){
                                empty_password_label.setVisible(true);
                            }else {
                                wrong_password_label.setVisible(true);
                            }
                        }
                    }
                }
            }
            if (!wrong_password_label.isVisible() && !empty_password_label.isVisible()){
                wrong_login_label.setVisible(true);
            }
        } else {
            empty_login_label.setVisible(true);
        }
        return new User("", "", new char[0]);
    }

    private void findUserProcess() {
        String login = login_textField.getText();
        char[] password1 = password_textField.getPassword();
        try {
            User user = findUser(login, password1);
            if (!user.getName().equals("")) {
                Main.setCurrentUser(user);
                Main.log.setVisible(false);
                if (Main.getCurrentUser().access == 1) {
                    Main.mw.user_label.setText(Main.getCurrentUser().getName());
                    Main.mw.current_election_label.setText("Current election: " + Main.getCurrentVoting().title);

//                Main.mw.user_label.setBounds(10, 10, 300,17);
//                Main.mw.current_election_label.setBounds(10, 27, 300,17);
//                Main.mw.candidates_label.setBounds(10, 44, 100,17);
//                Main.mw.new_candidate_label.setBounds(10, 61, 90,17);
//                Main.mw.new_candidate_textField.setBounds(100, 61, 190,17);
//                Main.mw.candidates_select_list.setBounds(80, 44, 210, 17);
//                Main.mw.new_election_button.setBounds(300, 27, 110, 17);
//                Main.mw.new_candidate_button.setBounds(120, 168, 110, 17);
//                Main.mw.election_results_button.setBounds(120, 168, 110, 17);
//                Main.mw.vote_button.setBounds(120, 168, 120, 17);
//                Main.mw.exit_button.setBounds(120, 168, 120, 17);

//                    Main.mw.p.add(Main.mw.user_label);
//                    Main.mw.p.add(Main.mw.current_election_label);
//                    Main.mw.p.add(Main.mw.candidates_label);
//                    Main.mw.p.add(Main.mw.new_candidate_label);
//                    Main.mw.p.add(Main.mw.new_candidate_textField);
//                    Main.mw.p.add(Main.mw.candidates_select_list);
//                    Main.mw.p.add(Main.mw.new_election_button);
//                    Main.mw.p.add(Main.mw.new_candidate_button);
//                    Main.mw.p.add(Main.mw.election_results_button);
//                    Main.mw.p.add(Main.mw.vote_button);
//                    Main.mw.p.add(Main.mw.exit_button);
//                Main.mw.add(Main.mw.p);
                    Main.mw.new_election_textField.setVisible(true);
                    Main.mw.vote_button.setVisible(false);
                    Main.mw.setVisible(true);
                } else {
                    Main.mw.user_label.setText(Main.getCurrentUser().getName());
                    Main.mw.current_election_label.setText("Current election: " + Main.getCurrentVoting().title);
//                Main.mw.user_label.setBounds(10, 10, 300,17);
//                Main.mw.current_election_label.setBounds(10, 27, 300,17);
//                Main.mw.candidates_label.setBounds(10, 44, 100,17);
//                Main.mw.new_candidate_label.setBounds(10, 61, 90,17);
//                Main.mw.new_candidate_textField.setBounds(100, 61, 190,17);
//                Main.mw.candidates_select_list.setBounds(80, 44, 210, 17);
//                Main.mw.new_election_button.setBounds(300, 27, 110, 17);
//                Main.mw.new_candidate_button.setBounds(120, 168, 110, 17);
//                Main.mw.election_results_button.setBounds(120, 168, 110, 17);
//                Main.mw.vote_button.setBounds(120, 168, 120, 17);
//                Main.mw.exit_button.setBounds(120, 168, 120, 17);

                    Main.mw.p.add(Main.mw.user_label);
                    Main.mw.p.add(Main.mw.current_election_label);
                    Main.mw.p.add(Main.mw.candidates_label);
//                    Main.mw.p.add(Main.mw.new_candidate_label);
//                    Main.mw.p.add(Main.mw.new_candidate_textField);
                    Main.mw.p.add(Main.mw.candidates_select_list);
//                    Main.mw.p.add(Main.mw.new_election_button);
//                    Main.mw.p.add(Main.mw.new_candidate_button);
                    Main.mw.p.add(Main.mw.election_results_button);
                    Main.mw.p.add(Main.mw.vote_button);
                    Main.mw.p.add(Main.mw.exit_button);
//                Main.mw.add(Main.mw.p);
                    Main.mw.new_election_textField.setVisible(false);
                    Main.mw.new_candidate_label.setVisible(false);
                    Main.mw.new_candidate_textField.setVisible(false);
                    Main.mw.new_election_button.setVisible(false);
                    Main.mw.new_candidate_button.setVisible(false);
                    Main.mw.vote_button.setVisible(true);
                    Main.mw.setVisible(true);
                }
            } else {
                Main.log.setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
