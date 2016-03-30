package model;

/**
 * Created by James Hollowell on 3/1/2016.
 */
public class Student {
    private String fullName;
    private String username;
    private String id;

    public Student(String fullName, String username, String id) {
        this.fullName = fullName;
        this.username = username;
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
