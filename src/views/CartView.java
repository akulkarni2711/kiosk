package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
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
	private JPanel cartPanel;
	private JScrollPane cartPane;
	private JButton backToMenuButton;

	public CartView(ViewManager manager, Cart cart) {
		super();
		c = cart;
		m = Menu.getInstance();
		this.manager = manager;

		init();
	}

	private void init() {
		this.removeAll();
		this.setLayout(null);

		initTitle();
		initTotalPrice();
		initCheckoutButton();
		initBackToMenuButton();
		initItemTable();
		initCartList();

	}

	private void initTitle() {
		JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
		title.setBounds(0, 20, 500, 35);
		title.setFont(new Font("DialogInput", Font.BOLD, 21));

		this.add(title);
	}

	private void initTotalPrice() {
		JLabel totalCost = new JLabel("Total Price: " + Cart.getTotalCost());
		totalCost.setBounds(250,75, 200, 100);

		this.add(totalCost);
	}

	private void initCheckoutButton() {
		JButton checkoutButton = new JButton("Go to checkout");
		checkoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.goToCheckout();
			}
		});
		checkoutButton.setBounds(300,400,200,40);
		this.add(checkoutButton);
	}
	
	private void initBackToMenuButton() {
		JButton backToMenuButton = new JButton("Back to menu");
		backToMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.goToMenu();
			}
		});
		backToMenuButton.setBounds(500,400,200,40);
		this.add(backToMenuButton);
	}

	private void initItemTable() {

		if (c.itemsOrdered.isEmpty() == true || c.itemsOrdered == null) {
			// add error message here
		} else {
			LinkedHashMap<Integer, Integer> h = c.getHashMap();
			HashMap<Integer, Item> w = m.getHashMap();
	    	
	    	Item i = null;
	    	
	    	Iterator it = h.entrySet().iterator();
	    	int n = 0;
	    	while (it.hasNext()) {
	    		HashMap.Entry pair = (HashMap.Entry)it.next();
	    		int id = (Integer) pair.getKey();
	    		i = w.get(id);
	    		allItems = new JLabel(i.getName() + ",   Quantity: " + c.getQuantity(id) + "- $ " + i.getCost() * c.getQuantity(id), SwingConstants.LEFT);
	    		allItems.setBounds(50, 80 + 40*n, 200, 35);
	    		
	    		removeButton = new JButton("Remove item");
	    		removeButton.setBounds(300, 80 + 40*n, 200, 35);
	    		removeButton.setActionCommand(Integer.toString(id));
	    		
	    		removeButton.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) {
	    				String action = e.getActionCommand();
	    				int action_id = Integer.parseInt(action);
	    				c.removeItem(action_id);
	    			}
	    		});
	    		
	    		n++;
	    		
	    		cartPanel.add(allItems);
	    		cartPanel.add(removeButton);
	    		
	    	}
	    	cartPanel.setPreferredSize(new Dimension(750, 40*h.size()));
		}
		
	}
	
    private void initCartList() {
    	
    	
    	
    	cartPanel = new JPanel();
    	cartPanel.setLayout(null);
    	cartPanel.setBounds(0, 80, 600, 600);
    	
    	
    	cartPane = new JScrollPane(cartPanel);
    	cartPane.setBounds(100, 80, 600, 400);
    	
    	cartPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	cartPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
    	cartPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	
    	if(m.getHashMap() != null) {
    		initItemTable();
    	}
    	
    	this.add(cartPane);
    	
    }
    
    public void updateCard() {
    	init();
    }

}
