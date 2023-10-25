package DataEditorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Data.Discount_Voucher;
import Enum.AccountType;
import Enum.InputType;
import Exception.InputException;
import UI.JPanelX;
import UI.MainWindow;
import UI.Panels.Voucher_Panel;
import Utilities.Utility;

public class VoucherEditor_Dialog extends JDialog{
	
	private JTextField txt_customer = new JTextField();
	private JTextField txt_voucherId = new JTextField();
	private JTextField txt_voucher = new JTextField();
	private JTextField txt_value = new JTextField();
	
	private String A, C;
	private int B;
	private double D;
		
	public VoucherEditor_Dialog(MainWindow frame, Voucher_Panel parent, String title, int row) {
		super(frame, title, true);
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lbl_customer = new JLabel("Customer:");
		JLabel lbl_voucherId = new JLabel("Voucher ID:");
		JLabel lbl_voucher = new JLabel("Voucher:");
		JLabel lbl_value = new JLabel("Value:");
		
		lbl_customer.setBounds(50, 50, 70, 30);
		lbl_voucherId.setBounds(50, 100, 70, 30);
		lbl_voucher.setBounds(50, 150, 70, 30);
		lbl_value.setBounds(50, 200, 70, 30);
		
		add(lbl_customer);
		add(lbl_voucherId);
		add(lbl_voucher);
		add(lbl_value);		
		
		txt_customer.setBounds(130, 50, 200, 30);
		txt_voucherId.setBounds(130, 100, 200, 30);
		txt_voucher.setBounds(130, 150, 200, 30);
		txt_value.setBounds(130, 200, 200, 30);

		add(txt_customer);
		add(txt_voucherId);
		add(txt_voucher);
		add(txt_value);
		
		JButton btn_Submit = new JButton("Submit");
		btn_Submit.setBounds(180, 260, 80, 35);
		btn_Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Discount_Voucher voucher = null;
				try {
					A = txt_customer.getText().isBlank() ? "" : Utility.checkString(txt_customer, lbl_customer, InputType.Alphabetic);
					B = Utility.checkInt(txt_voucherId, lbl_voucherId);
					C = Utility.checkString(txt_voucher, lbl_voucher, InputType.Alphanumeric);
					D = Utility.checkDouble(txt_value, lbl_value);
					voucher = new Discount_Voucher(A, B, C, D);
				} catch (InputException e2) {
					Utility.showErrorMessage(e2);
					return;
				}
				if(row != -1) {
					parent.voucherList.set(row, voucher);
					parent.model.removeRow(row);
					parent.model.insertRow(row, new Object[] {row + 1, voucher.getCustomer(), voucher.getVoucherId(), voucher.getVoucher(), voucher.getValue()});
					Utility.writeAllToFile("Discount-Vouchers.ASL", false, parent.voucherList);
				}
				else {
					parent.voucherList.add(voucher);
					parent.model.addRow(new Object[] {parent.voucherList.size(), voucher.getCustomer(), voucher.getVoucherId(), voucher.getVoucher(), voucher.getValue()});
					Utility.writeToFile("Discount-Vouchers.ASL", true, voucher);
				}
				dispose();
			}
		});	
		add(btn_Submit);
	}
	
	public void setVoucherDetails(String customer, int voucherId, String voucher, double value) {
		txt_customer.setText(customer);
		txt_voucherId.setText(Integer.toString(voucherId));
		txt_voucherId.setEditable(false);
		txt_voucher.setText(voucher);
		txt_value.setText(Double.toString(value));
	}
}
