package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Data.Account;
import DataEditorUI.*;
import TableCellCustom.TableEditRemove_Editor;
import TableCellCustom.TableEditRemove_Renderer;
import Utilities.Utility;

public class Account_Panel extends JPanelX{
	
	public DefaultTableModel model;
	public LinkedList<Account> accountList = new LinkedList<Account>();
	private MainWindow window;

	public Account_Panel(MainWindow window){
		this.window = window;
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 550);

		JButton btn_Add = new JButton("New Account");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//AccountEditor_Dialog dialog = new AccountEditor_Dialog(window, Account_Panel.this,"New Account", -1);
				//dialog.setVisible(true);
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
		
		table.setRowHeight(30);
		table.getTableHeader().setReorderingAllowed(false);
		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(5).setCellRenderer(renderer);
		table.getColumnModel().getColumn(5).setCellEditor(new TableEditRemove_Editor(model, window, this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 60, getWidth() - 180, 400);
				
		add(btn_Add);
		add(sp);
		
//		JComboBox<AccountType> x = new JComboBox<>(AccountType.values());
	}


	public void editRow(int row) {
		Account account = accountList.get(row);
//		AccountEditor_Dialog dialog = new AccountEditor_Dialog(window, this, "Edit Account" , row);
//		dialog.setAccountDetails(account.getUserID(), account.getAccountType(), account.getName(), account.getEmail(), account.getEmployeeID());
//		dialog.setVisible(true);
		
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
