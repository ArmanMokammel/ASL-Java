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
		
		JButton btn_1 = new JButton("New Customer");
		btn_1.setBounds(190, 100, 120, 30);
		
//		add(lbl_1);
//		add(lbl_2);
//		add(txt_1);
//		add(btn_1);
		
		
		
		
		JLabel lbl_3 = new JLabel("Customer:");
		lbl_3.setBounds(10, 20, 70, 30);
		
		JLabel lbl_4 = new JLabel(" Customer Name");
		lbl_4.setBounds(90, 20, 370, 30);
		lbl_4.setOpaque(true);
		lbl_4.setBackground(Color.cyan);
		
		JLabel lbl_5 = new JLabel("ID:");
		lbl_5.setBounds(10, 60, 70, 30);
		
		JLabel lbl_6 = new JLabel(" 2232450642");
		lbl_6.setBounds(90, 60, 370, 30);
		lbl_6.setOpaque(true);
		lbl_6.setBackground(Color.cyan);
		
		JLabel lbl_7 = new JLabel("Email:");
		lbl_7.setBounds(10, 100, 70, 30);
		
		JLabel lbl_8 = new JLabel(" armanmokammel@gmail.com");
		lbl_8.setBounds(90, 100, 370, 30);
		lbl_8.setOpaque(true);
		lbl_8.setBackground(Color.cyan);
		
		JLabel lbl_9 = new JLabel("Discount %:");
		lbl_9.setBounds(10, 140, 70, 30);
		
		JLabel lbl_10 = new JLabel(" 50.00%");
		lbl_10.setBounds(90, 140, 370, 30);
		lbl_10.setOpaque(true);
		lbl_10.setBackground(Color.cyan);
		
		JButton btn_2 = new JButton("Remove Customer");
		btn_2.setBounds(180, 180, 140, 30);
		
		add(lbl_3);
		add(lbl_4);
		add(lbl_5);
		add(lbl_6);
		add(lbl_7);
		add(lbl_8);
		add(lbl_9);
		add(lbl_10);
		add(btn_2);
	}
}
