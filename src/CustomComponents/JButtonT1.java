package CustomComponents;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Utilities.Utility;

public class JButtonT1 extends JButton implements MouseListener{
	
	private Image img;
	private int scale;
	private int width;
	private int height;
	private int x;
	private int y;
	
	public JButtonT1(String title, String imgPath, int scale) {
		super(title);
		this.scale = scale;
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setHorizontalTextPosition(SwingConstants.CENTER);
		img = Utility.getImage(imgPath);
		addMouseListener(this);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		setIcon(new ImageIcon(img.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		updateBounds(x, y, width, height);
		setIcon(new ImageIcon(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		updateBounds(x - (scale / 2), y - (scale / 2), width + scale, height + scale);
    	setIcon(new ImageIcon(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		updateBounds(x, y, width, height);
		setIcon(new ImageIcon(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH)));
	}
	
	public void updateBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
	}
}
