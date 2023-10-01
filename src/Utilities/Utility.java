package Utilities;

import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.*;

public class Utility {
	
	public static String checkString(JTextField component, JLabel label) throws Exception {
		if(component.getText().isBlank())
		{
			component.requestFocus();
			throw new Exception("Error at " + label.getText() + "\n" + "Please enter a value");
		}
		return component.getText();
	}
	
	public static String checkString(JComboBox<?> component, JLabel label) throws Exception {
		if(component.getSelectedItem().toString().isBlank())
		{
			component.requestFocus();
			throw new Exception("Error at " + label.getText() + "\n" + "Please enter a value");
		}
		return component.getSelectedItem().toString();
	}
	
	public static int checkInt(JTextField component, JLabel label) throws Exception {
		if(component.getText().isBlank())
		{
			component.requestFocus();
			throw new Exception("Error at " + label.getText() + "\n" + "Please enter a value");
		}
		else if(tryParseInt(component.getText())) {
			return Integer.parseInt(component.getText());
		}
		else {
			component.requestFocus();
			throw new Exception("Error at " + label.getText() + "\n" + "Integer Values Only");
		}
	}
	
	public static double checkDouble(JTextField component, JLabel label) throws Exception {
		if(component.getText().isBlank())
		{
			component.requestFocus();
			throw new Exception("Error at " + label.getText() + "\n" + "Please enter a value");
		}
		else if(tryParseDouble(component.getText())) {
			return Double.parseDouble(component.getText());
		}
		else {
			component.requestFocus();
			throw new Exception("Error at " + label.getText() + "\n" + "Double Values Only");
		}
	}
	
	public static void showErrorMessage(Exception ex) {
		Window activeWindow = FocusManager.getCurrentManager().getActiveWindow();
		JOptionPane.showMessageDialog(activeWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private static boolean tryParseInt(String s) {
		try {
			Integer.parseInt(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	private static boolean tryParseDouble(String s) {
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static void writeToFile(String fileName, boolean append, Object obj) {
		File file = new File(fileName);
		FileWriter fr;
		try {
			fr = new FileWriter(file, append);
			fr.write(obj.toString());
			
			fr.close();
			
		} catch (IOException e1) {
			System.out.println("SZZ Error");
		}
	}
	
	public static void writeAllToFile(String fileName, boolean append, LinkedList<?> obj) {
		File file = new File(fileName);
		FileWriter fr;
		try {
			fr = new FileWriter(file, append);
			for(Object object : obj) {
				fr.write(object.toString());
			}
			
			fr.close();
			
		} catch (IOException e1) {
			System.out.println("SZZ Error");
		}
	}
	
	public static ArrayList<String> readFile(String fileName) {
		File file = new File(fileName);
		Scanner sc;
		ArrayList<String> data = new ArrayList<String>();
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				data.add(sc.nextLine());
			}
    		sc.close();
    		return data;
		} catch (FileNotFoundException e1) {
			System.out.println("SZZ Error");
			return data;
		}
	}
}
