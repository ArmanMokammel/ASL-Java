package Data;

public class OrderMenuItem{
	
	private MenuItem item;
	private int quantity;
	private double discountedPrice;
	
	public OrderMenuItem(MenuItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public OrderMenuItem(MenuItem item, double discountedPrice, int quantity) {
		this.item = item;
		this.quantity = quantity;
		this.discountedPrice = discountedPrice;
	}
	
	public MenuItem getItem() {
		return item;
	}


	public void setItem(MenuItem item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String toString() {
		return item.toString().substring(0, item.toString().length() - 1) + "\t" + discountedPrice + "\t" + quantity + "\n";
	}
}
