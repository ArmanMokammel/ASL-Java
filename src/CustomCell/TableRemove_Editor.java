package CustomCell;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import CustomComponents.JPanelX;

public class TableRemove_Editor extends AbstractCellEditor implements TableCellEditor, ActionListener {
	
	private JPanel container;
	private JButton button;
	private JPanelX parent;
	int row;
	
	public TableRemove_Editor(JPanelX parent) {
		this.parent = parent;
		container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setBackground(null);
		
		ImageIcon ic = new ImageIcon("img\\remove.png");
		button = new JButton(ic);
		button.setBackground(null);
		button.addActionListener(this);
		
		container.add(button);
	}
	
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		this.row = row;
		return container;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.stopCellEditing();
		int confirmation = JOptionPane.showConfirmDialog(parent, "Are you sure you want to remove?", "Info", JOptionPane.YES_NO_OPTION);
		if(confirmation == 0)
			parent.removeRow(row);
	}

}
