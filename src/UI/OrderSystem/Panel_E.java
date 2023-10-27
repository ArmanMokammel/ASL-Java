package UI.OrderSystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableRemove_Editor;
import CustomCell.TableRemove_Renderer;
import Data.Order;
import Data.Payment;
import Exception.InputException;
import UI.JPanelX;
import UI.MainWindow;
import UI.Order_Screen;
import Utilities.Receipt;
import Utilities.Utility;

public class Panel_E extends JPanelX{
	
	private DefaultTableModel model ;
	
	private String A;
	private double B;
	
	public JLabel subTotal = new JLabel("0.0");
	public JLabel total = new JLabel();
	public JTextField txt_1 = new JTextField();
	public JLabel amtPaid = new JLabel("0.0");
	public JLabel amtDue = new JLabel();
	
	private double amountPaid = 0;
	
	public Panel_E() {
		setLayout(null);
				
		JPanel pnl_1 = new JPanel();
		pnl_1.setBounds(10, 0, 400, 250);
		pnl_1.setBackground(Color.lightGray);
		pnl_1.setLayout(null);
		
		JLabel lbl_subTotal = new JLabel("Sub-Total:");
		lbl_subTotal.setBounds(10, 20, 70, 30);
		
		subTotal.setBounds(80, 20, 100, 30);
		subTotal.setOpaque(true);
		
		JLabel lbl_discount = new JLabel("Discount:");
		lbl_discount.setBounds(10, 60, 70, 30);
		
		txt_1.setBounds(80, 60, 100, 30);
		
		JSeparator sp = new JSeparator();
		sp.setBounds(0, 110, 485, 10);
		
		JLabel lbl_total = new JLabel("Total:");
		lbl_total.setBounds(10, 120, 70, 30);
		
		total.setBounds(80, 120, 100, 30);
		total.setOpaque(true);
		
		JSeparator sp2 = new JSeparator();
		sp2.setBounds(0, 160, 485, 10);
		
		JLabel lbl_amtPaid = new JLabel("Amount Paid:");
		lbl_amtPaid.setBounds(10, 170, 80, 30);
		
		amtPaid.setBounds(90, 170, 100, 30);
		amtPaid.setOpaque(true);
		
		JLabel lbl_amtDue = new JLabel("Amount Due:");
		lbl_amtDue.setBounds(10, 210, 80, 30);
		
		amtDue.setBounds(90, 210, 100, 30);
		amtDue.setOpaque(true);
		
		pnl_1.add(lbl_subTotal);
		pnl_1.add(subTotal);
		pnl_1.add(lbl_discount);
		pnl_1.add(txt_1);
		pnl_1.add(sp);
		pnl_1.add(lbl_total);
		pnl_1.add(total);
		pnl_1.add(sp2);
		pnl_1.add(lbl_amtPaid);
		pnl_1.add(amtPaid);
		pnl_1.add(lbl_amtDue);
		pnl_1.add(amtDue);
		
		add(pnl_1);
		
		
		
		JPanel pnl_2 = new JPanel();
		pnl_2.setLayout(null);
		pnl_2.setBounds(430, 0, 565, 300);
		pnl_2.setBackground(Color.lightGray);
		
		JLabel lbl_10 = new JLabel("Payment Type:");
		lbl_10.setBounds(10, 20, 90, 30);
		
		ArrayList<String> options = Utility.readFile("Payment-Methods.ASL");
		
		JComboBox<String> cmbx_1 = new JComboBox<String>(options.toArray(new String[options.size()]));
		cmbx_1.setBounds(110, 20, 150, 30);
		
		JLabel lbl_11 = new JLabel("Amount:");
		lbl_11.setBounds(10, 60, 90, 30);
		
		JTextField txt_2 = new JTextField();
		txt_2.setBounds(110, 60, 150, 30);
		
		JButton btn_1 = new JButton("<html><center>"+"Add"+"<br>"+"Payment"+"</center></html>");
		btn_1.setBounds(280, 20, 85, 70);
		btn_1.setBackground(new Color(93, 130, 84));
		btn_1.setForeground(Color.white);
		btn_1.setOpaque(true);
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					A = (String)cmbx_1.getSelectedItem();
					B = Utility.checkDouble(txt_2, lbl_11);
					
					Order.addPayment(new Payment(A, B));
					amountPaid += B;
					amtPaid.setText(Double.toString(amountPaid));
					Panel_E.this.model.addRow(new Object[] {"", A, B});
				} catch (InputException e1) {
					Utility.showErrorMessage(e1);
				}
			}
		});
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if(column == 0)
					return true;
				else
					return false;
			}
		};
		model.addColumn("");
		model.addColumn("Type");
		model.addColumn("Amount");
				
		JTable table = new JTable(model);
		table.setBackground(new Color(214, 241, 216));
		table.setRowHeight(30);
		
		table.getColumnModel().getColumn(0).setCellRenderer(new TableRemove_Renderer());
		table.getColumnModel().getColumn(0).setCellEditor(new TableRemove_Editor(this));
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(Color.black);
		tableHeader.setForeground(Color.white);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 100, 465, 100);
		scrollPane.getViewport().setBackground(new Color(145, 214, 150));
		
		JButton btn_2 = new JButton("Hold");
		btn_2.setBounds(10, 260, 100, 30);
		
		JButton btn_3 = new JButton("Cancel");
		btn_3.setBounds(140, 260, 100, 30);
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order.resetOrder();
				Order_Screen.setCustomer(null);
				model.setRowCount(0);
				Panel_C.model.setRowCount(0);
				subTotal.setText("0.0");
				amtPaid.setText("0.0");
			}
		});
		
		JButton btn_4 = new JButton("Finish");
		btn_4.setBounds(250, 260, 100, 30);
		btn_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				JOptionPane.showMessageDialog(Panel_E.this, Order.generateOrderInfo());
				Receipt.generateReceipt();
				Order.incrementOrder();
				Order.resetOrder();
				Panel_A.txt_orderNo.setText(Order.getOrderNo());
			}
		});
		
		pnl_2.add(lbl_10);
		pnl_2.add(cmbx_1);
		pnl_2.add(lbl_11);
		pnl_2.add(txt_2);
		pnl_2.add(btn_1);
		pnl_2.add(scrollPane);
		pnl_2.add(btn_2);
		pnl_2.add(btn_3);
		pnl_2.add(btn_4);
		
		add(pnl_2);
	}

	@Override
	public void editRow(int row) {}

	@Override
	public void removeRow(int row) {
		amountPaid -= Order.getPayments().get(row).getAmount();
		amtPaid.setText(Double.toString(amountPaid));
		Order.removePayment(row);		
		model.removeRow(row);
		Order.setCustomer(null);
	}
}
