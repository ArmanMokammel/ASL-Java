package CustomComponents;

import java.awt.Dimension;

import javax.swing.JButton;

public class Settings_Button extends JButton{
	
	public Settings_Button(String title) {
		super(title);
		setPreferredSize(new Dimension(180, 50));
	}
}
