package UI.OrderSystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import Data.OrderController;
import UI.Login_Screen;
import UI.Order_Screen;
import UI.SaleHistory_Window;
import UI.SuspendedOrders_Screen;
import Utilities.Utility;

public class Panel_A extends JPanel {
	
	public static JLabel txt_orderNo;
	public static JComboBox<String> cmbx_orderType;
				
	public Panel_A(Order_Screen window) {
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.white, 2));
		
		Font f1 = new Font(null, Font.BOLD, 16);
		Font f2 = new Font(null, Font.BOLD, 18);

		ImageIcon logo = Utility.getImageIcon("img\\Logo.png", 310, 90);
		JLabel lbl_Logo = new JLabel(logo);
		lbl_Logo.setBounds(380, 30, 310, 90);
		add(lbl_Logo);		
		
		JPanel pnl_2 = new JPanel();
		pnl_2.setLayout(null);
		pnl_2.setBounds(20, 10, 340, 120);
		pnl_2.setBackground(new Color(255, 220, 113));
		
		JLabel lbl_3 = new JLabel("Branch:", SwingConstants.RIGHT);
		lbl_3.setBounds(10, 10, 95, 30);
		lbl_3.setFont(f1);
		JLabel txt_1 = new JLabel("Gulshan-1");
		txt_1.setFont(f1);
		txt_1.setOpaque(true);
		txt_1.setBounds(120, 10, 200, 30);
		
		
		JLabel lbl_OrderNo = new JLabel("Order no:", SwingConstants.RIGHT);
		lbl_OrderNo.setBounds(10, 45, 95, 30);
		lbl_OrderNo.setFont(f1);
		
		txt_orderNo = new JLabel();
		txt_orderNo.setBounds(120, 45, 200, 30);
		txt_orderNo.setOpaque(true);
		txt_orderNo.setFont(f1);
		txt_orderNo.setText(OrderController.getOrder().getOrderNo());
		
		JLabel lbl_5 = new JLabel("Order Type:", SwingConstants.RIGHT);
		lbl_5.setBounds(10, 80, 95, 30);	
		lbl_5.setFont(f1);
		cmbx_orderType = new JComboBox<String>(new String[] {"Dine-in", "Delivery", "Takeout"});
		cmbx_orderType.setBounds(120, 80, 200, 30);
		cmbx_orderType.setFont(f1);
		cmbx_orderType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderController.getOrder().setOrderType((String)cmbx_orderType.getSelectedItem());
			}
		});
		
		pnl_2.add(lbl_3);
		pnl_2.add(txt_1);
		pnl_2.add(lbl_OrderNo);
		pnl_2.add(txt_orderNo);
		pnl_2.add(lbl_5);
		pnl_2.add(cmbx_orderType);
		add(pnl_2);
		
		
		JPanel pnl_3 = new JPanel();
		pnl_3.setLayout(null);
		pnl_3.setBounds(710, 10, 432, 120);
		pnl_3.setBackground(null);
		
		JButton btn_logOut = new JButton("Logout");
		btn_logOut.setBounds(10, 20, 120, 80);
		btn_logOut.setBackground(new Color(255, 220, 113));
		btn_logOut.setOpaque(true);
		btn_logOut.setFont(f2);
		btn_logOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_Screen lg = new Login_Screen();
				lg.createUI();
				lg.setVisible(true);
				window.dispose();
			}
		});
		
		JPanel pnl_3_A = new JPanel();
		pnl_3_A.setLayout(null);
		pnl_3_A.setBounds(140, 0, 292, 120);
		pnl_3_A.setBackground(Color.black);
				
		JButton btn_3 = new JButton("Shortcuts");
		btn_3.setBounds(0, 0, 145, 60);
		btn_3.setBackground(new Color(255, 220, 113));
		btn_3.setOpaque(true);
		btn_3.setFont(f2);
		   
		JButton btn_selHis = new JButton("Sale History");
		btn_selHis.setBounds(147, 0, 145, 60);
		btn_selHis.setBackground(new Color(255, 220, 113));
		btn_selHis.setOpaque(true);
		btn_selHis.setFont(f2);
		btn_selHis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaleHistory_Window window = new SaleHistory_Window();
				window.setVisible(true);				
			}
		});
		
		JButton btn_susOrd = new JButton("Suspended Orders");
		btn_susOrd.setBounds(0, 70, 292, 50);
		btn_susOrd.setFont(f2);
		btn_susOrd.setBackground(new Color(87, 86, 86));
		btn_susOrd.setForeground(Color.white);
		btn_susOrd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SuspendedOrders_Screen(window);
			}
		});
		
		pnl_3_A.add(btn_susOrd);
		pnl_3_A.add(btn_3);
		pnl_3_A.add(btn_selHis);
		
		pnl_3.add(btn_logOut);
		pnl_3.add(pnl_3_A);
		
		add(pnl_3);
		
		
		AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).doClick();
            }
        };
        
        btn_selHis.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_1, KeyEvent.CTRL_DOWN_MASK), "selHis");
        btn_susOrd.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_2, KeyEvent.CTRL_DOWN_MASK), "susOrd");
        btn_logOut.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "logOut");
        
        btn_selHis.getActionMap().put("selHis", buttonPressed);
        btn_susOrd.getActionMap().put("susOrd", buttonPressed);
        btn_logOut.getActionMap().put("logOut", buttonPressed);
	}
}
