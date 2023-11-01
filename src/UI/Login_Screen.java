package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;

import CustomComponents.JButtonT1;
import Data.Account;
import Enum.AccountType;
import Utilities.Utility;

public class Login_Screen extends JFrame {

	public Login_Screen() {
		super("ASL - Restaurant Management System");
	}

	public void createUI() {
		Font f1 = new Font(null, Font.BOLD, 20);
		Font f2 = new Font(null, Font.PLAIN, 18);

//		save();

		setSize(615, 647);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		
		JLabel lll = new JLabel();
		lll.setBounds(150, 120, 310, 90);
		ImageIcon icon_1 = Utility.getImageIcon("img\\Logo.png", 310, 90);
		lll.setIcon(icon_1);
		
		
		JLabel Bg_Icon = new JLabel();
		ImageIcon background = new ImageIcon(Utility.getImage("img\\Login_Screen.png"));
		Bg_Icon.setIcon(background);
		Bg_Icon.setOpaque(true);
		setContentPane(Bg_Icon);
		
		JLabel lbl_userId = new JLabel("User ID: ");
		lbl_userId.setBounds(120, 250, 110, 30);
		lbl_userId.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_userId.setFont(f1);

		JTextField txt_userId = new JTextField();
		txt_userId.setBounds(240, 250, 200, 30);
		txt_userId.setFont(f2);

		JLabel lbl_pass = new JLabel("Password: ");
		lbl_pass.setBounds(120, 300, 110, 30);
		lbl_pass.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_pass.setFont(f1);

		JPasswordField txt_pass = new JPasswordField();
		txt_pass.setBounds(240, 300, 200, 30);
		txt_pass.setFont(f2);

		txt_userId.setText("Arman");
		txt_pass.setText("123");

		JLabel lbl_forgetPass = new JLabel("forgot password");
		lbl_forgetPass.setForeground(Color.BLUE.brighter());
		lbl_forgetPass.setBounds(330, 340, 100, 30);
		lbl_forgetPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_forgetPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(Login_Screen.this, "Sorry, Arman decided you shall not get access");
			}
		});

		JButtonT1 btn = new JButtonT1("Login", "img\\btn.png", 6);
		btn.setBounds(250, 390, 100, 40);
		btn.setFont(new Font(null, Font.BOLD, 16));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account ac = checkAccountDetails(txt_userId.getText(), txt_pass.getText());
				if (ac != null) {
					MainWindow.account = ac;
					if(ac.getAccountType() == AccountType.Order) {
						Order_Screen window = new Order_Screen(true);
						window.createUI();
						Login_Screen.this.dispose();
					} else {						
						MainWindow window = new MainWindow();
						window.createUI();
						Login_Screen.this.dispose();
					}
				}
			}
		});

		add(lll);
		add(lbl_userId);
		add(txt_userId);
		add(lbl_pass);
		add(txt_pass);
		add(lbl_forgetPass);
		add(btn);

		setVisible(true);
	}

	private void save() {
		File file = new File("Accounts.ASL");
		FileWriter fr;
		try {
			fr = new FileWriter(file, false);
			fr.write("Arman\t123\tSuperAdmin\tArman Mokammel\tarmanmokammel@gmail.com\t12\n");
			
			fr.close();
			
			JOptionPane.showMessageDialog(Login_Screen.this, "Saved Successfully!");
		} catch (IOException e1) {
			System.out.println("SZZ Error");
		}
	}

	private Account checkAccountDetails(String userID, String password) {
		File file = new File("Accounts.ASL");
		Scanner sc;
		String data = "";
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				data = sc.nextLine();
				String datas[] = data.split("\t");
				if (datas[0].equals(userID)) {
					if (datas[1].equals(password)) {
						sc.close();
						return new Account(datas[0], datas[1], AccountType.valueOf(datas[2]), datas[3], datas[4], Integer.parseInt(datas[5]));
					} else {
						sc.close();
						JOptionPane.showMessageDialog(this, "Incorrect Password!");
						return null;
					}
				}
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			System.out.println("SZZ Error");
		}

		JOptionPane.showMessageDialog(this, "Account Not Found!");
		return null;
	}
}
