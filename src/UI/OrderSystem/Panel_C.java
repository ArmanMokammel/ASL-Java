package UI.OrderSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Panel_C extends JPanel{
	
	public Panel_C() {
		setLayout(null);
		
		DefaultTableModel model = new DefaultTableModel();
		
		JTable table = new JTable(model);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(20, 20, 1000, 570);
				
		add(sp);
	}
}
