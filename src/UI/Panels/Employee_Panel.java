package UI.Panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import CustomCell.TableEditRemove_Editor;
import CustomCell.TableEditRemove_Renderer;
import CustomComponents.JPanelX;
import Data.Employee;
import DataEditorUI.EmployeeEditor_Dialog;
import UI.MainWindow;
import Utilities.Utility;

public class Employee_Panel extends JPanelX{
	
	public DefaultTableModel model;
	public LinkedList<Employee> employeeList;
	private MainWindow window;
	
	public Employee_Panel(MainWindow window) {
		this.window = window;
		employeeList = new LinkedList<Employee>();
		setLayout(null);
		setBounds(30, 150, window.getWidth() - 70, 660);
		setBackground(new Color(0,0,0,0));
		
		JButton btn_Add = new JButton("New Employee");
		btn_Add.setBounds(getWidth() - 245, 0, 130, 40);
		btn_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeeEditor_Dialog dialog = new EmployeeEditor_Dialog(window, Employee_Panel.this,"New Employee", -1);
				dialog.setVisible(true);
			}
		});
		
		model = new DefaultTableModel();
		model.addColumn("SL");
		model.addColumn("Employee ID");
		model.addColumn("Employee Name");
		model.addColumn("Gender");
		model.addColumn("Phone No");
		model.addColumn("Email");
		model.addColumn("Account ID");
		model.addColumn("");
		
		JTable table = new JTable(model) {
			@Override
			public boolean isCellEditable(int row, int column) {
				if(column == 7)
					return true;
				else
					return false;
			}
		};
		
		table.setRowHeight(40);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setBackground(new Color(117, 68, 0));
		tableHeader.setForeground(Color.white);

		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(7).setCellRenderer(renderer);
		table.getColumnModel().getColumn(7).setCellEditor(new TableEditRemove_Editor(this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 60, getWidth() - 180, 595);
				
		add(btn_Add);
		add(sp);
		
		ArrayList<String> lines = Utility.readFile("Employees.ASL");
		for(String line: lines) {
			String[] datas = line.split("\t");
			employeeList.add(new Employee(Integer.parseInt(datas[0]), datas[1], datas[2], datas[3], datas[4], datas[5]));
			model.addRow(new Object[] {employeeList.size(), datas[0], datas[1], datas[2], datas[3], datas[4], datas[5]});
		}
	}

	@Override
	public void editRow(int row) {
		Employee employee = (Employee) employeeList.get(row);
		EmployeeEditor_Dialog dialog = new EmployeeEditor_Dialog(window, this, "Edit Employee" , row);
		dialog.setEmployeeDetails(employee.getEmployeeId(), employee.getEmployeeName(), employee.getGender(), employee.getPhoneNo(), employee.getEmail(), employee.getAccountId());
		dialog.setVisible(true);
	}

	@Override
	public void removeRow(int row) {
		model.removeRow(row);
		employeeList.remove(row);
		for(int i = 0; i < employeeList.size(); i++) {
			model.setValueAt(i+1, i, 0);
		}
		Utility.writeAllToFile("Employees.ASL", false, employeeList);
	}

}
