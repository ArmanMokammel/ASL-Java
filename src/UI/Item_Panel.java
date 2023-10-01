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
import Data.Discount_Voucher;
import Data.MenuItem;
import DataEditorUI.AccountEditor_Dialog;
import TableCellCustom.TableEditRemove_Editor;
import TableCellCustom.TableEditRemove_Renderer;
import Utilities.Utility;

public class Item_Panel extends JPanelX{
	
	public DefaultTableModel model;
	public LinkedList<MenuItem> itemList = new LinkedList<MenuItem>();
	private MainWindow window;
	
	public Item_Panel(MainWindow window) {
		this.window = window;
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 550);
		
		JButton btn_Add = new JButton("New Item");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				MenuItemEditor_Dialog dialog = new MenuItemEditor_Dialog(window, Item_Panel.this,"New Item", -1);
//				dialog.setVisible(true);
			}
		});
		
		model = new DefaultTableModel();
		model.addColumn("SL");
		model.addColumn("Item ID");
		model.addColumn("Item Name");
		model.addColumn("Cost Price");
		model.addColumn("Selling Price");
		model.addColumn("");
		
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 5)
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
		
		ArrayList<String> lines = Utility.readFile("Menu-Items.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			itemList.add(new MenuItem(Integer.parseInt(datas[0]), datas[1], Double.parseDouble(datas[2]), Double.parseDouble(datas[3])));
			model.addRow(new Object[] {itemList.size(), Integer.parseInt(datas[0]), datas[1], Double.parseDouble(datas[2]), Double.parseDouble(datas[3])});
		}
	}

	public void editRow(int row) {
		MenuItem menuItem = itemList.get(row);
//		MenuItemEditor_Dialog dialog = new MenuItemEditor_Dialog(window, this, "Edit Item" , row);
//		dialog.setItemDetails(menuItem.getItemId(), menuItem.getItemName(), menuItem.getCostPrice(), menuItem.getSellingPrice());
//		dialog.setVisible(true);
	}

	public void removeRow(int row) {
		model.removeRow(row);
		itemList.remove(row);
		for(int i = 0; i < itemList.size(); i++) {
			model.setValueAt(i+1, i, 0);
		}
		Utility.writeAllToFile("Menu-Items.ASL", false, itemList);		
	}
}
