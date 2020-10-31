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
import model.Cart;


public class CheckoutView extends JPanel {
	
	private ViewManager manager;
	private JButton goBackToCart;
	private JButton checkoutButton;
	private JLabel totalCost;
	private JTextField CVVField;
	private JTextField creditCardField;
	private JTextField expDateField;
	
	
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
    	initBackToMenuButton();
    	
    }
    
	private void initTitle() {
		JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
		title.setBounds(0, 20, 500, 35);
		title.setFont(new Font("DialogInput", Font.BOLD, 21));
		
		this.add(title);
	}
	
	private void initInformation() {
		creditCardField = new JPasswordField("Enter your credit card number here:");
		CVVField = new JTextField("Enter your CVV code here:");
		expDateField = new JTextField("Enter the credit card expiration date here in the format MM/YY:");
		
		this.add(creditCardField);
		this.add(CVVField);
		this.add(expDateField);
	}
	
	private void initCheckoutButton() {
		checkoutButton = new JButton("Cancel Order and Logout");
    	checkoutButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			if (checkCreditCardNumber() == false) {
    				//add error message here
    			}
    			else if (checkDate() == false) {
    				//add error message here
    			}
    			else {
    				manager.switchTo("AFTER_CHECKOUT_VIEW");	
    			}
    		}
    	});
    	this.add(checkoutButton);
	}
	
	private boolean checkCreditCardNumber() {
		String creditCardNumber = creditCardField.getText();
		String number = creditCardNumber;
        int length = number.length();
        int temp1  = 0;
        int temp2 = 0;
        int totalSum = 0;
        System.out.println();
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
	
	private boolean checkDate() {
		String expDate = expDateField.getText();
		if (expDate.length() > 5 || expDate.length() < 5 || expDate.charAt(2) != '/') {
			return false;
		}
		int actualYear = Calendar.getInstance().get(Calendar.YEAR);
		int actualMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
		int creditCardYear = Integer.parseInt(expDate.substring(0,2));
		int creditCardMonth = Integer.parseInt(expDate.substring(3));
		if (actualYear > creditCardYear) {
			return false;
		}
		else if (actualYear == creditCardYear) {
			if (actualMonth > creditCardMonth) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return true;
		}
		
	}
    
	
	
    public void updateCard() {
    	init();
    }

}