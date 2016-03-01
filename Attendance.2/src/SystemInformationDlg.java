import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SystemInformationDlg extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemInformationDlg(Frame f, String title) {
		super(f, title, true);
		this.setSize(650, 150);
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JPanel panel = new JPanel();
		JLabel jl = new JLabel();	//create label to add to Panel with information about GUI
		jl.setFont(new Font("Serif", Font.PLAIN, 14));
		jl.setText("<html>Created by: xCOMM Team <br>For: Dr. Linville<br>Instructor: Leland Fecher");
		add(jl);
		
		panel.add(jl);
		add(panel);
	}
}