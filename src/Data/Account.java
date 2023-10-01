package Data;

import Enum.AccountType;

public class Account {
	private String userID;
	private String password;
	private AccountType accountType;
	private String name;
	

	public Account(String userID, String password, AccountType accountType, String name) {
		this.userID = userID;
		this.password = password;
		this.accountType = accountType;
		this.name = name;
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
}
