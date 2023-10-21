package Data;

import Enum.DiscountType;

public class MenuItem {
	
	private int itemId;
	private String category;
	private String itemName;
	private double costPrice;
	private double sellingPrice;
	private DiscountType discountType;
	private double discountValue;
	
	public MenuItem(int itemId, String category, String itemName, double costPrice, double sellingPrice, DiscountType discountType, double discountValue) {
		this.itemId = itemId;
		this.category = category;
		this.itemName = itemName;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
		this.discountType = discountType;
		this.discountValue = discountValue;
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
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public double getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(double discountValue) {
		this.discountValue = discountValue;
	}

	public String toString() {
		return itemId + "\t" + category + "\t" + itemName + "\t" + costPrice + "\t" + sellingPrice + "\t" + discountType + "\t" + discountValue + "\n";
	}
}
