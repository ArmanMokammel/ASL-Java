package DataEditorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Data.Customer;
import Enum.DiscountType;
import Enum.InputType;
import Exception.InputException;
import UI.CustomerWindow;
import UI.Panels.Customer_Panel;
import Utilities.Utility;

public class CustomerEditor_Dialog extends JDialog{
	
	private JTextField txt_customerID = new JTextField();
	public JTextField txt_customerName = new JTextField();
	private JComboBox<String> cmbx_gender = new JComboBox<> (new String[] {"Male", "Female"});
	private JTextField txt_email = new JTextField();
	private JTextField txt_phoneNo = new JTextField();
	private JComboBox<DiscountType> cmbx_discountType = new JComboBox<> (DiscountType.values());
	private JTextField txt_specialDiscount = new JTextField();
	
	private int A;
	private String B, C, D, E, F;
	private double G;
	
	private DefaultTableModel model;
	private LinkedList<Customer> customerList;
	
	public CustomerEditor_Dialog(JFrame frame, CustomerWindow parent, String title, int row) {
		super(frame, title, true);
		this.model = parent.model;
		this.customerList = parent.customerList;
		showUI(row);
	}
	
	public CustomerEditor_Dialog(JFrame frame, Customer_Panel parent, String title, int row) {
		super(frame, title, true);
		this.model = parent.model;
		this.customerList = parent.customerList;
		showUI(row);
	}
	
	public void showUI(int row) {
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lbl_customerID = new JLabel("Customer ID:");
		JLabel lbl_customerName = new JLabel("Customer Name:");
		JLabel lbl_gender = new JLabel("Gender:");
		JLabel lbl_email = new JLabel("Email:");
		JLabel lbl_phoneNo = new JLabel("Phone No:");
		JLabel lbl_discountType = new JLabel("Discount Type:");
		JLabel lbl_specialDiscount = new JLabel("Special Discount:");
		
		lbl_customerID.setBounds(50,  50,  100,  30);
		lbl_customerName.setBounds(50,  100,  100,  30);
		lbl_gender.setBounds(50,  150,  100,  30);
		lbl_email.setBounds(50,  200,  100,  30);
		lbl_phoneNo.setBounds(50,  250,  100,  30);
		lbl_discountType.setBounds(50,  300,  100,  30);
		lbl_specialDiscount.setBounds(50,  350,  100,  30);
		
		add(lbl_customerID);
		add(lbl_customerName);
		add(lbl_gender);
		add(lbl_email);
		add(lbl_phoneNo);
		add(lbl_discountType);
		add(lbl_specialDiscount);
		
		txt_customerID.setBounds(160,  50,  200,  30);
		txt_customerName.setBounds(160,  100,  200,  30);
		cmbx_gender.setBounds(160,  150,  200,  30);
		txt_email.setBounds(160,  200,  200,  30);
		txt_phoneNo.setBounds(160,  250,  200,  30);
		cmbx_discountType.setBounds(160,  300,  200,  30);
		txt_specialDiscount.setBounds(160,  350,  200,  30);
		
		add(txt_customerID);
		add(txt_customerName);
		add(cmbx_gender);
		add(txt_email);
		add(txt_phoneNo);
		add(cmbx_discountType);
		add(txt_specialDiscount);
		
		JButton btn_submit = new JButton("Submit");
		btn_submit.setBounds(200,  400,  80,  35);
		btn_submit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Customer customer = null;
				try {
					A = Utility.checkInt(txt_customerID, lbl_customerID);
					B = Utility.checkString(txt_customerName, lbl_customerName, InputType.Alphabetic);
					C = Utility.checkString(cmbx_gender, lbl_gender);
					D = Utility.checkString(txt_email, lbl_email, InputType.Email);
					E = Utility.checkString(txt_phoneNo, lbl_phoneNo, InputType.Alphanumeric);
					F = Utility.checkString(cmbx_discountType, lbl_discountType);
					G = Utility.checkDouble(txt_specialDiscount, lbl_specialDiscount);
					customer = new Customer(A, B, C, D, E, DiscountType.valueOf(F), G);
					
				} catch(InputException e2) {
					Utility.showErrorMessage(e2);
					return;
				}
				if (row != -1) {
					customerList.set(row, customer);
					model.removeRow(row);
					model.insertRow(row, new Object[] {row + 1, customer.getCustomerId(), customer.getCustomerName(), customer.getGender(), customer.getEmail(), customer.getPhoneNo(), customer.getSpecialDiscountType(), customer.getSpecialDiscount()});
					Utility.writeAllToFile("Customers.ASL", false, customerList);
				} else {
					customerList.add(customer);
					model.addRow(new Object[] {customerList.size(), customer.getCustomerId(), customer.getCustomerName(), customer.getGender(), customer.getEmail(), customer.getPhoneNo(), customer.getSpecialDiscountType(), customer.getSpecialDiscount()});
					Utility.writeToFile("Customers.ASL", true, customer);
				}
				dispose();
			}
		});
		add(btn_submit);
	}
	
	public void setCustomerDetails(int customerId, String customerName, String gender, String email, String phoneNo, DiscountType specialDiscountType, double specialDiscount) {
		txt_customerID.setText(Integer.toString(customerId));
		txt_customerID.setEditable(false);
		txt_customerName.setText(customerName);
		cmbx_gender.setSelectedItem(gender);
		txt_email.setText(email);
		txt_phoneNo.setText(phoneNo);
		cmbx_discountType.setSelectedItem(specialDiscountType);
		txt_specialDiscount.setText(Double.toString(specialDiscount));
	}
}
