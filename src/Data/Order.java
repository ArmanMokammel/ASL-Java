package Data;

import java.util.LinkedList;

public class Order {
	
	private String branch;
	private String orderNo;
	private String accountId;
	private String orderType;
	
	private Customer customer;
	private LinkedList<OrderMenuItem> items = new LinkedList<OrderMenuItem>();;
	private LinkedList<Payment> payments = new LinkedList<Payment>();;
		
	private double subTotal = 0;
	private double total = 0;
	private double amountPaid = 0;
	private double amountDue = 0;

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getBranch() {
		return branch;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
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
	
	public double getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public LinkedList<OrderMenuItem> getItems() {
		return items;
	}

	public void addItem(OrderMenuItem item) {
		this.items.add(item);
	}
	
	public void removeItem(int index) {
		this.items.remove(index);
	}

	public LinkedList<Payment> getPayments() {
		return payments;
	}

	public void addPayment(Payment payment) {
		this.payments.add(payment);
	}
	
	public void removePayment(int index) {
		this.payments.remove(index);
	}
		
	public String toString() {
		String s = items.size() + "\t" + payments.size() + "\n";
		
		s += branch + "\t" + orderNo + "\t" + accountId + "\n" + (customer == null? "None\n" : customer);
		
		s += subTotal + "\t" + total + "\t" + amountPaid + "\n";
		
		for(OrderMenuItem item : items) {
			s += item;
		}
		
		for(Payment payment : payments) {
			s += payment;
		}
		
		
		return s;
	}
	
	public static Order clone(Order order) {
		Order ord = new Order();
		ord.branch = order.getBranch();
		ord.orderNo = order.getOrderNo();
		ord.accountId = order.getAccountId();
		ord.customer = order.getCustomer();
		ord.items = new LinkedList<OrderMenuItem>(order.getItems());
		ord.payments = new LinkedList<Payment>(order.getPayments());
		ord.subTotal = order.getSubTotal();
		ord.total = order.getTotal();
		ord.amountPaid = order.getAmountPaid();
		ord.amountDue = order.getAmountDue();
		
		return ord;
	}
}
