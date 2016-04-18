import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;


public class AttendanceDlg extends JDialog {

	private Date m_date;

    public AttendanceDlg(final Frame f, String title, final int whichClass, Date date) {

        super(f, title + " Date: " + date.toString(), true);
		
		m_date = date;
		
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        //Set window size
        this.setSize(new Dimension(840, 589));


        //Set Icon image
        this.setIconImage(new ImageIcon("tigerpaw.jpg").getImage());

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
      
        //Set up table model
        Object[][] objField = ClassDataStore.getInstance().getClasses().get(whichClass).toAttendanceObjectField(date);
        Object[][] objF = new Object[objField.length][5];
        for (int i = 0; i < objField.length; ++i)
        {
        	objF[i][0] = objField[i][0];
        	objF[i][1] = objField[i][1];
        	objF[i][2] = objField[i][2];
        	objF[i][3] = objField[i][3];
            objF[i][4] = ((JComboBox) objField[i][4]).getSelectedItem();
        }
        final DefaultTableModel tableModel = new DefaultTableModel(
                objF,
                new String[]{
                        "Last Name", "First Name", "Username", "CUID", "Status"
                }) {
            @SuppressWarnings("rawtypes")
            Class[] columnTypes = new Class[]{
                    String.class, String.class,
                    String.class, String.class,
                    String.class
            };
            
            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
            	return column == 4 ? true : false;
            }
        };
        m_table.setModel(tableModel);
        JComboBox cb = new JComboBox();
        cb.addItem("Present");
        cb.addItem("Absent");
        cb.addItem("Tardy");
        m_table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(cb));
        
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
        		String stat;
        		for (int i = 0; i < m_table.getRowCount(); ++i)
        		{
        			ClassDate d;
        			stat = (String)m_table.getModel().getValueAt(i, 4);
                    switch (stat) {
                        case "Present":
                            d = new ClassDate(m_date, Status.Present);
                            break;
                        case "Absent":
                            d = new ClassDate(m_date, Status.Absent);
                            break;
                        case "Tardy":
                            d = new ClassDate(m_date, Status.Tardy);
                            break;
                        default:
                            d = new ClassDate(m_date, Status.NULL);
                    }
                    ClassDataStore.getInstance().getClasses().get(whichClass).getStudents().get(i).addDate(d.getDate(), d.getStatus());
        		}

                DefaultTableModel parentTableModel = (DefaultTableModel) ((ClassMainFrame) f).m_table.getModel();

                while (parentTableModel.getRowCount() != 0) {
                    parentTableModel.removeRow(0);
                }

                for (int i = 0; i < ClassDataStore.getInstance().getClasses().get(whichClass).getStudents().size(); i++) {

                    Student student = ClassDataStore.getInstance().getClasses().get(whichClass).getStudents().get(i);
                    parentTableModel.addRow(new Object[]{
                            student.getLastname(),
                            student.getFirstname(),
                            student.getUsername(),
                            student.getCUID(),
                            student.getNumAbsences(),
                            student.getNumTardies()
                    });
                }

                parentTableModel.fireTableDataChanged();

                dispose();
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
