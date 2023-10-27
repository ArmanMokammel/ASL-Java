package UI.OrderSystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import Data.Order;
import UI.Login_Screen;
import UI.MainWindow;
import UI.Order_Screen;
import Utilities.Utility;

public class Panel_A extends JPanel {
	
	public static JLabel txt_orderNo = new JLabel();
			
	public Panel_A(Order_Screen window) {
		setLayout(null);

		ImageIcon logo = Utility.getImageIcon("img\\Logo.png", 310, 90);
		JLabel lbl_Logo = new JLabel(logo);
		lbl_Logo.setBounds(370, 30, 310, 90);
		add(lbl_Logo);		
		
		JPanel pnl_2 = new JPanel();
		pnl_2.setLayout(null);
		pnl_2.setBounds(20, 10, 310, 120);
		pnl_2.setBackground(new Color(255, 220, 113));
		
		JLabel lbl_3 = new JLabel("Branch:");
		lbl_3.setBounds(10, 10, 70, 30);		
		JTextField txt_1 = new JTextField();
		txt_1.setBounds(90, 10, 200, 30);
		
		
		JLabel lbl_OrderNo = new JLabel("Order no:");
		lbl_OrderNo.setBounds(10, 45, 70, 30);	
		txt_orderNo.setBounds(90, 45, 200, 30);
		txt_orderNo.setOpaque(true);
		txt_orderNo.setText(Order.getOrderNo());
		
		JLabel lbl_5 = new JLabel("Order Type:");
		lbl_5.setBounds(10, 80, 70, 30);	
		JComboBox<String> cmbx_1 = new JComboBox<String>(new String[] {"Dine-in", "Delivery", "Takeout"});
		cmbx_1.setBounds(90, 80, 200, 30);
		
		pnl_2.add(lbl_3);
		pnl_2.add(txt_1);
		pnl_2.add(lbl_OrderNo);
		pnl_2.add(txt_orderNo);
		pnl_2.add(lbl_5);
		pnl_2.add(cmbx_1);
		add(pnl_2);
		
		
		JPanel pnl_3 = new JPanel();
		pnl_3.setLayout(null);
		pnl_3.setBounds(700, 10, 400, 120);
		pnl_3.setBackground(null);
		
		JButton btn_1 = new JButton("Logout");
		btn_1.setBounds(10, 20, 120, 80);
		btn_1.setBackground(new Color(255, 220, 113));
		btn_1.setOpaque(true);
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_Screen lg = new Login_Screen();
				lg.createUI();
				lg.setVisible(true);
				window.dispose();
			}
		});
		
		JPanel pnl_3_A = new JPanel();
		pnl_3_A.setLayout(null);
		pnl_3_A.setBounds(140, 0, 250, 120);
		pnl_3_A.setBackground(Color.blue);
				
		JButton btn_3 = new JButton("Shortcuts");
		btn_3.setBounds(0, 0, 125, 60);
		btn_3.setBackground(new Color(255, 220, 113));
		btn_3.setOpaque(true);
		
		JButton btn_4 = new JButton("Sell History");
		btn_4.setBounds(125, 0, 125, 60);
		btn_4.setBackground(new Color(255, 220, 113));
		btn_4.setOpaque(true);
		
		JButton btn_2 = new JButton("Suspended Orders");
		btn_2.setBounds(0, 70, 250, 50);
		
		pnl_3_A.add(btn_2);
		pnl_3_A.add(btn_3);
		pnl_3_A.add(btn_4);
		
		pnl_3.add(btn_1);
		pnl_3.add(pnl_3_A);
		
		add(pnl_3);
	}
}
