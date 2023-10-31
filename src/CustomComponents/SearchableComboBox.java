package CustomComponents;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * A class for filtered combo box.
 */
public class SearchableComboBox extends JComboBox {
	/**
	 * Entries to the combobox list.
	 */
	private List<String> entries;
	public boolean isTyping = false;

	public List<String> getEntries() {
		return entries;
	}

	@Override
	public void updateUI() {
		super.updateUI();
		UIManager.put("ComboBox.squareButton", Boolean.FALSE);
		setUI(new BasicComboBoxUI() {
			@Override
			protected JButton createArrowButton() {
				JButton b = new JButton();
				b.setBorder(BorderFactory.createEmptyBorder());
				b.setVisible(false);
				return b;
			}
		});
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
	}

	public SearchableComboBox(List<String> entries) {
		super(entries.toArray());
		this.entries = entries;
		this.setEditable(true);

		final JTextField textfield = (JTextField) this.getEditor().getEditorComponent();
		/**
		 * Listen for key presses.
		 */
		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						/**
						 * On key press filter the list. If there is "text" entered in text field of
						 * this combo use that "text" for filtering.
						 */
						isTyping = true;
						comboFilter(textfield.getText());
					}
				});
			}
		});
		setSelectedItem(null);
	}

	/**
	 * Build a list of entries that match the given filter.
	 */
	public void comboFilter(String enteredText) {
		List<String> entriesFiltered = new ArrayList<String>();

		for (String entry : getEntries()) {
			if (entry.toLowerCase().contains(enteredText.toLowerCase())) {
				entriesFiltered.add(entry);
			}
		}

		if (entriesFiltered.size() > 0) {
			this.setModel(new DefaultComboBoxModel(entriesFiltered.toArray()));
			this.setSelectedItem(enteredText);
			this.showPopup();
		} else {
			this.hidePopup();
		}
	}
}