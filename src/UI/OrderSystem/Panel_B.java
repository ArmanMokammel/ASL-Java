package UI.OrderSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	
	public static JLabel lbl_4 = new JLabel();
	public static JLabel lbl_6 = new JLabel();
	public static JLabel lbl_8 = new JLabel();
	public Panel_E p;
	
	public Panel_B(Order_Screen window, Panel_E p) {
		this.p = p;
		setLayout(null);
		
		Font f1 = new Font(null, Font.BOLD, 20);
		Font f2 = new Font(null, Font.BOLD, 16);
		
		container = new JPanel();
		container.setSize(590, 190);
		container.setLayout(null);
		container.setBackground(null);
		
		pnl_1 = new JPanel();
		pnl_1.setLayout(null);
		pnl_1.setBackground(null);
		pnl_1.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		
		JLabel lbl_1 = new JLabel("Select Customer", SwingConstants.CENTER);
		lbl_1.setBounds(2, 30, 583, 30);
		lbl_1.setOpaque(true);
		lbl_1.setBackground(Color.blue);
		lbl_1.setForeground(Color.white);
		lbl_1.setFont(f1);
		
				
		JButton btn_addCustomer = new JButton("New Customer");
		btn_addCustomer.setBounds(110, 80, 170, 40);
		btn_addCustomer.setFont(f2);
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
		btn_viewCustomers.setBounds(300, 80, 170, 40);
		btn_viewCustomers.setFont(f2);
		btn_viewCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CustomerWindow(window, false);			
			}
		});
		
		pnl_1.add(lbl_1);
		pnl_1.add(btn_addCustomer);
		pnl_1.add(btn_viewCustomers);
		
		container.add(pnl_1);
		
		pnl_2 = new JPanel();
		pnl_2.setLayout(null);
		pnl_2.setBackground(null);
		pnl_2.setBorder(BorderFactory.createLineBorder(Color.white, 2));
		
		JLabel lbl_3 = new JLabel("Customer:", SwingConstants.RIGHT);
		lbl_3.setBounds(10, 20, 100, 30);
		lbl_3.setFont(f2);
		
		lbl_4.setBounds(120, 20, 370, 30);
		lbl_4.setOpaque(true);
		lbl_4.setFont(f2);
		
		JLabel lbl_5 = new JLabel("ID:", SwingConstants.RIGHT);
		lbl_5.setBounds(10, 60, 100, 30);
		lbl_5.setFont(f2);
		
		lbl_6.setBounds(120, 60, 370, 30);
		lbl_6.setOpaque(true);
		lbl_6.setFont(f2);
		
		JLabel lbl_7 = new JLabel("Discount %:", SwingConstants.RIGHT);
		lbl_7.setBounds(10, 100, 100, 30);
		lbl_7.setFont(f2);
		
		lbl_8.setBounds(120, 100, 370, 30);
		lbl_8.setOpaque(true);
		lbl_8.setFont(f2);
		
		JButton btn_rmvCustomer = new JButton("Remove Customer");
		btn_rmvCustomer.setBounds(160, 140, 180, 30);
		btn_rmvCustomer.setBackground(new Color(255, 49, 49));
		btn_rmvCustomer.setForeground(Color.white);
		btn_rmvCustomer.setOpaque(true);
		btn_rmvCustomer.setFont(f2);
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
