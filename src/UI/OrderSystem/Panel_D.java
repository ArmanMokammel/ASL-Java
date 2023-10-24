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
import Enum.DiscountType;
import UI.ItemCategoryButton;
import UI.Order_Screen;
import UI.WrapLayout;
import Utilities.Utility;

public class Panel_D extends JPanel implements ActionListener{
	
	public HashMap<String, ArrayList<MenuItem>> items = new HashMap<String, ArrayList<MenuItem>>();
	private JPanel pannal = new JPanel();
	private Panel_C itemsPanel;
	
	public Panel_D(Panel_C itemsPanel) {
		setLayout(null);
		this.itemsPanel = itemsPanel;
		
		ArrayList<String> categoryList = Utility.readFile("Item-Categories.ASL");
		ArrayList<String> lines = Utility.readFile("Menu-Items.ASL");

		JPanel pnl = new JPanel(new WrapLayout());
		pnl.setBackground(Color.yellow);
		
		for(String s : categoryList) {
			JButton b = new JButton(s);
			b.setPreferredSize(new Dimension(240, 70));
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
		BoxLayout layout = new BoxLayout(pannal, BoxLayout.Y_AXIS);
		pannal.setLayout(layout);
		pannal.setBackground(Color.green);
				
		pnl_1.setBounds(10, 10, 280, 700);
		pnl_2.setBounds(290, 10, 590, 700);
		
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
		pannal.revalidate();
		pannal.repaint();
	}
}
