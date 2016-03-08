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

    public Class() {
        this.students = new ArrayList<>();
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

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
