package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 3/8/2016.
 */
public class ClassStore {
    private static ClassStore instance;
    private List<Class> classes;

    private ClassStore() {
        classes = new ArrayList<>();
    }

    public static ClassStore getInstance() {
        if (instance == null) {
            instance = new ClassStore();
        }
        return instance;
    }


}
