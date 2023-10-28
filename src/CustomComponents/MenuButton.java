package CustomComponents;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

public class MenuButton extends JToggleButton  {
	
	private Color c;

	public MenuButton(String text, ImageIcon img, Color c) {
		super(text, img);
		this.c = c;
		setBackground(this.c);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIconTextGap(5);
		setOpaque(false);
		setContentAreaFilled(true);
		setFocusPainted(false);

//		UIManager.put("Button.select", new Color(0.38f, 0.38f, 0.38f, .2f ));
	}
}
