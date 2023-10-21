package UI.OrderSystem;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

import UI.MainWindow;
import UI.Order_Screen;

public class Panel_A extends JPanel {
	
	private Order_Screen frame;
	
	public Panel_A(Order_Screen frame) {
		this.frame = frame;
		setLayout(null);
		
		JPanel pnl_1 = new JPanel();
		pnl_1.setLayout(null);
		pnl_1.setBounds(20, 10, 200, 100);
		
		JLabel lbl_1 = new JLabel("Total Cost", SwingConstants.CENTER);
		lbl_1.setSize(pnl_1.getWidth(), 30);
		lbl_1.setBackground(Color.black);
		lbl_1.setForeground(Color.orange);
		lbl_1.setOpaque(true);
		
		JLabel lbl_2 = new JLabel("1234.00", SwingConstants.CENTER);
		lbl_2.setBounds(0, 30, pnl_1.getWidth(), pnl_1.getHeight() - 30);
		lbl_2.setBackground(Color.LIGHT_GRAY);
		lbl_2.setForeground(Color.red);
		lbl_2.setOpaque(true);
		lbl_2.setBorder(new LineBorder(Color.black, 3));
			
		pnl_1.add(lbl_1);
		pnl_1.add(lbl_2);		
		add(pnl_1);
		
		
		JPanel pnl_2 = new JPanel();
		pnl_2.setLayout(null);
		pnl_2.setBounds(250, 10, 310, 120);
		pnl_2.setBackground(new Color(255, 220, 113));
		
		JLabel lbl_3 = new JLabel("Branch:");
		lbl_3.setBounds(10, 10, 70, 30);		
		JTextField txt_1 = new JTextField();
		txt_1.setBounds(90, 10, 200, 30);
		
		
		JLabel lbl_4 = new JLabel("Order no:");
		lbl_4.setBounds(10, 40, 70, 30);	
		JTextField txt_2 = new JTextField();
		txt_2.setBounds(90, 40, 200, 30);
		
		JLabel lbl_5 = new JLabel("Order Type:");
		lbl_5.setBounds(10, 70, 70, 30);	
		JComboBox<String> cmbx_1 = new JComboBox<String>(new String[] {"Dine-in", "Delivery", "Takeout"});
		cmbx_1.setBounds(90, 70, 200, 30);
		
		pnl_2.add(lbl_3);
		pnl_2.add(txt_1);
		pnl_2.add(lbl_4);
		pnl_2.add(txt_2);
		pnl_2.add(lbl_5);
		pnl_2.add(cmbx_1);
		add(pnl_2);
		
		
		JPanel pnl_3 = new JPanel();
		pnl_3.setLayout(null);
		pnl_3.setBounds(570, 10, 400, 120);
		pnl_3.setBackground(null);
		
		JButton btn_1 = new JButton("<html><center>"+"Open"+"<br>"+"Item Menu"+"</center></html>");
		btn_1.setBounds(10, 20, 120, 80);
		btn_1.setBackground(new Color(255, 220, 113));
		btn_1.setOpaque(true);
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
