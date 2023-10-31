package UI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import Data.Customer;
import Data.OrderController;
import Enum.DiscountType;
import UI.OrderSystem.*;

public class Order_Screen extends JFrame{
				
	public Order_Screen(boolean isOrder) {
		super("Take Order - " + MainWindow.account.getName());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(isOrder) {
					Login_Screen lg = new Login_Screen();
					lg.createUI();
					lg.setVisible(true);
				} else {
					OrderController.resetOrder();
					MainWindow window = new MainWindow();
					window.createUI();
				}
				OrderController.resetOrder();
				super.windowClosing(e);
			}
		});
		setSize(1500, 800);
		OrderController.init("TEST", MainWindow.account.getUserID());
	}
	
	public void createUI() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		this.getContentPane().setBackground(Color.black);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}
		});
		
		this.getRootPane().registerKeyboardAction (new ActionListener ()
        {
            public void actionPerformed (final ActionEvent e)
            {
            	dispose();
                setUndecorated(isUndecorated() ? false : true);
                setVisible(true);
            }
        }, KeyStroke.getKeyStroke ( KeyEvent.VK_F1, 0 ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		
		Panel_A pnl_1 = new Panel_A(this);
		Panel_C pnl_3 = new Panel_C();
		Panel_D pnl_6 = new Panel_D(pnl_3);
		Panel_E pnl_4 = new Panel_E();
		Panel_B pnl_2 = new Panel_B(this, pnl_4);
		pnl_3.setDisplayPanel(pnl_4);
		JLabel pnl_5 = new JLabel("Copyright my shit 2.0", SwingConstants.CENTER);
		
		pnl_1.setBackground(new Color(198, 198, 198));		
		pnl_2.setBackground(new Color(182, 169, 140));		
		pnl_3.setBackground(Color.blue);		
		pnl_4.setBackground(Color.magenta);
		pnl_5.setBackground(Color.yellow);
		pnl_6.setBackground(Color.red);
		
//		pnl_3.setBackground(Color.black);
//		pnl_4.setBackground(Color.black);
		
		pnl_5.setOpaque(true);
		
		add(pnl_1);
		add(pnl_2);
		add(pnl_3);
		add(pnl_6);
		add(pnl_4);
		add(pnl_5);
		
		setVisible(true);
		pnl_1.setBounds(0,0,getWidth() - 600, 150);
		pnl_2.setBounds(pnl_1.getWidth(),0,590, 190);
		pnl_3.setBounds(0,150,getWidth() - 900, getHeight() - 550);
		pnl_6.setBounds(pnl_3.getWidth(), pnl_2.getHeight(), 890, getHeight() - 260);
		pnl_5.setBounds(0, getHeight() - 70, getWidth(), 35);
		pnl_4.setBounds(0, pnl_1.getHeight() + pnl_3.getHeight(), getWidth() - 900, 300);
		System.gc();
	}
	
	public static void setCustomer(Customer customer) {
		if(customer != null) {
			Panel_B.lbl_4.setText(customer.getCustomerName());
			Panel_B.lbl_6.setText(Integer.toString(customer.getCustomerId()));
			Panel_B.lbl_8.setText(customer.getSpecialDiscountType() == DiscountType.Percentage ? Double.toString(customer.getSpecialDiscount()) + "%" : Double.toString(customer.getSpecialDiscount()));
			Panel_B.swapPanel(2);
		} 
		else {
			Panel_B.lbl_4.setText("");
			Panel_B.lbl_6.setText("");
			Panel_B.lbl_8.setText("");
			Panel_B.swapPanel(1);
		}
	}
}
