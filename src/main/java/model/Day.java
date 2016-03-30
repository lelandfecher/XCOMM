package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by James Hollowell on 3/29/2016.
 */
public class Day {
    private Map<String, Attendance> data = new HashMap<>();

    public void SetAttendance(Student student, Attendance attendance) {
        data.put(student.getId(), attendance);
    }

    public Attendance GetAttendance(Student student) {
        return data.get(student.getId());
    }

    enum Attendance {
        Present,
        Tardy,
        Absent
    }
}
