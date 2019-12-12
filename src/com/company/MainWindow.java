package com.company;

import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.*;

public class MainWindow extends JFrame {
    public JLabel user_label = new JLabel(":");
    public JLabel current_election_label = new JLabel("Current election: " + Main.getCurrentVoting().title);
    public JLabel candidates_label = new JLabel("Candidates:");
    public JLabel new_candidate_label = new JLabel("New candidate: ");
    public JTextField new_election_textField = new JTextField(40);
    public JTextField new_candidate_textField = new JTextField(40);
    public JComboBox candidates_select_list;
    public JButton new_election_button = new JButton("New election");
    public JButton new_candidate_button = new JButton("New Candidate");
    public JButton election_results_button = new JButton("Election results");
    public JButton vote_button = new JButton("Vote");
    public JButton exit_button = new JButton("Exit account");
    public JPanel p = new JPanel();

    MainWindow() {
        super("Voting system");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(467, 150);
        p = (JPanel) this.getContentPane();
        p.setLayout(null);

        candidates_select_list = new JComboBox(users_to_names(Main.getCurrentVoting().candidates));

        p.add(user_label);
        p.add(current_election_label);
        p.add(candidates_label);
        p.add(new_candidate_label);
        p.add(new_election_textField);
        p.add(new_candidate_textField);
        p.add(candidates_select_list);
        p.add(new_election_button);
        p.add(new_candidate_button);
        p.add(election_results_button);
        p.add(vote_button);
        p.add(exit_button);

//        add(p);

        Dimension size_new_election_button = new_election_button.getPreferredSize();
        Dimension size_new_candidate_button = new_candidate_button.getPreferredSize();
        Dimension size_election_results_button = election_results_button.getPreferredSize();
        Dimension size_vote_button = vote_button.getPreferredSize();
        Dimension size_exit_button = exit_button.getPreferredSize();

        user_label.setBounds(10, 10, 300,17);
        current_election_label.setBounds(10, 27, 300,17);
        candidates_label.setBounds(10, 61, 100,17);
        new_candidate_label.setBounds(10, 78, 90,17);
        new_election_textField.setBounds(10, 44, 280,17);
        new_candidate_textField.setBounds(100, 78, 190,17);
        candidates_select_list.setBounds(80, 61, 210, 17);
        new_election_button.setBounds(300, 44, size_new_election_button.width, 17);
        new_candidate_button.setBounds(300, 78, size_new_candidate_button.width, 17);
        election_results_button.setBounds(10, 107, size_election_results_button.width, 17);
        vote_button.setBounds(300, 61, size_vote_button.width,17);
        exit_button.setBounds(420 - size_exit_button.width, 107, size_exit_button.width, 17);

        ActionListener newElectionListener = new NewElectionListener();
        new_election_button.addActionListener(newElectionListener);

        ActionListener newCandidateListener = new NewCandidateListener();
        new_candidate_button.addActionListener(newCandidateListener);

        ActionListener resultListener = new ResultListener();
        election_results_button.addActionListener(resultListener);

        ActionListener voteListener = new VoteListener();
        vote_button.addActionListener(voteListener);

        ActionListener exitListener = new ExitListener();
        exit_button.addActionListener(exitListener);

        Insets insets = this.getInsets();
        this.setBounds(500, 300, 450 + insets.left + insets.right, 167 + insets.top + insets.bottom);
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
    }

    public class NewElectionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (Main.getCurrentVoting().title != "")
                JOptionPane.showMessageDialog(null, Main.getResults());
            Main.addVoting();
            current_election_label.setText("Current election: " + Main.getCurrentVoting().title);
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("current_voting.ser"));

                // Method for serialization of object
                out.writeObject(Main.getCurrentVoting());

                out.close();
            } catch (IOException e1) { }
        }
    }

    public class NewCandidateListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            setVisible(false);
//            findUserProcess();
            Main.addNewCandidate();
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("current_voting.ser"));

                // Method for serialization of object
                out.writeObject(Main.getCurrentVoting());

                out.close();
            } catch (IOException e1) { }
        }
    }

    public class ResultListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            setVisible(false);
//            findUserProcess();
            JOptionPane.showMessageDialog(null, Main.getResults());
        }
    }

    public class VoteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
//            setVisible(false);
//            findUserProcess();
            Main.vote();
            try {
                JOptionPane.showMessageDialog(null, Main.getResults());
                ObjectOutputStream out_voting = new ObjectOutputStream(new FileOutputStream("current_voting.ser"));
                ObjectOutputStream out_users = new ObjectOutputStream(new FileOutputStream("users.ser"));

                // Method for serialization of object
                out_voting.writeObject(Main.getCurrentVoting());
                out_users.writeObject(Main.getUsers());

                out_voting.close();
                out_users.close();
            } catch (IOException e1) { }
        }
    }

    public class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Main.mw.new_election_textField.setText("");
            Main.mw.new_candidate_textField.setText("");
            Main.log.login_textField.setText("");
            Main.log.password_textField.setText("");
            Main.log.setVisible(true);
        }
    }

    private String[] users_to_names(List<Candidate> candidates) {
        String[] candidates_names = new String[candidates.size()];
        for (int i = 0; i < candidates.size(); i++){
            candidates_names[i] = candidates.get(i).getName();
        }
        return candidates_names;
    }
}
