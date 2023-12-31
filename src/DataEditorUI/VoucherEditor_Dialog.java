package DataEditorUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import CustomComponents.JButtonT1;
import Data.Discount_Voucher;
import Enum.InputType;
import Exception.InputException;
import UI.MainWindow;
import UI.Panels.Voucher_Panel;
import Utilities.Utility;

public class VoucherEditor_Dialog extends JDialog{
	
	private JTextField txt_voucherId = new JTextField();
	private JTextField txt_voucher = new JTextField();
	private JTextField txt_value = new JTextField();
	
	private String B;
	private int A;
	private double C;
		
	public VoucherEditor_Dialog(MainWindow frame, Voucher_Panel parent, String title, int row) {
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
		
		JLabel lbl_title = new JLabel("Voucher");
		JLabel lbl_voucherId = new JLabel("Voucher ID:", SwingConstants.RIGHT);
		JLabel lbl_voucher = new JLabel("Voucher:", SwingConstants.RIGHT);
		JLabel lbl_value = new JLabel("Value:", SwingConstants.RIGHT);
		
		lbl_title.setFont(f1);
		lbl_voucherId.setFont(f2);
		lbl_voucher.setFont(f2);
		lbl_value.setFont(f2);
		
		lbl_title.setBounds(210, 60, 130, 30);
		lbl_voucherId.setBounds(30, 200, 140, 30);
		lbl_voucher.setBounds(30, 250, 140, 30);
		lbl_value.setBounds(30, 300, 140, 30);
		
		add(lbl_title);
		add(lbl_voucherId);
		add(lbl_voucher);
		add(lbl_value);		
		
		txt_voucherId.setBounds(180, 200, 240, 30);
		txt_voucher.setBounds(180, 250, 240, 30);
		txt_value.setBounds(180, 300, 240, 30);
		
		txt_voucherId.setFont(f3);
		txt_voucher.setFont(f3);
		txt_value.setFont(f3);

		add(txt_voucherId);
		add(txt_voucher);
		add(txt_value);
		
		JButtonT1 btn_Submit = new JButtonT1("Submit", "img\\btn.png", 6);
		btn_Submit.setBounds(220, 400, 100, 40);
		btn_Submit.setFont(new Font(null, Font.BOLD, 16));
		btn_Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Discount_Voucher voucher = null;
				try {
					A = Utility.checkInt(txt_voucherId, lbl_voucherId);
					B = Utility.checkString(txt_voucher, lbl_voucher, InputType.Alphanumeric);
					C = Utility.checkDouble(txt_value, lbl_value);
					voucher = new Discount_Voucher(A, B, C);
				} catch (InputException e2) {
					Utility.showErrorMessage(e2);
					return;
				}
				if(row != -1) {
					parent.voucherList.set(row, voucher);
					parent.model.removeRow(row);
					parent.model.insertRow(row, new Object[] {row + 1, voucher.getVoucherId(), voucher.getVoucher(), voucher.getValue()});
					Utility.writeAllToFile("Discount-Vouchers.ASL", false, parent.voucherList);
				}
				else {
					parent.voucherList.add(voucher);
					parent.model.addRow(new Object[] {parent.voucherList.size(), voucher.getVoucherId(), voucher.getVoucher(), voucher.getValue()});
					Utility.writeToFile("Discount-Vouchers.ASL", true, voucher);
				}
				dispose();
			}
		});	
		add(btn_Submit);
	}
	
	public void setVoucherDetails(int voucherId, String voucher, double value) {
		txt_voucherId.setText(Integer.toString(voucherId));
		txt_voucherId.setEditable(false);
		txt_voucher.setText(voucher);
		txt_value.setText(Double.toString(value));
	}
}
