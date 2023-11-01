package DataEditorUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
		
		JLabel lbl_userID = new JLabel("User ID:");
		JLabel lbl_password = new JLabel("Password:");
		JLabel lbl_name = new JLabel("Name:");
		JLabel lbl_employeeID = new JLabel("Employee ID:");
		JLabel lbl_email = new JLabel("Email Address:");
		JLabel lbl_accountType = new JLabel("Account type:");
		
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
		cmbx_accountType.setBounds(160, 150, 200, 30);
		txt_name.setBounds(160, 200, 200, 30);
		txt_employeeID.setBounds(160, 250, 200, 30);
		txt_email.setBounds(160, 300, 200, 30);
		
		add(txt_userID);
		add(txt_password);
		add(txt_name);
		add(txt_employeeID);
		add(txt_email);
		add(cmbx_accountType);
		
		JButton btn_submit = new JButton("Submit");
		btn_submit.setBounds(180, 350, 80, 35);
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
