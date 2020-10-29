package controller;


import java.awt.CardLayout;
import java.awt.Container;
import java.math.BigDecimal;

import GUI.Kiosk;
import model.Employee;
import model.Item;
import model.Menu;
import model.Order;
import views.LoginView;
import views.MenuView;
import views.ItemView;
import views.EmployeeItemView;
import views.EmployeeMenuView;
import views.CheckoutView;
import views.CartView;


public class ViewManager {
	
	private Container views;
	private Employee activeEmployee;
	
	public ViewManager(Container views) {
		this.views = views;
		this.activeEmployee = null;
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
				lv.toggleErrorMessage(true);
			}
			else {
				((EmployeeMenuView) views.getComponents()[Kiosk.EMPLOYEE_MENU_VIEW_INDEX]).populate(activeEmployee);
				
				switchTo(Kiosk.EMPLOYEE_MENU_VIEW);
				lv.clear();
			}
		} catch (NumberFormatException e) {
			lv.toggleErrorMessage(true);
		}
	}
	
	public void guestLogin() {
		
	}
	
	
	

}
