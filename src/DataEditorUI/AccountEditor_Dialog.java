package DataEditorUI;

import javax.swing.*;

import UI.*;

public class AccountEditor_Dialog extends JDialog{
	
	public AccountEditor_Dialog(MainWindow frame, Account_Panel parent, String title, int row) {
		super(frame, title, true);
		setSize(500,500);
		setLayout(null);
		setLocationRelativeTo(null);
	}

}
