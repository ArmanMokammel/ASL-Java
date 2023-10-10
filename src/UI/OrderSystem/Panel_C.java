package UI.OrderSystem;

import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Panel_C extends JPanel{
	
	public Panel_C() {
		setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Delete");
		model.addColumn("Item ID");
		model.addColumn("Item Name");
		model.addColumn("Price");
		model.addColumn("Discounted Price");
		model.addColumn("Quantity");
		model.addColumn("Total");
		
		JTable table = new JTable(model);
		table.setBackground(new Color(253, 253, 214));
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(new Color(0, 51, 118));
		tableHeader.setForeground(Color.white);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(20, 20, 1000, 570);
		
		model.addRow(new Object[] {"2", "234"});
				
		add(sp);
	}
}
