/* I have not uploaded anything using this class, but this could be
 * added to the Student class in a list/vector.
 */

import java.util.Date;

enum Status
{
	Present,
	Absent,
	Tardy,
	NULL
};

public class ClassDate
{
	
	private Date m_date;
	private Status m_status;
	
	public ClassDate(Date date, Status status)
	{
		m_date = date;
		m_status = status;
	}
	
	public void setDate(Date date)
	{
		m_date = date;
	}
	
	public Date getDate()
	{
		return m_date;
	}
	
	public void setStatus(Status status)
	{
		m_status = status;
	}
	
	public Status getStatus()
	{
		return m_status;
	}
}
