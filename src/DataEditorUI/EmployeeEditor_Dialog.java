package DataEditorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Data.Account;
import Data.Employee;
import Enum.AccountType;
import Enum.InputType;
import Exception.InputException;
import UI.JPanelX;
import UI.MainWindow;
import UI.Panels.Employee_Panel;
import Utilities.Utility;

public class EmployeeEditor_Dialog extends JDialog{
	
	private JTextField txt_employeeID = new JTextField();
	private JTextField txt_employeeName = new JTextField();
	private JComboBox<String> cmbx_gender = new JComboBox<> (new String[] {"Male", "Female"});
	private JTextField txt_phoneNo = new JTextField();
	private JTextField txt_email = new JTextField();
	private JTextField txt_accountId = new JTextField();

	int A;
	String B, C, D, E, F;
	
	public EmployeeEditor_Dialog(MainWindow frame, Employee_Panel parent, String title, int row) {
		super(frame, title, true);
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lbl_employeeID = new JLabel("Employee ID:");
		JLabel lbl_employeeName = new JLabel("Employee Name:");
		JLabel lbl_gender = new JLabel("Gender:");
		JLabel lbl_phoneNo = new JLabel("Phone No:");
		JLabel lbl_email = new JLabel("Email:");
		JLabel lbl_accountId = new JLabel("Account ID:");
		
		lbl_employeeID.setBounds(50, 50, 100, 30);
		lbl_employeeName.setBounds(50, 100, 100, 30);
		lbl_gender.setBounds(50, 150, 100, 30);
		lbl_phoneNo.setBounds(50, 200, 100, 30);
		lbl_email.setBounds(50, 250, 100, 30);
		lbl_accountId.setBounds(50, 300, 100, 30);
		
		add(lbl_employeeID);
		add(lbl_employeeName);
		add(lbl_gender);
		add(lbl_phoneNo);
		add(lbl_email);
		add(lbl_accountId);
		
		txt_employeeID.setBounds(160, 50, 200, 30);
		txt_employeeName.setBounds(160, 100, 200, 30);
		cmbx_gender.setBounds(160, 150, 200, 30);
		txt_phoneNo.setBounds(160, 200, 200, 30);
		txt_email.setBounds(160, 250, 200, 30);
		txt_accountId.setBounds(160, 300, 200, 30);
		
		add(txt_employeeID);
		add(txt_employeeName);
		add(cmbx_gender);
		add(txt_phoneNo);
		add(txt_email);
		add(txt_accountId);
		
		JButton btn_submit = new JButton("Submit");
		btn_submit.setBounds(180, 350, 80, 35);
		btn_submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Employee employee = null;
				try{
					A = Utility.checkInt(txt_employeeID, lbl_employeeID);
					B = Utility.checkString(txt_employeeName, lbl_employeeName, InputType.Alphabetic);
					C = Utility.checkString(cmbx_gender, lbl_gender);
					D = Utility.checkString(txt_phoneNo, lbl_phoneNo, InputType.Alphanumeric);
					E = Utility.checkString(txt_email, lbl_email, InputType.Email);
				    F = Utility.checkString(txt_accountId, lbl_accountId, InputType.Alphanumeric);
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
		txt_accountId.setText(accountId);
	}
}
