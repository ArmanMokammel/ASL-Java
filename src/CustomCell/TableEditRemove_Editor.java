package CustomCell;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import CustomComponents.EditRemove_Pane;
import UI.JPanelX;

public class TableEditRemove_Editor extends AbstractCellEditor implements TableCellEditor {

	private EditRemove_Pane pane;

	public TableEditRemove_Editor(JPanelX parent) {
		pane = new EditRemove_Pane(parent, this);
		pane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						stopCellEditing();
					}
				});
			}
		});
	}

	@Override
	public Object getCellEditorValue() {
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		pane.row = row;
		return pane;
	}

}
