package UI.OrderSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Data.MenuItem;
import UI.ItemCategoryButton;
import UI.Order_Screen;
import UI.WrapLayout;
import Utilities.Utility;

public class ItemMenu extends JDialog implements ActionListener{
	
	public HashMap<String, ArrayList<MenuItem>> items = new HashMap<String, ArrayList<MenuItem>>();
	private JPanel pannal = new JPanel();
	
	public ItemMenu(Order_Screen frame, String title) {
		super(frame, title, true);
		setSize(1500, 800);
		setLayout(null);
		setLocationRelativeTo(null);
		
		ArrayList<String> categoryList = Utility.readFile("Item-Categories.ASL");
		ArrayList<String> lines = Utility.readFile("Menu-Items.ASL");

		JPanel pnl_1 = new JPanel();
		pnl_1.setLayout(null);
		pnl_1.setBackground(Color.yellow);
		JPanel panuuul = new JPanel(new WrapLayout());
		panuuul.setBounds(20, 10, 500, 100);
		
		for(String s : categoryList) {
			JButton b = new JButton(s);
			b.addActionListener(this);
			panuuul.add(b);
		}
		
		for(String category : categoryList) {
			ArrayList<MenuItem> itm = new ArrayList<MenuItem>();
			for(String line: lines) {
				String[] datas = line.split("\t");
				if(category.equals(datas[1])) {
					itm.add(new MenuItem(Integer.parseInt(datas[0]), datas[1], datas[2], Double.parseDouble(datas[3]), Double.parseDouble(datas[4])));
				}
			}
			items.put(category, itm);
		}

		pnl_1.add(panuuul);
		
		JPanel pnl_2 = new JPanel();
		pnl_2.setBackground(Color.blue.brighter());
		pnl_2.setLayout(null);
		JScrollPane sp = new JScrollPane(pannal);
		sp.getVerticalScrollBar().setUnitIncrement(20);
		BoxLayout layout = new BoxLayout(pannal, BoxLayout.Y_AXIS);
		pannal.setLayout(layout);
		sp.setBounds(10, 10, 550, 600);
		pannal.setBackground(Color.green);
		
		pnl_2.add(sp);
		
		JPanel pnl_3 = new JPanel();
		pnl_3.setBackground(Color.magenta);
		
		pnl_1.setBounds(0, 0, getWidth(), 120);
		pnl_2.setBounds(0, 120, 600, getHeight() - 120);
		pnl_3.setBounds(600, 120, 900, getHeight() - 120);
		
		add(pnl_1);
		add(pnl_2);
		add(pnl_3);
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<MenuItem> itm = items.get(((JButton)e.getSource()).getText());
		pannal.removeAll();
		for(MenuItem s : itm) {
			pannal.add(new ItemCategoryButton(s.getItemName()));
			pannal.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		pannal.revalidate();
		pannal.repaint();
	}
}
