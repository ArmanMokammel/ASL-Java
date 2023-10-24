package UI.OrderSystem;

import java.awt.Color;
import java.util.LinkedList;

import Data.OrderMenuItem;
import UI.JPanelX;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableRemove_Editor;
import CustomCell.TableRemove_Renderer;

public class Panel_C extends JPanelX{
	
	private static DefaultTableModel model;
	private double total;
	private Panel_E displayPanel;
	
	public Panel_C() {
		setLayout(null);
		this.list = new LinkedList<OrderMenuItem>();
		this.displayPanel = displayPanel;
		
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
	
	public void setDisplayPanel(Panel_E displayPanel) {
		this.displayPanel = displayPanel;
	}
	
	public void addItem(OrderMenuItem ordItem) {
		this.list.add(ordItem);
		total += ordItem.getQuantity() * ordItem.getItem().getSellingPrice();
		displayPanel.lbl_2.setText(Double.toString(total));
		model.addRow(new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getItem().getSellingPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getItem().getSellingPrice()});
	}

	@Override
	public void editRow(int row) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeRow(int row) {
		OrderMenuItem item = (OrderMenuItem)list.get(row);
		total -= item.getQuantity() * item.getItem().getSellingPrice();
		this.list.remove(row);
		displayPanel.lbl_2.setText(Double.toString(total));
		model.removeRow(row);		
	}
}
