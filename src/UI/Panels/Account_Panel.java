package UI.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import Data.Account;
import Data.Discount_Voucher;
import DataEditorUI.*;
import Enum.AccountType;
import UI.JPanelX;
import UI.MainWindow;
import Utilities.Utility;

public class Account_Panel extends JPanelX{
	
	private MainWindow window;

	public Account_Panel(MainWindow window){
		this.window = window;
		this.list = new LinkedList<Account>();
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 550);

		JButton btn_Add = new JButton("New Account");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountEditor_Dialog dialog = new AccountEditor_Dialog(window, Account_Panel.this,"New Account", -1);
				dialog.setVisible(true);
			}
		});
		
		this.model = new DefaultTableModel();
		this.model.addColumn("SL");
		this.model.addColumn("Account ID");
		this.model.addColumn("Account Type");
		this.model.addColumn("Name");
		this.model.addColumn("Email");
		this.model.addColumn("EmployeeID");
		this.model.addColumn("");
		
		JTable table = new JTable(this.model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 6)
					return true;
				else
					return false;
			}
		};
		
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(6).setCellRenderer(renderer);
		table.getColumnModel().getColumn(6).setCellEditor(new TableEditRemove_Editor(model, window, this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 60, getWidth() - 180, 400);
				
		add(btn_Add);
		add(sp);
		
		ArrayList<String> lines = Utility.readFile("Accounts.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			this.list.add(new Account(datas[0], datas[1], AccountType.valueOf(datas[2]), datas[3], datas[4], Integer.parseInt(datas[5])));
			this.model.addRow(new Object[] {this.list.size(), datas[0], AccountType.valueOf(datas[2]),datas[3], datas[4], Integer.parseInt(datas[5])});
		}
	}


	public void editRow(int row) {
		Account account = (Account) this.list.get(row);
		AccountEditor_Dialog dialog = new AccountEditor_Dialog(window, this, "Edit Account" , row);
		dialog.setAccountDetails(account.getUserID(), account.getPassword(), account.getAccountType(), account.getName(), account.getEmail(), account.getEmployeeID());
		dialog.setVisible(true);
		
	}

	public void removeRow(int row) {
		this.model.removeRow(row);
		this.list.remove(row);
		for(int i = 0; i < this.list.size(); i++) {
			this.model.setValueAt(i+1, i, 0);
		}
		Utility.writeAllToFile("Accounts.ASL", false, this.list);		
	}
}
