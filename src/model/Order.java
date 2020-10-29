package model;
import java.util.Date;
import java.util.HashMap;
import java.util.Calendar;

public class Order {
	
	private static long orderCounter = 0;
	
	private Date date = new Date();
	private double totalCost;
	private long orderNumber;
	HashMap<Integer,Integer> itemsOrdered;
	private Menu m;
	
	public Order() {
		this.orderNumber = orderCounter++;
		totalCost = 0;
		itemsOrdered = new HashMap<Integer,Integer>();
	}
	
	public boolean addItem(int itemID, int quantity) {
		
		if (m.hasItem(itemID)) {
			if (itemsOrdered.containsKey(itemID)) {
				itemsOrdered.replace(itemID, itemsOrdered.get(itemID) + quantity);
			}
			else {
				itemsOrdered.put(itemID, quantity);
			}
			totalCost += m.getItem(itemID).getCost() * quantity;
			return true;
		}
		return false;
	}
	
	public boolean removeItem(int itemID) {
		
		if (itemsOrdered.containsKey(itemID)) {
			int q = itemsOrdered.get(itemID);
			itemsOrdered.remove(itemID);
			totalCost -= m.getItem(itemID).getCost() * q;
		
			return true;
		}
		return false;
	}
	
	public void removeAll() {
		itemsOrdered.clear();
	}
	
	
	public double getTotalCost() {
		return totalCost;
	}
	
	public long getOrderNumber() {
		return orderNumber;
	} 
}
