package CustomCell;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.AbstractCellEditor;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import Data.Discount_Voucher;
import UI.EditRemove_Pane;
import UI.JPanelX;
import UI.MainWindow;
import UI.Voucher_Panel;

public class TableEditRemove_Editor extends AbstractCellEditor implements TableCellEditor {

	private EditRemove_Pane pane;

	public TableEditRemove_Editor(DefaultTableModel model, MainWindow frame, JPanelX parent) {
		pane = new EditRemove_Pane(model, frame, parent, this);
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
		if (isSelected) {
			pane.setBackground(table.getSelectionBackground());
		} else {
			pane.setBackground(table.getBackground());
		}
		return pane;
	}

}
