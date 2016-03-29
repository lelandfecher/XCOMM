enum Method
{
	CountUp,
	CountDown,
	Average
}

public class ScoringOptions
{
	private double m_max;
	private double m_presentPoints;
	private double m_absentPoints;
	private double m_tardyPoints;
	private double m_tardyRatio;
	private int m_graceAbsence;
	private int m_graceTardy;
	private Method m_method;
	
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
