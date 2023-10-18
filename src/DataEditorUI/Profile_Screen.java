package DataEditorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Data.Account;

public class Profile_Screen extends JDialog {

	private JTextField txt_userID = new JTextField();
	private JTextField txt_password = new JTextField();
	private JTextField txt_name = new JTextField();
	private JTextField txt_email = new JTextField();
	private JTextField txt_employeeID = new JTextField();
	private JTextField txt_accountType = new JTextField();

	public Profile_Screen(JFrame frame, String title) {
		super(frame, title, true);
		setSize(500, 500);
		setLayout(null);
		setLocationRelativeTo(null);

		JLabel lbl_userID = new JLabel("User ID:");
		JLabel lbl_password = new JLabel("Password:");
		JLabel lbl_name = new JLabel("Name:");
		JLabel lbl_employeeID = new JLabel("Employee ID:");
		JLabel lbl_email = new JLabel("Email Address:");
		JLabel lbl_accountType = new JLabel("Account Type:");

		lbl_userID.setBounds(50, 50, 100, 30);
		lbl_password.setBounds(50, 100, 100, 30);
		lbl_accountType.setBounds(50, 150, 100, 30);
		lbl_name.setBounds(50, 200, 100, 30);
		lbl_employeeID.setBounds(50, 250, 100, 30);
		lbl_email.setBounds(50, 300, 100, 30);

		add(lbl_userID);
		add(lbl_password);
		add(lbl_name);
		add(lbl_employeeID);
		add(lbl_email);
		add(lbl_accountType);

		txt_userID.setBounds(160, 50, 200, 30);
		txt_password.setBounds(160, 100, 200, 30);
		txt_accountType.setBounds(160, 150, 200, 30);
		txt_name.setBounds(160, 200, 200, 30);
		txt_employeeID.setBounds(160, 250, 200, 30);
		txt_email.setBounds(160, 300, 200, 30);

		add(txt_userID);
		add(txt_password);
		add(txt_name);
		add(txt_employeeID);
		add(txt_email);
		add(txt_accountType);
		
		JButton btn_submit = new JButton ("Save");
		btn_submit.setBounds(180, 350, 80, 35);
		btn_submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Account account = null;
//				try {
//					
//					
//				}
				
				
			}
		});

		setVisible(true);
	}
}
