package Exception;

import javax.swing.*;

public class InputException extends Exception{
	
	public InputException(JTextField component, String message) {
		super(message);
		component.requestFocus();
	}
	
	public InputException(JComboBox<?> component, String message) {
		super(message);
		component.requestFocus();
	}
}
