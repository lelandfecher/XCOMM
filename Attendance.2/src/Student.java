
import java.io.Serializable;

import javax.swing.JComboBox;

public class Student implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String m_name;
	
	//Status = 0 for absent, 1 for present, and 2 for tardy
	private JComboBox<String> m_status;
	
	public Student(String name) {
		m_name = name;
		m_status = new JComboBox<String>();
		m_status.addItem("Absent");
		m_status.addItem("Present");
		m_status.addItem("Tardy");
		m_status.setSelectedIndex(1);
	}
	

	
	public String getName()
	{
		return m_name;
	}
	
	public void setName(String name)
	{
		m_name = name;
	}
	
	public Object getStatus()
	{
		return m_status.getSelectedItem();
	}
	
	public void setStatus(int status)
	{
		m_status.setSelectedIndex(status);
	}
	
	public void setPresent()
	{
		m_status.setSelectedIndex(1);
	}
	
	public void setTardy()
	{
		m_status.setSelectedIndex(2);
	}
	
	public void setAbsent()
	{
		m_status.setSelectedIndex(0);
	}
}

