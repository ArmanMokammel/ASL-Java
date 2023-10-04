package Exception;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.LineBorder;

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
