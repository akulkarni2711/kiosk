package model;

import java.util.LinkedHashMap;
import java.util.Calendar;
import GUI.Kiosk;
import java.util.ArrayList;

public class Cart {

	private static long orderCounter = 0;

	public static double totalCost;
	private long orderNumber;
	public static LinkedHashMap<Integer, Integer> itemsOrdered;
	private Menu m;

	public Cart() {
		this.orderNumber = orderCounter++;
		totalCost = 0;
		itemsOrdered = new LinkedHashMap<Integer, Integer>();
		m = Menu.getInstance();
	}

	public boolean addItem(int itemID, int quantity) {

		if (m.hasItem(itemID)) {
			if (itemsOrdered.containsKey(itemID)) {
				itemsOrdered.replace(itemID, itemsOrdered.get(itemID) + quantity);
			} else {
				itemsOrdered.put(itemID, quantity);
			}
			totalCost += m.getItem(itemID).getCost() * quantity;
			return true;
		}
		return false;
	}

	public void removeItem(int itemID) {

		if (itemsOrdered.containsKey(itemID)) {
			int q = itemsOrdered.get(itemID);
			itemsOrdered.remove(itemID);
			totalCost -= m.getItem(itemID).getCost() * q;
		}
	}

	public void removeAll() {
		itemsOrdered.clear();
	}

	public static double getTotalCost() {
		return totalCost;
	}

	public long getOrderNumber() {
		return orderNumber;
	}
	
	public int getQuantity(int itemID) {
		return itemsOrdered.get(itemID);
	}

	public LinkedHashMap<Integer, Integer> getHashMap() {
		return itemsOrdered;
	}
}
