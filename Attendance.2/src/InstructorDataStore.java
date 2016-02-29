

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.AbstractListModel;


public class InstructorDataStore extends AbstractListModel<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Singleton Instance
	private static InstructorDataStore instance = null;
	
	//Data to be stored
	private static Vector<Instructor> m_instructors;
	private int whichInstructor;
	//private static ArrayList<Student_t> m_students;
		
	//Constructor (Private for Singleton)
	private InstructorDataStore() {
		m_instructors = new Vector<Instructor>();
		m_instructors.add(new Instructor("Austin", "password"));
		whichInstructor = 0;
	}
	
	//Singleton Pattern
	public static InstructorDataStore getInstance() {
		if (instance == null) instance = new InstructorDataStore();
		
		return instance;
	}
	
	//Getter
	public static Vector<Instructor> getInstructors() {
		return m_instructors;
	}
	
	//Save classes (called upon application quit/exit)
	public static void saveInstructors(Vector<Instructor> instructors, String filename) throws IOException {
		System.out.println("Saving Instructors\n\n\n");
		FileOutputStream fos = new FileOutputStream(filename);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(instructors);
		oos.close();
		fos.close();
	}
	
	//Load classes (called upon application start)
	@SuppressWarnings("unchecked")
	public static Vector<Instructor> loadInstructors(String filename) throws Exception {
		System.out.println("Loading Instructors\n\n\n");
		FileInputStream fis = new FileInputStream(filename);
		ObjectInputStream ois = new ObjectInputStream(fis);
		m_instructors = (Vector<Instructor>)ois.readObject();
		ois.close();
		fis.close();
		return m_instructors;
	}

	public Object getElementAt(int index) {
		return m_instructors.get(whichInstructor).getClasses().get(index).getName();
	}

	public int getSize() {
		return m_instructors.get(whichInstructor).getClasses().size();
	}

	
}

