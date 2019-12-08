package com.company;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.border.*;

public class MainWindow extends JFrame {
    public static JLabel user_label = new JLabel(":");
    public static JLabel current_election_label = new JLabel("Current election: ");
    public static JLabel candidates_label = new JLabel("Candidates:");
    public static JLabel votes_label = new JLabel("Votes: ");
    public static JLabel new_candidate_label = new JLabel("New candidate: ");
    public static JTextField new_candidate_textField = new JTextField(40);
    public static JComboBox candidates_select_list = new JComboBox();
    public static JButton new_election_button = new JButton("New election");
    public static JButton new_candidate_button = new JButton("New Candidate");
    public static JButton election_results_button = new JButton("Election results");
    public static JButton vote_button = new JButton("Vote");
    public static JButton exit_button = new JButton("Exit account");
    public static JPanel p = new JPanel();

    MainWindow() {
        super("Voting system");
        setSize(600, 600);
        p.setLayout(null);

//        user_label.setBounds(80, 117, 36,17);
//        current_election_label.setBounds(80, 100, 36,17);
//        candidates_label.setBounds(56, 134, 60,17);
//        votes_label.setBounds(56, 134, 60,17);
//        new_candidate_label.setBounds(56, 134, 60,17);
//        new_candidate_textField.setBounds(15, 151, 110,17);
//        candidates_select_list.setBounds(120, 117, 120, 17);
//        new_election_button.setBounds(120, 100, 120, 17);
//        new_candidate_button.setBounds(120, 168, 120, 17);
//        election_results_button.setBounds(120, 168, 120, 17);
//        vote_button.setBounds(120, 168, 120, 17);
//        exit_button.setBounds(120, 168, 120, 17);
//
//        p.add(user_label);
//        p.add(current_election_label);
//        p.add(candidates_label);
//        p.add(votes_label);
//        p.add(new_candidate_label);
//        p.add(new_candidate_textField);
//        p.add(candidates_select_list);
//        p.add(new_election_button);
//        p.add(new_candidate_button);
//        p.add(election_results_button);
//        p.add(vote_button);
//        p.add(exit_button);
//        add(p);
    }
}
