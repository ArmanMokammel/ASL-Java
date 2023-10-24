package Data;

public class OrderMenuItem{
	
	private MenuItem item;
	private int quantity;
	
	public OrderMenuItem(MenuItem item, int quantity) {
		this.item = item;
		this.quantity = quantity;
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
	
	public String toString() {
		return item.toString() + quantity;
	}
}
