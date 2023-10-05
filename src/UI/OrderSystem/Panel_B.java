package UI.OrderSystem;

import java.awt.Color;

import javax.swing.*;
import javax.swing.SwingConstants;

public class Panel_B extends JPanel{
	
	public Panel_B() {
		setLayout(null);
		
		JLabel lbl_1 = new JLabel("Select Customer (Required for Due Payments)", SwingConstants.CENTER);
		lbl_1.setBounds(0, 10, 495, 30);
		lbl_1.setOpaque(true);
		lbl_1.setBackground(Color.blue);
		lbl_1.setForeground(Color.white);
		
		JLabel lbl_2 = new JLabel("Customer:");
		lbl_2.setBounds(10, 50, 60, 30);
		
		JTextField txt_1 = new JTextField();
		txt_1.setBounds(80, 50, 380, 30);
		
		JButton btn_1 = new JButton();
		
		add(lbl_1);
		add(lbl_2);
		add(txt_1);
	}
}
