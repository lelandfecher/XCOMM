import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JWindow;


public class SplashScreen extends JWindow { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Image img = new ImageIcon(getClass().getResource("catPic.jpg")).getImage();
	ImageIcon imgicon = new ImageIcon(img); 
	
	public SplashScreen() { 
		try { 
			setSize(599,848); 
			setLocationRelativeTo(null); 
			setVisible(true);
			Thread.sleep(5000); 
			dispose(); 
			//javax.swing.JOptionPane.showMessageDialog((java.awt.Component) null,"Welcome", "Welcome Screen:", javax.swing.JOptionPane.DEFAULT_OPTION); 
			} catch(Exception exception) { 
				javax.swing.JOptionPane.showMessageDialog((java.awt.Component) null,"Error"+exception.getMessage(), "Error:", javax.swing.JOptionPane.DEFAULT_OPTION); 
				} 
		} 
	
	public void paint(Graphics g) { 
		g.drawImage(img,0,0,this); 
		}
}

