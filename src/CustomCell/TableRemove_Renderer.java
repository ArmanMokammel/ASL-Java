package CustomCell;

import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableRemove_Renderer extends DefaultTableCellRenderer{
	
	private JPanel container;
	private JButton button;
	
	public TableRemove_Renderer() {
		container = new JPanel();
		container.setLayout(new GridBagLayout());
		container.setBackground(null);
				
		ImageIcon ic = new ImageIcon("img\\remove.png");

		button = new JButton(ic);
		button.setBackground(null);
		
		container.add(button);
	}
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return container;
    }
}
