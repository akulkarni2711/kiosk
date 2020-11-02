package controller;


import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Image;
import java.math.BigDecimal;

import GUI.Kiosk;
import model.Employee;
import model.Item;
import model.Menu;
import model.Cart;
import views.MenuView;
import views.LoginView;
import views.ItemView;
import views.EmployeeItemView;
import views.EmployeeMenuView;
import views.CheckoutView;
import views.CartView;


public class ViewManager {
	
	private static Container views;
	private static Employee activeEmployee;
	private static Item activeItem;
	private static Menu m;
	
	public ViewManager(Container views) {
		this.views = views;
		this.activeEmployee = null;
		this.activeItem = null;
		m = Menu.getInstance();
	}
	
	public Employee getActiveEmployee() {
		return activeEmployee;
	}
	
	public static void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	public void employeeLogin(Long employeeID, int pin) {
		LoginView lv = ((LoginView) views.getComponents()[Kiosk.LOGIN_VIEW_INDEX]);
		
		try {
			activeEmployee = Kiosk.lookupUser(employeeID, pin);
			
			if (activeEmployee == null) {
				lv.toggleErrorMessage(true);
			}
			else {
				//((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).updateCard(activeEmployee);
				
				switchTo(Kiosk.EMPLOYEE_MENU_VIEW);
				lv.clear();
			}
		} catch (NumberFormatException e) {
			lv.toggleErrorMessage(true);
		}
	}
	
	public void guestLogin() {
		((MenuView) views.getComponents()[Kiosk.MENU_VIEW_INDEX]).updateCard();
		Cart.itemsOrdered.remove(1);
		switchTo(Kiosk.MENU_VIEW);
	}
	
	public static void logOut() {
		activeEmployee = null;
		Cart.itemsOrdered.clear();
		switchTo(Kiosk.LOGIN_VIEW);
	}
	
	public void goToItem(int activeItemID) {
		activeItem = m.getItem(activeItemID);
		((ItemView) views.getComponents()[Kiosk.ITEM_VIEW_INDEX]).updateCard();
		switchTo(Kiosk.ITEM_VIEW);
	}
	
	public void goToItemEmployee(int activeItemID) {
		activeItem = m.getItem(activeItemID);
		((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).updateCard();
	}
	
	public void goToCart() {
		((CartView) views.getComponents()[Kiosk.CART_VIEW_INDEX]).init();
		switchTo(Kiosk.CART_VIEW);
	}
	
	public void goToCheckout() {
		((CheckoutView) views.getComponents()[Kiosk.CHECKOUT_VIEW_INDEX]).updateCard();
		switchTo(Kiosk.CHECKOUT_VIEW);
	} 
	
	public Item getActiveItem() {
		return activeItem;
	}
	
	public void addItem(int itemID, int quantity) {
		if (Kiosk.cart.addItem(itemID, quantity)) {
			Kiosk.cart.addItem(itemID, quantity);
		}
		else {
			
		}
	}
	
	public static void removeItemFromMenu(Item item) {
    	int itemID = item.getItemID();
		Menu.removeMenuItem(itemID);
    	ViewManager.switchTo("EMPLOYEE_MENU_VIEW");
	}
	
	public static void changeItemName(String newItemName, Item item) {
		item.setName(newItemName);
	}
	
	public static void changeItemPrice(double newItemPrice, Item item) {
		item.setCost(newItemPrice);
	}
	
	public static void changeItemDescription(String newItemDescription, Item item) {
		item.setDescription(newItemDescription);
	}
	
	public static void changeItemPicture(Image newItemPicture, Item item) {
		item.setPicture(newItemPicture);;
	}

}
