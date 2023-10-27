package UI;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EditRemove_Pane extends JPanel{
	
	private JButton edit;
	private JButton delete;
	public int row;
	
	public EditRemove_Pane(DefaultTableModel model, JPanelX parent, AbstractCellEditor editor){
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 3));
		setBackground(null);
		
		ImageIcon ic1 = new ImageIcon("img\\edit.png");
		ImageIcon ic2 = new ImageIcon("img\\remove.png");
		
        edit = new JButton(ic1);
        delete = new JButton(ic2);
        
        edit.setBackground(null);
        delete.setBackground(null);
        
        edit.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				parent.editRow(row);
			}
		});
        
        delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editor.stopCellEditing();
				int confirmation = JOptionPane.showConfirmDialog(parent, "Are you sure you want to remove?", "Info", JOptionPane.YES_NO_OPTION);
				if(confirmation == 0)
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
