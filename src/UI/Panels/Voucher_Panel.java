package UI.Panels;

import java.awt.Color;
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

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import Data.Discount_Voucher;
import DataEditorUI.VoucherEditor_Dialog;
import UI.JPanelX;
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
		setBounds(30, 150, window.getWidth() - 70, 550);
		setBackground(new Color(0,0,0,0));

		JButton btn_Add = new JButton("New Voucher");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VoucherEditor_Dialog dialog = new VoucherEditor_Dialog(window, Voucher_Panel.this,"New Voucher", -1);
				dialog.setVisible(true);
			}
		});
		
		model = new DefaultTableModel();
		model.addColumn("SL");
		model.addColumn("Customer");
		model.addColumn("Voucher ID");
		model.addColumn("Voucher Code");
		model.addColumn("Value");
		model.addColumn("");
		
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 5)
					return true;
				else
					return false;
			}
		};
		
		table.setOpaque(false);
		table.setBackground(new Color(253, 253, 214));
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(5).setCellRenderer(renderer);
		table.getColumnModel().getColumn(5).setCellEditor(new TableEditRemove_Editor(this));
		
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
		sp.setBounds(70, 60, getWidth() - 180, 400);
		sp.getViewport().setOpaque(false);
				
		add(btn_Add);
		add(sp);
		
		ArrayList<String> lines = Utility.readFile("Discount-Vouchers.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			voucherList.add(new Discount_Voucher(datas[0], Integer.parseInt(datas[1]), datas[2], Double.parseDouble(datas[3])));
			model.addRow(new Object[] {voucherList.size(), datas[0], Integer.parseInt(datas[1]), datas[2], Double.parseDouble(datas[3])});
		}
	}
	
	public void editRow(int row) {
		Discount_Voucher voucher = (Discount_Voucher) voucherList.get(row);
		VoucherEditor_Dialog dialog = new VoucherEditor_Dialog(window, this, "Edit Voucher" , row);
		dialog.setVoucherDetails(voucher.getCustomer(), voucher.getVoucherId(), voucher.getVoucher(), voucher.getValue());
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
