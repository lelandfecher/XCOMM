package model;

/**
 * Created by James Hollowell on 3/1/2016.
 */
public class Student {
    private String fullName;
    private Attendance attendance;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    enum Attendance {
        Present,
        Tardy,
        Absent
    }
}
