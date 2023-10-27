package Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;

import Utilities.Utility;

public class Order {
	
	private static String branch;
	private static String orderNo;
	private static String accountId;
	
	private static Customer customer;
	private static LinkedList<OrderMenuItem> items;
	private static LinkedList<Payment> payments;
		
	private static double total = 0;
	private static double amountPaid = 0;
	
	private static int lastOrderNo;
	
	public static void init(String branch, String accountId) {
		Order.branch = branch;
		Order.accountId = accountId;
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		ArrayList<String> s = Utility.readFile("Data.ASL");
		
		if(s.size() != 0) {			
			if(s.get(0).equals(dtf.format(date))) {			
				Order.lastOrderNo = Integer.parseInt(s.get(1));
			} else {
				Order.lastOrderNo = 1;
			}
		} else {
			Order.lastOrderNo = 1;
		}
		Order.orderNo = dtf.format(date) + lastOrderNo;		
		
		items = new LinkedList<OrderMenuItem>();
		payments = new LinkedList<Payment>();
	}

	public static String getBranch() {
		return branch;
	}

	public static String getOrderNo() {
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
	
	public static void incrementOrder() {
		lastOrderNo++;
		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		Utility.writeToFile("Data.ASL", false, dtf.format(date) + "\n" + lastOrderNo + "\n12" + "\n");
		Order.orderNo = dtf.format(date) + lastOrderNo;
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
