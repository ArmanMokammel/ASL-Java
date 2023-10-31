package UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import Data.Customer;
import Data.MenuItem;
import Data.Order;
import Data.OrderController;
import Data.OrderMenuItem;
import Data.Payment;
import Enum.DiscountType;
import UI.OrderSystem.Panel_A;
import UI.OrderSystem.Panel_B;
import UI.OrderSystem.Panel_C;
import UI.OrderSystem.Panel_E;
import Utilities.Utility;

public class SuspendedOrders_Screen extends JDialog{
	
	public LinkedList<Order> orderList;
	private ArrayList<Order> entriesFiltered = new ArrayList<Order>();
	public DefaultTableModel model;
	private Order selOrder;
	
	public SuspendedOrders_Screen(Order_Screen window) {
		super(window, "Suspended Orders", true);
		setSize(1500, 800);
		setLocationRelativeTo(null);
		setLayout(null);
		
		Font f1 = new Font(null, Font.BOLD, 40);
		
		JLabel lbl_title = new JLabel("Suspended Orders");
		lbl_title.setBounds(573, 20, 355, 50);
		lbl_title.setFont(f1);
		
		JPanel pnl = new JPanel();
		pnl.setBounds(5, 90, 750, 670);
		pnl.setBackground(Color.gray);
		pnl.setLayout(null);
		
		JLabel lbl_2 = new JLabel("Search:");
		lbl_2.setBounds(5, 0, 50, 30);
		
		JTextField txt_1 = new JTextField();
		txt_1.setBounds(55, 0, 695, 30);
		txt_1.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						comboFilter(txt_1.getText());
					}
				});
			}
		});
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		orderList = new LinkedList<Order>();
		model.addColumn("SL");
		model.addColumn("Branch");
		model.addColumn("Order no.");
		model.addColumn("Served By");
		model.addColumn("Customer");
		model.addColumn("Amount");
		
		List<File> files = Utility.getFiles(".txt", new File("Suspended Orders"));
		for(File file: files) {
			System.out.println(file.getName());
			ArrayList<String> order = Utility.readFile("Suspended Orders\\" + file.getName());
			Order ord = new Order();
			
			String[] info = order.get(0).split("\t");
			String[] datas = order.get(1).split("\t");
			String[] customerData = order.get(2).split("\t");
			
			Customer customer;
			if(customerData[0].equals("None")) {
				customer = null;
			}
			else {
				customer  = new Customer(Integer.parseInt(customerData[0]), customerData[1], customerData[2], customerData[3], customerData[4], DiscountType.valueOf(customerData[5]), Double.parseDouble(customerData[6]));			
			}
			String[] amounts = order.get(3).split("\t");
			
			for(int i = 0; i < Integer.parseInt(info[0]); i++) {				
				String[] itemData = order.get(4 + i).split("\t");
				MenuItem menuItem = new MenuItem(Integer.parseInt(itemData[0]), itemData[1], itemData[2], Double.parseDouble(itemData[3]), Double.parseDouble(itemData[4]), DiscountType.valueOf(itemData[5]), Double.parseDouble(itemData[6]));
				OrderMenuItem ordItem = new OrderMenuItem(menuItem, Double.parseDouble(itemData[7]), Integer.parseInt(itemData[8]));
				ord.addItem(ordItem);
			}
			
			for(int i = 0; i < Integer.parseInt(info[1]); i++) {
				String[] paymentsData = order.get(4 + Integer.parseInt(info[0]) + i).split("\t");
				Payment payment = new Payment(paymentsData[0], Double.parseDouble(paymentsData[1]));
				ord.addPayment(payment);
			}
			
			ord.setCustomer(customer);
			ord.setBranch(datas[0]);
			ord.setOrderNo(datas[1]);
			ord.setAccountId(datas[2]);
			ord.setSubTotal(Double.parseDouble(amounts[0]));
			ord.setTotal(Double.parseDouble(amounts[1]));
			ord.setAmountPaid(Double.parseDouble(amounts[2]));
						
			orderList.add(ord);
			
			model.addRow(new Object[] {1, datas[0], datas[1], datas[2], customer == null ? "None" : customer.getCustomerId(), amounts[0]});

		}
		entriesFiltered = new ArrayList<Order>(orderList);
		
		JTable table = new JTable(model);
		table.setRowHeight(40);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(5, 30, 735, 625);
		
		pnl.add(lbl_2);
		pnl.add(txt_1);
		pnl.add(sp);
		
		JPanel pnl2 = new JPanel();
		pnl2.setBounds(755, 90, 230, 670);
		pnl2.setBackground(Color.blue);
		pnl2.setLayout(null);
		
		JButton btn_1 = new JButton("Reinstate Order");
		btn_1.setBounds(5, 5, 215, 70);
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {
					selOrder = orderList.get(table.getSelectedRow());
					OrderController.reinstateOrder(selOrder);
					Panel_C.model.setRowCount(0);
					Panel_E.model.setRowCount(0);
					
					Order_Screen.setCustomer(selOrder.getCustomer());
					
					Panel_A.txt_orderNo.setText(selOrder.getOrderNo());
					
					for(OrderMenuItem ordItem : selOrder.getItems()) {
						Panel_C.model.addRow(new Object[] {"", ordItem.getItem().getItemId(), ordItem.getItem().getItemName(), ordItem.getItem().getSellingPrice(), ordItem.getDiscountedPrice(), ordItem.getQuantity(), ordItem.getQuantity() * ordItem.getDiscountedPrice()});
					}
					
					for(Payment payment : selOrder.getPayments()) {
						Panel_E.model.addRow(new Object[] {"", payment.getPaymentType(), payment.getAmount()});
					}
					
					Panel_E.subTotal.setText(Double.toString(selOrder.getSubTotal()));
					Panel_E.total.setText(Double.toString(selOrder.getTotal()));
					Panel_E.amtPaid.setText(Double.toString(selOrder.getAmountPaid()));
					
					File file = new File("Suspended Orders\\" + selOrder.getOrderNo() + ".txt");
					file.delete();
					
					dispose();
				}
				
			}
		});
		
		pnl2.add(btn_1);
		
		add(lbl_title);
		add(pnl);
		add(pnl2);
		
		setVisible(true);
	}
	
	public void comboFilter(String enteredText) {
		entriesFiltered.clear();
		model.setRowCount(0);

		for (Order ord : orderList) {			
			if (Integer.toString(ord.getCustomer().getCustomerId()).startsWith(enteredText)) {
				entriesFiltered.add(ord);
				model.addRow(new Object[] {entriesFiltered.size(), ord.getBranch(), ord.getOrderNo(), ord.getAccountId(), ord.getCustomer().getCustomerId(), ord.getTotal()});
			}
		}
	}
}
