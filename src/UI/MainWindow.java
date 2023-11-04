package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import CustomComponents.MenuButton;
import Data.Account;
import DataEditorUI.Profile_Screen;
import Enum.AccountType;
import UI.Panels.*;
import Utilities.Utility;

public class MainWindow extends JFrame{
	
	public static Account account;
	public JPanel currentPanel = null;
	
	public MainWindow() {
		super("ASL.Java - Welcome " + account.getName() + " (" + account.getAccountType() + ")");
	}

	public void createUI() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 800);
		JPanel p = new JPanel() {
			@Override
			public void setLayout(LayoutManager mgr) {
				super.setLayout(null);
			}
			
			@Override
		    public void paintComponent(Graphics g) {
				if(g instanceof Graphics2D) {					
					Graphics2D g2d = (Graphics2D) g;
					g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
					int w = getWidth(), h = getHeight();
					Color color2 = new Color(242, 228, 70);
					Color color1 = new Color(184, 183, 178);
					GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
					g2d.setPaint(gp);
					g2d.fillRect(0, 0, w, h);
				}
				else {
					super.paintComponent(g);
				}
		    }
		};
		setContentPane(p);
		
		FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		FlowLayout layout2 = new FlowLayout(FlowLayout.RIGHT);
	    layout.setHgap(10);
	    layout2.setHgap(50);
		JPanel pnl_Head = new JPanel(layout2);
		JPanel pnl_Items = new JPanel(layout);
		pnl_Items.setBounds(500, 0, 500, 50);
		pnl_Items.setOpaque(false);
		
		pnl_Head.setBackground(Color.orange);
				
		MenuButton btn = new MenuButton("Vouchers", Utility.getImageIcon("img\\DiscountVoucher.png", 60, 60), null);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.swapPanel(new Voucher_Panel(MainWindow.this));
			}
		});
		
		MenuButton btn1 = new MenuButton("Accounts", Utility.getImageIcon("img\\Add_User.png", 60, 60), null);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.swapPanel(new Account_Panel(MainWindow.this));				
			}
		});
		
		MenuButton btn2 = new MenuButton("Order", Utility.getImageIcon("img\\Order.png", 60, 60), null);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order_Screen window = new Order_Screen(false);
				window.createUI();
				MainWindow.this.dispose();
			}
		});
		
		MenuButton btn3 = new MenuButton("Items", Utility.getImageIcon("img\\Item.png", 60, 60), null);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.swapPanel(new Item_Panel(MainWindow.this));								
			}
		});
		
		MenuButton btn4 = new MenuButton("Employees", Utility.getImageIcon("img\\Employees.png", 60, 60), null);
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.swapPanel(new Employee_Panel(MainWindow.this));				
			}
		});
		
		MenuButton btn5 = new MenuButton("Customers", Utility.getImageIcon("img\\Customers.png", 60, 60), null);
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.swapPanel(new Customer_Panel(MainWindow.this));
			}
		});
		
		MenuButton btn6 = new MenuButton("Sale History", Utility.getImageIcon("img\\SaleHistory.png", 60, 60), null);
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaleHistory_Window();				
			}
		});
		
		MenuButton btn7 = new MenuButton("Settings", Utility.getImageIcon("img\\Settings.png", 60, 60), null);
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Settings_Screen("Settings");			
			}
		});
		
		MenuButton btnA = new MenuButton("Profile", Utility.getImageIcon("img\\User.png", 60, 60), null);
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Profile_Screen profileScreen = new Profile_Screen(MainWindow.this, "Profile");
				profileScreen.setAccountDetails(MainWindow.account);
				profileScreen.setVisible(true);
			}
		});
		
		MenuButton btn_Logout = new MenuButton("Logout", Utility.getImageIcon("img\\Logout.png", 60, 60), new Color(250, 240, 200));
		btn_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_Screen lg = new Login_Screen();
				lg.createUI();
				dispose();
			}
		});
		
		pnl_Items.setBorder(new EmptyBorder(0, 0, 0, 60));
		pnl_Items.setBackground(Color.orange);		
		pnl_Items.add(btn);
		
		if(account.getAccountType() == AccountType.SuperAdmin)
			pnl_Items.add(btn1);
		
		pnl_Items.add(btn2);
		pnl_Items.add(btn3);
		
		if(account.getAccountType() == AccountType.SuperAdmin)
			pnl_Items.add(btn4);
			
		pnl_Items.add(btn5);
		pnl_Items.add(btn6);
		pnl_Items.add(btn7);
		
		add(btn_Logout);
		pnl_Head.add(pnl_Items);
		pnl_Head.add(btnA);
		
		JLabel lb2 = new JLabel("Copyright ASL", SwingConstants.CENTER);
		lb2.setOpaque(true);
		lb2.setFont(new Font(null, Font.BOLD, 18));
		lb2.setBackground(Color.lightGray);
		
		add(pnl_Head);
		add(lb2);
		Welcome_Panel pnl = new Welcome_Panel(this, account.getName());
		add(pnl);

		setVisible(true);
		pnl_Head.setBounds(0,0, getWidth(), 110);
		btn_Logout.setBounds(getWidth() - 140, getHeight() - 200, 100, 100);
		lb2.setBounds(0, getHeight() - 88, getWidth(), 50);
		pnl.setBounds(30, 150, getWidth() - 70, 550);
		currentPanel = pnl;				
	}
	
	public void swapPanel(JPanel newPanel) {
		remove(currentPanel);
		add(newPanel);
		revalidate();
		repaint();
		currentPanel = newPanel;
		System.gc();
	}
}
