import javax.swing.*;

//import javafx.beans.value.ObservableValue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ScoringOptionsDialog extends JDialog
{
	private ScoringOptions so;
	
	private String[] methods = { "Count up", "Count down", "Average" };
	
	
	public ScoringOptions getScoringOptions()
	{
		return so;
	}
	
	public ScoringOptionsDialog(final Frame frame, String title, ScoringOptions s)
	{
		super(frame, title, true);
		this.setSize(new Dimension(400, 500));
        this.setIconImage(new ImageIcon(getClass().getResource("tigerpaw.jpg")).getImage());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        
        so = s;
        if (so.getScoringMethod() == Method.CountUp)
        	setCountUp();
        else if (so.getScoringMethod() == Method.CountDown)
        	setCountDown();
        else
        	setAverage();
	}	
	
	private void setCountUp()
	{
		so.setScoringMethod(Method.CountUp);
		
		JPanel mainp = new JPanel();
		
		JPanel methodp = new JPanel();
		JLabel methodl = new JLabel("Scoring Method: ", JLabel.TRAILING);
		methodp.add(methodl, BorderLayout.WEST);
		final JComboBox methodCB = new JComboBox(methods);
		methodCB.setSelectedIndex(0);
		methodCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				clr();
				if (methodCB.getSelectedIndex() == 0)
					setCountUp();
				else if (methodCB.getSelectedIndex() == 1)
					setCountDown();
				else if (methodCB.getSelectedIndex() == 2)
					setAverage();
			}
		});
		methodp.add(methodCB, BorderLayout.EAST);
		mainp.add(methodp);
		
		JPanel absp = new JPanel();
		JLabel absl = new JLabel("Points Per Presence: ", JLabel.TRAILING);
		absp.add(absl, BorderLayout.WEST);
		SpinnerModel smabs = new SpinnerNumberModel(so.getPointsPerPresent(), 0, Integer.MAX_VALUE, 1);
		final JSpinner abssp = new JSpinner(smabs);
		abssp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setPointsPerPresent((double)abssp.getValue());
			}				
		});
		absp.add(abssp, BorderLayout.EAST);
		mainp.add(absp);
		
		JPanel trdypp = new JPanel();
		JLabel trdypl = new JLabel("Percent Worth of Tardy: ", JLabel.TRAILING);
		trdypp.add(trdypl, BorderLayout.WEST);
		SpinnerModel smtrdyp = new SpinnerNumberModel(so.getTardyWorthPercent(), 0, 100, 1);
		final JSpinner trdypsp = new JSpinner(smtrdyp);
		trdypsp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setTardyWorthPercent((double)trdypsp.getValue());
			}			
		});
		trdypp.add(trdypsp, BorderLayout.EAST);
		mainp.add(trdypp);
		
		JPanel gabsp = new JPanel();
		JLabel gabsl = new JLabel("Grace Absences: ", JLabel.TRAILING);
		gabsp.add(gabsl, BorderLayout.WEST);
		SpinnerModel smgabs = new SpinnerNumberModel(so.getNumGraceAbsences(), 0, Integer.MAX_VALUE, 1);
		final JSpinner gabssp = new JSpinner(smgabs);
		gabssp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setNumGraceAbsences((double)gabssp.getValue());
			}			
		});
		gabsp.add(gabssp, BorderLayout.EAST);
		mainp.add(gabsp);
		
		JPanel gtrdyp = new JPanel();
		JLabel gtrdyl = new JLabel("Grace Tardies: ", JLabel.TRAILING);
		gtrdyp.add(gtrdyl, BorderLayout.WEST);
		SpinnerModel smgtrdy = new SpinnerNumberModel(so.getNumGraceTardies(), 0, Integer.MAX_VALUE, 1);
		final JSpinner gtrdysp = new JSpinner(smgtrdy);
		gtrdysp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setNumGraceTardies((double)gtrdysp.getValue());
			}			
		});
		gtrdyp.add(gtrdysp, BorderLayout.EAST);
		mainp.add(gtrdyp);
		
		this.add(mainp);
		mainp.revalidate();
		mainp.repaint();
		rst();
	}
	
	private void setCountDown()
	{
		so.setScoringMethod(Method.CountDown);
				
		JPanel mainp = new JPanel();
		
		JPanel methodp = new JPanel();
		JLabel methodl = new JLabel("Scoring Method: ", JLabel.TRAILING);
		methodp.add(methodl, BorderLayout.WEST);
		final JComboBox methodCB = new JComboBox(methods);
		methodCB.setSelectedIndex(1);
		methodCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				clr();
				if (methodCB.getSelectedIndex() == 0)
					setCountUp();
				else if (methodCB.getSelectedIndex() == 1)
					setCountDown();
				else if (methodCB.getSelectedIndex() == 2)
					setAverage();
			}
		});
		methodp.add(methodCB, BorderLayout.EAST);
		mainp.add(methodp);
		
		JPanel maxp = new JPanel();
		JLabel maxl = new JLabel("Max Score: ", JLabel.TRAILING);
		maxp.add(maxl, BorderLayout.WEST);
		SpinnerModel smmax = new SpinnerNumberModel(so.getMax(), 0, Integer.MAX_VALUE, 1);
		final JSpinner maxsp = new JSpinner(smmax);
		maxsp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setMax((double)maxsp.getValue());
			}			
		});
		maxp.add(maxsp, BorderLayout.EAST);
		mainp.add(maxp);
		
		JPanel absp = new JPanel();
		JLabel absl = new JLabel("Points Per Absence: ", JLabel.TRAILING);
		absp.add(absl, BorderLayout.WEST);
		SpinnerModel smabs = new SpinnerNumberModel(so.getPointsPerAbsent(), 0, Integer.MAX_VALUE, 1);
		final JSpinner abssp = new JSpinner(smabs);
		abssp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setPointsPerAbsent((double)abssp.getValue());
			}				
		});
		absp.add(abssp, BorderLayout.EAST);
		mainp.add(absp);
		
		JPanel trdyp = new JPanel();
		JLabel trdyl = new JLabel("Points Per Tardy: ", JLabel.TRAILING);
		trdyp.add(trdyl, BorderLayout.WEST);
		SpinnerModel smtrdy = new SpinnerNumberModel(so.getPointsPerTardy(), 0, Integer.MAX_VALUE, 1);
		final JSpinner trdysp = new JSpinner(smtrdy);
		trdysp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setPointsPerTardy((double)trdysp.getValue());
			}			
		});
		trdyp.add(trdysp, BorderLayout.EAST);
		mainp.add(trdyp);
		
		JPanel gabsp = new JPanel();
		JLabel gabsl = new JLabel("Grace Absences: ", JLabel.TRAILING);
		gabsp.add(gabsl, BorderLayout.WEST);
		SpinnerModel smgabs = new SpinnerNumberModel(so.getNumGraceAbsences(), 0, Integer.MAX_VALUE, 1);
		final JSpinner gabssp = new JSpinner(smgabs);
		gabssp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setNumGraceAbsences((double)gabssp.getValue());
			}			
		});
		gabsp.add(gabssp, BorderLayout.EAST);
		mainp.add(gabsp);
		
		JPanel gtrdyp = new JPanel();
		JLabel gtrdyl = new JLabel("Grace Tardies: ", JLabel.TRAILING);
		gtrdyp.add(gtrdyl, BorderLayout.WEST);
		SpinnerModel smgtrdy = new SpinnerNumberModel(so.getNumGraceTardies(), 0, Integer.MAX_VALUE, 1);
		final JSpinner gtrdysp = new JSpinner(smgtrdy);
		gtrdysp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setNumGraceTardies((double)gtrdysp.getValue());
			}			
		});
		gtrdyp.add(gtrdysp, BorderLayout.EAST);
		mainp.add(gtrdyp);
		
		this.add(mainp);
		mainp.revalidate();
		mainp.repaint();
		rst();
	}
	
	private void setAverage()
	{
		so.setScoringMethod(Method.Average);
		
		JPanel mainp = new JPanel();
		
		JPanel methodp = new JPanel();
		JLabel methodl = new JLabel("Scoring Method: ", JLabel.TRAILING);
		methodp.add(methodl, BorderLayout.WEST);
		final JComboBox methodCB = new JComboBox(methods);
		methodCB.setSelectedIndex(2);
		methodCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				clr();
				if (methodCB.getSelectedIndex() == 0)
					setCountUp();
				else if (methodCB.getSelectedIndex() == 1)
					setCountDown();
				else if (methodCB.getSelectedIndex() == 2)
					setAverage();
			}
		});
		methodp.add(methodCB, BorderLayout.EAST);
		mainp.add(methodp);
		
		JPanel trdypp = new JPanel();
		JLabel trdypl = new JLabel("Percent Worth of Tardy: ", JLabel.TRAILING);
		trdypp.add(trdypl, BorderLayout.WEST);
		SpinnerModel smtrdyp = new SpinnerNumberModel(so.getTardyWorthPercent(), 0, 100, 1);
		final JSpinner trdypsp = new JSpinner(smtrdyp);
		trdypsp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setTardyWorthPercent((double)trdypsp.getValue());
			}			
		});
		trdypp.add(trdypsp, BorderLayout.EAST);
		mainp.add(trdypp);
		
		JPanel gabsp = new JPanel();
		JLabel gabsl = new JLabel("Grace Absences: ", JLabel.TRAILING);
		gabsp.add(gabsl, BorderLayout.WEST);
		SpinnerModel smgabs = new SpinnerNumberModel(so.getNumGraceAbsences(), 0, Integer.MAX_VALUE, 1);
		final JSpinner gabssp = new JSpinner(smgabs);
		gabssp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setNumGraceAbsences((double)gabssp.getValue());
			}			
		});
		gabsp.add(gabssp, BorderLayout.EAST);
		mainp.add(gabsp);
		
		JPanel gtrdyp = new JPanel();
		JLabel gtrdyl = new JLabel("Grace Tardies: ", JLabel.TRAILING);
		gtrdyp.add(gtrdyl, BorderLayout.WEST);
		SpinnerModel smgtrdy = new SpinnerNumberModel(so.getNumGraceTardies(), 0, Integer.MAX_VALUE, 1);
		final JSpinner gtrdysp = new JSpinner(smgtrdy);
		gtrdysp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0)
			{
				so.setNumGraceTardies((double)gtrdysp.getValue());
			}			
		});
		gtrdyp.add(gtrdysp, BorderLayout.EAST);
		mainp.add(gtrdyp);
		
		this.add(mainp);
		rst();
	}
	
	private void clr()
	{
		this.getContentPane().removeAll();
	}
	
	private void rst()
	{
		this.getContentPane().revalidate();
		this.getContentPane().repaint();
	}
}
