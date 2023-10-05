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
import javax.swing.JPanel;
import javax.swing.KeyStroke;

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
		JPanel pnl_3 = new JPanel();
		JPanel pnl_4 = new JPanel();
		JPanel pnl_5 = new JPanel();
		
		pnl_1.setBackground(Color.red);		
		pnl_2.setBackground(Color.green);		
		pnl_3.setBackground(Color.blue);		
		pnl_4.setBackground(Color.magenta);
		pnl_5.setBackground(Color.yellow);
		
		add(pnl_1);
		add(pnl_2);
		add(pnl_3);
		add(pnl_4);
		add(pnl_5);
		
		setVisible(true);
		pnl_1.setBounds(0,0,getWidth() - 510, 150);
		pnl_2.setBounds(pnl_1.getWidth(),0,495,150);
		pnl_3.setBounds(0,150,getWidth() - 450,getHeight() - 220);
		pnl_4.setBounds(pnl_1.getWidth(), 150, 450, getHeight() - 220);
		pnl_5.setBounds(0, getHeight() - 150, getWidth(), 150);
	}
}
