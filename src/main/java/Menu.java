import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Choice;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.List;
import java.awt.ScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.event.ListSelectionEvent;
import javax.swing.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu {

	private JFrame frame;
	private JTable table;
	private Boolean skip;
	private ArrayList<class_t> classes = new ArrayList<class_t>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		initialize_classes();
		table = new JTable();
		final JList list = new JList();
		skip = false;
		
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 589);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmThisDoesNothing = new JMenuItem("This does nothing");
		mnFile.add(mntmThisDoesNothing);
		
		JMenu mnEit = new JMenu("Edit");
		menuBar.add(mnEit);
		
		JMenuItem mntmAddClass = new JMenuItem("Add Class");
		mntmAddClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String nam = JOptionPane.showInputDialog("Class name: ");
				if (nam != null)
				{
					class_t temp = new class_t();
					temp.setName(nam);
					classes.add(temp);
					update_list(list);
					skip = true;
					((DefaultTableModel)table.getModel()).addRow(new Object[] {"",false,false});
					skip = false;
				}
			}
		});
		mnEit.add(mntmAddClass);
		
		JMenuItem mntmRemoveClass = new JMenuItem("Remove Class");
		mntmRemoveClass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				classes.remove(list.getSelectedIndex());
				update_list(list);
			}
		});
		mnEit.add(mntmRemoveClass);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				skip = true;
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				while (dtm.getRowCount() != 0)
					dtm.removeRow(0);
				for (int i = 0; i < classes.get(list.getSelectedIndex()).students.size(); i++)
				{
					dtm.addRow(new Object[] {classes.get(list.getSelectedIndex()).students.get(i).getName(), classes.get(list.getSelectedIndex()).students.get(i).isPresent(), classes.get(list.getSelectedIndex()).students.get(i).isTardy()});
				}
				skip = false;
			}
		});
		list.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
			}
		});
		final String[] class_names = new String[classes.size()];
		for (int i = 0; i < classes.size(); i++)
			class_names[i] = classes.get(i).getName();
		list.setModel(new AbstractListModel() {
			String[] values = class_names;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		splitPane.setLeftComponent(list);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
				{
					((DefaultTableModel)table.getModel()).addRow(new Object[] {null, null, null});
				}
			}
		});
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(
			classes.get(0).toObjectField(),
			new String[] {
				"Student", "Present", "Tardy"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Boolean.class, Boolean.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getModel().addTableModelListener(new TableModelListener()
		{
			@Override
			public void tableChanged(TableModelEvent arg0)
			{
				if (!skip)
				{
					DefaultTableModel dtm = (DefaultTableModel)table.getModel();
					class_t cls = classes.get(list.getSelectedIndex());
					cls.students.clear();
					for (int i = 0; i < dtm.getRowCount(); ++i)
					{
						if (dtm.getValueAt(i, 0) != null)
						{
							student_t temp = new student_t();
							temp.setName((String)dtm.getValueAt(i, 0));
							temp.setIsPresent((Boolean)dtm.getValueAt(i, 1));
							temp.setIsTardy((Boolean)dtm.getValueAt(i, 2));
							cls.students.add(temp);
						}
					}
					((DefaultTableModel)table.getModel()).addRow(new Object[] {null,null,null});
				}
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(500);
		scrollPane.setViewportView(table);
	}
	
	void initialize_classes()
	{
		classes.add(new class_t());
		classes.get(0).setName("XCOMM");
		classes.get(0).students.add(new student_t());
		classes.get(0).students.get(0).setName("Jack");
		classes.get(0).students.get(0).setIsPresent(false);
		classes.get(0).students.get(0).setIsTardy(false);
		classes.get(0).students.add(new student_t());
		classes.get(0).students.get(1).setName("Austin");
		classes.get(0).students.get(1).setIsPresent(false);
		classes.get(0).students.get(1).setIsTardy(false);
		classes.get(0).students.add(new student_t());
		classes.get(0).students.get(2).setName("Molly");
		classes.get(0).students.get(2).setIsPresent(false);
		classes.get(0).students.get(2).setIsTardy(false);
		classes.get(0).students.add(new student_t());
		classes.get(0).students.get(3).setName("Zak");
		classes.get(0).students.get(3).setIsPresent(false);
		classes.get(0).students.get(3).setIsTardy(false);
		classes.get(0).students.add(new student_t());
		classes.get(0).students.get(4).setName("James");
		classes.get(0).students.get(4).setIsPresent(false);
		classes.get(0).students.get(4).setIsTardy(false);
		classes.add(new class_t());
		classes.get(1).setName("COMM 1500 - 001");
		classes.get(1).students.add(new student_t());
		classes.get(1).students.get(0).setName("George");
		classes.get(1).students.get(0).setIsPresent(false);
		classes.get(1).students.get(0).setIsTardy(false);
		classes.get(1).students.add(new student_t());
		classes.get(1).students.get(1).setName("Bob");
		classes.get(1).students.get(1).setIsPresent(false);
		classes.get(1).students.get(1).setIsTardy(false);
		classes.get(1).students.add(new student_t());
		classes.get(1).students.get(2).setName("Susan");
		classes.get(1).students.get(2).setIsPresent(false);
		classes.get(1).students.get(2).setIsTardy(false);
		classes.get(1).students.add(new student_t());
		classes.get(1).students.get(3).setName("Dickbutt");
		classes.get(1).students.get(3).setIsPresent(false);
		classes.get(1).students.get(3).setIsTardy(false);
		classes.get(1).students.add(new student_t());
		classes.get(1).students.get(4).setName("Will");
		classes.get(1).students.get(4).setIsPresent(false);
		classes.get(1).students.get(4).setIsTardy(false);
		classes.add(new class_t());
		classes.get(2).setName("COMM 1501 - 001");
		classes.get(2).students.add(new student_t());
		classes.get(2).students.get(0).setName("Luke");
		classes.get(2).students.get(0).setIsPresent(false);
		classes.get(2).students.get(0).setIsTardy(false);
		classes.get(2).students.add(new student_t());
		classes.get(2).students.get(1).setName("Jillian");
		classes.get(2).students.get(1).setIsPresent(false);
		classes.get(2).students.get(1).setIsTardy(false);
		classes.get(2).students.add(new student_t());
		classes.get(2).students.get(2).setName("Olivia");
		classes.get(2).students.get(2).setIsPresent(false);
		classes.get(2).students.get(2).setIsTardy(false);
		classes.get(2).students.add(new student_t());
		classes.get(2).students.get(3).setName("Summer");
		classes.get(2).students.get(3).setIsPresent(false);
		classes.get(2).students.get(3).setIsTardy(false);
		classes.get(2).students.add(new student_t());
		classes.get(2).students.get(4).setName("Jim");
		classes.get(2).students.get(4).setIsPresent(false);
		classes.get(2).students.get(4).setIsTardy(false);
		classes.add(new class_t());
		classes.get(3).setName("COMM 2500 - 001");
		classes.get(3).students.add(new student_t());
		classes.get(3).students.get(0).setName("Jean-Luc");
		classes.get(3).students.get(0).setIsPresent(false);
		classes.get(3).students.get(0).setIsTardy(false);
		classes.get(3).students.add(new student_t());
		classes.get(3).students.get(1).setName("James");
		classes.get(3).students.get(1).setIsPresent(false);
		classes.get(3).students.get(1).setIsTardy(false);
		classes.get(3).students.add(new student_t());
		classes.get(3).students.get(2).setName("Benjamin");
		classes.get(3).students.get(2).setIsPresent(false);
		classes.get(3).students.get(2).setIsTardy(false);
		classes.get(3).students.add(new student_t());
		classes.get(3).students.get(3).setName("Catherine");
		classes.get(3).students.get(3).setIsPresent(false);
		classes.get(3).students.get(3).setIsTardy(false);
		classes.get(3).students.add(new student_t());
		classes.get(3).students.get(4).setName("Jonathan");
		classes.get(3).students.get(4).setIsPresent(false);
		classes.get(3).students.get(4).setIsTardy(false);
		classes.add(new class_t());
		classes.get(4).setName("COMM 2501 - 001");
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(0).setName("Gary");
		classes.get(4).students.get(0).setIsPresent(false);
		classes.get(4).students.get(0).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(1).setName("Gary");
		classes.get(4).students.get(1).setIsPresent(false);
		classes.get(4).students.get(1).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(2).setName("Gary");
		classes.get(4).students.get(2).setIsPresent(false);
		classes.get(4).students.get(2).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(3).setName("Gary");
		classes.get(4).students.get(3).setIsPresent(false);
		classes.get(4).students.get(3).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(4).setName("Gary");
		classes.get(4).students.get(4).setIsPresent(false);
		classes.get(4).students.get(4).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(5).setName("Gary");
		classes.get(4).students.get(5).setIsPresent(false);
		classes.get(4).students.get(5).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(6).setName("Gary");
		classes.get(4).students.get(6).setIsPresent(false);
		classes.get(4).students.get(6).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(7).setName("Gary");
		classes.get(4).students.get(7).setIsPresent(false);
		classes.get(4).students.get(7).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(8).setName("Gary");
		classes.get(4).students.get(8).setIsPresent(false);
		classes.get(4).students.get(8).setIsTardy(false);
		classes.get(4).students.add(new student_t());
		classes.get(4).students.get(9).setName("Gary");
		classes.get(4).students.get(9).setIsPresent(false);
		classes.get(4).students.get(9).setIsTardy(false);
	}
	
	void update_list(JList list)
	{
		final String[] class_names = new String[classes.size()];
		for (int i = 0; i < classes.size(); ++i)
			class_names[i] = classes.get(i).getName();
		list.setModel(new AbstractListModel() {
			String[] values = class_names;
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(classes.size() - 1);
	}

}



