package DataEditorUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import CustomComponents.JButtonT1;
import Data.Account;
import Enum.AccountType;
import Enum.InputType;
import Exception.InputException;
import UI.*;
import UI.Panels.Account_Panel;
import Utilities.Utility;

public class AccountEditor_Dialog extends JDialog{
	
	private JTextField txt_userID = new JTextField();
	private JTextField txt_password = new JTextField();
	private JTextField txt_name = new JTextField();
	private JTextField txt_email = new JTextField();
	private JTextField txt_employeeID = new JTextField();
	private JComboBox<AccountType> cmbx_accountType = new JComboBox<>(AccountType.values());
	
	private String A, B, D, E;
	private String C;
	private int F;
	
	public AccountEditor_Dialog(MainWindow frame, Account_Panel parent, String title, int row) {
		super(frame, title, true);
		setSize(565, 597);
		setLayout(null);
		setLocationRelativeTo(null);
		
		Font f1 = new Font(null, Font.BOLD, 32);
		Font f2 = new Font(null, Font.BOLD, 18);
		Font f3 = new Font(null, Font.PLAIN, 16);
		
		JLabel Bg_Icon = new JLabel();
		ImageIcon background = new ImageIcon(Utility.getImage("img\\Editor_UI.png"));
		Bg_Icon.setIcon(background);
		Bg_Icon.setOpaque(true);
		setContentPane(Bg_Icon);
		
		JLabel lbl_title = new JLabel("Account");
		JLabel lbl_userID = new JLabel("User ID:", SwingConstants.RIGHT);
		JLabel lbl_password = new JLabel("Password:", SwingConstants.RIGHT);
		JLabel lbl_name = new JLabel("Name:", SwingConstants.RIGHT);
		JLabel lbl_employeeID = new JLabel("Employee ID:", SwingConstants.RIGHT);
		JLabel lbl_email = new JLabel("Email Address:", SwingConstants.RIGHT);
		JLabel lbl_accountType = new JLabel("Account type:", SwingConstants.RIGHT);
		
		lbl_title.setBounds(210, 40, 130, 30);
		lbl_userID.setBounds(30, 110, 140, 30);
		lbl_password.setBounds(30, 160, 140, 30);
		lbl_accountType.setBounds(30, 210, 140, 30);
		lbl_name.setBounds(30, 260, 140, 30);
		lbl_employeeID.setBounds(30, 310, 140, 30);
		lbl_email.setBounds(30, 360, 140, 30);
		
		lbl_title.setFont(f1);
		lbl_userID.setFont(f2);
		lbl_password.setFont(f2);
		lbl_accountType.setFont(f2);
		lbl_name.setFont(f2);
		lbl_employeeID.setFont(f2);
		lbl_email.setFont(f2);
		
		add(lbl_title);
		add(lbl_userID);
		add(lbl_password);
		add(lbl_name);
		add(lbl_employeeID);
		add(lbl_email);
		add(lbl_accountType);
		
		txt_userID.setBounds(180, 110, 240, 30);
		txt_password.setBounds(180, 160, 240, 30);
		cmbx_accountType.setBounds(180, 210, 240, 30);
		txt_name.setBounds(180, 260, 240, 30);
		txt_employeeID.setBounds(180, 310, 240, 30);
		txt_email.setBounds(180, 360, 240, 30);
		
		txt_userID.setFont(f3);
		txt_password.setFont(f3);
		cmbx_accountType.setFont(f3);
		txt_name.setFont(f3);
		txt_employeeID.setFont(f3);
		txt_email.setFont(f3);
		
		add(txt_userID);
		add(txt_password);
		add(txt_name);
		add(txt_employeeID);
		add(txt_email);
		add(cmbx_accountType);
		
		JButtonT1 btn_submit = new JButtonT1("Submit", "img\\btn.png", 6);
		btn_submit.setBounds(220, 430, 100, 40);
		btn_submit.setFont(new Font(null, Font.BOLD, 16));
		btn_submit.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				Account account = null;
				try{
					A = Utility.checkString(txt_userID, lbl_userID, InputType.Alphanumeric);
					B = Utility.checkString(txt_password, lbl_password, InputType.Password);
					C = Utility.checkString(cmbx_accountType, lbl_accountType);
					D = Utility.checkString(txt_name, lbl_name, InputType.Alphabetic);
					E = Utility.checkString(txt_email, lbl_email, InputType.Email);
				    F = Utility.checkInt(txt_employeeID, lbl_employeeID);
				    account = new Account(A, B, AccountType.valueOf(C), D, E, F);
				}
				catch (InputException e2) {
					Utility.showErrorMessage(e2);
					return;
				}
				if (row != -1) {
					parent.accountList.set(row, account);
					parent.model.removeRow(row);
					parent.model.insertRow(row, new Object[] {row + 1, account.getUserID(), account.getAccountType(), account.getName(), account.getEmail(), account.getEmployeeID()});
					Utility.writeAllToFile("Accounts.ASL", false, parent.accountList);
				} else {
					parent.accountList.add(account);
					parent.model.addRow(new Object[] {parent.accountList.size(), account.getUserID(), account.getAccountType(), account.getName(), account.getEmail(), account.getEmployeeID()});
					Utility.writeToFile("Accounts.ASL", true, account);
				}
				dispose();
			}
		});
		add(btn_submit);
	}
	
	public void setAccountDetails(String userID, String password, AccountType accountType, String name, String email, int employeeID) {
		txt_userID.setText(userID);
		txt_userID.setEditable(false);
		txt_password.setText(password);
		cmbx_accountType.setSelectedItem(accountType);
		txt_name.setText(name);
		txt_email.setText(email);
		txt_employeeID.setText(Integer.toString(employeeID));
	}

}
