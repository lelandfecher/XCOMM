

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;

public class Class_t implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<Student> m_students;
	private String m_name;
	
	//list for days attendance has already been taken for this class
    private List<ClassDate> m_dates;

	
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
	
	public List<ClassDate> getDates() {
		return m_dates;
	}
	
	public void addDate(ClassDate date) {
		m_dates.add(date);
	}
	
	public Object[][] toObjectField()
	{
		//Date date = new Date();
		
		Object[][] ret = new Object[m_students.size()][6];
		for (int i = 0; i < m_students.size(); i++)
		{
			ret[i][0] = m_students.get(i).getLastname();
			ret[i][1] = m_students.get(i).getFirstname();
			ret[i][2] = m_students.get(i).getUsername();
			ret[i][3] = m_students.get(i).getCUID();
			ret[i][4] = m_students.get(i).getNumAbsences();
			ret[i][5] = m_students.get(i).getNumTardies();
		}
		return ret;
	}
	
	public Object[][] toAttendanceObjectField(Date date)
	{
		//Date date = new Date();
		String[] statuses = { "Present", "Tardy", "Absent"};
		
		Object[][] ret = new Object[m_students.size()][6];
		for (int i = 0; i < m_students.size(); i++)
		{
			ret[i][0] = m_students.get(i).getLastname();
			ret[i][1] = m_students.get(i).getFirstname();
			ret[i][2] = m_students.get(i).getUsername();
			ret[i][3] = m_students.get(i).getCUID();
			JComboBox comboBox = new JComboBox(statuses);
			comboBox.setSelectedIndex(0);
			//m_students.get(i).getStatus(date);
			ret[i][4] = comboBox;
		}
		return ret;
	}

	@Override
	public String toString() {
		return m_name;
	}
}