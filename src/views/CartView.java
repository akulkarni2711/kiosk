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
	private String cartItemsShown = "<html>";
	
	
    public CartView(ViewManager manager) {
        super();
        
        this.manager = manager;

        init();
    }
    
    public void init() {
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
    		//add error message here
    	}
    	else {
    		cartItems = Cart.itemsOrdered.toString();
        	cartItems = cartItems.replace("{", "");
        	cartItems = cartItems.replace("}", "");
        	for (int k = 0; k < cartItems.length(); k++) {
        		if (cartItems.charAt(k) == ('=')) {
        			cartItems = cartItems.replaceFirst("=", "");
        			count1 = 0;
        		}
        		else if (cartItems.charAt(k) == ',') {
        			cartItems = cartItems.replaceFirst(", ", "");
        			count2 = 0;
        		}
        		else {
        			if (count1 == 0) {
        				if (Character.isDigit(cartItems.charAt(k+1))) {
            				count1 = Character.getNumericValue(cartItems.charAt(k)) * 10;
            			}
            			else {
            				count1 += Character.getNumericValue(cartItems.charAt(k));
            				cartItemsShown += "Item: " + Kiosk.items.get(count1).getName() + "<br/>";
            			}
        			}
        			else {
        				if (Character.isDigit(cartItems.charAt(k+1))) {
            				count2 = Character.getNumericValue(cartItems.charAt(k)) * 10;
            			}
            			else {
            				count2 += Character.getNumericValue(cartItems.charAt(k));
            				cartItemsShown += "Quantity: " + Integer.toString(count2) + "<br/><br/><br/>";
            			}
        			}
        		}
        	}
        	
        	cartItemsShown += "</html>";
        	
        	allItems = new JLabel(cartItemsShown);
        	
        	this.add(allItems);
        	
        }
    }
    		
    	}
    	
    	