import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class AttendanceDlg extends JDialog {

	public AttendanceDlg(Frame f, String title, int whichClass) {
		
		super(f, title, true);
		
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Set window size
        this.setSize(new Dimension(840, 589));


        //Set Icon image
        //this.setIconImage(new ImageIcon("path/to/image.png").getImage());

        //Center it on screen by finding screen dimensions
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        
      //Create two panels, one from list/table, one for buttons
        JPanel mainPanel = new JPanel();
        this.getContentPane().add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new BorderLayout(25, 25));

        JPanel buttonPanel = new JPanel();
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        final JTable m_table = new JTable();
      
        Date date = new Date();
        //Set up table model
        Object[][] objField = ClassDataStore.getInstance().getClasses().get(whichClass).toAttendanceObjectField(date);
        final DefaultTableModel tableModel = new DefaultTableModel(
                objField,
                new String[]{
                        "Last Name", "First Name", "Username", "CUID", "Status"
                }) {
            @SuppressWarnings("rawtypes")
            Class[] columnTypes = new Class[]{
                    String.class, String.class,
                    String.class, String.class,
                    JComboBox.class
            };

            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
            	return false;
            }
        };
        m_table.setModel(tableModel);
        
        //Create menuBar and set it as this frame's menuBar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        //Add file menu and components
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        //Add exit item on file menu to exit program and save data
        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        exitMenuItem.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent arg0) {
                try {
                    ClassDataStore.getInstance().Save();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        
        
       //Add about menu
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        JMenuItem aboutMenuItem = new JMenuItem("About", KeyEvent.VK_A);
        aboutMenuItem.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                Frame frame = new Frame();
                SystemInformationDlg sid = new SystemInformationDlg(frame, "About");
                sid.setVisible(true);
            }
        });
        helpMenu.add(aboutMenuItem);

        this.setJMenuBar(menuBar);
        
        
        final JButton takeAttendanceBtn = new JButton("Submit Attendance!");
        final JButton cancelBtn = new JButton("Cancel");
        
        takeAttendanceBtn.addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent e) {
        		//TODO submit attendance
        	}
        });
        buttonPanel.add(takeAttendanceBtn, BorderLayout.SOUTH);
        
        cancelBtn.addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent e) {
        		dispose();
        	}
        });
        buttonPanel.add(cancelBtn, BorderLayout.SOUTH);
        
        JScrollPane scrollPane = new JScrollPane(m_table);
        mainPanel.add(scrollPane, BorderLayout.NORTH);
        
        //Set colors
        Color orange = new Color(234, 106, 32);
        Color purple = new Color(82, 45, 128);
        Color white = new Color(255,255,255);
        mainPanel.setBackground(purple);
        m_table.setForeground(purple);
        this.getContentPane().setBackground(purple);
        buttonPanel.setBackground(purple);

        takeAttendanceBtn.setBackground(orange);
        takeAttendanceBtn.setForeground(white);
        cancelBtn.setBackground(orange);
        cancelBtn.setForeground(white);
	}

}
