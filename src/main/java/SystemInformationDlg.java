import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.IOException;


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
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        
        JPanel panel = new JPanel();
        JLabel jl = new JLabel();    //create label to add to Panel with information about GUI
        jl.setFont(new Font("Serif", Font.PLAIN, 14));
        jl.setText("<html>Created by xCOMM Team: <br>Molly Sullivan, James Hollowell, Zak Parks, Jack Bernard, and Austin Fitzpatrick <br> For: Dr. Linvill<br>Instructor: Leland Fecher");
        add(jl);

        panel.add(jl);
        
        add(panel);
        
      //Set colors
        Color orange = new Color(234, 106, 32);
        Color purple = new Color(82, 45, 128);
        Color white = new Color(255,255,255);
        
        panel.setBackground(purple);
        jl.setForeground(white);
    }
}
