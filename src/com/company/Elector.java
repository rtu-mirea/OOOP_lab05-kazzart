package com.company;

public class Elector extends User implements java.io.Serializable {
    private boolean voted = false;

    public Elector(String name, String login, char[] password) {
        super(name, login, password);
        access = 0;
    }

    public boolean isVoted() {
        return voted;
    }

    public void vote() {
        voted = true;
    }
}
