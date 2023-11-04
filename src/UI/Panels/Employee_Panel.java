package UI.Panels;

import java.awt.Color;
import java.awt.Font;
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
import CustomComponents.JButtonT1;
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
		setBounds(30, 150, window.getWidth() - 70, 685);
		setOpaque(false);
		
		Font f1 = new Font(null, Font.BOLD, 16);
		Font f2 = new Font(null, Font.PLAIN, 16);
		
		JButton btn_Add = new JButtonT1("New Employee", "img\\btn.png", 6);
		btn_Add.setBounds(getWidth() - 260, 6, 150, 50);
		btn_Add.setFont(new Font(null, Font.BOLD, 16));
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
		table.setBackground(new Color(253, 253, 214));
		table.setFont(f2);

		JTableHeader tableHeader = table.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		tableHeader.setBackground(new Color(117, 68, 0));
		tableHeader.setForeground(Color.white);
		tableHeader.setFont(f1);

		TableEditRemove_Renderer renderer = new TableEditRemove_Renderer();
		table.getColumnModel().getColumn(7).setCellRenderer(renderer);
		table.getColumnModel().getColumn(7).setCellEditor(new TableEditRemove_Editor(this));
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(70, 76, getWidth() - 180, 600);
				
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
