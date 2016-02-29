import java.io.Serializable;
import java.util.Vector;


public class Instructor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Vector<Class_t> m_classes;
	private String m_name;
	private String m_password;
	
	public Instructor(String name, String password)
	{
		m_classes = new Vector<Class_t>();
		m_classes.add(new Class_t("COMM 1500"));
		setName(name);
		setPassword(password);
	}
	
	public String getName()
	{
		return m_name;
	}
	
	public void setName(String name)
	{
		m_name = name;
	}
	
	public String getPassword() {
		return m_password;
	}

	public void setPassword(String m_password) {
		this.m_password = m_password;
	}
	
	public Vector<Class_t> getClasses() {
		return m_classes;
	}
	
	public void addClass(String name) {
		m_classes.add(new Class_t(name));
	}


	
	

}
