package UI.OrderSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import CustomComponents.ItemCategoryButton;
import CustomComponents.WrapLayout;
import Data.MenuItem;
import Enum.DiscountType;
import Utilities.Utility;

public class Panel_D extends JPanel implements ActionListener{
	
	public HashMap<String, ArrayList<MenuItem>> items = new HashMap<String, ArrayList<MenuItem>>();
	private JPanel pannal = new JPanel();
	private Panel_C itemsPanel;
	
	public Panel_D(Panel_C itemsPanel) {
		setLayout(null);
		this.itemsPanel = itemsPanel;
		setBorder(BorderFactory.createLineBorder(Color.white, 2));
		
		Font f1 = new Font(null, Font.BOLD, 22);
		
		JLabel lbl_title1 = new JLabel("Categories");
		lbl_title1.setBounds(85, 7, 110, 30);
		lbl_title1.setFont(f1);
		lbl_title1.setForeground(Color.white);
		
		JLabel lbl_title2 = new JLabel("Items");
		lbl_title2.setBounds(550, 7, 70, 30);
		lbl_title2.setFont(f1);
		lbl_title2.setForeground(Color.white);
		
		ArrayList<String> categoryList = Utility.readFile("Item-Categories.ASL");
		ArrayList<String> lines = Utility.readFile("Menu-Items.ASL");

		JPanel pnl = new JPanel(new WrapLayout());
		pnl.setBackground(Color.yellow);
		
		for(String s : categoryList) {
			JButton b = new JButton(s);
			b.setPreferredSize(new Dimension(240, 80));
			b.setFont(f1);
			b.setBackground(new Color(214, 66, 17));
			b.setForeground(Color.white);
			b.addActionListener(this);
			pnl.add(b);
			pnl.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		
		JScrollPane pnl_1 = new JScrollPane(pnl);
		
		for(String category : categoryList) {
			ArrayList<MenuItem> itm = new ArrayList<MenuItem>();
			for(String line: lines) {
				String[] datas = line.split("\t");
				if(category.equals(datas[1])) {
					itm.add(new MenuItem(Integer.parseInt(datas[0]), datas[1], datas[2], Double.parseDouble(datas[3]), Double.parseDouble(datas[4]), DiscountType.valueOf(datas[5]), Double.parseDouble(datas[6])));
				}
			}
			items.put(category, itm);
		}
		
		JScrollPane pnl_2 = new JScrollPane(pannal);
		pnl_1.getVerticalScrollBar().setUnitIncrement(10);
		pnl_2.getVerticalScrollBar().setUnitIncrement(20);
		pannal.setLayout(new WrapLayout());
		pannal.setBackground(Color.green);
				
		pnl_1.setBounds(10, 50, 280, 700);
		pnl_2.setBounds(290, 50, 590, 700);
		
		add(lbl_title1);
		add(lbl_title2);
		add(pnl_1);
		add(pnl_2);
		
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<MenuItem> itm = items.get(((JButton)e.getSource()).getText());
		pannal.removeAll();
		for(MenuItem s : itm) {
			pannal.add(new ItemCategoryButton(s, Panel_D.this.itemsPanel));
			pannal.add(Box.createRigidArea(new Dimension(0, 10)));
		}
		pannal.updateUI();
	}
}
