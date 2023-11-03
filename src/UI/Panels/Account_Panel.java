package UI.Panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import CustomComponents.JPanelX;
import Data.Account;
import DataEditorUI.*;
import Enum.AccountType;
import UI.MainWindow;
import Utilities.Utility;

public class Account_Panel extends JPanelX{
	
	public DefaultTableModel model;
	public LinkedList<Account> accountList;
	private MainWindow window;

	public Account_Panel(MainWindow window){
		this.window = window;
		accountList = new LinkedList<Account>();
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 550);
		setBackground(new Color(0,0,0,0));

		JButton btn_Add = new JButton("New Account");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountEditor_Dialog dialog = new AccountEditor_Dialog(window, Account_Panel.this,"New Account", -1);
				dialog.setVisible(true);
			}
		});
		
		model = new DefaultTableModel();
		model.addColumn("SL");
		model.addColumn("Account ID");
		model.addColumn("Account Type");
		model.addColumn("Name");
		model.addColumn("Email");
		model.addColumn("EmployeeID");
		model.addColumn("");
		
		JTable table = new JTable(model) {
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
		table.getColumnModel().getColumn(6).setCellEditor(new TableEditRemove_Editor(this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 60, getWidth() - 180, 400);
				
		add(btn_Add);
		add(sp);
		
		ArrayList<String> lines = Utility.readFile("Accounts.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			accountList.add(new Account(datas[0], datas[1], AccountType.valueOf(datas[2]), datas[3], datas[4], Integer.parseInt(datas[5])));
			model.addRow(new Object[] {accountList.size(), datas[0], datas[2],datas[3], datas[4], datas[5]});
		}
	}


	public void editRow(int row) {
		Account account = accountList.get(row);
		AccountEditor_Dialog dialog = new AccountEditor_Dialog(window, this, "Edit Account" , row);
		dialog.setAccountDetails(account.getUserID(), account.getPassword(), account.getAccountType(), account.getName(), account.getEmail(), account.getEmployeeID());
		dialog.setVisible(true);
		
	}

	public void removeRow(int row) {
		model.removeRow(row);
		accountList.remove(row);
		for(int i = 0; i < accountList.size(); i++) {
			model.setValueAt(i+1, i, 0);
		}
		Utility.writeAllToFile("Accounts.ASL", false, accountList);		
	}
}
