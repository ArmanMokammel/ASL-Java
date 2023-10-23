package UI;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import Data.Customer;

public abstract class JPanelX extends JPanel{
	
	public LinkedList<Customer> customerList = new LinkedList<Customer>();
	public DefaultTableModel model = new DefaultTableModel();

	public abstract void editRow(int row);
	public abstract void removeRow(int row);
}
