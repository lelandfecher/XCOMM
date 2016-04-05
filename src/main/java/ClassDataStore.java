import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassDataStore {

    private static final transient Logger logger = LogManager.getLogger("ClassDataStore");
    private static final String[] columnNames = {"Last Name", "First Name", "Username", "CUID", "Status"};
    //Singleton Instance
    private static transient ClassDataStore instance = null;
    //Data to be stored
    private List<Class_t> classes;

    //Constructor (Private for Singleton)
    private ClassDataStore() {
        classes = new ArrayList<Class_t>();
    }

    //Singleton Pattern
    public static ClassDataStore getInstance() {
        if (instance == null) instance = new ClassDataStore();

        return instance;
    }

    //Getter
    public List<Class_t> getClasses() {
        return classes;
    }

    public void Load() throws IOException {
        Load("classes.json");
    }

    public void Load(String filePath) throws IOException {
        if (new File(filePath).exists()) {
            Gson gson = new Gson();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
            StringBuilder sb = new StringBuilder();
            while (br.ready()) {
                sb.append(br.readLine());
            }
            ClassDataStore cds = gson.fromJson(sb.toString(), ClassDataStore.class);
            this.classes = cds.getClasses();
        }
    }

    public void Save() throws IOException {
        Save("classes.json");
    }

    public void Save(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists() && !file.createNewFile()) {
            throw new IOException("Could not create settings file");
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this, ClassDataStore.class);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(json);
        fileWriter.close();
    }

    public void DeleteClass(int selectedRow) {
        classes.remove(selectedRow);
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
}

