package UI;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditRemove_Pane extends JPanel{
	
	private JButton edit;
	private JButton delete;
	public int row;
	
	public EditRemove_Pane(DefaultTableModel model, MainWindow frame, JPanelX parent, AbstractCellEditor editor){
        setLayout(new GridBagLayout());
        edit = new JButton("Edit");
        delete = new JButton("Remove");
        
        edit.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				parent.editRow(row);
			}
		});
        
        delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.stopCellEditing();
				parent.removeRow(row);
			}
		});
        
        add(edit);
        add(delete);
	}
	
	public void addActionListener(ActionListener listener) {
        edit.addActionListener(listener);
        delete.addActionListener(listener);
    }
}
