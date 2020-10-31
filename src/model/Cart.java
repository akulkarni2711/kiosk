package model;
import java.util.LinkedHashMap;
import java.util.Calendar;
import GUI.Kiosk;
import java.util.ArrayList;



public class Cart {
	
	private static long orderCounter = 0;
	
	private double totalCost;
	private long orderNumber;
	public static LinkedHashMap<Integer,Integer> itemsOrdered;
	public ArrayList<Integer> stuff = new ArrayList<Integer>(100);
	private Menu m;
	
	public Cart() {
		this.orderNumber = orderCounter++;
		totalCost = 0;
		itemsOrdered = new LinkedHashMap<Integer,Integer>();
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
			stuff.add(itemID);
			return true;
		}
		return false;
	}
	
	public void removeItem(int itemID) {
		
		if (itemsOrdered.containsKey(itemID)) {
			int q = itemsOrdered.get(itemID);
			itemsOrdered.remove(itemID);
			totalCost -= m.getItem(itemID).getCost() * q;
			int position = stuff.indexOf(itemID);
			stuff.remove(position);
		}
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
	
	public LinkedHashMap<Integer, Integer> getHashMap() {
		return itemsOrdered;
	}
	
}
