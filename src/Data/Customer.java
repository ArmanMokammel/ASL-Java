package Data;

import Enum.DiscountType;

public class Customer {
	
	private int customerId;
	private String customerName;
	private String gender;
	private String email;
	private String phoneNo;
	private DiscountType specialDiscountType;
	private double specialDiscount;
	
	public Customer(int customerId, String customerName, String gender, String email, String phoneNo, DiscountType specialDiscountType, double specialDiscount) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.gender = gender;
		this.email = email;
		this.phoneNo = phoneNo;
		this.specialDiscountType = specialDiscountType;
		this.specialDiscount = specialDiscount;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public DiscountType getSpecialDiscountType() {
		return specialDiscountType;
	}

	public void setSpecialDiscountType(DiscountType specialDiscountType) {
		this.specialDiscountType = specialDiscountType;
	}

	public double getSpecialDiscount() {
		return specialDiscount;
	}

	public void setSpecialDiscount(double specialDiscount) {
		this.specialDiscount = specialDiscount;
	}
	
	public String toString() {
		return customerId + "\t" + customerName + "\t" + gender + "\t" + email + "\t" + phoneNo + "\t" + specialDiscountType + "\t" + specialDiscount + "\n";
	}
}
