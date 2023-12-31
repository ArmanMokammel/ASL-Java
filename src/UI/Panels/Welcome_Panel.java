package UI.Panels;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import CustomComponents.MenuButton;
import Enum.AccountType;
import UI.*;
import Utilities.Utility;

public class Welcome_Panel extends JPanel {
	
	private MainWindow window;

	public Welcome_Panel(MainWindow window, String name) {
		
		this.window = window;
		
		Font f1 = new Font(null, Font.BOLD, 28);
		Font f2 = new Font(null, Font.PLAIN, 22);

		setLayout(null);
		setBackground(new Color(0, 0, 0, 0));

		JLabel lbl_Welcome = new JLabel("Welcome " + name + "!");
		lbl_Welcome.setBounds(210, 30, 500, 30);
		lbl_Welcome.setFont(f1);
		
		JLabel lbl_Welcome2 = new JLabel("Select an option below to get started");
		lbl_Welcome2.setBounds(210, 70, 500, 30);
		lbl_Welcome2.setFont(f2);
				
	    FlowLayout layout = new FlowLayout();
	    layout.setHgap(15);
	    JPanel pnl = new JPanel(layout);
	    pnl.setBackground(new Color(0,0,0,0));
	    
		MenuButton btn = new MenuButton("Vouchers", Utility.getImageIcon("img\\DiscountVoucher.png", 100, 100), new Color(255, 235, 156));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.swapPanel(new Voucher_Panel(window));
			}
		});
		
		MenuButton btn1 = new MenuButton("Accounts", Utility.getImageIcon("img\\Add_User.png", 100, 100), new Color(255, 235, 156));
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.swapPanel(new Account_Panel(window));				
			}
		});
		
		MenuButton btn2 = new MenuButton("Order", Utility.getImageIcon("img\\Order.png", 100, 100), new Color(255, 235, 156));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order_Screen window = new Order_Screen(false);
				window.createUI();
				Welcome_Panel.this.window.dispose();				
			}
		});
		
		MenuButton btn3 = new MenuButton("Items", Utility.getImageIcon("img\\Item.png", 100, 100), new Color(255, 235, 156));
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.swapPanel(new Item_Panel(window));				
			}
		});
		
		MenuButton btn4 = new MenuButton("Employees", Utility.getImageIcon("img\\Employees.png", 100, 100), new Color(255, 235, 156));
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.swapPanel(new Employee_Panel(window));
			}
		});
		
		MenuButton btn5 = new MenuButton("Customers", Utility.getImageIcon("img\\Customers.png", 100, 100), new Color(255, 235, 156));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.swapPanel(new Customer_Panel(window));
			}
		});
		
		MenuButton btn6 = new MenuButton("Sale History", Utility.getImageIcon("img\\SaleHistory.png", 100, 100), new Color(255, 235, 156));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaleHistory_Window();
			}
		});

		pnl.setBounds(210, 150, 800, 300);
		
		pnl.add(btn);
		
		if(MainWindow.account.getAccountType() == AccountType.SuperAdmin)
			pnl.add(btn1);
			
		pnl.add(btn2);
		pnl.add(btn3);
		
		if(MainWindow.account.getAccountType() == AccountType.SuperAdmin)
			pnl.add(btn4);
		
		pnl.add(btn5);
		pnl.add(btn6);
		
		add(lbl_Welcome);
		add(lbl_Welcome2);
		add(pnl);
	}
}
