package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import SettingsPanels.ItemSettings;
import UI.OrderSystem.Panel_A;

public class Settings_Screen extends JFrame{
	
	public JPanel currentPanel;
	
	public Settings_Screen(String title) {
		super(title);
		setSize(700, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		JPanel panelA = new JPanel(new WrapLayout());
		JPanel panelB = new JPanel();
		
		panelA.setBackground(Color.yellow);
		panelB.setBackground(Color.cyan);
		
		panelA.setBounds(0,0, 200, 600);
		panelB.setBounds(200, 0, 500, 600);
		
		JButton btnItemSettings = new Settings_Button("Item Settings");
		btnItemSettings.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings_Screen.this.swapPanel(new ItemSettings());
			}
		});
		
		panelA.add(btnItemSettings);
		panelA.add(new Settings_Button("Settings 2"));
		panelA.add(new Settings_Button("Settings 3"));
		panelA.add(new Settings_Button("Settings 4"));
		panelA.add(new Settings_Button("Settings 5"));
				
		add(panelA);
		add(panelB);
		
		currentPanel = panelB;
		
		setVisible(true);
	}
	
	public void swapPanel(JPanel newPanel) {
		remove(currentPanel);
		add(newPanel);
		revalidate();
		repaint();
		currentPanel = newPanel;
		System.gc();
	}
}
