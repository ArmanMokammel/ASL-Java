package UI.OrderSystem;

import java.awt.Color;

import Data.Customer;
import Data.MenuItem;
import Data.Order;
import Data.OrderController;
import Data.OrderMenuItem;
import Enum.DiscountType;
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
		MenuItem item = ordItem.getItem();
		
		if(item.getDiscountType() == DiscountType.Value)
			ordItem.setDiscountedPrice(item.getSellingPrice() - item.getDiscountValue());
		else if(item.getDiscountType() == DiscountType.Percentage)
			ordItem.setDiscountedPrice(Math.round((item.getSellingPrice() * (100.0 - item.getDiscountValue()) / 100.0) * 100.0) / 100.0);
		else
			ordItem.setDiscountedPrice(item.getSellingPrice());
		
		order.setSubTotal(order.getSubTotal() + ordItem.getQuantity() * ordItem.getDiscountedPrice());
		if(order.getCustomer() != null) {
			Customer customer = order.getCustomer();
			if(customer.getSpecialDiscountType() == DiscountType.Value)
				order.setTotal(order.getSubTotal() - customer.getSpecialDiscount());
			else if (customer.getSpecialDiscountType() == DiscountType.Percentage)
				order.setTotal(Math.round((order.getSubTotal() * (100 - customer.getSpecialDiscount()) / 100.0) * 100.0) / 100.0);
			else
				order.setTotal(order.getSubTotal());
		}
		else {
			order.setTotal(order.getSubTotal());
		}
		displayPanel.subTotal.setText(Double.toString(order.getSubTotal()));
		displayPanel.total.setText(Double.toString(order.getTotal()));
		model.addRow(new Object[] {"", item.getItemId(), item.getItemName(), item.getSellingPrice(), ordItem.getDiscountedPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getDiscountedPrice()});
	}

	@Override
	public void editRow(int row) {
		String input = JOptionPane.showInputDialog("Update Quantity:");
		
		if(input == null || input.isBlank())
			return;
		
		int quantity = Integer.parseInt(input);
		
		OrderMenuItem ordItem = order.getItems().get(row);
		order.setSubTotal(order.getSubTotal() - ordItem.getQuantity() * ordItem.getDiscountedPrice());
		ordItem.setQuantity(quantity);
		order.setSubTotal(order.getSubTotal() + quantity * ordItem.getDiscountedPrice());
		if(order.getCustomer() != null) {
			Customer customer = order.getCustomer();
			if(customer.getSpecialDiscountType() == DiscountType.Value)
				order.setTotal(order.getSubTotal() - customer.getSpecialDiscount());
			else if (customer.getSpecialDiscountType() == DiscountType.Percentage)
				order.setTotal(Math.round((order.getSubTotal() * (100 - customer.getSpecialDiscount()) / 100.0) * 100.0) / 100.0);
			else
				order.setTotal(order.getSubTotal());
		}
		else {
			order.setTotal(order.getSubTotal());
		}
		displayPanel.subTotal.setText(Double.toString(order.getSubTotal()));
		displayPanel.total.setText(Double.toString(order.getTotal()));
		model.removeRow(row);
		model.insertRow(row, new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getDiscountedPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getDiscountedPrice()});
		
	}

	@Override
	public void removeRow(int row) {
		OrderMenuItem ordItem = order.getItems().get(row);
		order.setSubTotal(order.getSubTotal() - ordItem.getQuantity() * ordItem.getDiscountedPrice());
		order.removeItem(row);
		if(order.getCustomer() != null) {
			Customer customer = order.getCustomer();
			if(customer.getSpecialDiscountType() == DiscountType.Value)
				order.setTotal(order.getSubTotal() - customer.getSpecialDiscount());
			else if (customer.getSpecialDiscountType() == DiscountType.Percentage)
				order.setTotal(Math.round((order.getSubTotal() * (100 - customer.getSpecialDiscount()) / 100.0) * 100.0) / 100.0);
			else
				order.setTotal(order.getSubTotal());
		}
		else {
			order.setTotal(order.getSubTotal());
		}
		displayPanel.subTotal.setText(Double.toString(order.getSubTotal()));
		displayPanel.total.setText(Double.toString(order.getTotal()));
		model.removeRow(row);		
	}
}
