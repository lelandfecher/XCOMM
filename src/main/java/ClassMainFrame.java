import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public class ClassMainFrame extends JFrame {

    //	//Variable instances

    public final JTable m_table;
    private Class_t whichClass;


    public ClassMainFrame(String title) {
        //Call super constructor to set up JFrame
        super(title);

        //For use in context where that is not the frame
        final ClassMainFrame that = this;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Set window size
        this.setSize(new Dimension(840, 589));


        //Set Icon image
        this.setIconImage(new ImageIcon("tigerpaw.jpg").getImage());

        //Center it on screen by finding screen dimensions
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Load file of classes
        try {
            ClassDataStore.getInstance().Load();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create two panels, one from list/table, one for buttons
        JPanel mainPanel = new JPanel();
        this.getContentPane().add(mainPanel, BorderLayout.NORTH);
        mainPanel.setLayout(new BorderLayout(25, 25));

        JPanel buttonPanel = new JPanel();
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);


        //Set up list model
        final JList<Object> m_list = new JList<>(ClassDataStore.getInstance().getClasses().toArray());
        m_list.setSelectedIndex(0);


        m_table = new JTable();

        //Set up table model
        Object[][] objField = ClassDataStore.getInstance().getClasses().size() > 0 ? ClassDataStore.getInstance().getClasses().get(0).toObjectField() : new Object[][]{new Object[]{"", "", "", "", ""}};
        final DefaultTableModel tableModel = new DefaultTableModel(
                objField,
                new String[]{
                        "Last Name", "First Name", "Username", "CUID", "Num Absences", "Num Tardies"
                }) {
            @SuppressWarnings("rawtypes")
            Class[] columnTypes = new Class[]{
                    String.class, String.class,
                    String.class, String.class,
                    String.class, String.class
            };

            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                //first column false
//                if (column < 4) {
//                    return false;
//                } else {
//                    return true;
//                }
            	//I think all cells shouldn't be editable
            	return false;
            }
        };
        m_table.setModel(tableModel);

        //Create menuBar and set it as this frame's menuBar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        //Add file menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        JMenuItem importClassMenuItem = new JMenuItem("Import Class");
        importClassMenuItem.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent arg0) {
        		JFileChooser fc = new JFileChooser();
        		int status = fc.showOpenDialog(ClassMainFrame.this);
        		if (status == JFileChooser.APPROVE_OPTION)
        		{
        			String name = JOptionPane.showInputDialog("Class name: ");
        			if (name == null)
        				name = fc.getSelectedFile().getPath().split(".")[0];
        			Class_t cls = new Class_t(name);
        			try
					{
						cls = CSVPort.importClass(cls, fc.getSelectedFile().getPath());
						ClassDataStore.getInstance().getClasses().add(cls);
						update_list(m_list);
					} 
        			catch (IOException e)
					{
						JOptionPane.showMessageDialog(ClassMainFrame.this, "Unable to open file!");
					}
        		}
        	}
        });
        fileMenu.add(importClassMenuItem);
        
        JMenuItem exportClassMenuItem = new JMenuItem("Export Class");
        exportClassMenuItem.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseReleased(MouseEvent arg0) {
        		JFileChooser fc = new JFileChooser();
        		FileNameExtensionFilter filter = new FileNameExtensionFilter(".csv", "csv");
        		fc.setFileFilter(filter);
        		int status = fc.showSaveDialog(ClassMainFrame.this);
        		if (status == JFileChooser.APPROVE_OPTION)
        		{
        			String file = fc.getSelectedFile().getPath();
        			if (!file.endsWith(".csv"))
        				file += ".csv";
        			ScoringOptions opt = new ScoringOptions();
        			try
					{
						CSVPort.exportClass(ClassDataStore.getInstance().getClasses().get(m_list.getSelectedIndex()), file, opt);
					} catch (FileNotFoundException e)
					{
						JOptionPane.showMessageDialog(ClassMainFrame.this, "Unable to save file!");
					}
        		}
        	}
        });
        fileMenu.add(exportClassMenuItem);
        
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
        
        

        //Add edit menu
        JMenu editMenu = new JMenu("Edit");
        menuBar.add(editMenu);

        JMenuItem addClassMenuItem = new JMenuItem("Add Class");
        addClassMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent arg0) {
            	Frame f = new Frame();
                String name = JOptionPane.showInputDialog(f, "Class name:", "Add New Class", JOptionPane.QUESTION_MESSAGE);
                if (name != null) {
                    ClassDataStore.getInstance().getClasses().add(new Class_t(name));
                    update_list(m_list);
                }
            }
        });
        editMenu.add(addClassMenuItem);

        JMenuItem removeClassMenuItem = new JMenuItem("Remove Class");
        removeClassMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                ClassDataStore.getInstance().getClasses().remove(m_list.getSelectedIndex());
                update_list(m_list);
            }
        });
        editMenu.add(removeClassMenuItem);

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


        final JButton takeAttendanceBtn = new JButton("Take/Check Attendance");
//        final JButton checkAttendanceBtn = new JButton("Check Attendance");
        final JButton addButton = new JButton("Add Student");
        final JButton editButton = new JButton("Edit Student");
        final JButton deleteButton = new JButton("Delete Student");
        
        takeAttendanceBtn.addMouseListener(new MouseAdapter() {
        	public void mouseReleased(MouseEvent e) {

	        	//if a class is selected
        		if (m_list.getSelectedIndex() != -1 && !ClassDataStore.getInstance().getClasses().get(m_list.getSelectedIndex()).getStudents().isEmpty()) {
        			//Ask for date then take attendance
	        		Date date = new Date();
                    DateSelectionDlg dsd = new DateSelectionDlg(that, "Please Enter Date", date, m_list.getSelectedIndex());
                    dsd.setVisible(true);
        		}
        	}

        });
        buttonPanel.add(takeAttendanceBtn, BorderLayout.SOUTH);
        if (m_list.getSelectedIndex() == -1)
            takeAttendanceBtn.setEnabled(false);

//        checkAttendanceBtn.addMouseListener(new MouseAdapter() {
//        	public void mouseReleased(MouseEvent e) {
//        		//TODO
//        		//Ask for date
//        		//Show attendance for each student in selected Class for specific date
//        	}
//        });
//        buttonPanel.add(checkAttendanceBtn, BorderLayout.SOUTH);
//        if (m_list.getSelectedIndex() == -1)
//        	checkAttendanceBtn.setEnabled(false);

        addButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                Frame frame = new Frame();
                StudentEditingDlg sed = new StudentEditingDlg(frame, "New Student", m_table, m_list.getSelectedIndex(), true);
                sed.setVisible(true);
            }
        });
        buttonPanel.add(addButton, BorderLayout.SOUTH);
        if (m_list.getSelectedIndex() == -1)
            addButton.setEnabled(false);

        editButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (m_table.getSelectedRow() != -1) {
                    Frame frame = new Frame();
                    StudentEditingDlg sed = new StudentEditingDlg(frame, "Edit", m_table, m_table.getSelectedRow(), m_list.getSelectedIndex());
                    sed.setVisible(true);
                }
            }
        });
        buttonPanel.add(editButton, BorderLayout.SOUTH);
        if (m_table.getSelectedRow() == -1)
            editButton.setEnabled(false);

        deleteButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (m_table.getSelectedRow() != -1) {
                    JFrame f = new JFrame();
                    int i = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete " + ClassDataStore.getInstance().getClasses().get(m_list.getSelectedIndex()).getStudents().get(m_table.getSelectedRow()).getFirstname() + " ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (i == 0) {
                        ClassDataStore.getInstance().getClasses().get(m_list.getSelectedIndex()).getStudents().remove(m_table.getSelectedRow());
                        DefaultTableModel model = (DefaultTableModel) m_table.getModel();
                        model.removeRow(m_table.getSelectedRow());
                    }
                }
            }
        });
        buttonPanel.add(deleteButton, BorderLayout.SOUTH);
        if (m_table.getSelectedRow() == -1)
            deleteButton.setEnabled(false);

        JScrollPane leftScrollPane = new JScrollPane(m_list);
        JScrollPane rightScrollPane = new JScrollPane(m_table);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(leftScrollPane);
        splitPane.setRightComponent(rightScrollPane);
        splitPane.setDividerLocation(150);

        mainPanel.add(splitPane, BorderLayout.NORTH);


        //Set colors
        Color orange = new Color(234, 106, 32);
        Color purple = new Color(82, 45, 128);
        Color white = new Color(255,255,255);
        mainPanel.setBackground(purple);
        m_list.setForeground(white);
        m_table.setForeground(purple);
        m_list.setBackground(orange);
        this.getContentPane().setBackground(purple);
        buttonPanel.setBackground(purple);

        takeAttendanceBtn.setBackground(orange);
        takeAttendanceBtn.setForeground(white);
//        checkAttendanceBtn.setBackground(orange);
//        checkAttendanceBtn.setForeground(white);
        addButton.setBackground(orange);
        addButton.setForeground(white);
        editButton.setBackground(orange);
        editButton.setForeground(white);
        deleteButton.setBackground(orange);
        deleteButton.setForeground(white);


        m_table.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                //update table?

                //Enable edit/delete button
                editButton.setEnabled(true);
                deleteButton.setEnabled(true);
            }
        });

        m_list.addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent arg0) {

                //Check to see if some class is selected (will be -1 if a class has just been deleted)
                if (m_list.getSelectedIndex() == -1) {
                    whichClass = ClassDataStore.getInstance().getClasses().get(0);
                } else {
                    whichClass = ClassDataStore.getInstance().getClasses().get(m_list.getSelectedIndex());
                    takeAttendanceBtn.setEnabled(true);
//                    checkAttendanceBtn.setEnabled(true);
                }
                while (tableModel.getRowCount() != 0) {
                    tableModel.removeRow(0);
                }

                for (int i = 0; i < whichClass.getStudents().size(); i++) {

                    Student student = ClassDataStore.getInstance().getClasses().get(m_list.getSelectedIndex()).getStudents().get(i);
                    tableModel.addRow(new Object[] {
                    		student.getLastname(), 
                    		student.getFirstname(), 
                    		student.getUsername(),
                            student.getCUID(),
                            student.getNumAbsences(),
                            student.getNumTardies()
                            });
                }
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
                addButton.setEnabled(true);

                if(ClassDataStore.getInstance().getClasses().get(m_list.getSelectedIndex()).getStudents().isEmpty()) {
                	takeAttendanceBtn.setEnabled(false);
//                	checkAttendanceBtn.setEnabled(false);
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    ClassDataStore.getInstance().Save();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
    }

    void update_list(JList<Object> m_list) {
        List<Class_t> classes = ClassDataStore.getInstance().getClasses();
        final String[] class_names = new String[classes.size()];
        for (int i = 0; i < classes.size(); ++i)
            class_names[i] = classes.get(i).getName();
        m_list.setModel(new AbstractListModel<Object>() {
            String[] values = class_names;

            public int getSize() {
                return values.length;
            }

            public String getElementAt(int index) {
                return values[index];
            }
        });
        m_list.setSelectedIndex(classes.size() - 1);
    }
}
