package UI;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Flow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Welcome_Panel extends JPanel {

	public Welcome_Panel(MainWindow window, String name) {
		
		Font f1 = new Font(null, Font.BOLD, 20);
		Font f2 = new Font(null, Font.PLAIN, 17);

		setLayout(null);

		JLabel lbl_Welcome = new JLabel("Welcome " + name + "!");
		lbl_Welcome.setBounds(210, 30, 500, 30);
		lbl_Welcome.setFont(f1);
		
		JLabel lbl_Welcome2 = new JLabel("Select an option below to get started");
		lbl_Welcome2.setBounds(210, 70, 500, 30);
		lbl_Welcome2.setFont(f2);
		
		ImageIcon img = null;
	    img = new ImageIcon("img\\test.png");
		
	    FlowLayout layout = new FlowLayout();
	    layout.setHgap(15);
	    JPanel pnl = new JPanel(layout);
	    
		MenuButton btn = new MenuButton("Vouchers", img, Color.white);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.swapPanel(new Voucher_Panel(window));
			}
		});
		
		MenuButton btn1 = new MenuButton("Accounts", img, Color.white);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.swapPanel(new Account_Panel(window));				
			}
		});
		
		MenuButton btn2 = new MenuButton("Order", img, Color.white);
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(window, "Not implemented yet!");
				
			}
		});
		
		MenuButton btn3 = new MenuButton("Items", img, Color.white);
		MenuButton btn4 = new MenuButton("Employees", img, Color.white);

		pnl.setBounds(210, 150, 800, 300);
		
		pnl.add(btn);
		pnl.add(btn1);
		pnl.add(btn2);
		pnl.add(btn3);
		pnl.add(btn4);
		
		add(lbl_Welcome);
		add(lbl_Welcome2);
		add(pnl);
	}
}
