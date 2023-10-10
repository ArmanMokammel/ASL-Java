package UI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import Enum.AccountType;
import UI.OrderSystem.*;

public class Order_Screen extends JFrame{
	
	private String name;
	private AccountType accountType;
	
	public Order_Screen(String name, AccountType accountType) {
		super("Take Order - " + name);
		this.name = name;
		this.accountType = accountType;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				MainWindow window = new MainWindow(name, Order_Screen.this.accountType);
				window.createUI();
				super.windowClosing(e);
			}
		});
		setSize(1500, 800);
	}
	
	public void createUI() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLayout(null);
		this.getContentPane().setBackground(Color.black);
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
			}
		});
		
		this.getRootPane().registerKeyboardAction (new ActionListener ()
        {
            @Override
            public void actionPerformed (final ActionEvent e)
            {
            	dispose();
                setUndecorated(isUndecorated() ? false : true);
                setVisible(true);
            }
        }, KeyStroke.getKeyStroke ( KeyEvent.VK_F1, 0 ), JComponent.WHEN_IN_FOCUSED_WINDOW );
		
		Panel_A pnl_1 = new Panel_A();
		Panel_B pnl_2 = new Panel_B();
		Panel_C pnl_3 = new Panel_C();
		Panel_D pnl_4 = new Panel_D();
		JLabel pnl_5 = new JLabel("Copyright my shit 2.0", SwingConstants.CENTER);
		
		pnl_1.setBackground(new Color(198, 198, 198));		
		pnl_2.setBackground(new Color(182, 169, 140));		
//		pnl_3.setBackground(Color.blue);		
//		pnl_4.setBackground(Color.magenta);
		pnl_5.setBackground(Color.yellow);
		
		pnl_3.setBackground(Color.black);
		pnl_4.setBackground(Color.black);
		
		pnl_5.setOpaque(true);
		
		add(pnl_1);
		add(pnl_2);
		add(pnl_3);
		add(pnl_4);
		add(pnl_5);
		
		setVisible(true);
		pnl_1.setBounds(0,0,getWidth() - 510, 150);
		pnl_2.setBounds(pnl_1.getWidth(),0,495, 190);
		pnl_3.setBounds(0,150,getWidth() - 510, getHeight() - 220);
		pnl_4.setBounds(pnl_1.getWidth(), pnl_2.getHeight(), 495, getHeight() - 260);
		pnl_5.setBounds(0, getHeight() - 70, getWidth(), 35);
	}
}
