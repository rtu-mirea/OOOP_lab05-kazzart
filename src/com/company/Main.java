package com.company;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static Voting currentVoting;
    private static User currentUser;
    public static Login log;
    public static Register reg;
    public static MainWindow mw ;
    public static void main(String[] args) {
        try
        {
            // Reading the object from a file
            ObjectInputStream in_voting = new ObjectInputStream(new FileInputStream("current_voting.ser"));
            ObjectInputStream in_users = new ObjectInputStream(new FileInputStream("users.ser"));

            // Method for deserialization of object
            currentVoting = (Voting)in_voting.readObject();
            users = (List<User>)in_users.readObject();

            in_voting.close();
            in_users.close();

        } catch(IOException e) { }
        catch(ClassNotFoundException e) { }
        log = new Login();
        reg = new Register();
        mw = new MainWindow();
        log.setVisible(true);
//        reg.setVisible(true);
//        mw.setVisible(true);
        char[] pass = {'a', 'd', 'm', 'i', 'n'};
        users.add(new Admin("admin", "admin", pass));
        int comm0 = -1;
        Scanner in = new Scanner(System.in);
        while (comm0 != 0) {
            outputMenu0();
            System.out.print("Choose variant: ");
            comm0 = in.nextInt();
            System.out.println();
            switch (comm0) {
                case 1:
//                    findUserProcess();
                    if (currentUser != null && currentUser.access == 0) {
                        int comm1 = -1;
                        while (comm1 != 0) {
                            outputMenu1_1();
                            System.out.print("Choose variant: ");
                            comm1 = in.nextInt();
                            System.out.println();
                            switch (comm1) {
                                case 1:
                                    vote();
                                    break;
                                case 2:
                                    getResults();
                                    break;
                                case 0:
                                    System.out.println("Exiting the account");
                                    System.out.println();
                                    currentUser = null;
                                    break;
                                default:
                                    System.out.println("Entered wrong command");
                                    System.out.println();
                                    break;
                            }
                        }
                    } else if(currentUser != null && currentUser.access == 1) {
                        int comm1 = -1;
                        while (comm1 != 0) {
                            outputMenu1_2();
                            System.out.print("Choose variant: ");
                            comm1 = in.nextInt();
                            System.out.println();
                            switch (comm1) {
                                case 1:
                                    addVoting();
                                    break;
                                case 2:
                                    addNewCandidate();
                                    break;
                                case 3:
                                    getResults();
                                    break;
                                case 0:
                                    System.out.println("Exiting the account");
                                    System.out.println();
                                    currentUser = null;
                                    break;
                                default:
                                    System.out.println("Entered wrong command");
                                    System.out.println();
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
//                    addUserProcess();
                    break;
                case 0:
                    System.out.println("Ending the program...");
                    break;
                default:
                    System.out.println("Entered wrong command");
                    break;
            }
        }
    }

//    private static User addUser(String name, String login, char[] password, String role) throws Exception {
//        if (!users.isEmpty()) {
//            for (User user: users) {
//                if (user.login.equals(login)) {
//                    System.out.println("0");
//                    throw new Exception("Login already exists");
//                }
//            }
//        }
//
//        if (role.equals("admin")) {
//            Admin user = new Admin(name, login, password);
//            users.add(user);
//            return user;
//        } else if (role.equals("elector")) {
//            Elector user = new Elector(name, login, password);
//            users.add(user);
//            return user;
//        }
//        throw new Exception("Wrong role!");
//    }
//
//    private static void addUserProcess() {
//        boolean done = false;
//        while (!done) {
//            Scanner in = new Scanner(System.in);
//            System.out.print("Enter your name: ");
//            String name = in.nextLine();
//            System.out.print("Enter your login: ");
//            String login = in.nextLine();
//            System.out.print("Enter your password: ");
//            String password1 = in.nextLine();
//            System.out.print("Enter your password again: ");
//            String password2 = in.nextLine();
//            if (password1.equals(password2)) {
//                try {
//                    addUser(name, login, password1, "elector");
//                    System.out.println("New elector registered");
//                    System.out.println();
//                    done = true;
//                } catch (Exception e) {
//                    System.out.println(e.getMessage());
//                }
//            } else {
//                System.out.println("Passwords are different");
//            }
//        }
//    }
//
//    private static User findUser(String login, String password) throws Exception {
//        if (!users.isEmpty()) {
//            for (User user: users) {
//                if (user.login.equals(login)) {
//                    if (user.password.equals(password)) {
//                        return user;
//                    } else {
//                        throw new Exception("Wrong password!");
//                    }
//                }
//            }
//        }
//        throw new Exception("Login not found!");
//    }
//
//    private static void findUserProcess() {
//        Scanner in = new Scanner(System.in);
//        System.out.print("Enter your login: ");
//        String login = in.nextLine();
//        System.out.print("Enter your password: ");
//        String password1 = in.nextLine();
//        System.out.println();
//        try {
//            currentUser = findUser(login, password1);
//            if (currentUser.access == 1) {
//                System.out.println("Logined as admin - " + currentUser.login);
//                System.out.println();
//            } else {
//                System.out.println("Logined as elector - " + currentUser.login);
//                System.out.println();
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }

    public static void addVoting() {
        String title = mw.new_election_textField.getText();
        currentVoting = new Voting(title, new ArrayList<>());
    }

    public static void addNewCandidate() {
        if (currentVoting != null) {
            String name = mw.new_candidate_textField.getText();
            currentVoting.candidates.add(new Candidate(name));
            mw.candidates_select_list.addItem(name);
        } else {
            JOptionPane.showMessageDialog(null, "There is no any elections right now");
        }
    }

    public static void vote() {
        if (!currentVoting.candidates.isEmpty()) {
            if (!currentUser.lastElection.title.equals(currentVoting.title)) {
                int choiceDone = -1;
                while (choiceDone < 0 || choiceDone > currentVoting.candidates.size()) {
                    choiceDone = mw.candidates_select_list.getSelectedIndex();
//                    System.out.println();
                    if (choiceDone >= 0 && choiceDone <= currentVoting.candidates.size()) {
                        Candidate votedCandidate = currentVoting.candidates.get(choiceDone);
                        votedCandidate.addVoice();
                        currentVoting.candidates.set(choiceDone, votedCandidate);
                        currentUser.lastElection = currentVoting;
                    } else {
                        JOptionPane.showMessageDialog(null, "Wrong input!");
                        choiceDone = -1;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "You cannot vote in this elections anymore");
            }
        } else {
            JOptionPane.showMessageDialog(null, "There is no any person in the candidates list");
        }
    }

    public static String getResults() {
//        Scanner in = new Scanner(System.in);
        String res = "";
        if (currentVoting != null && !currentVoting.candidates.isEmpty()) {
            res += "Elections: " + currentVoting.title + "\n";
            for (int i = 0; i < currentVoting.candidates.size(); i++) {
                res += Integer.toString(i + 1) + ". " + currentVoting.candidates.get(i).getName() + ": " +
                        currentVoting.candidates.get(i).getVoices() + "\n";
            }
            System.out.println();

        } else {
            res = "There is no any person in the candidates list";
        }
        return res;
    }

    private static void outputMenu0() {
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("0. Exit");
    }

    private static void outputMenu1_1() {
        System.out.println("1. Vote");
        System.out.println("2. Election results");
        System.out.println("0. Exit account");
    }

    private static void outputMenu1_2() {
        System.out.println("1. Hold new elections");
        System.out.println("2. Add new candidate in the list");
        System.out.println("3. Election results");
        System.out.println("0. Exit account");
    }

    public static void setUsers(List<User> users) {
        Main.users = users;
    }

    public static void setCurrentUser(User currentUser) {
        Main.currentUser = currentUser;
    }

    public static void setCurrentVoting(Voting currentVoting) {
        Main.currentVoting = currentVoting;
    }

    public static List<User> getUsers() {
        return users;
    }

    public static User getCurrentUser() {
        if (currentUser != null) {
            return currentUser;
        } else
            return new User("", "", new char[0]);
    }

    public static Voting getCurrentVoting() {

        if (currentVoting != null) {
            return currentVoting;
        } else
            return new Voting("", new ArrayList<>());
    }
}
