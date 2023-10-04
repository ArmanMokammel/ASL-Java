package UI;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		JPanel pnl_1 = new JPanel();
		JPanel pnl_2 = new JPanel();
		JPanel pnl_3 = new JPanel();
		JPanel pnl_4 = new JPanel();
		JPanel pnl_5 = new JPanel();
		
		pnl_1.setBackground(Color.red);		
		pnl_2.setBackground(Color.green);		
		pnl_3.setBackground(Color.blue);		
		pnl_4.setBackground(Color.magenta);
		pnl_5.setBackground(Color.yellow);
		
		add(pnl_1);
		add(pnl_2);
		add(pnl_3);
		add(pnl_4);
		add(pnl_5);
		
		setVisible(true);
		pnl_1.setBounds(0,0,getWidth() - 450,150);
		pnl_2.setBounds(pnl_1.getWidth(),0,450,150);
		pnl_3.setBounds(0,150,getWidth() - 450,getHeight() - 220);
		pnl_4.setBounds(pnl_1.getWidth(), 150, 450, getHeight() - 220);
		pnl_5.setBounds(0, getHeight() - 150, getWidth(), 150);
	}
}
