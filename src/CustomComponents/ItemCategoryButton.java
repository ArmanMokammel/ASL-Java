package CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import UI.OrderSystem.Panel_C;
import Data.MenuItem;
import Data.OrderMenuItem;

public class ItemCategoryButton extends JButton implements ActionListener{
	
	private MenuItem item;
	private Panel_C itemsPanel;
	
	public ItemCategoryButton(MenuItem item, Panel_C itemsPanel) {
		super(item.getItemName());
		this.item = item;
		this.itemsPanel = itemsPanel;
		setPreferredSize(new Dimension(270, 100));
		setFont(new Font(null, Font.BOLD, 22));
		setBackground(new Color(70, 212, 93));
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane p = new JOptionPane();
		String input = p.showInputDialog("Enter Quantity:");
		
		if(input == null || input.isBlank())
			return;
		
		int quantity = Integer.parseInt(input);
		itemsPanel.addItem(new OrderMenuItem(item, quantity));		
	}

}
