import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

public class LoginDlg extends JDialog {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Vector<Instructor> m_instructors;
    private JTextField name;
    private JPasswordField pwd;
    private boolean correctCombo;
    private int whichInstructor;


    public LoginDlg(Frame f, String title) {
        super(f, title, true);
        correctCombo = false;
        m_instructors = InstructorDataStore.getInstructors();

        this.setSize(300, 150);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        JPanel northpanel = new JPanel();
        northpanel.setLayout(new GridLayout(2, 1));
        JPanel southpanel = new JPanel();
        JPanel first = new JPanel();
        JPanel second = new JPanel();

        final JLabel username = new JLabel("Username:");//create label and textfield for username, and add it to the 1st panel
        first.add(username);
        name = new JTextField(15);
        username.setLabelFor(name);
        first.add(name);

        final JLabel password = new JLabel("Password:");//create label and passwordfield for password, and add it to the 1st panel
        second.add(password);
        pwd = new JPasswordField(15);
        password.setLabelFor(pwd);
        second.add(pwd);

        final JButton signinButton = new JButton("Sign-In");    //create signin Button
        signinButton.addMouseListener(new MouseAdapter() {        //create actionListener for when it is pressed
            public void mouseClicked(MouseEvent arg0) {
                JPanel panel = new JPanel();
                for (int i = 0; i < m_instructors.size(); i++) {
                    if (name.getText().contentEquals(m_instructors.get(i).getName()) || pwd.getPassword().equals(m_instructors.get(i).getPassword())) {
                        JOptionPane.showMessageDialog(panel, "Welcome " + name.getText() + "!", null, JOptionPane.DEFAULT_OPTION/*, new ImageIcon("SORS.png")*/);
                        correctCombo = true;
                        whichInstructor = i;
                        dispose();
                    } else if (i == m_instructors.size() - 1) {
                        JOptionPane.showMessageDialog(panel, "Username/Password Combination Not Found", "ERROR", JOptionPane.DEFAULT_OPTION);
                    }
                }
            }
        });
        southpanel.add(signinButton, BorderLayout.WEST);


        final JButton cancelButton = new JButton("Cancel");    //create cancel Button
        cancelButton.addMouseListener(new MouseAdapter() {        //create actionListener for when it is pressed
            public void mouseClicked(MouseEvent arg0) {
                System.exit(EXIT_ON_CLOSE);
            }
        });
        southpanel.add(cancelButton, BorderLayout.CENTER);

        northpanel.add(first);
        northpanel.add(second);

        add(northpanel);
        add(southpanel, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(EXIT_ON_CLOSE);
            }
        });
    }


    public boolean signedIn() {
        return correctCombo;
    }

    public int getWhichInstructor() {
        return whichInstructor;
    }
}