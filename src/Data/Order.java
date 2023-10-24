package Data;

import java.util.LinkedList;

public class Order {
	
	private String branch;
	private int orderNo;
	private String accountId;
	
	private Customer customer;
	private LinkedList<OrderMenuItem> items;
	private LinkedList<Payment> payments;
		
	private double total;
	private double amountPaid;
	
	public Order(String branch, int orderNo, String accountId) {
		this.branch = branch;
		this.orderNo = orderNo;
		this.accountId = accountId;
	}

	public String getBranch() {
		return branch;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public String getAccountId() {
		return accountId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	
	public LinkedList<OrderMenuItem> getItems() {
		return items;
	}

	public void setItems(LinkedList<OrderMenuItem> items) {
		this.items = items;
	}

	public LinkedList<Payment> getPayments() {
		return payments;
	}

	public void setPayments(LinkedList<Payment> payments) {
		this.payments = payments;
	}
	
	public String toString() {
		return customer.toString() + items.toString() + payments.toString();
	}
}
