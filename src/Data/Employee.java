package Data;

public class Employee {
	
	private int employeeId;
	private String employeeName;
	private String gender;
	private String phoneNo;
	private String email;
	private String accountId;
	
	public Employee(int employeeId, String employeeName, String gender, String phoneNo, String email, String accountId) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.gender = gender;
		this.phoneNo = phoneNo;
		this.email = email;
		this.accountId = accountId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	public String toString() {
		return employeeId + "\t" + employeeName + "\t" + gender + "\t" + phoneNo + "\t" + email + "\t" + accountId + "\n";
	}
}
