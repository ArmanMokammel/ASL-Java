package UI.Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import CustomComponents.JButtonT1;
import CustomComponents.JPanelX;
import Data.Customer;
import DataEditorUI.CustomerEditor_Dialog;
import Utilities.Utility;

import Enum.DiscountType;
import UI.MainWindow; 

public class Customer_Panel extends JPanelX{
	
	public DefaultTableModel model;
	public LinkedList<Customer> customerList;
	private MainWindow window;
	
	public Customer_Panel(MainWindow window){
		this.window = window;
		customerList = new LinkedList<Customer>();
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 685);
		setOpaque(false);
		
		Font f1 = new Font(null, Font.BOLD, 16);
		Font f2 = new Font(null, Font.PLAIN, 16);
		
		JButton btn_Add = new JButtonT1("New Customer", "img\\btn.png", 6);
		btn_Add.setBounds(getWidth() - 260, 6, 150, 50);
		btn_Add.setFont(new Font(null, Font.BOLD, 16));
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerEditor_Dialog dialog = new CustomerEditor_Dialog(window, Customer_Panel.this,"New Customer", -1);
				dialog.setVisible(true);
			}
		});
		
		model = new DefaultTableModel();
		model.addColumn("SL");
		model.addColumn("Customer ID");
		model.addColumn("Customer Name");
		model.addColumn("Gender");
		model.addColumn("Email");
		model.addColumn("Phone No");
		model.addColumn("Special Discount Type");
		model.addColumn("Special Discount");
		model.addColumn("");
		
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 8)
					return true;
				else
					return false;
			}
		};
		
		table.setRowHeight(40);
		table.setBackground(new Color(253, 253, 214));
		table.setFont(f2);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setBackground(new Color(117, 68, 0));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(f1);

		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(8).setCellRenderer(renderer);
		table.getColumnModel().getColumn(8).setCellEditor(new TableEditRemove_Editor(this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 76, getWidth() - 180, 600);
				
		add(btn_Add);
		add(sp);
		
		ArrayList<String> lines = Utility.readFile("Customers.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			customerList.add(new Customer(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], DiscountType.valueOf(datas[5]), Double.parseDouble(datas[6])));
			model.addRow(new Object[] {customerList.size(), datas[0], datas[1], datas[2], datas[3], datas[4], datas[5], datas[6]});
		}
	}
	
	public void editRow(int row) {
		Customer customer = (Customer) customerList.get(row);
		CustomerEditor_Dialog dialog = new CustomerEditor_Dialog(window, this, "Edit Customer Details" , row);
		dialog.setCustomerDetails(customer.getCustomerId(), customer.getCustomerName(), customer.getGender(), customer.getEmail(), customer.getPhoneNo(), customer.getSpecialDiscountType(), customer.getSpecialDiscount());
		dialog.setVisible(true);
	}
	
	public void removeRow(int row) {
		model.removeRow(row);
		customerList.remove(row);
		for(int i = 0; i < customerList.size(); i++) {
			model.setValueAt(i+1, i, 0);
		}
		Utility.writeAllToFile("Customers.ASL", false, customerList);
	}
}
