package UI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;

public class SuspendedOrders_Screen extends JDialog{
	
	public SuspendedOrders_Screen(Order_Screen window) {
		super(window, "Suspended Orders", true);
		setSize(1500, 800);
		setLocationRelativeTo(null);
		setLayout(null);
		
		Font f1 = new Font(null, Font.BOLD, 40);
		
		JLabel lbl_title = new JLabel("Suspended Orders");
		lbl_title.setBounds(573, 20, 355, 50);
		lbl_title.setFont(f1);
		
		add(lbl_title);
		
		setVisible(true);
	}

}
