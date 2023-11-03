package Data;

public class Discount_Voucher {
	
	private int voucherId;
	private String voucher;
	private double value;
	
	public Discount_Voucher(int voucherId, String voucher, double value) {
		this.voucherId = voucherId;
		this.voucher = voucher;
		this.value = value;
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
		return voucherId + "\t" + voucher + "\t" + value + "\n";
	}
}
