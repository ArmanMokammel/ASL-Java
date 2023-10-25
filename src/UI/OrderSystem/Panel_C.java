package UI.OrderSystem;

import java.awt.Color;

import Data.Order;
import Data.OrderMenuItem;
import UI.JPanelX;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;

public class Panel_C extends JPanelX{
	
	private static DefaultTableModel model;
	private Panel_E displayPanel;
	
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
		
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 0)
					return true;
				else
					return false;
			}
		};
		table.setBackground(new Color(253, 253, 214));
		table.setRowHeight(40);
		table.getColumnModel().getColumn(0).setCellRenderer(new TableEditRemove_Renderer());
		table.getColumnModel().getColumn(0).setCellEditor(new TableEditRemove_Editor(model, this));
		
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
		Order.addItem(ordItem);
		Order.setTotal(Order.getTotal() + ordItem.getQuantity() * ordItem.getItem().getSellingPrice());
		displayPanel.subTotal.setText(Double.toString(Order.getTotal()));
		model.addRow(new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getItem().getSellingPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getItem().getSellingPrice()});
	}

	@Override
	public void editRow(int row) {
		JOptionPane p = new JOptionPane();
		String input = p.showInputDialog("Update Quantity:");
		
		if(input == null || input.isBlank())
			return;
		
		int quantity = Integer.parseInt(input);
		
		OrderMenuItem ordItem = Order.getItems().get(row);
		Order.setTotal(Order.getTotal() - ordItem.getQuantity() * ordItem.getItem().getSellingPrice());
		ordItem.setQuantity(quantity);
		Order.setTotal(Order.getTotal() + quantity * ordItem.getItem().getSellingPrice());
		displayPanel.subTotal.setText(Double.toString(Order.getTotal()));
		model.removeRow(row);
		model.insertRow(row, new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getItem().getSellingPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getItem().getSellingPrice()});
		
	}

	@Override
	public void removeRow(int row) {
		OrderMenuItem item = Order.getItems().get(row);
		Order.setTotal(Order.getTotal() - item.getQuantity() * item.getItem().getSellingPrice());
		Order.removeItem(row);
		displayPanel.subTotal.setText(Double.toString(Order.getTotal()));
		model.removeRow(row);		
	}
}
