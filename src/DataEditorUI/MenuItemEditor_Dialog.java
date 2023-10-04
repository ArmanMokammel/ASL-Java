package DataEditorUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Data.MenuItem;
import Enum.InputType;
import UI.*;
import Utilities.Utility;

public class MenuItemEditor_Dialog extends JDialog{
	
	private JTextField txt_itemId = new JTextField();
	private JTextField txt_itemName = new JTextField();
	private JTextField txt_costPrice = new JTextField();
	private JTextField txt_sellingPrice = new JTextField();
	
	int A;
	String B;
	double C,D;
	
	public MenuItemEditor_Dialog(MainWindow frame, Item_Panel parent, String title, int row) {
		super(frame, title, true);
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lbl_itemId = new JLabel("Item ID:");
		JLabel lbl_itemName = new JLabel("Item Name:");
		JLabel lbl_costPrice = new JLabel("Cost Price:");
		JLabel lbl_sellingPrice = new JLabel("Selling Price:");
		
		lbl_itemId.setBounds(50, 50, 70, 30);
		lbl_itemName.setBounds(50, 100, 70, 30);
		lbl_costPrice.setBounds(50, 150, 70, 30);
		lbl_sellingPrice.setBounds(50, 200, 80, 30);
		
		add(lbl_itemId);
		add(lbl_itemName);
		add(lbl_costPrice);
		add(lbl_sellingPrice);		
		
		txt_itemId.setBounds(140, 50, 200, 30);
		txt_itemName.setBounds(140, 100, 200, 30);
		txt_costPrice.setBounds(140, 150, 200, 30);
		txt_sellingPrice.setBounds(140, 200, 200, 30);

		add(txt_itemId);
		add(txt_itemName);
		add(txt_costPrice);
		add(txt_sellingPrice);
		
		JButton btn_Submit = new JButton("Submit");
		btn_Submit.setBounds(180, 260, 80, 35);
		btn_Submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuItem item = null;
				try {
					A = Utility.checkInt(txt_itemId, lbl_itemId);
					B = Utility.checkString(txt_itemName, lbl_itemName, InputType.Text);
					C = Utility.checkDouble(txt_costPrice, lbl_costPrice);
					D = Utility.checkDouble(txt_sellingPrice, lbl_sellingPrice);
					
					item = new MenuItem(A, B, C, D);
				} catch (Exception e2) {
					Utility.showErrorMessage(e2);
					return;
				}
				if(row != -1) {
					parent.itemList.set(row, item);
					parent.model.removeRow(row);
					parent.model.insertRow(row, new Object[] {row + 1, item.getItemId(), item.getItemName(), item.getCostPrice(), item.getSellingPrice()});
					Utility.writeAllToFile("Menu-Items.ASL", false, parent.itemList);
				}
				else {
					parent.itemList.add(item);
					parent.model.addRow(new Object[] {parent.itemList.size(), item.getItemId(), item.getItemName(), item.getCostPrice(), item.getSellingPrice()});
					Utility.writeToFile("Menu-Items.ASL", true, item);
				}
				dispose();
			}
		});
		add(btn_Submit);
	}

	public void setItemDetails(int itemId, String itemName, double costPrice, double sellingPrice) {
		txt_itemId.setText(Integer.toString(itemId));
		txt_itemId.setEditable(false);
		txt_itemName.setText(itemName);
		txt_costPrice.setText(Double.toString(costPrice));
		txt_sellingPrice.setText(Double.toString(sellingPrice));
	}

}
