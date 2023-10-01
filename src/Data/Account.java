package Data;

import Enum.AccountType;

public class Account {
	private String userID;
	private String password;
	private AccountType accountType;
	private String name;
	private String email;
	private int employeeID;
	
	

	public Account(String userID, String password, AccountType accountType, String name, String email, int employeeID) {
		this.userID = userID;
		this.password = password;
		this.accountType = accountType;
		this.name = name;
		this.email = email;
		this.employeeID = employeeID;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserID() {
		return userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return userID + "\t" + password + "\t" + accountType + "\t" + name + "\t" + email + "\t" + employeeID + "\n";
	}
}
