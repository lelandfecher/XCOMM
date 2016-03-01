package model;

/**
 * Created by James Hollowell on 3/1/2016.
 */
public class Teacher {
    private String fullName;
    private String password; //TODO: Not plain text.

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
