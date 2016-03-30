package model;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by James Hollowell on 3/8/2016.
 */
public class ClassStore {
    private static transient ClassStore instance;
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

    public void Load() throws FileNotFoundException {
        Load("classes.json");
    }

    public void Load(String filePath) throws FileNotFoundException {
//        Gson gson = new Gson();
//        InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath));
//        classes = (List<Class>) gson.fromJson(isr, classes.getClass());
    }

    public void Save() throws IOException {
        Save("classes.json");
    }

    public void Save(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Could not create settings file");
        }
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String json = gson.toJson(this, classes.getClass());
//        FileWriter fileWriter = new FileWriter(file);
//        fileWriter.write(json);
//        fileWriter.close();
    }

}
