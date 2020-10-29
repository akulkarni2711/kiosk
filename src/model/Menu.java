package model;
import java.util.HashMap;

public class Menu {
	
	
	private static HashMap<Integer, Item> menuItems;
	private static Menu instance;
	
	
	public Menu() {
		
	}
	
	public static Menu getInstance() {
		if (instance == null) {
			instance = new Menu();
		}
		return instance;
	}
	
	public boolean hasItem(int itemID) {
		if (menuItems.containsKey(itemID)) {
			return true;
		}
		return false;
	}
	
	public Item getItem(int itemID) {
		return menuItems.get(itemID);
	}
	
	public boolean addMenuItem(Item item) {
		return true;
	}
	
	public HashMap<Integer, Item> getHashMap() {
		return menuItems;
	}

}
