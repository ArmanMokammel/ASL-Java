package Data;

public class Payment {
	
	private String paymentType;
	private double amount;
	
	public Payment(String paymentType, double amount) {
		this.paymentType = paymentType;
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String toString() {
		return paymentType + "\t" + amount + "\n";
	}
}
