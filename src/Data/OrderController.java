package Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import UI.OrderSystem.Panel_A;
import Utilities.Utility;

public class OrderController {
	
	private static Order order;
	
	private static int lastOrderNo;
	
	public static void init(String branch, String accountId) {
		order = new Order();
		order.setBranch(branch);
		order.setAccountId(accountId);
		order.setOrderType("Dine-in");
		
		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		ArrayList<String> s = Utility.readFile("Data.ASL");
		
		if(s.size() != 0) {			
			if(s.get(0).equals(dtf.format(date))) {			
				lastOrderNo = Integer.parseInt(s.get(1));
			} else {
				lastOrderNo = 1;
			}
		} else {
			lastOrderNo = 1;
		}
		order.setOrderNo(dtf.format(date) + lastOrderNo);		
	}
	
	public static Order getOrder() {
		return order;
	}
	
	public static void suspendCurrentOrder() {
		Utility.writeToFile("Suspened Orders\\" + order.getOrderNo() + ".txt", false, order);
		
	}
	
	public static void reinstateOrder(Order ord) {
		order = Order.clone(ord);
	}

	public static void incrementOrder() {
		lastOrderNo++;
		LocalDate date = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		Utility.writeToFile("Data.ASL", false, dtf.format(date) + "\n" + lastOrderNo + "\n12" + "\n");
		order.setOrderNo(dtf.format(date) + lastOrderNo);
		Panel_A.txt_orderNo.setText(dtf.format(date) + lastOrderNo);
		OrderController.getOrder().setOrderNo(dtf.format(date) + lastOrderNo);
	}
	
	public static void resetOrder() {
		order.getItems().clear();
		order.getPayments().clear();
		order.setCustomer(null);
		order.setSubTotal(0);
		order.setTotal(0);
		order.setAmountPaid(0);
		order.setAmountDue(0);
	}
}
