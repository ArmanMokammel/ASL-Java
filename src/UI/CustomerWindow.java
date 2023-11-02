package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Data.Customer;
import Data.OrderController;
import DataEditorUI.CustomerEditor_Dialog;
import Enum.DiscountType;
import UI.OrderSystem.Panel_B;
import UI.OrderSystem.Panel_E;
import Utilities.Utility;

public class CustomerWindow extends JDialog{
	
	public LinkedList<Customer> customerList;
	private ArrayList<Customer> entriesFiltered;
	public DefaultTableModel model;
	private Customer selCustomer;
	
	public CustomerWindow(JFrame window, boolean isNewCustomer) {
		super(window, "Customer", true);
		setSize(1000, 800);
		setLayout(null);
		setLocationRelativeTo(null);
		
		customerList = new LinkedList<Customer>();
		entriesFiltered = new ArrayList<Customer>();
		
		Font f1 = new Font(null, Font.BOLD, 40);
		
		JLabel lbl_title = new JLabel("Customer Search");
		lbl_title.setBounds(330, 20, 350, 50);
		lbl_title.setFont(f1);
		
		JPanel pnl = new JPanel();
		pnl.setBounds(5, 90, 750, 670);
		pnl.setBackground(Color.gray);
		pnl.setLayout(null);
		
		JLabel lbl_2 = new JLabel("Search:");
		lbl_2.setBounds(5, 0, 50, 30);
		
		JTextField txt_1 = new JTextField();
		txt_1.setBounds(55, 0, 695, 30);
		txt_1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						comboFilter(txt_1.getText());
					}
				});
			}
		});
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		customerList = new LinkedList<Customer>();
		model.addColumn("SL");
		model.addColumn("Customer ID");
		model.addColumn("Customer Name");
		model.addColumn("Gender");
		model.addColumn("Email");
		model.addColumn("Phone No");
		
		ArrayList<String> lines = Utility.readFile("Customers.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			customerList.add(new Customer(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], DiscountType.valueOf(datas[5]), Double.parseDouble(datas[6])));
			model.addRow(new Object[] {customerList.size(), Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], DiscountType.valueOf(datas[5]), Double.parseDouble(datas[6])});
		}
		entriesFiltered = new ArrayList<Customer>(customerList);
		
		JTable table = new JTable(model);
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(5, 30, 735, 625);
		
		pnl.add(lbl_2);
		pnl.add(txt_1);
		pnl.add(sp);
		
		JPanel pnl2 = new JPanel();
		pnl2.setBounds(755, 90, 230, 670);
		pnl2.setBackground(Color.blue);
		pnl2.setLayout(null);
		
		JButton btn_1 = new JButton("Select Customer");
		btn_1.setBounds(5, 5, 215, 70);
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					selCustomer = entriesFiltered.get(table.getSelectedRow());
					OrderController.getOrder().setCustomer(selCustomer);
					Order_Screen.setCustomer(selCustomer);
					
					double discountVal = 0;
					
					if(selCustomer.getSpecialDiscountType() == DiscountType.Value)
						discountVal = OrderController.getOrder().getSubTotal() - selCustomer.getSpecialDiscount();
//						OrderController.getOrder().setTotal(OrderController.getOrder().getSubTotal() - selCustomer.getSpecialDiscount());
					else if (selCustomer.getSpecialDiscountType() == DiscountType.Percentage)
						discountVal = Math.round((OrderController.getOrder().getSubTotal() * (100 - selCustomer.getSpecialDiscount()) / 100.0) * 100.0) / 100.0;
//						OrderController.getOrder().setTotal(Math.round((OrderController.getOrder().getSubTotal() * (100 - selCustomer.getSpecialDiscount()) / 100.0) * 100.0) / 100.0);
					else
						discountVal = OrderController.getOrder().getSubTotal();
//						OrderController.getOrder().setTotal(OrderController.getOrder().getSubTotal());
					
					OrderController.getOrder().setTotal(discountVal);
					OrderController.getOrder().setAmountDue(OrderController.getOrder().getTotal() - OrderController.getOrder().getAmountPaid());

					Panel_E.discount.setText(Double.toString(OrderController.getOrder().getSubTotal() - discountVal));
					Panel_E.total.setText(Double.toString(OrderController.getOrder().getTotal()));
					Panel_E.amtDue.setText(Double.toString(OrderController.getOrder().getAmountDue()));
					dispose();
				}
				
			}
		});
		
		JButton btn_2 = new JButton("Edit Customer");
		btn_2.setBounds(5, 80, 215, 70);
		btn_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1)
					return;
				
				int row;
				selCustomer = entriesFiltered.get(table.getSelectedRow());
				
				for(int i = 0; i < customerList.size(); i++) {
					if(customerList.get(i).getCustomerId() == selCustomer.getCustomerId()) {
						row = i;
						CustomerEditor_Dialog dlg = new CustomerEditor_Dialog(window, CustomerWindow.this, getTitle(), row);
						dlg.setCustomerDetails(selCustomer.getCustomerId(), selCustomer.getCustomerName(), selCustomer.getGender(), selCustomer.getEmail(), selCustomer.getPhoneNo(), selCustomer.getSpecialDiscountType(), selCustomer.getSpecialDiscount());
						comboFilter("");
						dlg.setVisible(true);
					}
				}
				
			}
		});
		
		JButton btn_3 = new JButton("New Customer");
		btn_3.setBounds(5, 160, 215, 70);
		btn_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerEditor_Dialog dlg = new CustomerEditor_Dialog(window, CustomerWindow.this, "New Customer", -1);
				dlg.setVisible(true);				
			}
		});
		
		pnl2.add(btn_1);
		pnl2.add(btn_2);
		pnl2.add(btn_3);
		
		add(lbl_title);
		add(pnl);
		add(pnl2);
		
		
		if(isNewCustomer) {
			Thread t = new Thread() {
				public void run() {
					CustomerEditor_Dialog dlg = new CustomerEditor_Dialog(window, CustomerWindow.this, "New Customer", -1);
					dlg.setVisible(true);
				}
			};
			t.start();
		}
		setVisible(true);
	}
	
	
	public void comboFilter(String enteredText) {
		entriesFiltered.clear();
		model.setRowCount(0);

		for (Customer customer : customerList) {
			if (customer.getPhoneNo().startsWith(enteredText)) {
				entriesFiltered.add(customer);
				model.addRow(new Object[] {entriesFiltered.size(), customer.getCustomerId(), customer.getCustomerName(), customer.getGender(), customer.getEmail(), customer.getPhoneNo()});
			}
		}
	}
}
