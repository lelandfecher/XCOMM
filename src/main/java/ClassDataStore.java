import javax.swing.table.AbstractTableModel;
import java.io.*;
import java.util.Vector;

public class ClassDataStore extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //Singleton Instance
    private static ClassDataStore instance = null;

    //Data to be stored
    private static Vector<Class_t> m_classes;
    //private static ArrayList<Student_t> m_students;

    private String[] columnNames = {"Student", "Status"};

    //Constructor (Private for Singleton)
    private ClassDataStore() {
        m_classes = new Vector<Class_t>();
        //m_classes = initialize_classes();
    }

    //Singleton Pattern
    public static ClassDataStore getInstance() {
        if (instance == null) instance = new ClassDataStore();

        return instance;
    }

    //Getter
    public static Vector<Class_t> getClasses() {
        return m_classes;
    }

    //Save classes (called upon application quit/exit)
    public static void saveClasses(Vector<Class_t> classes, String filename) throws IOException {
        System.out.println("Saving Classes\n\n\n");
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(classes);
        oos.close();
        fos.close();
    }

    //Load classes (called upon application start)
    @SuppressWarnings("unchecked")
    public static Vector<Class_t> loadClasses(String filename) throws Exception {
        System.out.println("Loading Classes\n\n\n");
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        m_classes = (Vector<Class_t>) ois.readObject();
        ois.close();
        fis.close();
        return m_classes;
    }

	
	/*Vector<Class_t> initialize_classes()
	{
		System.out.println("Initializing Classes\n\n\n");
		Vector<Class_t> classes = new Vector<Class_t>();
		classes.add(new Class_t());
		classes.get(0).setName("XCOMM");
		classes.get(0).students.add(new Student("Jack"));
		classes.get(0).students.add(new Student("Austin"));
		classes.get(0).students.add(new Student("Molly"));
		classes.get(0).students.add(new Student("Zak"));
		classes.get(0).students.add(new Student("James"));
		
		classes.add(new Class_t());
		classes.get(1).setName("COMM 1500 - 001");
		classes.get(1).students.add(new Student("George"));
		classes.get(1).students.add(new Student("Bob"));
		classes.get(1).students.add(new Student("Susan"));
		classes.get(1).students.add(new Student("Dick"));
		classes.get(1).students.add(new Student("Will"));
		
		classes.add(new Class_t());
		classes.get(2).setName("COMM 1501 - 001");
		classes.get(2).students.add(new Student("Luke"));
		classes.get(2).students.add(new Student("Jillian"));
		classes.get(2).students.add(new Student("Olivia"));
		classes.get(2).students.add(new Student("Summer"));
		classes.get(2).students.add(new Student("Jim"));

		classes.add(new Class_t());
		classes.get(3).setName("COMM 2500 - 001");
		classes.get(3).students.add(new Student("Johnathon"));
		classes.get(3).students.add(new Student("Catherine"));
		classes.get(3).students.add(new Student("Jean-Luc"));
		classes.get(3).students.add(new Student("Saransh"));
		classes.get(3).students.add(new Student("Mike"));
		
		classes.add(new Class_t());
		classes.get(4).setName("COMM 2501 - 001");
		for (int i = 1; i < 35; i++) {
			classes.get(4).students.add(new Student("Student " + i));
		}
		
		return classes;
	}*/

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public int getRowCount() {
        return m_classes.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //If first column, student last name
        if (columnIndex == 0)
            return m_classes.get(rowIndex).getStudents().elementAt(columnIndex).getFirstname();
        //If second column, student first name
        else if (columnIndex == 1)
            return m_classes.get(rowIndex).getStudents().elementAt(columnIndex).getLastname();
        //If third column, student username
        else if (columnIndex == 2)
            return m_classes.get(rowIndex).getStudents().elementAt(columnIndex).getUsername();
        //If fourth column, student cuid
        else if (columnIndex == 3)
            return m_classes.get(rowIndex).getStudents().elementAt(columnIndex).getCUID();
        //Otherwise return tardy or present or absent
        else
            return m_classes.get(rowIndex).getStudents().elementAt(columnIndex).getStatus();
    }

    public void delClass(int selectedRow) {
        m_classes.remove(selectedRow);
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
}

