package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 3/8/2016.
 */
public class TeacherDataStore {
    private static TeacherDataStore instance;
    private List<Teacher> teachers;

    private TeacherDataStore() {
        teachers = new ArrayList<>();
    }

    public static TeacherDataStore getInstance() {
        if (instance == null) {
            instance = new TeacherDataStore();
        }
        return instance;
    }

}
