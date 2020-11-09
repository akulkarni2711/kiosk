package controller;


import java.awt.AlphaComposite;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
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
import views.EmployeeView;
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
				((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).updateCard();
				
				switchTo(Kiosk.EMPLOYEE_VIEW);
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
		if (Cart.totalCost <= 0) {
			JOptionPane.showMessageDialog(null, "You can go to an empty cart",
					"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
		} else {
			((CartView) views.getComponents()[Kiosk.CART_VIEW_INDEX]).updateCard();
			switchTo(Kiosk.CART_VIEW);
		}

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
    	((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).removeAll();
        ((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).updateCard();
        ((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).revalidate();
        ((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).repaint();
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
	
	public void changeEmployeeFirstName(String newFirstName, Employee employee) {
		employee.setFirstName(newFirstName);
		
		JOptionPane.showMessageDialog(null, "Employee first name succesfully changed",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).removeAll();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).updateCard();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).revalidate();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).repaint();
	}
	
	public void changeEmployeeLastName(String newLastName, Employee employee) {
		employee.setLastName(newLastName);
		
		JOptionPane.showMessageDialog(null, "Employee last name succesfully changed",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).removeAll();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).updateCard();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).revalidate();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).repaint();
	}
	
	public void changeEmployeeEmail(String newEmail, Employee employee) {
		employee.setEmailAddress(newEmail);
		
		JOptionPane.showMessageDialog(null, "Employee email address succesfully changed",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).removeAll();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).updateCard();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).revalidate();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).repaint();
	}
	
	public void changeEmployeePhone(Long newPhone, Employee employee) {
		employee.setPhoneNumber(newPhone);
		
		JOptionPane.showMessageDialog(null, "Employee phone number succesfully changed",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).removeAll();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).updateCard();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).revalidate();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).repaint();
	}
	
	public void changeEmployeePin(int newPin, Employee employee) {
		employee.setPin(newPin);
		
		JOptionPane.showMessageDialog(null, "The new pin is: " + newPin ,
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
  
    	((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).removeAll();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).updateCard();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).revalidate();
        ((EmployeeView) views.getComponents()[Kiosk.EMPLOYEE_VIEW_INDEX]).repaint();
	}
	
	public void addNewItem(String name, double price, String description, String picturePath) {
		
		m.addMenuItem(new Item(name, price, description, picturePath, ""));
		
    	((MenuView) views.getComponents()[Kiosk.MENU_VIEW_INDEX]).removeAll();
        ((MenuView) views.getComponents()[Kiosk.MENU_VIEW_INDEX]).updateCard();
        ((MenuView) views.getComponents()[Kiosk.MENU_VIEW_INDEX]).revalidate();
        ((MenuView) views.getComponents()[Kiosk.MENU_VIEW_INDEX]).repaint();
		
        ((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).removeAll();
        ((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).updateCard();
        ((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).revalidate();
        ((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).repaint();
        
        switchTo(Kiosk.EMPLOYEE_MENU_VIEW);
	}
	
	public void addNewEmployee(String fName, String lName, String email, Long phone, int pin) {
		Kiosk.employees.add(new Employee(fName, lName, email, phone, pin));
	}
	
	public void removeEmployee(Employee employee) {
		if (Kiosk.employees.size() <= 1) {
			JOptionPane.showMessageDialog(null, "You can not remove the last employee",
					"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
		} else {
			Kiosk.employees.remove(employee);
			((LoginView) views.getComponents()[Kiosk.LOGIN_VIEW_INDEX]).updateCard();
			switchTo(Kiosk.LOGIN_VIEW);
		}
	}
	
	public BufferedImage resizeImage(final Image image) {
        final BufferedImage bufferedImage = new BufferedImage(250, 200, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, 250, 200, null);
        graphics2D.dispose();
        return bufferedImage;
    }
}
