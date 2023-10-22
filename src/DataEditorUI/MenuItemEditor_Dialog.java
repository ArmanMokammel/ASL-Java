package DataEditorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.*;

import Data.MenuItem;
import Enum.DiscountType;
import Enum.InputType;
import Exception.InputException;
import UI.*;
import Utilities.Utility;

public class MenuItemEditor_Dialog extends JDialog{
	
	private JComboBox<String> cmbx_category = new JComboBox<String>();
	private JTextField txt_itemId = new JTextField();
	private JTextField txt_itemName = new JTextField();
	private JTextField txt_costPrice = new JTextField();
	private JTextField txt_sellingPrice = new JTextField();
	private JComboBox<DiscountType> cmbx_discountType = new JComboBox<DiscountType>(DiscountType.values());
	private JTextField txt_discountValue = new JTextField();
	
	int A;
	String B, C, F;
	double D, E, G;
	
	public MenuItemEditor_Dialog(MainWindow frame, Item_Panel parent, String title, int row) {
		super(frame, title, true);
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		txt_discountValue.setText("0.0");
		
		JLabel lbl_itemCategory = new JLabel("Item Category:");
		JLabel lbl_itemId = new JLabel("Item ID:");
		JLabel lbl_itemName = new JLabel("Item Name:");
		JLabel lbl_costPrice = new JLabel("Cost Price:");
		JLabel lbl_sellingPrice = new JLabel("Selling Price:");
		JLabel lbl_discountType = new JLabel("Discount Type:");
		JLabel lbl_discountValue = new JLabel("Discount Value:");
		
		lbl_itemCategory.setBounds(50, 50, 80, 30);
		lbl_itemId.setBounds(50, 100, 70, 30);
		lbl_itemName.setBounds(50, 150, 70, 30);
		lbl_costPrice.setBounds(50, 200, 70, 30);
		lbl_sellingPrice.setBounds(50, 250, 80, 30);
		lbl_discountType.setBounds(50, 300, 80, 30);
		lbl_discountValue.setBounds(50, 350, 80, 30);
		
		add(lbl_itemCategory);
		add(lbl_itemId);
		add(lbl_itemName);
		add(lbl_costPrice);
		add(lbl_sellingPrice);
		add(lbl_discountType);
		add(lbl_discountValue);
		
		cmbx_category.setBounds(140, 50, 200, 30);
		txt_itemId.setBounds(140, 100, 200, 30);
		txt_itemName.setBounds(140, 150, 200, 30);
		txt_costPrice.setBounds(140, 200, 200, 30);
		txt_sellingPrice.setBounds(140, 250, 200, 30);
		cmbx_discountType.setBounds(140, 300, 200, 30);
		txt_discountValue.setBounds(140, 350, 200, 30);
		
		ArrayList<String> categoryList = Utility.readFile("Item-Categories.ASL");
		
		for(String category : categoryList) {
			cmbx_category.addItem(category);
		}
		
		cmbx_category.setSelectedItem(null);

		add(cmbx_category);
		add(txt_itemId);
		add(txt_itemName);
		add(txt_costPrice);
		add(txt_sellingPrice);
		add(cmbx_discountType);
		add(txt_discountValue);
		
		JButton btn_Submit = new JButton("Submit");
		btn_Submit.setBounds(180, 390, 80, 35);
		btn_Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuItem item = null;
				try {
					A = Utility.checkInt(txt_itemId, lbl_itemId);
					B = Utility.checkString(cmbx_category, lbl_itemCategory);
					C = Utility.checkString(txt_itemName, lbl_itemName, InputType.Text);
					D = Utility.checkDouble(txt_costPrice, lbl_costPrice);
					E = Utility.checkDouble(txt_sellingPrice, lbl_sellingPrice);
					F = Utility.checkString(cmbx_discountType, lbl_discountType);
					G = Utility.checkDouble(txt_discountValue, lbl_discountValue);
					
					item = new MenuItem(A, B, C, D, E, DiscountType.valueOf(F), G);
				} catch (InputException e2) {
					Utility.showErrorMessage(e2);
					return;
				}
				if(row != -1) {
					parent.itemList.set(row, item);
					parent.model.removeRow(row);
					parent.model.insertRow(row, new Object[] {row + 1, item.getItemId(), item.getItemName(), item.getCostPrice(), item.getSellingPrice(), item.getDiscountValue(), item.getDiscountType()});
					parent.items.put(item.getCategory(), new ArrayList<MenuItem>(parent.itemList));
					File file = new File("Menu-Items.ASL");
					file.delete();
					for(Map.Entry<String, ArrayList<MenuItem>> itms : parent.items.entrySet()) {
						Utility.writeAllToFile("Menu-Items.ASL", true, new LinkedList<MenuItem>(itms.getValue()));
					}
				}
				else {
					parent.itemList.add(item);
					parent.model.addRow(new Object[] {parent.itemList.size(), item.getItemId(), item.getItemName(), item.getCostPrice(), item.getSellingPrice(), item.getDiscountValue(), item.getDiscountType()});
					for(Map.Entry<String, ArrayList<MenuItem>> itms : parent.items.entrySet()) {
						if(itms.getKey().equals(item.getCategory())) {
							ArrayList<MenuItem> c = itms.getValue();
							c.add(item);
						}
					}
					parent.cmbx_ItemCategory.setSelectedItem(item.getCategory());
					Utility.writeToFile("Menu-Items.ASL", true, item);
				}
				dispose();
			}
		});
		add(btn_Submit);
	}

	public void setItemDetails(int itemId, String category, String itemName, double costPrice, double sellingPrice, DiscountType discountType, double discountValue) {
		txt_itemId.setText(Integer.toString(itemId));
		txt_itemId.setEditable(false);
		cmbx_category.setSelectedItem(category);
		cmbx_category.setEnabled(false);
		txt_itemName.setText(itemName);
		txt_costPrice.setText(Double.toString(costPrice));
		txt_sellingPrice.setText(Double.toString(sellingPrice));
		cmbx_discountType.setSelectedItem(discountType);
		txt_discountValue.setText(Double.toString(discountValue));
	}

}
