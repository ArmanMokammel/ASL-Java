package CustomCell;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class TableRemove_Editor extends AbstractCellEditor implements TableCellEditor {
	
	private JPanel container;
	private JButton button;
	
	public TableRemove_Editor() {
		container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setBackground(null);
		
		ImageIcon ic = new ImageIcon("img\\remove.png");
		button = new JButton(ic);
		button.setBackground(null);
		
		container.add(button);
	}
	
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return container;
	}

}
