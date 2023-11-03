package UI.OrderSystem;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import Data.Customer;
import Data.MenuItem;
import Data.OrderController;
import Data.OrderMenuItem;
import Enum.DiscountType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import CustomComponents.JPanelX;

public class Panel_C extends JPanelX{
	
	public static DefaultTableModel model;
	
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
		table.setOpaque(false);
		table.setBackground(new Color(253, 253, 214));
		table.setRowHeight(40);
		table.getColumnModel().getColumn(0).setCellRenderer(new TableEditRemove_Renderer());
		table.getColumnModel().getColumn(0).setCellEditor(new TableEditRemove_Editor(this));
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setBackground(new Color(0, 51, 118));
		tableHeader.setForeground(Color.white);
		
		JScrollPane sp = new JScrollPane(table) {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				int w = getWidth(), h = getHeight();
				Color color1 = new Color(242, 228, 70);
				Color color2 = new Color(240, 240, 201);
				GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, w, h);
			}
		};
		sp.setBounds(20, 20, 1000, 450);
		sp.getViewport().setOpaque(false);
						
		add(sp);
	}
	
	public void addItem(OrderMenuItem ordItem) {
		OrderController.getOrder().addItem(ordItem);		
		MenuItem item = ordItem.getItem();
		
		if(item.getDiscountType() == DiscountType.Value)
			ordItem.setDiscountedPrice(item.getSellingPrice() - item.getDiscountValue());
		else if(item.getDiscountType() == DiscountType.Percentage)
			ordItem.setDiscountedPrice(Math.round((item.getSellingPrice() * (100.0 - item.getDiscountValue()) / 100.0) * 100.0) / 100.0);
		else
			ordItem.setDiscountedPrice(item.getSellingPrice());
		
		OrderController.getOrder().setSubTotal(OrderController.getOrder().getSubTotal() + ordItem.getQuantity() * ordItem.getDiscountedPrice());
		
		double discountVal = 0;
		if(OrderController.getOrder().getCustomer() != null) {
			Customer customer = OrderController.getOrder().getCustomer();
			
			if(customer.getSpecialDiscountType() == DiscountType.Value)
				discountVal = OrderController.getOrder().getSubTotal() - customer.getSpecialDiscount();
			else if (customer.getSpecialDiscountType() == DiscountType.Percentage)
				discountVal = Math.round((OrderController.getOrder().getSubTotal() * (100 - customer.getSpecialDiscount()) / 100.0) * 100.0) / 100.0;
			else
				discountVal = OrderController.getOrder().getSubTotal();
			
		}
		else {
			discountVal = OrderController.getOrder().getSubTotal();
		}
		OrderController.getOrder().setTotal(discountVal);

		OrderController.getOrder().setAmountDue(OrderController.getOrder().getTotal() - OrderController.getOrder().getAmountPaid());
		Panel_E.subTotal.setText(Double.toString(OrderController.getOrder().getSubTotal()));
		Panel_E.discount.setText(Double.toString(OrderController.getOrder().getSubTotal() - discountVal));
		Panel_E.total.setText(Double.toString(OrderController.getOrder().getTotal()));
		Panel_E.amtDue.setText(Double.toString(OrderController.getOrder().getAmountDue()));
		Panel_E.amt.setText(Double.toString(OrderController.getOrder().getAmountDue()));
		model.addRow(new Object[] {"", item.getItemId(), item.getItemName(), item.getSellingPrice(), ordItem.getDiscountedPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getDiscountedPrice()});
	}

	@Override
	public void editRow(int row) {
		String input = JOptionPane.showInputDialog("Update Quantity:");
		
		if(input == null || input.isBlank())
			return;
		
		int quantity = Integer.parseInt(input);
		
		OrderMenuItem ordItem = OrderController.getOrder().getItems().get(row);
		OrderController.getOrder().setSubTotal(OrderController.getOrder().getSubTotal() - ordItem.getQuantity() * ordItem.getDiscountedPrice());
		ordItem.setQuantity(quantity);
		OrderController.getOrder().setSubTotal(OrderController.getOrder().getSubTotal() + quantity * ordItem.getDiscountedPrice());
		
		double discountVal = 0;
		if(OrderController.getOrder().getCustomer() != null) {
			Customer customer = OrderController.getOrder().getCustomer();
			
			if(customer.getSpecialDiscountType() == DiscountType.Value)
				discountVal = OrderController.getOrder().getSubTotal() - customer.getSpecialDiscount();
			else if (customer.getSpecialDiscountType() == DiscountType.Percentage)
				discountVal = Math.round((OrderController.getOrder().getSubTotal() * (100 - customer.getSpecialDiscount()) / 100.0) * 100.0) / 100.0;
			else
				discountVal = OrderController.getOrder().getSubTotal();
			
		}
		else {
			discountVal = OrderController.getOrder().getSubTotal();
		}
		OrderController.getOrder().setTotal(discountVal);
		
		OrderController.getOrder().setAmountDue(OrderController.getOrder().getTotal() - OrderController.getOrder().getAmountPaid());
		Panel_E.subTotal.setText(Double.toString(OrderController.getOrder().getSubTotal()));
		Panel_E.discount.setText(Double.toString(OrderController.getOrder().getSubTotal() - discountVal));
		Panel_E.total.setText(Double.toString(OrderController.getOrder().getTotal()));
		Panel_E.amtDue.setText(Double.toString(OrderController.getOrder().getAmountDue()));
		Panel_E.amt.setText(Double.toString(OrderController.getOrder().getAmountDue()));
		model.removeRow(row);
		model.insertRow(row, new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getDiscountedPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getDiscountedPrice()});
		
	}

	@Override
	public void removeRow(int row) {
		OrderMenuItem ordItem = OrderController.getOrder().getItems().get(row);
		OrderController.getOrder().setSubTotal(OrderController.getOrder().getSubTotal() - ordItem.getQuantity() * ordItem.getDiscountedPrice());
		OrderController.getOrder().removeItem(row);
		
		double discountVal = 0;
		if(OrderController.getOrder().getCustomer() != null) {
			Customer customer = OrderController.getOrder().getCustomer();
			
			if(customer.getSpecialDiscountType() == DiscountType.Value)
				discountVal = OrderController.getOrder().getSubTotal() - customer.getSpecialDiscount();
			else if (customer.getSpecialDiscountType() == DiscountType.Percentage)
				discountVal = Math.round((OrderController.getOrder().getSubTotal() * (100 - customer.getSpecialDiscount()) / 100.0) * 100.0) / 100.0;
			else
				discountVal = OrderController.getOrder().getSubTotal();
			
		}
		else {
			discountVal = OrderController.getOrder().getSubTotal();
		}
		OrderController.getOrder().setTotal(discountVal);
		
		OrderController.getOrder().setAmountDue(OrderController.getOrder().getTotal() - OrderController.getOrder().getAmountPaid());
		Panel_E.subTotal.setText(Double.toString(OrderController.getOrder().getSubTotal()));
		Panel_E.discount.setText(Double.toString(OrderController.getOrder().getSubTotal() - discountVal));
		Panel_E.total.setText(Double.toString(OrderController.getOrder().getTotal()));
		Panel_E.amtDue.setText(Double.toString(OrderController.getOrder().getAmountDue()));
		Panel_E.amt.setText(Double.toString(OrderController.getOrder().getAmountDue()));
		model.removeRow(row);		
	}
}
