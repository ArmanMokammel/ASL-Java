package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import Data.MenuItem;
import DataEditorUI.MenuItemEditor_Dialog;
import Enum.DiscountType;
import Utilities.Utility;

public class Item_Panel extends JPanelX{
	
	public DefaultTableModel model;
	public LinkedList<MenuItem> itemList = new LinkedList<MenuItem>();
	private MainWindow window;
	public HashMap<String, ArrayList<MenuItem>> items = new HashMap<String, ArrayList<MenuItem>>();
	public JComboBox<String> cmbx_ItemCategory;
	
	public Item_Panel(MainWindow window) {
		this.window = window;
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 550);
		
		cmbx_ItemCategory = new JComboBox<String>();
		cmbx_ItemCategory.setBounds(70, 0, 200, 40);
		
		ArrayList<String> categoryList = Utility.readFile("Item-Categories.ASL");
		
		ArrayList<String> lines = Utility.readFile("Menu-Items.ASL");
		
		for(String category : categoryList) {
			cmbx_ItemCategory.addItem(category);
			ArrayList<MenuItem> itm = new ArrayList<MenuItem>();
			for(String line: lines) {
				String[] datas = line.split("\t");
				if(category.equals(datas[1])) {
					itm.add(new MenuItem(Integer.parseInt(datas[0]), datas[1], datas[2], Double.parseDouble(datas[3]), Double.parseDouble(datas[4]), DiscountType.valueOf(datas[5]), Double.parseDouble(datas[6])));
				}
			}
			items.put(category, itm);
		}
		
		cmbx_ItemCategory.setSelectedItem(null);
		
		
		JButton btn_Add = new JButton("New Item");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuItemEditor_Dialog dialog = new MenuItemEditor_Dialog(window, Item_Panel.this,"New Item", -1);
				dialog.setVisible(true);
			}
		});
		
		model = new DefaultTableModel();
		model.addColumn("SL");
		model.addColumn("Item ID");
		model.addColumn("Item Name");
		model.addColumn("Cost Price");
		model.addColumn("Selling Price");
		model.addColumn("Discount");
		model.addColumn("DiscountType");
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
		
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(7).setCellRenderer(renderer);
		table.getColumnModel().getColumn(7).setCellEditor(new TableEditRemove_Editor(model, window, this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 60, getWidth() - 180, 400);
				
		cmbx_ItemCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemList.clear();
				model.setRowCount(0);
				ArrayList<MenuItem> itm = items.get((String)cmbx_ItemCategory.getSelectedItem());
				itemList.addAll(itm);
				int sl = 1;
				for(MenuItem i : itm) {
					model.addRow(new Object[] {sl, i.getItemId(), i.getItemName(), i.getCostPrice(), i.getSellingPrice(), i.getDiscountValue(), i.getDiscountType()});
					sl++;
				}
			}
		});
		add(cmbx_ItemCategory);
		add(btn_Add);
		add(sp);
	}

	public void editRow(int row) {
		MenuItem menuItem = itemList.get(row);
		MenuItemEditor_Dialog dialog = new MenuItemEditor_Dialog(window, this, "Edit Item" , row);
		dialog.setItemDetails(menuItem.getItemId(), menuItem.getCategory(), menuItem.getItemName(), menuItem.getCostPrice(), menuItem.getSellingPrice());
		dialog.setVisible(true);
	}

	public void removeRow(int row) {
		model.removeRow(row);
		itemList.remove(row);
		for(int i = 0; i < itemList.size(); i++) {
			model.setValueAt(i+1, i, 0);
		}
		items.put((String)cmbx_ItemCategory.getSelectedItem(), new ArrayList<MenuItem>(itemList));
		File file = new File("Menu-Items.ASL");
		file.delete();
		for(Map.Entry<String, ArrayList<MenuItem>> itms : items.entrySet()) {
			Utility.writeAllToFile("Menu-Items.ASL", true, itms.getValue());
		}		
	}
}
