package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.time.Month;
import java.util.Date;
import java.time.LocalDate;

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import placeholders.PHTF;
import model.Cart;


public class CheckoutView extends JPanel {
	
	//TODO: Make sure "back to cart" button works
	
	private ViewManager manager;
	private JButton backToCartButton;
	private JButton checkoutButton;
	private JLabel totalCost;
	private PHTF CVVField;
	private PHTF creditCardField;
	private PHTF expDateField;
	
	
    public CheckoutView(ViewManager manager) {
        super();
        this.manager = manager;
    }
    
    
    private void init() {
    	this.removeAll();
    	this.setLayout(null);
    	initTitle();
    	initInformation();
    	initCheckoutButton();
    	initBackToCartButton();
    	initTotalPrice();
    }
    
	private void initTitle() {
		JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
		title.setBounds(0, 20, 500, 35);
		title.setFont(new Font("DialogInput", Font.BOLD, 21));
		
		this.add(title);
	}
	
	private void initInformation() {
		creditCardField = new PHTF(16);
		creditCardField.setPlaceholder("Enter your credit card: ");
		creditCardField.setBounds(50,150,325,50);
		this.add(creditCardField);
		
		CVVField = new PHTF(3);
		CVVField.setPlaceholder("Enter your CVV: ");
		CVVField.setBounds(50,250,325,50);
		this.add(CVVField);
		
		expDateField = new PHTF(5);
		expDateField.setPlaceholder("Enter the Expiration date in MM/YY format: ");
		expDateField.setBounds(50,350,325,50);
		this.add(expDateField);
	}
	
	private void initCheckoutButton() {
		checkoutButton = new JButton("Finish order and checkout");
		checkoutButton.setBounds(500, 150, 200, 50);
    	checkoutButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if (checkCreditCardNumber() == false) {
    				JOptionPane.showMessageDialog(null, "Please enter a valid credit card number",
    			               "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
    			}
    			else if (checkCVV() == false) {
    				JOptionPane.showMessageDialog(null, "Please make sure that the CVV code is valid",
    						"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
    			}
    			else if (checkDate() == false) {
    				JOptionPane.showMessageDialog(null, "Please make sure that the date is valid and in the future",
    						"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
    			}
    			else {
    				manager.switchTo(Kiosk.AFTER_CHECKOUT_VIEW);	
    			}
    		}
    	});
    	this.add(checkoutButton);
	}
	
	private boolean checkCreditCardNumber() {
		String creditCardNumber = creditCardField.getText();
		if (creditCardNumber == null || creditCardNumber.length() == 0) {
			return false;
		} else {
			String number = creditCardNumber;
	        int length = number.length();
	        int temp1  = 0;
	        int temp2 = 0;
	        int totalSum = 0;
	        for (int k = length-2; k >= 0; k -=2) {
	            char value = number.charAt(k);
	            int intValue = Character.getNumericValue(value) * 2;
	            if (intValue >= 10) {
	                int tens = (int) intValue/10;
	                int ones = intValue % 10;
	                temp1 = temp1 + tens + ones;
	            }
	            else {
	                temp1 = temp1 + intValue;
	            }
	        }
	        for (int m = length -1; m >= 0; m -=2) {
	            char value = number.charAt(m);
	            int intValue = Character.getNumericValue(value);
	            temp2 = temp2 + intValue;
	        }
	        totalSum = temp1 + temp2;
	        if (totalSum % 10 == 0) {
	            if (((number.substring(0,2).equals("34")) || (number.substring(0,2).equals("37"))) && (length == 15)) {
	                return true;
	            }
	            else if (((number.substring(0,2).equals("51")) || (number.substring(0,2).equals("52")) || (number.substring(0,2).equals("53")) || (number.substring(0,2).equals("54")) || (number.substring(0,2).equals("55"))) && (length == 16)) {
	                return true;
	            }
	            else if ((number.substring(0,1).equals("4")) && ((length == 13) || (length == 16))) {
	                return true;
	            }
	            else {
	                return false;
	            }
	        }
	        else {
	            return false;
	        }
		}

	}
	
	private boolean checkCVV() {
		String CVV = CVVField.getText();
		if (CVV == null || CVV.length() != 3) {
			return false;
		}
		for (int k = 0; k < 3; k++) {
			if (!Character.isDigit(CVV.charAt(k))) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkDate() {
		String expDate = expDateField.getText();
		if (expDate.length() != 5 || expDate.charAt(2) != '/') {
			return false;
		}
		Calendar c = Calendar.getInstance();
		String y = Integer.toString(c.get(Calendar.YEAR)).substring(0,2);
		int year = Integer.parseInt(y);
		int month = c.get(Calendar.MONTH) + 1;
		int creditCardMonth = Integer.parseInt(expDate.substring(0,2));
		int creditCardYear = Integer.parseInt(expDate.substring(3));
		if (year > creditCardYear) {
			return false;
		}
		else if (year == creditCardYear) {
			if (month >= creditCardMonth) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return true;
		}
		
	}
	
	private void initBackToCartButton() {
		
		backToCartButton = new JButton("Go back to your cart");
		backToCartButton.setBounds(500,250,200,50);
    	backToCartButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    				manager.switchTo("CART_VIEW");	
    		}
    	});
    	this.add(backToCartButton);
		
	}
	
	private void initTotalPrice() {
		JLabel totalCost = new JLabel("Total Price: " + Cart.getTotalCost());
		totalCost.setBounds(250,75, 200, 100);
		
		this.add(totalCost);
	}
	
	
    public void updateCard() {
    	init();
    }

}