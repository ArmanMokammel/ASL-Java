package UI.OrderSystem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableRemove_Editor;
import CustomCell.TableRemove_Renderer;
import CustomComponents.JPanelX;
import CustomComponents.SearchableComboBox;
import Data.Discount_Voucher;
import Data.OrderController;
import Data.Payment;
import Exception.InputException;
import UI.Order_Screen;
import Utilities.Receipt;
import Utilities.Utility;

public class Panel_E extends JPanelX{
	
	public static DefaultTableModel model ;
	
	private String A;
	private double B;
	
	public static JLabel subTotal;
	public static JLabel discount;
	public static JLabel total;
	public static JLabel amtPaid;
	public static JLabel amtDue;
	public static JTextField amt;
	
	private ArrayList<Discount_Voucher> discountVouchers;
	
	private JPanel pnl_1;
	private JPanel pnl_2;
	private SearchableComboBox cmbx_2;
			
	public Panel_E() {
		setLayout(null);
		
		subTotal = new JLabel("0.0");
		discount = new JLabel("0.0");
		total = new JLabel("0.0");
		amtPaid = new JLabel("0.0");
		amtDue = new JLabel("0.0");
		
		ArrayList<String> vouchers = Utility.readFile("Discount-Vouchers.ASL");
		discountVouchers = new ArrayList<Discount_Voucher>();
		
		for(String voucher: vouchers) {
			String[] datas = voucher.split("\t");
			Discount_Voucher vch = new Discount_Voucher(datas[0], Integer.parseInt(datas[1]), datas[2], Double.parseDouble(datas[3]));
			discountVouchers.add(vch);
		}
						
		pnl_1 = new JPanel();
		pnl_1.setBounds(10, 0, 400, 250);
		pnl_1.setBackground(Color.lightGray);
		pnl_1.setLayout(null);
		
		JLabel lbl_subTotal = new JLabel("Sub-Total:");
		lbl_subTotal.setBounds(10, 20, 70, 30);
		
		subTotal.setBounds(80, 20, 100, 30);
		subTotal.setOpaque(true);
		
		JLabel lbl_discount = new JLabel("Discount:");
		lbl_discount.setBounds(10, 60, 70, 30);

		discount.setBounds(80, 60, 100, 30);
		discount.setOpaque(true);
		
		JSeparator sp = new JSeparator();
		sp.setBounds(0, 110, 485, 10);
		
		JLabel lbl_total = new JLabel("Total:");
		lbl_total.setBounds(10, 120, 70, 30);
		
		total.setBounds(80, 120, 100, 30);
		total.setOpaque(true);
		
		JSeparator sp2 = new JSeparator();
		sp2.setBounds(0, 160, 485, 10);
		
		JLabel lbl_amtPaid = new JLabel("Amount Paid:");
		lbl_amtPaid.setBounds(10, 170, 80, 30);
		
		amtPaid.setBounds(90, 170, 100, 30);
		amtPaid.setOpaque(true);
		
		JLabel lbl_amtDue = new JLabel("Amount Due:");
		lbl_amtDue.setBounds(10, 210, 80, 30);
		
		amtDue.setBounds(90, 210, 100, 30);
		amtDue.setOpaque(true);
		
		pnl_1.add(lbl_subTotal);
		pnl_1.add(subTotal);
		pnl_1.add(lbl_discount);
		pnl_1.add(discount);
		pnl_1.add(sp);
		pnl_1.add(lbl_total);
		pnl_1.add(total);
		pnl_1.add(sp2);
		pnl_1.add(lbl_amtPaid);
		pnl_1.add(amtPaid);
		pnl_1.add(lbl_amtDue);
		pnl_1.add(amtDue);
		
		add(pnl_1);
		
		
		
		pnl_2 = new JPanel();
		pnl_2.setLayout(null);
		pnl_2.setBounds(430, 0, 565, 300);
		pnl_2.setBackground(Color.lightGray);
		
		JLabel lbl_10 = new JLabel("Payment Type:");
		lbl_10.setBounds(10, 20, 90, 30);
		
		ArrayList<String> options = Utility.readFile("Payment-Methods.ASL");
		options.add("Voucher");
		
		JComboBox<String> cmbx_1 = new JComboBox<String>(options.toArray(new String[options.size()]));
		cmbx_1.setBounds(110, 20, 150, 30);
		cmbx_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(((String)cmbx_1.getSelectedItem()).equals("Voucher")) {
					pnl_2.remove(amt);
					pnl_2.add(cmbx_2);
					pnl_2.updateUI();
				} else {
					pnl_2.remove(cmbx_2);
					pnl_2.add(amt);
					pnl_2.updateUI();
				}				
			}
		});
		
		JLabel lbl_11 = new JLabel("Amount:");
		lbl_11.setBounds(10, 60, 90, 30);
		
		amt = new JTextField();
		amt.setBounds(110, 60, 150, 30);
		
		ArrayList<String> voucherId = new ArrayList<String>();
		for(Discount_Voucher v : discountVouchers) {
			voucherId.add(v.getVoucher());
		}
		cmbx_2 = new SearchableComboBox(voucherId);
		cmbx_2.setBounds(110, 60, 150, 30);
		
		JButton btn_addPayment = new JButton("<html><center>"+"Add"+"<br>"+"Payment"+"</center></html>");
		btn_addPayment.setBounds(280, 20, 85, 70);
		btn_addPayment.setBackground(new Color(93, 130, 84));
		btn_addPayment.setForeground(Color.white);
		btn_addPayment.setOpaque(true);
		btn_addPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					A = (String)cmbx_1.getSelectedItem();
					if(A.equals("Voucher")) {
						Utility.checkString(cmbx_2, lbl_11);
						for(Discount_Voucher v : discountVouchers) {
							if(((String)cmbx_2.getSelectedItem()).equals(v.getVoucher())){								
								B = v.getValue();
								A += " (" + v.getVoucher() + ")";
							}
						}
					}
					else {
						B = Utility.checkDouble(amt, lbl_11);
					}
					
					OrderController.getOrder().addPayment(new Payment(A, B));
					OrderController.getOrder().setAmountPaid(OrderController.getOrder().getAmountPaid() + B);
					OrderController.getOrder().setAmountDue(OrderController.getOrder().getTotal() - OrderController.getOrder().getAmountPaid());
					amtPaid.setText(Double.toString(OrderController.getOrder().getAmountPaid()));
					amtDue.setText(Double.toString(OrderController.getOrder().getAmountDue()));
					amt.setText(Double.toString(OrderController.getOrder().getAmountDue()));
					Panel_E.model.addRow(new Object[] {"", A, B});
				} catch (InputException e1) {
					Utility.showErrorMessage(e1);
				}
			}
		});
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				if(column == 0)
					return true;
				else
					return false;
			}
		};
		model.addColumn("");
		model.addColumn("Type");
		model.addColumn("Amount");
				
		JTable table = new JTable(model);
		table.setBackground(new Color(214, 241, 216));
		table.setRowHeight(30);
		
		table.getColumnModel().getColumn(0).setCellRenderer(new TableRemove_Renderer());
		table.getColumnModel().getColumn(0).setCellEditor(new TableRemove_Editor(this));
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setBackground(Color.black);
		tableHeader.setForeground(Color.white);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 100, 465, 100);
		scrollPane.getViewport().setBackground(new Color(145, 214, 150));
		
		JButton btn_hold = new JButton("Hold");
		btn_hold.setBounds(10, 260, 100, 30);
		btn_hold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderController.suspendCurrentOrder();
				File dir = new File("Suspended Orders");
				if (!dir.exists()){
				    dir.mkdir();
				}
				Utility.writeToFile("Suspended Orders\\" + OrderController.getOrder().getOrderNo() + ".txt", false, OrderController.getOrder());
				OrderController.incrementOrder();
				OrderController.resetOrder();
				Order_Screen.setCustomer(null);
				model.setRowCount(0);
				Panel_C.model.setRowCount(0);
				subTotal.setText("0.0");
				discount.setText("0.0");
				total.setText("0.0");
				amtPaid.setText("0.0");
				amtDue.setText("0.0");
				amt.setText("");
			}
		});
		
		JButton btn_cancel = new JButton("Cancel");
		btn_cancel.setBounds(140, 260, 100, 30);
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderController.resetOrder();
				Order_Screen.setCustomer(null);
				model.setRowCount(0);
				Panel_C.model.setRowCount(0);
				subTotal.setText("0.0");
				discount.setText("0.0");
				total.setText("0.0");
				amtPaid.setText("0.0");
				amtDue.setText("0.0");
				amt.setText("");
			}
		});
		
		JButton btn_finish = new JButton("Finish");
		btn_finish.setBounds(250, 260, 100, 30);
		btn_finish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File dir = new File("Orders");
				if (!dir.exists()){
				    dir.mkdir();
				}
				Utility.writeToFile("Orders\\" + OrderController.getOrder().getOrderNo() + ".txt", false, OrderController.getOrder());
				Receipt.generateReceipt();
				OrderController.incrementOrder();
				OrderController.resetOrder();
				Panel_A.txt_orderNo.setText(OrderController.getOrder().getOrderNo());
				model.setRowCount(0);
				Panel_C.model.setRowCount(0);
				subTotal.setText("0.0");
				discount.setText("0.0");
				total.setText("0.0");
				amtPaid.setText("0.0");
				amtDue.setText("0.0");
				amt.setText("");
			}
		});
		
		pnl_2.add(lbl_10);
		pnl_2.add(cmbx_1);
		pnl_2.add(lbl_11);
		pnl_2.add(amt);
		pnl_2.add(btn_addPayment);
		pnl_2.add(scrollPane);
		pnl_2.add(btn_hold);
		pnl_2.add(btn_cancel);
		pnl_2.add(btn_finish);
		
		add(pnl_2);
		
		AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).doClick();
            }
        };
        
        btn_addPayment.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_3, KeyEvent.ALT_DOWN_MASK), "addPay");        
        btn_finish.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_4, KeyEvent.ALT_DOWN_MASK), "finishOrd");        
        btn_hold.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_5, KeyEvent.ALT_DOWN_MASK), "holdOrd");        
        btn_cancel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_6, KeyEvent.ALT_DOWN_MASK), "cancelOrd");        
        
        btn_addPayment.getActionMap().put("addPay", buttonPressed);
        btn_finish.getActionMap().put("finishOrd", buttonPressed);
        btn_hold.getActionMap().put("holdOrd", buttonPressed);
        btn_cancel.getActionMap().put("cancelOrd", buttonPressed);
	}

	@Override
	public void editRow(int row) {}

	@Override
	public void removeRow(int row) {
		OrderController.getOrder().setAmountPaid(OrderController.getOrder().getAmountPaid() - OrderController.getOrder().getPayments().get(row).getAmount());
		OrderController.getOrder().setAmountDue(OrderController.getOrder().getTotal() - OrderController.getOrder().getAmountPaid());
		amtPaid.setText(Double.toString(OrderController.getOrder().getAmountPaid()));
		amtDue.setText(Double.toString(OrderController.getOrder().getAmountDue()));
		amt.setText(Double.toString(OrderController.getOrder().getAmountDue()));
		OrderController.getOrder().removePayment(row);		
		model.removeRow(row);
	}
}
