package UI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import Enum.AccountType;

public class Order_Screen extends JFrame{
	
	private String name;
	private AccountType accountType;
	
	public Order_Screen(String name, AccountType accountType) {
		super("Take Order - " + name);
		this.name = name;
		this.accountType = accountType;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainWindow window = new MainWindow(name, Order_Screen.this.accountType);
				window.createUI();
				super.windowClosing(e);
			}
		});
	}
	
	public void createUI() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1500, 800);
		
		setVisible(true);
	}
}
