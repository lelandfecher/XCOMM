import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;


public class DateSelectionDlg extends JDialog {
	
	private String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	//private String[] lessDays = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
	//private String[] febDays = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28" };
	private String[] years = { "2016", "2017" };
	
	private Date m_date;

    public DateSelectionDlg(final Frame frame, String title, Date date, final int whichClass) {

        super(frame, title, true);
		this.setSize(new Dimension(300, 150));
        this.setIconImage(new ImageIcon("tigerpaw.jpg").getImage());
		
		m_date = date;
		
		//Center on screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        JPanel northpanel = new JPanel();
        //Two subpanels for northpanel
        JPanel one = new JPanel();
        JPanel two = new JPanel();
        
        JPanel southpanel = new JPanel();
        
        JLabel month = new JLabel("Month", JLabel.TRAILING);
        one.add(month, BorderLayout.WEST);
        final JComboBox monthDropDown = new JComboBox(months);
        monthDropDown.setSelectedIndex(date.getMonth());
        two.add(monthDropDown,BorderLayout.WEST);
        
        JLabel day = new JLabel("               Day", JLabel.TRAILING);
        one.add(day, BorderLayout.CENTER);
        final JComboBox dayDropDown = new JComboBox(days);
        dayDropDown.setSelectedIndex(date.getDate() - 1);
        two.add(dayDropDown,BorderLayout.CENTER);
        
        JLabel year1 = new JLabel("     Year", JLabel.TRAILING);
        one.add(year1, BorderLayout.EAST);
        JLabel year2 = new JLabel(years[date.getYear() - 116], JLabel.TRAILING);
        two.add(year2, BorderLayout.EAST);
        
        final JButton confirmButton = new JButton("Take Attendance!");    //create save Button
        confirmButton.addMouseListener(new MouseAdapter() {        //create actionListener for when it is pressed
            public void mouseClicked(MouseEvent arg0) {
            	
            	//Open up new dialog box for taking attendance with current class on current day
            	m_date.setMonth(monthDropDown.getSelectedIndex());
            	m_date.setDate(dayDropDown.getSelectedIndex() + 1);


                AttendanceDlg attendanceDlg = new AttendanceDlg(frame, "Let's Take Attendance!", whichClass, m_date);
                attendanceDlg.setVisible(true);
                
            	dispose();
            }
        });
        southpanel.add(confirmButton, BorderLayout.WEST);

        final JButton cancelButton = new JButton("Cancel");    //create cancel Button
        cancelButton.addMouseListener(new MouseAdapter() {        //create actionListener for when it is pressed
            public void mouseClicked(MouseEvent arg0) {
                dispose();
            }
        });
        southpanel.add(cancelButton, BorderLayout.CENTER);
        
        northpanel.add(one);
        northpanel.add(two);
        
        add(northpanel);
        add(southpanel, BorderLayout.SOUTH);
        
        
	}

}
