

import java.io.Serializable;
import java.util.Vector;

public class Class_t implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<Student> m_students;
	private String m_name;
	
	private String[] statuses = {"Absent", "Present", "Tardy"};

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
	
	public void addStudent(String name) {
		m_students.add(new Student(name));
	}
	
	public Object[][] toObjectField()
	{
		Object[][] ret = new Object[m_students.size()][3];
		for (int i = 0; i < m_students.size(); i++)
		{
			ret[i][0] = m_students.get(i).getName();
			ret[i][1] = m_students.get(i).getStatus();
		}
		return ret;
	}
}