package Utilities;

import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import Enum.InputType;
import Exception.InputException;

public class Utility {
	
	public static String checkString(JTextField component, JLabel label, InputType inputType) throws InputException {
		if(component.getText().isBlank())
		{
			throwException(component, "Error at " + label.getText() + "\n" + "Please enter a value");
		}
		
		Pattern digit = Pattern.compile("[0-9]");
		Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
		Pattern special2 = Pattern.compile ("[!#$%&*()_+=|<>?{}\\[\\]~-]");
		
		Matcher hasDigit =  digit.matcher(component.getText());
		Matcher hasSpecial =  special.matcher(component.getText());
		Matcher hasSpecial2 =  special2.matcher(component.getText());
		
		if(inputType == InputType.Alphabetic) {
			if(hasDigit.find() || hasSpecial.find()) {
				throwException(component, "Error at " + label.getText() + "\n" + "Digits and Special characters not allowed");
			}
		}
		
		if(inputType == InputType.Alphanumeric) {
			if(hasSpecial.find()) {
				throwException(component, "Error at " + label.getText() + "\n" + "Special characters not allowed");
			}
		}
		
		if(inputType == InputType.Email) {
			if(!component.getText().contains("@") || !component.getText().contains(".") || hasSpecial2.find()) {
				throwException(component, "Error at " + label.getText() + "\n" + "Invalid email type");
			}
		}

		component.setBorder(new LineBorder(Color.green));
		return component.getText();
	}
	
	public static String checkString(JComboBox<?> component, JLabel label) throws InputException {
		if(component.getSelectedItem() == null)
		{
			throwException(component, "Error at " + label.getText() + "\n" + "Please enter a value");
		}
		component.setBorder(new LineBorder(Color.green));
		return component.getSelectedItem().toString();
	}
	
	public static int checkInt(JTextField component, JLabel label) throws InputException {
		if(component.getText().isBlank())
		{
			throwException(component, "Error at " + label.getText() + "\n" + "Please enter a value");
		}
		else if(tryParseInt(component.getText())) {
			component.setBorder(new LineBorder(Color.green));
			return Integer.parseInt(component.getText());
		}
		else {
			throwException(component, "Error at " + label.getText() + "\n" + "Integer Values Only");
		}
		return -1;
	}
	
	public static double checkDouble(JTextField component, JLabel label) throws InputException {
		if(component.getText().isBlank())
		{
			throwException(component, "Error at " + label.getText() + "\n" + "Please enter a value");
		}
		else if(tryParseDouble(component.getText())) {
			component.setBorder(new LineBorder(Color.green));
			return Double.parseDouble(component.getText());
		}
		else {
			throwException(component, "Error at " + label.getText() + "\n" + "Double Values Only");
		}
		return -1;
	}
	
	public static void showErrorMessage(Exception ex) {
		Window activeWindow = FocusManager.getCurrentManager().getActiveWindow();
		JOptionPane.showMessageDialog(activeWindow, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private static void throwException(JTextField component, String message) throws InputException {
		component.setBorder(new LineBorder(Color.red));
		throw new InputException(component, message);
	}
	
	private static void throwException(JComboBox<?> component, String message) throws InputException {
		component.setBorder(new LineBorder(Color.red));
		throw new InputException(component, message);
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
	
	public static void writeAllToFile(String fileName, boolean append, List<?> obj) {
		File file = new File(fileName);
		FileWriter fr;
		boolean string = false;
		try {
			fr = new FileWriter(file, append);
			for(Object object : obj) {
				if(object instanceof String) {
					string = true;
					fr.write(object.toString());
				}
				else {					
					fr.write(object.toString());
				}
			}
			
			if(string) {
				fr.write("\n");
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
			System.out.println("File not found");
			return data;
		}
	}
	
	public static ImageIcon getImageIcon(String file, int width, int height) {
		Image img = null;
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(dimg);
	}
	
	public static Image getImage(String file, int width, int height) {
		Image img = null;
		try {
			img = ImageIO.read(new File(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
