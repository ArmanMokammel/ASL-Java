package CustomCell;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import UI.EditRemove_Pane;

public class TableEditRemove_Renderer extends DefaultTableCellRenderer{
	
	private EditRemove_Pane pane;
	
	public TableEditRemove_Renderer() {
		pane = new EditRemove_Pane(null, null, null);
	}
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//        if (isSelected) {
//        	pane.setBackground(table.getSelectionBackground());
//        } else {
//        	pane.setBackground(table.getBackground());
//        }
        return pane;
    }
}
