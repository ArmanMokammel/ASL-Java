package DataEditorUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import CustomComponents.JButtonT1;
import Data.Account;
import Enum.AccountType;
import Enum.InputType;
import Exception.InputException;
import Utilities.Utility;

public class Profile_Screen extends JDialog {

	private JTextField txt_userID = new JTextField();
	private JTextField txt_password = new JTextField();
	private JTextField txt_name = new JTextField();
	private JTextField txt_email = new JTextField();
	private JTextField txt_employeeID = new JTextField();
	private JTextField txt_accountType = new JTextField();

	private String A, B, D, E;
	private String C;
	private int F;

	public Profile_Screen(JFrame frame, String title) {
		super(frame, title, true);
		setSize(565, 597);
		setLayout(null);
		setLocationRelativeTo(null);
		
		Font f1 = new Font(null, Font.BOLD, 32);
		Font f2 = new Font(null, Font.BOLD, 18);
		Font f3 = new Font(null, Font.PLAIN, 16);

		txt_userID.setEditable(false);
		txt_employeeID.setEditable(false);
		txt_accountType.setEditable(false);
		
		JLabel Bg_Icon = new JLabel();
		ImageIcon background = new ImageIcon(Utility.getImage("img\\Profile_Screen.png"));
		Bg_Icon.setIcon(background);
		Bg_Icon.setOpaque(true);
		setContentPane(Bg_Icon);
		
		JLabel lbl_profile = new JLabel("Profile");
		JLabel lbl_userID = new JLabel("User ID:");
		JLabel lbl_password = new JLabel("Password:");
		JLabel lbl_name = new JLabel("Name:");
		JLabel lbl_employeeID = new JLabel("Employee ID:");
		JLabel lbl_email = new JLabel("Email Address:");
		JLabel lbl_accountType = new JLabel("Account Type:");

		lbl_profile.setFont(f1);
		lbl_profile.setBounds(220, 40, 120, 30);
		lbl_userID.setBounds(60, 110, 140, 30);
		lbl_userID.setFont(f2);
		lbl_userID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_password.setBounds(60, 160, 140, 30);
		lbl_password.setFont(f2);
		lbl_password.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_accountType.setBounds(60, 210, 140, 30);
		lbl_accountType.setFont(f2);
		lbl_accountType.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_name.setBounds(60, 260, 140, 30);
		lbl_name.setFont(f2);
		lbl_name.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_employeeID.setBounds(60, 310, 140, 30);
		lbl_employeeID.setFont(f2);
		lbl_employeeID.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_email.setBounds(60, 360, 140, 30);
		lbl_email.setFont(f2);
		lbl_email.setHorizontalAlignment(SwingConstants.RIGHT);

		add(lbl_profile);
		add(lbl_userID);
		add(lbl_password);
		add(lbl_name);
		add(lbl_employeeID);
		add(lbl_email);
		add(lbl_accountType);

		txt_userID.setBounds(210, 110, 240, 30);
		txt_userID.setFont(f3);
		txt_password.setBounds(210, 160, 240, 30);
		txt_password.setFont(f3);
		txt_accountType.setBounds(210, 210, 240, 30);
		txt_accountType.setFont(f3);
		txt_name.setBounds(210, 260, 240, 30);
		txt_name.setFont(f3);
		txt_employeeID.setBounds(210, 310, 240, 30);
		txt_employeeID.setFont(f3);
		txt_email.setBounds(210, 360, 240, 30);
		txt_email.setFont(f3);

		add(txt_userID);
		add(txt_password);
		add(txt_name);
		add(txt_employeeID);
		add(txt_email);
		add(txt_accountType);

		JButtonT1 btn_submit = new JButtonT1("Save", new Color(94, 23, 235), "img\\Save.png", 6);
		btn_submit.setBounds(220, 430, 100, 40);
		btn_submit.setForeground(Color.white);
		btn_submit.setFont(new Font(null, Font.BOLD, 16));
		btn_submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Account account = null;
				try {
					B = Utility.checkString(txt_password, lbl_password, InputType.Password);
					D = Utility.checkString(txt_name, lbl_name, InputType.Alphabetic);
					E = Utility.checkString(txt_email, lbl_email, InputType.Email);
					account = new Account(A, B, AccountType.valueOf(C), D, E, F);
				} catch (InputException e2) {
					Utility.showErrorMessage(e2);
					return;
				}
				
				ArrayList<String> accountList = Utility.readFile("Accounts.ASL");
				
				for(int i = 0; i < accountList.size(); i++){
					if(accountList.get(i).split("\t")[0].equals(A)) {
						accountList.set(i, account.toString());
					}
				}
				
				Utility.writeAllToFile("Accounts.ASL", false, accountList);

				dispose();
			}
		});
		
		add(btn_submit);	
	}
		public void setAccountDetails(Account account) {
			txt_userID.setText(account.getUserID());
			txt_userID.setEditable(false);
			A = account.getUserID();
			txt_password.setText(account.getPassword());
			txt_accountType.setText(account.getAccountType().toString());
			txt_accountType.setEditable(false);
			C = account.getAccountType().toString();
			txt_name.setText(account.getName());
			txt_email.setText(account.getEmail());
			txt_employeeID.setText(Integer.toString(account.getEmployeeID()));
			txt_employeeID.setEditable(false);
			F = account.getEmployeeID();
		}
	
}
