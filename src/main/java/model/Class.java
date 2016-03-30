package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 3/1/2016.
 */
public class Class {
    private transient Teacher teacher;
    private int teacherID;
    private List<Student> students;
    private List<Day> days;

    public Class() {
        this.students = new ArrayList<>();
        this.days = new ArrayList<>();
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacherID = teacher.getID();
        this.teacher = teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Day> getDays() {
        return days;
    }
}
