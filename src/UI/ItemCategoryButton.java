package UI;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ItemCategoryButton extends JButton{
	
	public ItemCategoryButton(String title) {
		super(title);
		setMaximumSize(new Dimension(550, 100));
		setPreferredSize(new Dimension(550, 100));
	}

}
