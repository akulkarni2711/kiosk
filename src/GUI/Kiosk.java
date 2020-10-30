package GUI;
import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.ViewManager;
import model.Employee;
import model.Cart;
import model.Menu;
import model.Item;
import views.LoginView;
import views.MenuView;
import views.CartView;
import views.CheckoutView;
import views.ItemView;
import views.EmployeeMenuView;
import views.EmployeeItemView;

@SuppressWarnings("serial")
public class Kiosk extends JFrame {
	
	public static final String LOGIN_VIEW = "LOGIN_VIEW";
	public static final String MENU_VIEW = "MENU_VIEW";
	public static final String CART_VIEW = "CART_VIEW";
	public static final String CHECKOUT_VIEW = "CHECKOUT_VIEW";
	public static final String ITEM_VIEW = "ITEM_VIEW";
	public static final String EMPLOYEE_MENU_VIEW = "EMPLOYEE_MENU_VIEW";
	public static final String EMPLOYEE_ITEM_VIEW = "EMPLOYEE_ITEM_VIEW";
	
	public static final int LOGIN_VIEW_INDEX = 0;
	public static final int MENU_VIEW_INDEX = 1;
	public static final int CART_VIEW_INDEX = 2;
	public static final int CHECKOUT_VIEW_INDEX = 3;
	public static final int ITEM_VIEW_INDEX = 4;
	public static final int EMPLOYEE_MENU_VIEW_INDEX = 5;
	public static final int EMPLOYEE_ITEM_VIEW_INDEX = 6;
	
	public static final String[] errorMessages = {
			"",
			"The given email and password combination does not exist. Please try again.",
			"The given credit card has already expired."
	};
	
	
	private static ArrayList<Employee> employees;
	public static ArrayList<Item> items;
	
	public Kiosk() {
		super("Kiosk");
		Kiosk.employees = new ArrayList<Employee>();
		
		Kiosk.employees.add(new Employee("Joe", "Schmoe", "jschmoe@gmail.com", 1234567890, 1234));
		Kiosk.employees.add(new Employee("Bob", "Dabuilder", "bdabuilder@gmail.com", 1234566543, 4321));
		
		Kiosk.items = new ArrayList<Item>();
		
		Kiosk.items.add(new Item("Chicken Dumplings", 9.99, "Fried chicken dumplings with soy sauce.", null));
		Kiosk.items.add(new Item("Fried Rice", 3.99, "Fried white rice with corn, peas, and mushrooms.", null));
		
		
	}
	
	public static Employee lookupUser(long employeeID, int pin) {
		for (int k = 0; k < employees.size(); k++) {
			if (employees.get(k).getAccount() == employeeID && employees.get(k).getPin() == pin) {
				return employees.get(k);
			}
		}
		return null;
	}
	
	private void init() {
		JPanel views = new JPanel(new CardLayout());
		ViewManager manager = new ViewManager(views);
		views.add(new LoginView(manager), LOGIN_VIEW);
		views.add(new MenuView(manager), MENU_VIEW);
		views.add(new CartView(manager), CART_VIEW);
		views.add(new CheckoutView(manager), CHECKOUT_VIEW);
		views.add(new ItemView(manager), ITEM_VIEW);
		views.add(new EmployeeMenuView(manager), EMPLOYEE_MENU_VIEW);
		views.add(new EmployeeItemView(manager), EMPLOYEE_ITEM_VIEW);
		
		this.add(views);
		this.setBounds(100,100,500,500);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		Menu menu = new Menu();
		Cart cart = new Cart();
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new Kiosk().init();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
