import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String m_Firstname;
    private String m_Lastname;
    private String m_Username;
    private String m_CUID;

    private List<ClassDate> m_dates;

    public Student() {
        m_dates = new ArrayList<>();
    }

    public Student(String first, String last, String username, String CUID) {
        m_Firstname = first;
        m_Lastname = last;
        m_Username = username;
        m_CUID = CUID;
        m_dates = new ArrayList<>();
    }

    public String getFirstname() {
        return m_Firstname;
    }

    public void setFirstname(String first) {
        m_Firstname = first;
    }

    public String getLastname() {
        return m_Lastname;
    }

    public void setLastname(String last) {
        m_Lastname = last;
    }

    public String getUsername() {
        return m_Username;
    }

    public void setUsername(String name) {
        m_Username = name;
    }

    public String getCUID() {
        return m_CUID;
    }

    public void setCUID(String cuid) {
        m_CUID = cuid;
    }

    public Status getStatus(Date date) {
        ClassDate cd = new ClassDate(date, Status.NULL);
        for (ClassDate d : m_dates)
            if (date == d.getDate()) {
                cd = d;
                break;
            }
        return cd.getStatus();
    }

    public void setStatus(Date date, Status status) {
        ClassDate cd = new ClassDate(date, Status.NULL);
        for (ClassDate d : m_dates)
            if (date == d.getDate()) {
                cd = d;
                break;
            }
        cd.setStatus(status);
    }

    public void setPresent(Date date) {
        ClassDate cd = new ClassDate(date, Status.NULL);
        for (ClassDate d : m_dates)
            if (date == d.getDate()) {
                cd = d;
                break;
            }
        cd.setStatus(Status.Present);
    }

    public void setTardy(Date date) {
        ClassDate cd = new ClassDate(date, Status.NULL);
        for (ClassDate d : m_dates)
            if (date == d.getDate()) {
                cd = d;
                break;
            }
        cd.setStatus(Status.Tardy);
    }

    public void setAbsent(Date date) {
        ClassDate cd = new ClassDate(date, Status.NULL);
        for (ClassDate d : m_dates)
            if (date == d.getDate()) {
                cd = d;
                break;
            }
        cd.setStatus(Status.Absent);
    }

    public void addDate(Date date, Status status) {
        for (ClassDate d : m_dates)
            if (date == d.getDate()) {
                UnsupportedOperationException exception = new UnsupportedOperationException("The passed in date is already in record");
                throw exception;
            }
        m_dates.add(new ClassDate(date, status));
    }

    public double getScore(ScoringOptions opt) {
        double score = 100;
        int presentCount = 0;
        int tardyCount = 0;
        int absentCount = 0;
        Method method = opt.getScoringMethod();

        for (ClassDate cd : m_dates) {
            if (cd.getStatus() == Status.Present)
                ++presentCount;
            else if (cd.getStatus() == Status.Absent)
                ++absentCount;
            else if (cd.getStatus() == Status.Tardy)
                ++tardyCount;
        }

        if (opt.getNumGraceAbsences() > absentCount) {
            presentCount += absentCount;
            absentCount = 0;
        } else {
            presentCount += opt.getNumGraceAbsences();
            absentCount -= opt.getNumGraceAbsences();
        }

        if (opt.getNumGraceTardies() > tardyCount) {
            presentCount += tardyCount;
            tardyCount = 0;
        } else {
            presentCount += opt.getNumGraceTardies();
            absentCount -= opt.getNumGraceTardies();
        }

        if (method == Method.CountDown)
            score = opt.getMax() - (opt.getPointsPerAbsent() * absentCount) - (opt.getPointsPerTardy() * tardyCount);
        else if (method == Method.CountUp)
            score = (opt.getPointsPerPresent() * presentCount) + ((opt.getTardyWorthPercent() / 100) * tardyCount);
        else if (method == Method.Average)
            score = (double) (presentCount + ((opt.getTardyWorthPercent() / 100) * tardyCount)) / (presentCount + tardyCount + absentCount) * 100;

        return score;
    }
    
    public int getNumAbsences() {
    	int absentCount = 0;
    	
        for (ClassDate cd : m_dates) {
            if (cd.getStatus() == Status.Absent)
                ++absentCount;
        }
        
        return absentCount;
    }
    
    public int getNumTardies() {
    	int tardyCount = 0;
    	
        for (ClassDate cd : m_dates) {
            if (cd.getStatus() == Status.Absent)
                ++tardyCount;
        }
        
        return tardyCount;
    }
}

