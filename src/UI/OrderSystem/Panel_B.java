package UI.OrderSystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import Data.Customer;
import UI.SearchableComboBox;

public class Panel_B extends JPanel{
	
	public Customer customer = null;
	
	public Panel_B() {
		setLayout(null);
		
		JLabel lbl_1 = new JLabel("Select Customer (Required for Due Payments)", SwingConstants.CENTER);
		lbl_1.setBounds(0, 10, 495, 30);
		lbl_1.setOpaque(true);
		lbl_1.setBackground(Color.blue);
		lbl_1.setForeground(Color.white);
		
		JLabel lbl_2 = new JLabel("Customer:");
		lbl_2.setBounds(10, 50, 60, 30);
		
		List<String> myWords = new ArrayList<String>();

		myWords.add("bike");
		myWords.add("car");
		myWords.add("cap");
		myWords.add("cape");
		myWords.add("canadian");
		myWords.add("caprecious");
		myWords.add("catepult abs");
		
		SearchableComboBox txt_1 = new SearchableComboBox(myWords);		
		txt_1.setBounds(80, 50, 380, 30);
		txt_1.setMaximumRowCount(5);
		txt_1.setSelectedItem(null);
		txt_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(customer == null) {
					if((String)((SearchableComboBox)e.getSource()).getSelectedItem() == (String)((SearchableComboBox)e.getSource()).getItemAt(0)) {					
						//JOptionPane.showMessageDialog(null, "123");
						//customer = new Customer(0, null, null, null, null, null, 0);
					}
				}
			}
		});
		txt_1.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED)
				{	
					if(txt_1.isTyping) {					
						txt_1.isTyping = false;
						return;
					}
					txt_1.isTyping = false;
					
					//Add customer
					//To be changed later
					JOptionPane.showMessageDialog(null, "123");

					customer = new Customer(0, null, null, null, null, null, 0);
				}
			}
		});
		
		JButton btn_1 = new JButton("New Customer");
		btn_1.setBounds(190, 100, 120, 30);
		
		add(lbl_1);
		add(lbl_2);
		add(txt_1);
		add(btn_1);
		
		
		
		
		JLabel lbl_3 = new JLabel("Customer:");
		lbl_3.setBounds(10, 20, 70, 30);
		
		JLabel lbl_4 = new JLabel(" Customer Name");
		lbl_4.setBounds(90, 20, 370, 30);
		lbl_4.setOpaque(true);
		
		JLabel lbl_5 = new JLabel("ID:");
		lbl_5.setBounds(10, 60, 70, 30);
		
		JLabel lbl_6 = new JLabel(" 2232450642");
		lbl_6.setBounds(90, 60, 370, 30);
		lbl_6.setOpaque(true);
		
		JLabel lbl_7 = new JLabel("Discount %:");
		lbl_7.setBounds(10, 100, 70, 30);
		
		JLabel lbl_8 = new JLabel(" 50.00%");
		lbl_8.setBounds(90, 100, 370, 30);
		lbl_8.setOpaque(true);
		
		JButton btn_2 = new JButton("Remove Customer");
		btn_2.setBounds(180, 150, 140, 30);
		btn_2.setBackground(new Color(255, 49, 49));
		btn_2.setForeground(Color.white);
		btn_2.setOpaque(true);
		
//		add(lbl_3);
//		add(lbl_4);
//		add(lbl_5);
//		add(lbl_6);
//		add(lbl_7);
//		add(lbl_8);
//		add(btn_2);
	}
}
