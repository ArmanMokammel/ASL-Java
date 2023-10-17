package DataEditorUI;

import javax.swing.JDialog;
import javax.swing.JTextField;

import UI.Account_Panel;
import UI.Customer_Panel;
import UI.MainWindow;

public class CustomerEditor_Dialog extends JDialog{
	
	public CustomerEditor_Dialog(MainWindow frame, Customer_Panel parent, String title, int row) {
		super(frame, title, true);
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
	}
}
