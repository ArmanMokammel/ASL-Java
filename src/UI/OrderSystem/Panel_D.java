package UI.OrderSystem;

import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Panel_D extends JPanel{
	
	public Panel_D() {
		setLayout(null);
		
		JPanel pnl_1 = new JPanel();
		pnl_1.setBounds(10, 10, 485, 250);
		pnl_1.setBackground(Color.lightGray);
		pnl_1.setLayout(null);
		
		JLabel lbl_1 = new JLabel("Sub-Total:");
		lbl_1.setBounds(10, 20, 70, 30);
		
		JLabel lbl_2 = new JLabel("  123123.00");
		lbl_2.setBounds(80, 20, 100, 30);
		lbl_2.setOpaque(true);
		
		JLabel lbl_3 = new JLabel("VAT:");
		lbl_3.setBounds(10, 60, 70, 30);
		
		JTextField txt_1 = new JTextField();
		txt_1.setBounds(80, 60, 100, 30);
		
		JSeparator sp = new JSeparator();
		sp.setBounds(0, 110, 485, 10);
		
		JLabel lbl_4 = new JLabel("Total:");
		lbl_4.setBounds(10, 120, 70, 30);
		
		JLabel lbl_5 = new JLabel("  4523492.00");
		lbl_5.setBounds(80, 120, 100, 30);
		lbl_5.setOpaque(true);
		
		JSeparator sp2 = new JSeparator();
		sp2.setBounds(0, 160, 485, 10);
		
		JLabel lbl_6 = new JLabel("Amount Paid:");
		lbl_6.setBounds(10, 170, 80, 30);
		
		JLabel lbl_7 = new JLabel("  651635.00");
		lbl_7.setBounds(90, 170, 100, 30);
		lbl_7.setOpaque(true);
		
		JLabel lbl_8 = new JLabel("Amount Due:");
		lbl_8.setBounds(10, 210, 80, 30);
		
		JLabel lbl_9 = new JLabel("  0.00");
		lbl_9.setBounds(90, 210, 100, 30);
		lbl_9.setOpaque(true);
		
		pnl_1.add(lbl_1);
		pnl_1.add(lbl_2);
		pnl_1.add(lbl_3);
		pnl_1.add(txt_1);
		pnl_1.add(sp);
		pnl_1.add(lbl_4);
		pnl_1.add(lbl_5);
		pnl_1.add(sp2);
		pnl_1.add(lbl_6);
		pnl_1.add(lbl_7);
		pnl_1.add(lbl_8);
		pnl_1.add(lbl_9);
		
		add(pnl_1);
		
		
		
		JPanel pnl_2 = new JPanel();
		pnl_2.setLayout(null);
		pnl_2.setBounds(10, 270, 485, 300);
		pnl_2.setBackground(Color.lightGray);
		
		JLabel lbl_10 = new JLabel("Payment Type:");
		lbl_10.setBounds(10, 20, 90, 30);
		
		JComboBox<String> cmbx_1 = new JComboBox<String>(new String[] {"Cash", "Debit Card", "Credit Card", "Gift Card", "Due"});
		cmbx_1.setBounds(110, 20, 150, 30);
		
		JLabel lbl_11 = new JLabel("Amount:");
		lbl_11.setBounds(10, 60, 90, 30);
		
		JTextField txt_2 = new JTextField();
		txt_2.setBounds(110, 60, 150, 30);
		
		JButton btn_1 = new JButton("Add Payment");
		btn_1.setBounds(100, 110, 110, 30);
		btn_1.setBackground(new Color(93, 130, 84));
		btn_1.setForeground(Color.white);
		btn_1.setOpaque(true);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Delete");
		model.addColumn("Type");
		model.addColumn("Amount");
		
		model.addRow(new Object[] {"2", "234"});
		
		JTable table = new JTable(model);
		table.setBackground(new Color(214, 241, 216));
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(Color.black);
		tableHeader.setForeground(Color.white);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 155, 465, 100);
		scrollPane.getViewport().setBackground(new Color(145, 214, 150));
		
		JButton btn_2 = new JButton("Hold");
		btn_2.setBounds(10, 260, 100, 30);
		
		JButton btn_3 = new JButton("Cancel");
		btn_3.setBounds(140, 260, 100, 30);
		
		pnl_2.add(lbl_10);
		pnl_2.add(cmbx_1);
		pnl_2.add(lbl_11);
		pnl_2.add(txt_2);
		pnl_2.add(btn_1);
		pnl_2.add(scrollPane);
		pnl_2.add(btn_2);
		pnl_2.add(btn_3);
		
		add(pnl_2);
	}
}
