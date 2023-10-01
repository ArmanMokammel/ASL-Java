package UI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class MenuButton extends JButton{
	
	public MenuButton(String text, ImageIcon img, Color c) {
		super(text, img);
		setBackground(c);
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setIconTextGap(5);
		setOpaque(false);
		setContentAreaFilled(true);
		setFocusPainted(false);
		
		UIManager.put("Button.select", new Color(0.38f, 0.38f, 0.38f, .2f ));
	}
}
