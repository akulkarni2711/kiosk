package controller;


import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Image;
import java.math.BigDecimal;

import javax.swing.JOptionPane;

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
import placeholders.PHTF;
import placeholders.PHTF;


public class ViewManager {
	
	private static Container views;
	private static Employee activeEmployee;
	private static Item activeItem;
	private static Menu m;
	private static Cart c;
	
	public ViewManager(Container views) {
		this.views = views;
		this.activeEmployee = null;
		this.activeItem = null;
		m = Menu.getInstance();
		c = new Cart();
	}
	
	public Employee getActiveEmployee() {
		return activeEmployee;
	}
	
	public void switchTo(String view) {
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	public void employeeLogin(Long employeeID, int pin) {
		LoginView lv = ((LoginView) views.getComponents()[Kiosk.LOGIN_VIEW_INDEX]);
		
		try {
			activeEmployee = Kiosk.lookupUser(employeeID, pin);
			
			if (activeEmployee == null) {
				JOptionPane.showMessageDialog(null, "Please enter a valid ID and password, or login as a guest",
						"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
			}
			else {
				((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).updateCard();
				
				switchTo(Kiosk.EMPLOYEE_MENU_VIEW);
			}
		} catch (NumberFormatException e) {
			
		}
	}
	
	public void guestLogin() {
		Cart.itemsOrdered.clear();
		switchTo(Kiosk.MENU_VIEW);
	}
	
	public void logOut() {
		LoginView.pinField.setText("");
		LoginView.IDField.setText("");
		activeEmployee = null;
		Cart.itemsOrdered.clear();
		Cart.totalCost = 0;
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
		switchTo(Kiosk.EMPLOYEE_ITEM_VIEW);
	}
	
	public void goToCart() {
		((CartView) views.getComponents()[Kiosk.CART_VIEW_INDEX]).updateCard();
		switchTo(Kiosk.CART_VIEW);
	}
	
	public void goToCheckout() {
		((CheckoutView) views.getComponents()[Kiosk.CHECKOUT_VIEW_INDEX]).updateCard();
		switchTo(Kiosk.CHECKOUT_VIEW);
	} 
	
	public void goToMenu() {
		((MenuView) views.getComponents()[Kiosk.MENU_VIEW_INDEX]).updateCard();
		switchTo(Kiosk.MENU_VIEW);
	}
	
	public Item getActiveItem() {
		return activeItem;
	}
	
	public void addItem(int itemID, int quantity) {
		JOptionPane.showMessageDialog(null, "Item succesfully added from cart",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
		Kiosk.cart.addItem(itemID, quantity);
	}
	
	public void removeItemFromMenu(Item item) {
    	int itemID = item.getItemID();
		Menu.removeMenuItem(itemID);
    	switchTo("EMPLOYEE_MENU_VIEW");
	}
	
	public void removeItemFromCart(int itemID) {
		c.removeItem(itemID);
		JOptionPane.showMessageDialog(null, "Item succesfully removed from cart",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((CartView) views.getComponents()[Kiosk.CART_VIEW_INDEX]).removeAll();
        ((CartView) views.getComponents()[Kiosk.CART_VIEW_INDEX]).updateCard();
        ((CartView) views.getComponents()[Kiosk.CART_VIEW_INDEX]).revalidate();
        ((CartView) views.getComponents()[Kiosk.CART_VIEW_INDEX]).repaint();
		
	}
	
	public void changeItemName(String newItemName, Item item) {
		item.setName(newItemName);
		JOptionPane.showMessageDialog(null, "Item name succesfully changed",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).removeAll();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).updateCard();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).revalidate();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).repaint();
	}
	
	public void changeItemPrice(double newItemPrice, Item item) {
		item.setCost(newItemPrice);
		
		JOptionPane.showMessageDialog(null, "Item price succesfully changed",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).removeAll();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).updateCard();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).revalidate();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).repaint();
	}
	
	public void changeItemDescription(String newItemDescription, Item item) {
		item.setDescription(newItemDescription);
		
		JOptionPane.showMessageDialog(null, "Item description succesfully changed",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).removeAll();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).updateCard();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).revalidate();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).repaint();
	}
	
	public void changeItemPicture(Image newItemPicture, Item item) {
		item.setPicture(newItemPicture);
		
		JOptionPane.showMessageDialog(null, "Item picture succesfully changed",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).removeAll();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).updateCard();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).revalidate();
        ((EmployeeItemView) views.getComponents()[Kiosk.EMPLOYEE_ITEM_VIEW_INDEX]).repaint();
	}
	
	public void addNewItem() {
		m.addMenuItem(new Item(null, 0, null, null));
	}

}
