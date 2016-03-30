

import java.io.Serializable;
import java.util.Date;
import java.util.Vector;

public class Class_t implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<Student> m_students;
	private String m_name;
	
	//private String[] statuses = {"Absent", "Present", "Tardy"};

	public Class_t(String name)
	{
		m_students = new Vector<Student>();
		m_name = name;
	}
	

	
	public String getName()
	{
		return m_name;
	}
	
	public void setName(String name)
	{
		m_name = name;
	}
	
	public Vector<Student> getStudents() {
		return m_students;
	}
	
	public void addStudent(String first, String last, String user, String cuid) {
		m_students.add(new Student(first, last, user, cuid));
	}
	
	public void addStudent(Student s) {
		m_students.add(s);
	}
	
	public void setStudent(int index, Student s) {
		m_students.set(index, s);
	}
	
	public Object[][] toObjectField()
	{
		Date date = new Date();
		
		Object[][] ret = new Object[m_students.size()][5];
		for (int i = 0; i < m_students.size(); i++)
		{
			ret[i][0] = m_students.get(i).getLastname();
			ret[i][1] = m_students.get(i).getFirstname();
			ret[i][2] = m_students.get(i).getUsername();
			ret[i][3] = m_students.get(i).getCUID();
			ret[i][4] = m_students.get(i).getStatus(date);
		}
		return ret;
	}
}