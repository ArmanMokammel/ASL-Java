package UI.OrderSystem;

import java.awt.Color;

import Data.Order;
import Data.OrderController;
import Data.OrderMenuItem;
import UI.JPanelX;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;

public class Panel_C extends JPanelX{
	
	public static DefaultTableModel model;
	private Panel_E displayPanel;
	
	private Order order;
	
	public Panel_C() {
		setLayout(null);
		
		order = OrderController.getOrder();
		
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
		order.addItem(ordItem);
		order.setTotal(order.getTotal() + ordItem.getQuantity() * ordItem.getItem().getSellingPrice());
		displayPanel.subTotal.setText(Double.toString(order.getTotal()));
		model.addRow(new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getItem().getSellingPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getItem().getSellingPrice()});
	}

	@Override
	public void editRow(int row) {
		String input = JOptionPane.showInputDialog("Update Quantity:");
		
		if(input == null || input.isBlank())
			return;
		
		int quantity = Integer.parseInt(input);
		
		OrderMenuItem ordItem = order.getItems().get(row);
		order.setTotal(order.getTotal() - ordItem.getQuantity() * ordItem.getItem().getSellingPrice());
		ordItem.setQuantity(quantity);
		order.setTotal(order.getTotal() + quantity * ordItem.getItem().getSellingPrice());
		displayPanel.subTotal.setText(Double.toString(order.getTotal()));
		model.removeRow(row);
		model.insertRow(row, new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getItem().getSellingPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getItem().getSellingPrice()});
		
	}

	@Override
	public void removeRow(int row) {
		OrderMenuItem item = order.getItems().get(row);
		order.setTotal(order.getTotal() - item.getQuantity() * item.getItem().getSellingPrice());
		order.removeItem(row);
		displayPanel.subTotal.setText(Double.toString(order.getTotal()));
		model.removeRow(row);		
	}
}
