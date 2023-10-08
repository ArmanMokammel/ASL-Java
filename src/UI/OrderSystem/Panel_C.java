package UI.OrderSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(20, 20, 1000, 570);
				
		add(sp);
	}
}
