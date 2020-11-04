package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Cart;
import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;

//TODO: Add a go to checkout button
// Add a remove button
//

public class CartView extends JPanel {

	private ViewManager manager;
	private JLabel allItems;
	private JButton removeButton;
	private JButton checkoutButton;
	private JLabel totalCost;
	private Cart c;
	private String cartItems;
	private int count1;
	private int count2;
	private String cartItemsShown = "<html><body>";
	private Menu m;

	public CartView(ViewManager manager, Cart cart) {
		super();
		c = cart;
		m = Menu.getInstance();
		this.manager = manager;

		init();
	}

	public void init() {
		this.removeAll();
		this.setLayout(null);

		initTitle();
		initTotalPrice();
		initCheckoutButton();
		initRemoveItemButton();
		initItemTable();

	}

	private void initTitle() {
		JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
		title.setBounds(0, 20, 500, 35);
		title.setFont(new Font("DialogInput", Font.BOLD, 21));

		this.add(title);
	}

	private void initTotalPrice() {
		JLabel totalCost = new JLabel("Total Price: " + Cart.getTotalCost());

		this.add(totalCost);
	}

	private void initCheckoutButton() {
		JButton checkoutButton = new JButton("Go to checkout");
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewManager.switchTo("CHECKOUT_VIEW");
			}
		});
	}

	private void initRemoveItemButton() {

	}

	private void initItemTable() {

		if (c.itemsOrdered.isEmpty() == true || c.itemsOrdered == null) {
			// add error message here
		} else {
			for (Map.Entry mapElement : c.itemsOrdered.entrySet()) {
				int key = (int) mapElement.getKey();
				Item item = m.getItem(key);
				int quantity = ((int) mapElement.getValue());

				cartItemsShown += "Item: " + item.getName() + "             Quantity: " + quantity + "<br/><br/>";
			}
			cartItemsShown += "</body></html>";
			allItems = new JLabel(cartItemsShown);
			allItems.setBounds(100, 100, 500, 300);
			this.add(allItems);
		}
	}

}
