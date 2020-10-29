package controller;


import java.awt.CardLayout;
import java.awt.Container;
import java.math.BigDecimal;

import GUI.Kiosk;
import model.Employee;
import model.Item;
import model.Menu;
import model.Order;


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
	
	public void employeeLogin(String email, int pin) {
		LoginView lv = ((LoginView) views.getComponenets()[Kiosk.LOGIN_VIEW_INDEX]);
	}
	
	public void guestLogin() {
		
	}
	
	
	

}
