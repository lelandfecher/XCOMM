import java.util.ArrayList;

public class class_t
{
	public class_t()
	{
		students = new ArrayList<student_t>();
		name = null;
	}
	
	public ArrayList<student_t> students;
	private String name;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String arg)
	{
		name = arg;
	}
	
	public Object[][] toObjectField()
	{
		Object[][] ret = new Object[students.size()][3];
		for (int i = 0; i < students.size(); i++)
		{
			ret[i][0] = students.get(i).getName();
			ret[i][1] = students.get(i).isPresent();
			ret[i][2] = students.get(i).isTardy();
		}
		return ret;
	}
}