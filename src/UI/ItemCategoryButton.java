package UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import UI.OrderSystem.Panel_C;
import Data.MenuItem;

public class ItemCategoryButton extends JButton implements ActionListener{
	
	private MenuItem item;
	
	public ItemCategoryButton(MenuItem item) {
		super(item.getItemName());
		this.item = item;
		setMaximumSize(new Dimension(550, 100));
		setPreferredSize(new Dimension(550, 100));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Panel_C.addItem(item);
		
	}

}
