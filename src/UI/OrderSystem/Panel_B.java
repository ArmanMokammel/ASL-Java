package UI.OrderSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import Data.OrderController;
import UI.CustomerWindow;
import UI.Order_Screen;

public class Panel_B extends JPanel{
		
	private static JPanel container;
	private static JPanel pnl_1;
	private static  JPanel pnl_2;
	
	public JTextField txt_1 = new JTextField();
	
	public static JLabel lbl_4 = new JLabel();
	public static JLabel lbl_6 = new JLabel();
	public static JLabel lbl_8 = new JLabel();
	public Panel_E p;
	
	public Panel_B(Order_Screen window, Panel_E p) {
		this.p = p;
		setLayout(null);
		
		container = new JPanel();
		container.setSize(590, 190);
		container.setLayout(null);
		container.setBackground(null);
		
		pnl_1 = new JPanel();
		pnl_1.setLayout(null);
		pnl_1.setBackground(null);
		
		JLabel lbl_1 = new JLabel("Select Customer", SwingConstants.CENTER);
		lbl_1.setBounds(0, 10, 585, 30);
		lbl_1.setOpaque(true);
		lbl_1.setBackground(Color.blue);
		lbl_1.setForeground(Color.white);
		
		JLabel lbl_2 = new JLabel("Customer:");
		lbl_2.setBounds(10, 50, 60, 30);
		
		txt_1.setBounds(80, 50, 380, 30);
		
		JButton btn_addCustomer = new JButton("New Customer");
		btn_addCustomer.setBounds(100, 100, 120, 30);
		btn_addCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Thread t = new Thread() {
					@Override
					public void run() {
						new CustomerWindow(window, true);
					}
				};
				t.start();
			}
		});
		
		JButton btn_viewCustomers = new JButton("View Customers");
		btn_viewCustomers.setBounds(230, 100, 130, 30);
		btn_viewCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerWindow(window, false);			
			}
		});
		
		pnl_1.add(lbl_1);
		pnl_1.add(txt_1);
		pnl_1.add(lbl_2);
		pnl_1.add(btn_addCustomer);
		pnl_1.add(btn_viewCustomers);
		
		container.add(pnl_1);
		
		pnl_2 = new JPanel();
		pnl_2.setLayout(null);
		pnl_2.setBackground(null);
		
		JLabel lbl_3 = new JLabel("Customer:");
		lbl_3.setBounds(10, 20, 70, 30);
		
		lbl_4.setBounds(90, 20, 370, 30);
		lbl_4.setOpaque(true);
		
		JLabel lbl_5 = new JLabel("ID:");
		lbl_5.setBounds(10, 60, 70, 30);
		
		lbl_6.setBounds(90, 60, 370, 30);
		lbl_6.setOpaque(true);
		
		JLabel lbl_7 = new JLabel("Discount %:");
		lbl_7.setBounds(10, 100, 70, 30);
		
		lbl_8.setBounds(90, 100, 370, 30);
		lbl_8.setOpaque(true);
		
		JButton btn_rmvCustomer = new JButton("Remove Customer");
		btn_rmvCustomer.setBounds(180, 150, 140, 30);
		btn_rmvCustomer.setBackground(new Color(255, 49, 49));
		btn_rmvCustomer.setForeground(Color.white);
		btn_rmvCustomer.setOpaque(true);
		btn_rmvCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				OrderController.getOrder().setTotal(OrderController.getOrder().getSubTotal());
				OrderController.getOrder().setAmountDue(OrderController.getOrder().getTotal() - OrderController.getOrder().getAmountPaid());
				Order_Screen.setCustomer(null);
				OrderController.getOrder().setCustomer(null);
				Panel_E.discount.setText("0.0");
				Panel_E.total.setText(Double.toString(OrderController.getOrder().getTotal()));
				Panel_E.amtDue.setText(Double.toString(OrderController.getOrder().getAmountDue()));
				Panel_E.amt.setText(Double.toString(OrderController.getOrder().getAmountDue()));
			}
		});
		
		pnl_2.add(lbl_3);
		pnl_2.add(lbl_4);
		pnl_2.add(lbl_5);
		pnl_2.add(lbl_6);
		pnl_2.add(lbl_7);
		pnl_2.add(lbl_8);
		pnl_2.add(btn_rmvCustomer);
		
		pnl_2.setSize(new Dimension(590, 190));
		pnl_1.setSize(new Dimension(590, 190));

		
		add(container);
		
		AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).doClick();
            }
        };
        
        btn_viewCustomers.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.ALT_DOWN_MASK), "viewCus");        
        btn_addCustomer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.ALT_DOWN_MASK), "addCus");
        
        btn_viewCustomers.getActionMap().put("viewCus", buttonPressed);
        btn_addCustomer.getActionMap().put("addCus", buttonPressed);
	}
	
	public static void swapPanel(int n) {
		if(n == 1) {
			container.remove(pnl_2);
			container.add(pnl_1);
		} else if (n == 2) {
			container.remove(pnl_1);
			container.add(pnl_2);
		}
		container.updateUI();
	}
}
