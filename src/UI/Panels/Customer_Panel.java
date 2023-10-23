package UI.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import Data.Customer;
import DataEditorUI.CustomerEditor_Dialog;
import Utilities.Utility;

import Enum.DiscountType;
import UI.JPanelX;
import UI.MainWindow; 

public class Customer_Panel extends JPanelX{
	
	private MainWindow window;
	
	public Customer_Panel(MainWindow window){
		this.window = window;
		this.list = new LinkedList<Customer>();
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 550);
		
		JButton btn_Add = new JButton("New Customer");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerEditor_Dialog dialog = new CustomerEditor_Dialog(window, Customer_Panel.this,"New Customer", -1);
				dialog.setVisible(true);
			}
		});
		
		this.model = new DefaultTableModel();
		this.model.addColumn("SL");
		this.model.addColumn("Customer ID");
		this.model.addColumn("Customer Name");
		this.model.addColumn("Gender");
		this.model.addColumn("Email");
		this.model.addColumn("Phone No");
		this.model.addColumn("Special Discount Type");
		this.model.addColumn("Special Discount");
		this.model.addColumn("");
		
		JTable table = new JTable(this.model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 8)
					return true;
				else
					return false;
			}
		};
		
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(8).setCellRenderer(renderer);
		table.getColumnModel().getColumn(8).setCellEditor(new TableEditRemove_Editor(model, window, this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 60, getWidth() - 180, 400);
				
		add(btn_Add);
		add(sp);
		
		ArrayList<String> lines = Utility.readFile("Customers.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			this.list.add(new Customer(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], DiscountType.valueOf(datas[5]), Double.parseDouble(datas[6])));
			this.model.addRow(new Object[] {this.list.size(), Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], DiscountType.valueOf(datas[5]), Double.parseDouble(datas[6])});
		}
	}
	
	public void editRow(int row) {
		Customer customer = (Customer) this.list.get(row);
		CustomerEditor_Dialog dialog = new CustomerEditor_Dialog(window, this, "Edit Customer Details" , row);
		dialog.setCustomerDetails(customer.getCustomerId(), customer.getCustomerName(), customer.getGender(), customer.getEmail(), customer.getPhoneNo(), customer.getSpecialDiscountType(), customer.getSpecialDiscount());
		dialog.setVisible(true);
	}
	
	public void removeRow(int row) {
		this.model.removeRow(row);
		this.list.remove(row);
		for(int i = 0; i < this.list.size(); i++) {
			this.model.setValueAt(i+1, i, 0);
		}
		Utility.writeAllToFile("Customers.ASL", false, this.list);
	}
}
