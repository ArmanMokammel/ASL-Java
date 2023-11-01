package Exception;

import javax.swing.*;

public class InputException extends Exception{
	
	public InputException(JComponent component, String message) {
		super(message);
		component.requestFocus();
	}
}
