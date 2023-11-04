package UI;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Data.Customer;
import Data.MenuItem;
import Data.Order;
import Data.OrderController;
import Data.OrderMenuItem;
import Data.Payment;
import Enum.DiscountType;
import UI.OrderSystem.Panel_A;
import UI.OrderSystem.Panel_C;
import UI.OrderSystem.Panel_E;
import Utilities.Utility;

public class SaleHistory_Window extends JFrame {
	
	public LinkedList<String[]> orderList;
	private ArrayList<String[]> entriesFiltered = new ArrayList<String[]>();
	public DefaultTableModel model;
	private String selOrder;

	public SaleHistory_Window() {
		super("Sale History");
		setSize(getPreferredSize());
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setLayout(null);

		Font f1 = new Font(null, Font.BOLD, 40);
		Font f2 = new Font(null, Font.BOLD, 16);
		Font f3 = new Font(null, Font.PLAIN, 16);

		JLabel lbl_title = new JLabel("Sale History");
		lbl_title.setBounds(325, 20, 355, 50);
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
		orderList = new LinkedList<String[]>();
		model.addColumn("SL");
		model.addColumn("Branch");
		model.addColumn("Order no.");
		model.addColumn("Served By");
		model.addColumn("Customer");
		model.addColumn("Amount");
		
		File f = new File("Orders");
		if(!f.exists()) {
			f.mkdir();
		}

		List<File> files = Utility.getFiles(".txt", f);
		for (File file : files) {
			System.out.println(file.getName());
			ArrayList<String> order = Utility.readFile("Orders\\" + file.getName());

			String[] datas = order.get(1).split("\t");
			String[] customerData = order.get(2).split("\t");
			String[] amounts = order.get(3).split("\t");
			
			String[] stuff = { datas[0], datas[1], datas[2], customerData[0], amounts[1]};
			
			orderList.add(stuff);
			model.addRow(new Object[] {orderList.size(), stuff[0], stuff[1], stuff[2], stuff[3], stuff[4]});

		}
		entriesFiltered = new ArrayList<String[]>(orderList);

		JTable table = new JTable(model);
		table.setRowHeight(40);
		table.setBackground(new Color(253, 253, 214));
		table.setFont(f3);
		
		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setBackground(new Color(117, 68, 0));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(f2);

		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(5, 30, 735, 625);

		pnl.add(lbl_2);
		pnl.add(txt_1);
		pnl.add(sp);

		JPanel pnl2 = new JPanel();
		pnl2.setBounds(755, 90, 230, 670);
		pnl2.setBackground(Color.blue);
		pnl2.setLayout(null);

		JButton btn_1 = new JButton("View Order Reciept");
		btn_1.setBounds(5, 5, 215, 70);
		btn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					selOrder = entriesFiltered.get(table.getSelectedRow())[1];
					
					try {
						Desktop.getDesktop().open(new File(selOrder + ".pdf"));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

		for (String[] ord : orderList) {			
			if (ord[1].startsWith(enteredText)) {
				entriesFiltered.add(ord);
				model.addRow(new Object[] {entriesFiltered.size(), ord[0], ord[1], ord[2], ord[3], ord[4]});
			}
		}
	}
}
