package Data;

import java.util.LinkedList;

public class Order {
	
	private static String branch;
	private static int orderNo;
	private static String accountId;
	
	private static Customer customer;
	private static LinkedList<OrderMenuItem> items;
	private static LinkedList<Payment> payments;
		
	private static double total = 0;
	private static double amountPaid = 0;
	
	public static void init(String branch, String accountId) {
		Order.branch = branch;
		Order.accountId = accountId;
		items = new LinkedList<OrderMenuItem>();
		payments = new LinkedList<Payment>();
	}

	public static String getBranch() {
		return branch;
	}

	public static int getOrderNo() {
		return orderNo;
	}

	public static String getAccountId() {
		return accountId;
	}

	public static Customer getCustomer() {
		return customer;
	}

	public static void setCustomer(Customer customer) {
		Order.customer = customer;
	}

	public static double getTotal() {
		return total;
	}

	public static void setTotal(double total) {
		Order.total = total;
	}

	public static double getAmountPaid() {
		return amountPaid;
	}

	public static void setAmountPaid(double amountPaid) {
		Order.amountPaid = amountPaid;
	}
	
	public static LinkedList<OrderMenuItem> getItems() {
		return items;
	}

	public static void addItem(OrderMenuItem item) {
		Order.items.add(item);
	}
	
	public static void removeItem(int index) {
		Order.items.remove(index);
	}

	public static LinkedList<Payment> getPayments() {
		return payments;
	}

	public static void addPayment(Payment payment) {
		Order.payments.add(payment);
	}
	
	public static void removePayment(int index) {
		Order.payments.remove(index);
	}
	
	public static String generateOrderInfo() {
		return Order.customer.toString() + Order.items.toString() + Order.payments.toString();
	}
	
	public static void resetOrder() {
		Order.items.clear();
		Order.payments.clear();
		Order.customer = null;
		Order.total = 0;
		Order.amountPaid = 0;
	}
}
