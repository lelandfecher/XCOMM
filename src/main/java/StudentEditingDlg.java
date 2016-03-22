import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


public class StudentEditingDlg extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField firstnameField;
	private JTextField lastnameField;
	private JTextField usernameField;
	private JTextField cuidField;

	public StudentEditingDlg(Frame frame, String title, final int whichInstructor, final int whichClass) {
		//Call super constructor and set size
		super(frame, title, true);
		this.setSize(new Dimension(200, 400));
		
		//Center on screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		//Create panels for text fields and buttons
		JPanel northpanel = new JPanel();
		northpanel.setLayout(new GridLayout(4, 1));
		JPanel first = new JPanel();
		JPanel second = new JPanel();
		JPanel third = new JPanel();
		JPanel fourth = new JPanel();
		JPanel southpanel = new JPanel();
		
		JLabel firstname = new JLabel("First Name", JLabel.TRAILING);
		first.add(firstname, BorderLayout.NORTH);
		firstnameField = new JTextField(15);
		firstname.setLabelFor(firstnameField);
		first.add(firstnameField);
		
		JLabel lastname = new JLabel("Last Name", JLabel.TRAILING);
		second.add(lastname, BorderLayout.NORTH);
		lastnameField = new JTextField(15);
		lastname.setLabelFor(lastnameField);
		second.add(lastnameField);
		
		JLabel username = new JLabel("UserName", JLabel.TRAILING);	//create label and textfield for username, and add it to the 2nd panel
		third.add(username);
		usernameField = new JTextField(15);
		username.setLabelFor(usernameField);
		third.add(usernameField);
		
		JLabel cuid = new JLabel("CUID", JLabel.TRAILING);	//create label and textfield for cuid, and add it to the 3rd panel
		fourth.add(cuid);
		cuidField = new JTextField(15);
		cuid.setLabelFor(cuidField);
		fourth.add(cuidField);
		
		final JButton saveButton = new JButton("Save");	//create save Button
		saveButton.addMouseListener(new MouseAdapter() {		//create actionListener for when it is pressed
			public void mouseClicked(MouseEvent arg0) {
				Student s = new Student(firstnameField.getText(), lastnameField.getText(),
						usernameField.getText(), cuidField.getText());
				InstructorDataStore.getInstructors().get(whichInstructor).getClasses().get(whichClass).addStudent(s);
				//TODO update table here?
				dispose();
			}
		});
		southpanel.add(saveButton, BorderLayout.WEST);
		
		final JButton cancelButton = new JButton("Cancel");	//create cancel Button
		cancelButton.addMouseListener(new MouseAdapter() {		//create actionListener for when it is pressed
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		southpanel.add(cancelButton, BorderLayout.CENTER);
		
		northpanel.add(first);	//add all panels to JFrame
		northpanel.add(second);
		northpanel.add(third);
		northpanel.add(fourth);
		
		add(northpanel);

		add(southpanel, BorderLayout.SOUTH);
	}

	public StudentEditingDlg(Frame frame, String title, final JTable table, final int rowIndex, final int whichInstructor, final int whichClass) {
		super(frame, title, true);
		this.setSize(200, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
				
		JPanel northpanel = new JPanel();
		northpanel.setLayout(new GridLayout(4, 1));
		JPanel southpanel = new JPanel();	//create new Panel instances
		JPanel first = new JPanel();
		JPanel second = new JPanel();
		JPanel third = new JPanel();
		JPanel fourth = new JPanel();
		
		
		JLabel firstname = new JLabel("First Name", JLabel.TRAILING);	//create label and textfield for first name, and add it to the 1st panel
		first.add(firstname, BorderLayout.NORTH);
		firstnameField = new JTextField((String)table.getValueAt(rowIndex, 1), 15);
		firstname.setLabelFor(firstnameField);
		first.add(firstnameField);
		
		JLabel lastname = new JLabel("Last Name", JLabel.TRAILING);	//create label and textfield for last name address, and add it to the 2nd panel
		second.add(lastname);
		lastnameField = new JTextField((String)table.getValueAt(rowIndex, 0), 15);
		lastname.setLabelFor(lastnameField);
		second.add(lastnameField);
		
		JLabel username = new JLabel("Username", JLabel.TRAILING);	//create label and textfield for username, and add it to the 2nd panel
		third.add(username);
		usernameField = new JTextField((String)table.getValueAt(rowIndex, 2), 15);
		username.setLabelFor(usernameField);
		third.add(usernameField);
		
		JLabel cuid = new JLabel("CUID", JLabel.TRAILING);	//create label and textfield for cuid, and add it to the 3rd panel
		fourth.add(cuid);
		cuidField = new JTextField((String)table.getValueAt(rowIndex, 3), 15);
		cuid.setLabelFor(cuidField);
		fourth.add(cuidField);
		
		final JButton saveButton = new JButton("Save");	//create save Button
		saveButton.addMouseListener(new MouseAdapter() {		//create actionListener for when it is pressed
			public void mouseClicked(MouseEvent arg0) {
				Student s = new Student(firstnameField.getText(), lastnameField.getText(),
						usernameField.getText(), cuidField.getText());
				InstructorDataStore.getInstructors().get(whichInstructor).getClasses().get(whichClass).setStudent(rowIndex, s);
				((AbstractTableModel) table.getModel()).fireTableDataChanged();
				dispose();
			}
		});
		southpanel.add(saveButton, BorderLayout.WEST);
		
		final JButton cancelButton = new JButton("Cancel");	//create cancel Button
		cancelButton.addMouseListener(new MouseAdapter() {		//create actionListener for when it is pressed
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		southpanel.add(cancelButton, BorderLayout.CENTER);
		
		northpanel.add(first);	//add all panels to JFrame
		northpanel.add(second);
		northpanel.add(third);
		northpanel.add(fourth);
		
		add(northpanel);
		add(southpanel, BorderLayout.SOUTH);
	}

}
