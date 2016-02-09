public class student_t
{
	public student_t()
	{
		name = null;
		present = false;
		tardy = false;
	}
	
	private String name;
	private Boolean present;
	private Boolean tardy;
	
	public String getName()
	{
		return name;
	}
	
	public Boolean isPresent()
	{
		return present;
	}
	
	public Boolean isTardy()
	{
		return tardy;
	}
	
	public void setName(String arg)
	{
		name = arg;
	}
	
	public void setIsPresent(Boolean arg)
	{
		present = arg;
	}
	
	public void setIsTardy(Boolean arg)
	{
		tardy = arg;
	}
}