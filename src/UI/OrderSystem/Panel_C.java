package UI.OrderSystem;

import java.awt.Color;

import Data.MenuItem;
import Data.OrderMenuItem;
import UI.JPanelX;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableRemove_Editor;
import CustomCell.TableRemove_Renderer;

public class Panel_C extends JPanelX{
	
	private static DefaultTableModel model;
	
	public Panel_C() {
		setLayout(null);
		
		model = new DefaultTableModel();
		model.addColumn("");
		model.addColumn("Item ID");
		model.addColumn("Item Name");
		model.addColumn("Price");
		model.addColumn("Discounted Price");
		model.addColumn("Quantity");
		model.addColumn("Total");
		
		JTable table = new JTable(model);
		table.setBackground(new Color(253, 253, 214));
		table.setRowHeight(30);
		table.getColumnModel().getColumn(0).setCellRenderer(new TableRemove_Renderer());
		table.getColumnModel().getColumn(0).setCellEditor(new TableRemove_Editor(this));
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setBackground(new Color(0, 51, 118));
		tableHeader.setForeground(Color.white);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(20, 20, 1000, 450);
						
		add(sp);
	}
	
	public static void addItem(OrderMenuItem ordItem) {
		model.addRow(new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getItem().getSellingPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getItem().getSellingPrice()});
	}

	@Override
	public void editRow(int row) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRow(int row) {
		// TODO Auto-generated method stub
		
	}
}
