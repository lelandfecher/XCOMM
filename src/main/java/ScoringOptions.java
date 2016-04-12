// This class allows different options for how attendance is graded.

public class ScoringOptions
{
	private double m_max; // The maximum score to start from - used for the CountDown method
	private double m_presentPoints; // The number of points each date of present is worth - used for CountUp method
	private double m_absentPoints; // The number of points for each date of absent is worth - used for CountDown method
	private double m_tardyPoints; // The number of points for each date of tardy is worth - used for CountDown method
	private double m_tardyRatio; // The percentage of a present that a tardy is worth as a value between 0 and 1 - used for Average method
	private int m_graceAbsence; // The number of absences that will not be factored into the score (ex. Points will be deducted for each absence after 2)
	private int m_graceTardy; // The number of tardies that will not be factored into the score
	private Method m_method; // The method use for determining the score - see enumerator Method
	
	public ScoringOptions()
	{
		m_max = 100;
		m_presentPoints = 0;
		m_absentPoints = 0;
		m_tardyPoints = 0;
		m_tardyRatio = 0;
		m_graceAbsence = 0;
		m_graceTardy = 0;
		m_method = Method.CountDown;
	}
	
	public double getMax()
	{
		return m_max;
	}
	
	public void setMax(double max)
	{
		m_max = max;
	}
	
	public double getPointsPerPresent()
	{
		return m_presentPoints;
	}
	
	public void setPointsPerPresent(double points)
	{
		m_presentPoints = points;
	}
	
	public double getPointsPerAbsent()
	{
		return m_absentPoints;
	}
	
	public void setPointsPerAbsent(double points)
	{
		m_absentPoints = points;
	}
	
	public double getPointsPerTardy()
	{
		return m_tardyPoints;
	}
	
	public void setPointsPerTardy(double points)
	{
		m_tardyPoints = points;
	}
	
	public double getTardyWorthPercent()
	{
		return m_tardyRatio;
	}
	
	public void setTardyWorthPercent(double points)
	{
		m_tardyRatio = points;
	}

	public int getNumGraceAbsences()
	{
		return m_graceAbsence;
	}
	
	public void setNumGraceAbsences(int count)
	{
		m_graceAbsence = count;
	}
	
	public int getNumGraceTardies()
	{
		return m_graceTardy;
	}
	
	public void setNumGraceTardies(int count)
	{
		m_graceTardy = count;
	}
	
	public Method getScoringMethod()
	{
		return m_method;
	}
	
	public void setScoringMethod(Method method)
	{
		m_method = method;
	}
}

enum Method
{
	CountUp, // Start from 0 and count up for each present and (if wanted) tardy
	CountDown, // Start from a max score and count down for each absent and tardy
	Average // Calculate the number of days present and tardy out of all the days on record and score as a percent (out of 100)
}