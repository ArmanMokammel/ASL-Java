package UI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DataEditorUI.Profile_Screen;
import Enum.AccountType;

public class MainWindow extends JFrame{
	
	private String name;
	private String employeeId;
	public static AccountType accountType;
	public JPanel currentPanel = null;
	
	public MainWindow(String name, AccountType type) {
		super("ASL.Java - Welcome " + name + " (" + type + ")");
		this.name = name;
		MainWindow.accountType = type;
	}
	
	public void createUI() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 800);
		
		FlowLayout layout = new FlowLayout(FlowLayout.RIGHT);
		FlowLayout layout2 = new FlowLayout(FlowLayout.RIGHT);
	    layout.setHgap(10);
	    layout2.setHgap(50);
		JPanel pnl_Head = new JPanel(layout2);
		JPanel pnl_Items = new JPanel(layout);
		pnl_Items.setBounds(500, 0, 500, 50);
		pnl_Items.setOpaque(false);
		
		pnl_Head.setBackground(Color.orange);
		Image image = null;
		try {
			image = ImageIO.read(new File("img\\Placeholder.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Image dimg = image.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		
		ImageIcon img = new ImageIcon(dimg);
		
		MenuButton btn = new MenuButton("Vouchers", img, null);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.swapPanel(new Voucher_Panel(MainWindow.this));
			}
		});
		
		MenuButton btn1 = new MenuButton("Accounts", img, null);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.swapPanel(new Account_Panel(MainWindow.this));				
			}
		});
		
		MenuButton btn2 = new MenuButton("Order", img, null);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order_Screen window = new Order_Screen(name, accountType);
				window.createUI();
				MainWindow.this.dispose();
			}
		});
		
		MenuButton btn3 = new MenuButton("Items", img, null);
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.this.swapPanel(new Item_Panel(MainWindow.this));								
			}
		});
		
		MenuButton btn4 = new MenuButton("Employees", img, null);
		
		MenuButton btnA = new MenuButton("Profile", img, null);
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Profile_Screen(MainWindow.this, "Profile");
				
			}
		});
		
		MenuButton btn_Logout = new MenuButton("Logout", img, null);
		btn_Logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_Screen lg = new Login_Screen("ASL - Restaurant Management System");
				lg.createUI();
				dispose();
			}
		});
		
		pnl_Items.setBorder(new EmptyBorder(0, 0, 0, 60));
		pnl_Items.setBackground(Color.orange);
		pnl_Items.add(btn);
		pnl_Items.add(btn1);
		pnl_Items.add(btn2);
		pnl_Items.add(btn3);
		pnl_Items.add(btn4);
		add(btn_Logout);
		pnl_Head.add(pnl_Items);
		pnl_Head.add(btnA);
		
		JLabel lb2 = new JLabel("Copyright my shit", SwingConstants.CENTER);
		lb2.setOpaque(true);
		lb2.setBackground(Color.lightGray);
		
		add(pnl_Head);
		add(lb2);
		Welcome_Panel pnl = new Welcome_Panel(this, name);
		add(pnl);

		setVisible(true);
		pnl_Head.setBounds(0,0, getWidth(), 110);
		btn_Logout.setBounds(getWidth() - 120, getHeight() - 200, 100, 100);
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
