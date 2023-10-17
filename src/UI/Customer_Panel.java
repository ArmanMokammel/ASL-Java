package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Data.Account;
import Data.Customer;
import DataEditorUI.AccountEditor_Dialog;
import DataEditorUI.CustomerEditor_Dialog;
import Enum.AccountType;
import TableCellCustom.TableEditRemove_Editor;
import TableCellCustom.TableEditRemove_Renderer;
import Utilities.Utility;

import Enum.DiscountType; 

public class Customer_Panel extends JPanelX{
	
	public DefaultTableModel model;
	public LinkedList<Customer> customerList = new LinkedList<Customer>();
	private MainWindow window;
	
	public Customer_Panel(MainWindow window){
		this.window = window;
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 550);
		
		JButton btn_Add = new JButton("New Coustomer");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerEditor_Dialog dialog = new CustomerEditor_Dialog(window, Customer_Panel.this,"New Customer", -1);
				dialog.setVisible(true);
			}
		});
		
		model = new DefaultTableModel();
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
				if(column == 7)
					return true;
				else
					return false;
			}
		};
		
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(7).setCellRenderer(renderer);
		table.getColumnModel().getColumn(7).setCellEditor(new TableEditRemove_Editor(model, window, this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 60, getWidth() - 180, 400);
				
		add(btn_Add);
		add(sp);
		
		ArrayList<String> lines = Utility.readFile("Customer.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			customerList.add(new Customer(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], DiscountType.valueOf(datas[5]), Integer.parseInt(datas[6])));
			model.addRow(new Object[] {customerList.size(), Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], DiscountType.valueOf(datas[5]), Integer.parseInt(datas[6])});
		}
	}
	
	public void editRow(int row) {
		
	}
	
	public void removeRow(int row) {
			
	}

}
