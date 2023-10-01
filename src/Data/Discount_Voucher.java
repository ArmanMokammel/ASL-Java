package Data;

public class Discount_Voucher {
	
	private String customer;
	private int voucherId;
	private String voucher;
	private double value;
	
	public Discount_Voucher(String customer, int voucherId, String voucher, double value) {
		this.customer = customer.equals(null) ? "" : customer;
		this.voucherId = voucherId;
		this.voucher = voucher;
		this.value = value;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public int getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(int voucherId) {
		this.voucherId = voucherId;
	}

	public String getVoucher() {
		return voucher;
	}

	public void setVoucher(String voucher) {
		this.voucher = voucher;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public String toString() {
		return customer + "\t" + voucherId + "\t" + voucher + "\t" + value + "\n";
	}
}
