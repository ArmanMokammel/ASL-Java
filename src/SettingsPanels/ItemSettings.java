package SettingsPanels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableRemove_Editor;
import CustomCell.TableRemove_Renderer;
import UI.JPanelX;
import Utilities.Utility;

public class ItemSettings extends JPanelX{
	
	private DefaultTableModel model;
	private LinkedList<String> categories;
	
	public ItemSettings() {
		setBackground(Color.red);
		setBounds(200, 0, 500, 600);
		setLayout(null);
		
		JButton btn_Add = new JButton("New Category");
		btn_Add.setBounds(320, 30, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ItemSettings.this.model.addRow(new Object[] {model.getRowCount() + 1, ""});		
			}
		});
		
		JButton btn_Save = new JButton("Save");
		btn_Save.setBounds(200, 500, 70, 40);
		btn_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categories = new LinkedList<String>();
				int count = ItemSettings.this.model.getRowCount();
				for(int i = 0; i < count; i++) {
					if(i != count-1)
						categories.add((String)model.getValueAt(i, 1) + "\n");
					else
						categories.add((String)model.getValueAt(i, 1));
				}
				Utility.writeAllToFile("Item-Categories.ASL", false, categories);
				JOptionPane.showMessageDialog(ItemSettings.this, "Saved Successfully!");
			}
		});
		
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 0)
					return false;
				else
					return true;
			}
		};
		model.addColumn("Serial");
		model.addColumn("Category");
		model.addColumn("");
		
		JTable table = new JTable(model);
		table.setBackground(new Color(253, 253, 214));
		table.setRowHeight(30);
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setBackground(new Color(117, 68, 0));
		tableHeader.setForeground(Color.white);
		table.getColumnModel().getColumn(2).setCellRenderer(new TableRemove_Renderer());
		table.getColumnModel().getColumn(2).setCellEditor(new TableRemove_Editor(this));
		table.putClientProperty("terminateEditOnFocusLost", true);

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(50, 80, 400, 400);
		
		LinkedList<String> categoryList = new LinkedList<String>(Utility.readFile("Item-Categories.ASL"));
		int count = 0;
		for(String category : categoryList) {
			count++;
			model.addRow(new Object[] {count, category});
		}
		
		add(btn_Add);
		add(sp);
		add(btn_Save);
	}

	public void editRow(int row) {}

	@Override
	public void removeRow(int row) {
		model.removeRow(row);
		for(int i = 0; i < model.getRowCount(); i++) {
			model.setValueAt(i+1, i, 0);
		}
	}
}
