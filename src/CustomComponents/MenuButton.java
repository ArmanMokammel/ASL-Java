package CustomComponents;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MenuButton extends JButton  {
	
	private Color c;

	public MenuButton(String text, ImageIcon img, Color c) {
		super(text, img);
		this.c = c;
		setBackground(this.c);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIconTextGap(5);
		setFocusPainted(false);

		UIManager.put("Button.select", new Color(0.38f, 0.38f, 0.38f, .2f ));
	}
}
