package UI.Panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import CustomComponents.JButtonT1;
import CustomComponents.JPanelX;
import Data.Discount_Voucher;
import DataEditorUI.VoucherEditor_Dialog;
import UI.MainWindow;
import Utilities.Utility;

public class Voucher_Panel extends JPanelX{
	
	public DefaultTableModel model;
	public LinkedList<Discount_Voucher> voucherList;
	private MainWindow window;
	
	public Voucher_Panel(MainWindow window){
		this.window = window;
		voucherList = new LinkedList<Discount_Voucher>();
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 685);
		setOpaque(false);
		
		Font f1 = new Font(null, Font.BOLD, 16);
		Font f2 = new Font(null, Font.PLAIN, 16);

		JButton btn_Add = new JButtonT1("New Voucher", "img\\btn.png", 6);
		btn_Add.setBounds(getWidth() - 260, 6, 150, 50);
		btn_Add.setFont(new Font(null, Font.BOLD, 16));
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VoucherEditor_Dialog dialog = new VoucherEditor_Dialog(window, Voucher_Panel.this,"New Voucher", -1);
				dialog.setVisible(true);
			}
		});
		
		model = new DefaultTableModel();
		model.addColumn("SL");
		model.addColumn("Voucher ID");
		model.addColumn("Voucher Code");
		model.addColumn("Value");
		model.addColumn("");
		
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 4)
					return true;
				else
					return false;
			}
		};
		
		table.setBackground(new Color(253, 253, 214));
		table.setRowHeight(40);
		table.setFont(f2);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setBackground(new Color(117, 68, 0));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(f1);

		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(4).setCellRenderer(renderer);
		table.getColumnModel().getColumn(4).setCellEditor(new TableEditRemove_Editor(this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 76, getWidth() - 180, 600);
		sp.getViewport().setOpaque(false);
				
		add(btn_Add);
		add(sp);
		
		ArrayList<String> lines = Utility.readFile("Discount-Vouchers.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			voucherList.add(new Discount_Voucher(Integer.parseInt(datas[0]), datas[1], Double.parseDouble(datas[2])));
			model.addRow(new Object[] {voucherList.size(), datas[0], datas[1], datas[2]});
		}
	}
	
	public void editRow(int row) {
		Discount_Voucher voucher = (Discount_Voucher) voucherList.get(row);
		VoucherEditor_Dialog dialog = new VoucherEditor_Dialog(window, this, "Edit Voucher" , row);
		dialog.setVoucherDetails(voucher.getVoucherId(), voucher.getVoucher(), voucher.getValue());
		dialog.setVisible(true);
	}
	
	public void removeRow(int row) {		
		model.removeRow(row);
		voucherList.remove(row);
		for(int i = 0; i < voucherList.size(); i++) {
			model.setValueAt(i+1, i, 0);
		}
		Utility.writeAllToFile("Discount-Vouchers.ASL", false, voucherList);
	}
}
