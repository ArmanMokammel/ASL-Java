package UI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

import Data.Account;
import Enum.AccountType;

public class Login_Screen extends JFrame {

	public Login_Screen(String title) {
		super(title);
	}

	public void createUI() {
		Font f1 = new Font(null, Font.BOLD, 20);
//		save();

		setSize(500, 500);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:\\Dvlp2\\NSU\\NSU-Java\\ASL.Java - Restaurant Management System\\img\\Untitled-1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		JLabel lll = new JLabel();
		lll.setBounds(150, 20, 150, 150);
		Image dimg = img.getScaledInstance(lll.getWidth(), lll.getHeight(),
				Image.SCALE_SMOOTH);
		lll.setIcon(new ImageIcon(dimg));
		//lll.setOpaque(true);
		//lll.setBackground(Color.red);

		JLabel lbl_userId = new JLabel("User ID: ");
		lbl_userId.setBounds(40, 170, 90, 30);
		lbl_userId.setFont(f1);

		JTextField txt_userId = new JTextField();
		txt_userId.setBounds(160, 170, 200, 30);

		JLabel lbl_pass = new JLabel("Password: ");
		lbl_pass.setBounds(40, 220, 110, 30);
		lbl_pass.setFont(f1);

		JTextField txt_pass = new JTextField();
		txt_pass.setBounds(160, 220, 200, 30);

		txt_userId.setText("Arman");
		txt_pass.setText("123");

		JLabel lbl_forgetPass = new JLabel("forgot password");
		lbl_forgetPass.setForeground(Color.BLUE.brighter());
		lbl_forgetPass.setBounds(265, 260, 100, 30);
		lbl_forgetPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lbl_forgetPass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked");

			}
		});

		JButton btn = new JButton("Login");
		btn.setBounds(190, 320, 80, 30);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Account ac = checkAccountDetails(txt_userId.getText(), txt_pass.getText());
				if (ac != null) {
					MainWindow window = new MainWindow(ac.getName(), ac.getAccountType());
					window.createUI();
					Login_Screen.this.dispose();
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

//	private void save() {
//		File file = new File("Accounts.ASL");
//		FileWriter fr;
//		try {
//			fr = new FileWriter(file, false);
//			fr.write("Arman\t123\tSuperAdmin\tArman Mokammel\tarmanmokammel@gmail.com\t12");
//			
//			fr.close();
//			
//			JOptionPane.showMessageDialog(Login_Screen.this, "Saved Successfully!");
//		} catch (IOException e1) {
//			System.out.println("SZZ Error");
//		}
//	}

	private Account checkAccountDetails(String userID, String password) {
		File file = new File("Accounts.ASL");
		Scanner sc;
		String data = "";
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				data = sc.nextLine();
				String datas[] = data.split("\t");
				System.out.println(data);
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
