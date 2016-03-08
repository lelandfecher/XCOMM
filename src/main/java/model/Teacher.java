package model;

import java.util.Arrays;

/**
 * Created by James Hollowell on 3/1/2016.
 */
public class Teacher {
    private int id;
    private String fullName;
    private char[] password; //TODO: Not plain text.

    public Teacher(int id, String fullName, char[] password) {
        this.id = id;
        this.fullName = fullName;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean checkPassword(char[] password) {
        return Arrays.equals(this.password, password);
    }

    public void setPassword(char[] password) {
        this.password = Arrays.copyOf(password, password.length);
    }

    public int getID() {
        return id;
    }
}
