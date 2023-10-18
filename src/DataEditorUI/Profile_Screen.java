package DataEditorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.sound.sampled.Line;
import javax.swing.*;

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
		setSize(500, 500);
		setLayout(null);
		setLocationRelativeTo(null);

		txt_userID.setEditable(false);
		txt_employeeID.setEditable(false);
		txt_accountType.setEditable(false);

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

		JButton btn_submit = new JButton("Save");
		btn_submit.setBounds(180, 350, 80, 35);
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
				
				Utility.writeAllToFile("Accounts.ASL", false, new LinkedList(accountList));


			}
		});
		
		add(btn_submit);
		
		setVisible(true);		
	}
		public void setAccountDetails(String userID, String password, AccountType accountType, String name, String email, int employeeID) {
			txt_userID.setText(userID);
			txt_userID.setEditable(false);
			txt_password.setText(password);
			txt_accountType.setText(accountType.toString());
			txt_accountType.setEditable(false);
			txt_name.setText(name);
			txt_email.setText(email);
			txt_employeeID.setText(Integer.toString(employeeID));
			txt_employeeID.setEditable(false);
		}
	
}
