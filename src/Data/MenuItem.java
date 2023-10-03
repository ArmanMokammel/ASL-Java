package Data;

public class MenuItem {
	
	private int itemId;
	private String itemName;
	private double costPrice;
	private double sellingPrice;
	
	public MenuItem(int itemId, String itemName, double costPrice, double sellingPrice) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	
	public String toString() {
		return itemId + "\t" + itemName + "\t" + costPrice + "\t" + sellingPrice + "\n";
	}
}
