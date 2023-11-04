package DataEditorUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import CustomComponents.JButtonT1;
import CustomComponents.SearchableComboBox;
import Data.Account;
import Data.Employee;
import Enum.AccountType;
import Enum.InputType;
import Exception.InputException;
import UI.MainWindow;
import UI.Panels.Employee_Panel;
import Utilities.Utility;

public class EmployeeEditor_Dialog extends JDialog{
	
	private JTextField txt_employeeID;
	private JTextField txt_employeeName;
	private JComboBox<String> cmbx_gender;
	private JTextField txt_phoneNo;
	private JTextField txt_email;
	private SearchableComboBox txt_accountId;

	int A;
	String B, C, D, E, F;
	
	public EmployeeEditor_Dialog(MainWindow frame, Employee_Panel parent, String title, int row) {
		super(frame, title, true);
		setSize(565, 597);
		setLayout(null);
		setLocationRelativeTo(null);
		
		Font f1 = new Font(null, Font.BOLD, 32);
		Font f2 = new Font(null, Font.BOLD, 18);
		Font f3 = new Font(null, Font.PLAIN, 16);
		
		txt_employeeID = new JTextField();
		txt_employeeName = new JTextField();
		cmbx_gender = new JComboBox<> (new String[] {"Male", "Female"});
		txt_phoneNo = new JTextField();
		txt_email = new JTextField();
		
		ArrayList<String> lines = Utility.readFile("Accounts.ASL");
		ArrayList<String> accountIds = new ArrayList<String>();
		for(String line: lines) {
			accountIds.add(line.split("\t")[0]);
		}
		
		txt_accountId = new SearchableComboBox(accountIds);
		
		JLabel Bg_Icon = new JLabel();
		ImageIcon background = new ImageIcon(Utility.getImage("img\\Editor_UI.png"));
		Bg_Icon.setIcon(background);
		Bg_Icon.setOpaque(true);
		setContentPane(Bg_Icon);
		
		JLabel lbl_title = new JLabel("Employee");
		JLabel lbl_employeeID = new JLabel("Employee ID:", SwingConstants.RIGHT);
		JLabel lbl_employeeName = new JLabel("Employee Name:", SwingConstants.RIGHT);
		JLabel lbl_gender = new JLabel("Gender:", SwingConstants.RIGHT);
		JLabel lbl_phoneNo = new JLabel("Phone No:", SwingConstants.RIGHT);
		JLabel lbl_email = new JLabel("Email:", SwingConstants.RIGHT);
		JLabel lbl_accountId = new JLabel("Account ID:", SwingConstants.RIGHT);
		
		lbl_title.setBounds(200, 40, 150, 30);
		lbl_employeeID.setBounds(30, 110, 155, 30);
		lbl_employeeName.setBounds(30, 160, 155, 30);
		lbl_gender.setBounds(30, 210, 155, 30);
		lbl_phoneNo.setBounds(30, 260, 155, 30);
		lbl_email.setBounds(30, 310, 155, 30);
		lbl_accountId.setBounds(30, 360, 155, 30);
		
		lbl_title.setFont(f1);
		lbl_employeeID.setFont(f2);
		lbl_employeeName.setFont(f2);
		lbl_gender.setFont(f2);
		lbl_phoneNo.setFont(f2);
		lbl_email.setFont(f2);
		lbl_accountId.setFont(f2);
		
		add(lbl_title);
		add(lbl_employeeID);
		add(lbl_employeeName);
		add(lbl_gender);
		add(lbl_phoneNo);
		add(lbl_email);
		add(lbl_accountId);
		
		txt_employeeID.setBounds(195, 110, 240, 30);
		txt_employeeName.setBounds(195, 160, 240, 30);
		cmbx_gender.setBounds(195, 210, 240, 30);
		txt_phoneNo.setBounds(195, 260, 240, 30);
		txt_email.setBounds(195, 310, 240, 30);
		txt_accountId.setBounds(195, 360, 240, 30);
		
		txt_employeeID.setFont(f3);
		txt_employeeName.setFont(f3);
		cmbx_gender.setFont(f3);
		txt_phoneNo.setFont(f3);
		txt_email.setFont(f3);
		txt_accountId.setFont(f3);
		
		add(txt_employeeID);
		add(txt_employeeName);
		add(cmbx_gender);
		add(txt_phoneNo);
		add(txt_email);
		add(txt_accountId);
		
		JButtonT1 btn_submit = new JButtonT1("Submit", "img\\btn.png", 6);
		btn_submit.setBounds(220, 430, 100, 40);
		btn_submit.setFont(new Font(null, Font.BOLD, 16));
		btn_submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Employee employee = null;
				try{
					A = Utility.checkInt(txt_employeeID, lbl_employeeID);
					B = Utility.checkString(txt_employeeName, lbl_employeeName, InputType.Alphabetic);
					C = Utility.checkString(cmbx_gender, lbl_gender);
					D = Utility.checkString(txt_phoneNo, lbl_phoneNo, InputType.Alphanumeric);
					E = Utility.checkString(txt_email, lbl_email, InputType.Email);
				    F = txt_accountId.getSelectedItem() == null || ((String)txt_accountId.getSelectedItem()).equals("") ? "None" : (String)txt_accountId.getSelectedItem();
				    employee = new Employee(A, B, C, D, E, F);
				}
				catch (InputException e2) {
					Utility.showErrorMessage(e2);
					return;
				}
				if (row != -1) {
					parent.employeeList.set(row, employee);
					parent.model.removeRow(row);
					parent.model.insertRow(row, new Object[] {row + 1, employee.getEmployeeId(), employee.getEmployeeName(), employee.getGender(), employee.getPhoneNo(), employee.getEmail(), employee.getAccountId()});
					Utility.writeAllToFile("Employees.ASL", false, parent.employeeList);
				} else {
					parent.employeeList.add(employee);
					parent.model.addRow(new Object[] {parent.employeeList.size(), employee.getEmployeeId(), employee.getEmployeeName(), employee.getGender(), employee.getPhoneNo(), employee.getEmail(), employee.getAccountId()});
					Utility.writeToFile("Employees.ASL", true, employee);
				}
				dispose();
			}
		});
		
		add(btn_submit);
	}
	
	public void setEmployeeDetails(int employeeId, String employeeName, String gender, String phoneNo, String email, String accountId) {
		txt_employeeID.setText(Integer.toString(employeeId));
		txt_employeeID.setEditable(false);
		txt_employeeName.setText(employeeName);
		cmbx_gender.setSelectedItem(gender);
		txt_phoneNo.setText(phoneNo);
		txt_email.setText(email);
		txt_accountId.setSelectedItem(accountId.equals("None") ? null : accountId);
	}
}
