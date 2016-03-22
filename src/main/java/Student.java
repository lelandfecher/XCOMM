import javax.swing.*;
import java.io.Serializable;

public class Student implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String m_Firstname;
    private String m_Lastname;
    private String m_Username;
    private String m_CUID;

    //Status = 0 for absent, 1 for present, and 2 for tardy
    private JComboBox<String> m_status;

    public Student(String first, String last, String username, String CUID) {
        m_Firstname = first;
        m_Lastname = last;
        m_Username = username;
        m_CUID = CUID;
        m_status = new JComboBox<String>();
        m_status.addItem("Absent");
        m_status.addItem("Present");
        m_status.addItem("Tardy");
        m_status.setSelectedIndex(1);
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

    public Object getStatus() {
        return m_status.getSelectedItem();
    }

    public void setStatus(int status) {
        m_status.setSelectedIndex(status);
    }

    public void setPresent() {
        m_status.setSelectedIndex(1);
    }

    public void setTardy() {
        m_status.setSelectedIndex(2);
    }

    public void setAbsent() {
        m_status.setSelectedIndex(0);
    }
}

