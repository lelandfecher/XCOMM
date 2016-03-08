import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Vector;


public class ClassMainFrame extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    //	//Variable instances
//	private JPanel mainPanel;
//	private JPanel buttonPanel;
//	//private DefaultTableModel model;
//	private JList<Class_t> classes = new JList<Class_t>();
    private int m_whichInstructor;
    private Class_t whichClass;


    public ClassMainFrame(String title, final InstructorDataStore list, final int whichInstructor) {
        //Call super constructor to set up JFrame
        super(title);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //Set window size
        this.setSize(new Dimension(840, 589));
        
        m_whichInstructor = whichInstructor;

        //Set Icon image
        //this.setIconImage(new ImageIcon("path/to/image.png").getImage());

        //Center it on screen by finding screen dimensions
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        //Load file of classes
        try {
            InstructorDataStore.loadInstructors("instructors.ser");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create two panels, one from list/table, one for buttons
        JPanel mainPanel = new JPanel();
        this.getContentPane().add(mainPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        
        //Set up list model
        //final DefaultListModel<Object> listModel = new DefaultListModel<Object>();
        final JList<Object> m_list = new JList<Object>(list);
        
        
        final JTable m_table = new JTable();

        //Set up table model
        final DefaultTableModel tableModel = new DefaultTableModel(
                InstructorDataStore.getInstructors().get(m_whichInstructor).getClasses().get(0).toObjectField(),
                new String[]{
                        "Student", "Status"
                }
        ) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;
            @SuppressWarnings("rawtypes")
            Class[] columnTypes = new Class[]{
                    String.class, String.class
            };

            public Class<?> getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                //first column false
                if (column == 0) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        m_table.setModel(tableModel);

        //Create menuBar and set it as this frame's menuBar
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        //Add file menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        //Add exit item on file menu to exit program and save data
        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_E);
        exitMenuItem.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent arg0) {
                try {
                    InstructorDataStore.saveInstructors(InstructorDataStore.getInstructors(), "instructors.ser");
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
                String name = JOptionPane.showInputDialog("Class name: ");
                if (name != null) {
                    InstructorDataStore.getInstructors().get(m_whichInstructor).addClass(name);
                    update_list(m_list);
                }
            }
        });
        editMenu.add(addClassMenuItem);

        JMenuItem removeClassMenuItem = new JMenuItem("Remove Class");
        removeClassMenuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                InstructorDataStore.getInstructors().get(m_whichInstructor).removeClass(m_list.getSelectedIndex());
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


        final JButton addButton = new JButton("Add");
        final JButton editButton = new JButton("Edit");
        final JButton deleteButton = new JButton("Delete");

        addButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                JFrame f = new JFrame();
                String name = JOptionPane.showInputDialog(f, "Enter a new student's name!", "INSERT NAME HERE");
                if (name != null) {
                    //table.addRow(new Object[] {name, false, false});
                    InstructorDataStore.getInstructors().get(whichInstructor).getClasses().get(m_list.getSelectedIndex()).addStudent(name);
                    ((DefaultTableModel) m_table.getModel()).fireTableDataChanged();
//					whichClass.addStudent(name);
//					table.fireTableDataChanged();
                }
            }
        });
        buttonPanel.add(addButton, BorderLayout.SOUTH);
        if (InstructorDataStore.getInstructors().size() == 0
        		|| m_list.getSelectedIndex() == -1)
            addButton.setEnabled(false);

        editButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (m_table.getSelectedRow() != -1) {
                    JFrame f = new JFrame();
                    String name = JOptionPane.showInputDialog(f, "Edit Student's Name", InstructorDataStore.getInstructors().get(whichInstructor).getClasses().get(m_list.getSelectedIndex()).getStudents().get(m_table.getSelectedRow()).getName());
                    if (name != null) {
                        InstructorDataStore.getInstructors().get(whichInstructor).getClasses().get(m_list.getSelectedIndex()).getStudents().get(m_table.getSelectedRow()).setName(name);
                        //whichClass.getStudents().get(m_table.getSelectedRow()).setName(name);
                        //table.fireTableCellUpdated(m_table.getSelectedRow(), m_table.getSelectedColumn());
                    }
                    //table.fireTableDataChanged();

                }
            }
        });
        buttonPanel.add(editButton, BorderLayout.SOUTH);
        if (InstructorDataStore.getInstructors().size() == 0
                || m_table.getSelectedRow() == -1)
            editButton.setEnabled(false);

        deleteButton.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (m_table.getSelectedRow() != -1 && m_table.getSelectedColumn() == 1) {
                    JFrame f = new JFrame();
                    int i = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete " + m_list.getSelectedValue() + " ?", "", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
                    if (i == 0) {
                        InstructorDataStore.getInstructors().get(whichInstructor).getClasses().get(m_list.getSelectedIndex()).getStudents().remove(m_table.getSelectedRow());
//						whichClass.getStudents().remove(m_table.getSelectedRow());
//						table.fireTableDataChanged();
                    }
                }
            }
        });
        buttonPanel.add(deleteButton, BorderLayout.SOUTH);
        if (InstructorDataStore.getInstructors().size() == 0
                || m_table.getSelectedRow() == -1)
            deleteButton.setEnabled(false);

        JScrollPane leftScrollPane = new JScrollPane(m_list);
        JScrollPane rightScrollPane = new JScrollPane(m_table);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setLeftComponent(leftScrollPane);
        splitPane.setRightComponent(rightScrollPane);
        splitPane.setDividerLocation(200);

        mainPanel.add(splitPane, BorderLayout.NORTH);


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
                //skip = true;
            	
            	//Check to see if some class is selected (will be -1 if a class has just been deleted)
            	if (m_list.getSelectedIndex() == -1) {
            		whichClass = InstructorDataStore.getInstructors().get(m_whichInstructor).getClasses().get(0);
            	} else {
            		whichClass = InstructorDataStore.getInstructors().get(m_whichInstructor).getClasses().get(m_list.getSelectedIndex());
            	}
            	while (tableModel.getRowCount() != 0) {
                    tableModel.removeRow(0);
                }
                for (int i = 0; i < whichClass.getStudents().size(); i++) {
                    //TODO
                	//BUG here with adding/deleting classes
                	tableModel.addRow(new Object[]{InstructorDataStore.getInstructors().get(m_whichInstructor).getClasses().get(m_list.getSelectedIndex()).getStudents().get(i).getName(), InstructorDataStore.getInstructors().get(whichInstructor).getClasses().get(m_list.getSelectedIndex()).getStudents().get(i).getStatus()});
                }
                editButton.setEnabled(false);
                deleteButton.setEnabled(false);
                addButton.setEnabled(true);
                //skip = false;
            }
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    InstructorDataStore.saveInstructors(InstructorDataStore.getInstructors(), "instructors.ser");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        });
    }

	void update_list(JList<Object> m_list)
	{
		Vector<Class_t> classes = InstructorDataStore.getInstructors().get(m_whichInstructor).getClasses();
		final String[] class_names = new String[classes.size()];
		for (int i = 0; i < classes.size(); ++i)
			class_names[i] = classes.get(i).getName();
		m_list.setModel(new AbstractListModel<Object>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
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
