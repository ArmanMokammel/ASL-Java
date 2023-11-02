package DataEditorUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import CustomComponents.JButtonT1;
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
		
		JLabel lbl_title = new JLabel("Item");
		JLabel lbl_customerID = new JLabel("Customer ID:", SwingConstants.RIGHT);
		JLabel lbl_customerName = new JLabel("Customer Name:", SwingConstants.RIGHT);
		JLabel lbl_gender = new JLabel("Gender:", SwingConstants.RIGHT);
		JLabel lbl_email = new JLabel("Email:", SwingConstants.RIGHT);
		JLabel lbl_phoneNo = new JLabel("Phone No:", SwingConstants.RIGHT);
		JLabel lbl_discountType = new JLabel("Discount Type:", SwingConstants.RIGHT);
		JLabel lbl_specialDiscount = new JLabel("Special Discount:", SwingConstants.RIGHT);
		
		lbl_title.setBounds(240, 25, 80, 30);
		lbl_customerID.setBounds(30, 80, 140, 30);
		lbl_customerName.setBounds(30, 130, 140, 30);
		lbl_gender.setBounds(30, 180, 140, 30);
		lbl_email.setBounds(30, 230, 140, 30);
		lbl_phoneNo.setBounds(30, 280, 140, 30);
		lbl_discountType.setBounds(30, 330, 140, 30);
		lbl_specialDiscount.setBounds(30, 380, 140, 30);
		
		lbl_title.setFont(f1);
		lbl_customerID.setFont(f2);
		lbl_customerName.setFont(f2);
		lbl_gender.setFont(f2);
		lbl_email.setFont(f2);
		lbl_phoneNo.setFont(f2);
		lbl_discountType.setFont(f2);
		lbl_specialDiscount.setFont(f2);
		
		add(lbl_title);
		add(lbl_customerID);
		add(lbl_customerName);
		add(lbl_gender);
		add(lbl_email);
		add(lbl_phoneNo);
		add(lbl_discountType);
		add(lbl_specialDiscount);
		
		txt_customerID.setBounds(180, 80, 240, 30);
		txt_customerName.setBounds(180, 130, 240, 30);
		cmbx_gender.setBounds(180, 180, 240, 30);
		txt_email.setBounds(180, 230, 240, 30);
		txt_phoneNo.setBounds(180, 280, 240, 30);
		cmbx_discountType.setBounds(180, 330, 240, 30);
		txt_specialDiscount.setBounds(180, 380, 240, 30);
		
		txt_customerID.setFont(f3);
		txt_customerName.setFont(f3);
		cmbx_gender.setFont(f3);
		txt_email.setFont(f3);
		txt_phoneNo.setFont(f3);
		cmbx_discountType.setFont(f3);
		txt_specialDiscount.setFont(f3);
		
		add(txt_customerID);
		add(txt_customerName);
		add(cmbx_gender);
		add(txt_email);
		add(txt_phoneNo);
		add(cmbx_discountType);
		add(txt_specialDiscount);
		
		JButtonT1 btn_submit = new JButtonT1("Submit", "img\\btn.png", 6);
		btn_submit.setBounds(220, 440, 100, 40);
		btn_submit.setFont(new Font(null, Font.BOLD, 16));
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
