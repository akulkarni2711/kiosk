package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import model.Cart;


public class EmployeeItemView extends JPanel {
	
	//TODO:
	
	private ViewManager manager;
	private JLabel itemName;
	private JLabel itemPrice;
	private JLabel itemDescription;
	private JLabel itemPicture;
	private JButton backToMenuButton;
	private JButton removeItemButton;
	private JButton changeItemPriceButton;
	private Item item;
	private Menu m;
	private JButton changeItemNameButton;
	private JButton changeItemDescriptionButton; 
	private JTextField newName;
	private JTextField newPrice;
	private JTextField newDescription;
	private JButton changeItemPictureButton;
	private JTextField newPicture;
	
    public EmployeeItemView(ViewManager manager) {
        super();
        
        m = Menu.getInstance();
        
        this.manager = manager;
    }
    
    private void init() {
    	this.removeAll();
    	this.setLayout(null);
    	this.item = manager.getActiveItem();
    	initTitle();
    	initInformation(item);
    	initChangePriceButton();
    	initChangeNameButton();
    	initChangeDescriptionButton();
    	initChangePictureButton();
    	initRemoveItemButton();
    	initBackToMenuButton();
    	
    }
    
    public void updateCard() {
    	init();
    }
    
    private void initTitle() {
    	JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
    	title.setBounds(0, 20, 500, 35);
    	title.setFont(new Font("DialogInput", Font.BOLD, 21));
    	
    	this.add(title);
    }
    
    private void initInformation(Item item) {
    	itemName = new JLabel(item.getName());
    	itemPrice = new JLabel(Double.toString(item.getCost()));
    	itemDescription = new JLabel(item.getDescription());
    	itemPicture = new JLabel();
    	this.add(itemName);
    	this.add(itemPrice);
    	this.add(itemDescription);
    	this.add(itemPicture);
    }
    
    private void initChangePriceButton() {
    	
    	changeItemPriceButton = new JButton("Change the price of this item");
    	changeItemPriceButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			newPrice = new JTextField("Enter a new item price here");
    			double newPriceValue = Double.parseDouble(newPrice.getText());
    			
    			ViewManager.changeItemPrice(newPriceValue, item);
    		}
    	});
    	
    	this.add(changeItemPriceButton);
    	
    }
    
    private void initChangeNameButton() {
    	changeItemNameButton = new JButton("Change the name of this item");
    	changeItemNameButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			newName = new JTextField("Enter a new item name here");
    			String newNameValue = newName.getText();
    			
    			ViewManager.changeItemName(newNameValue, item);
    		}
    	});
    	this.add(changeItemNameButton);
    	
    }
    
    private void initChangeDescriptionButton() {
    	changeItemDescriptionButton = new JButton("Change the description of this item");
    	changeItemDescriptionButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			newDescription = new JTextField("ENter a new description here");
    			String newDescriptionValue = newDescription.getText();
    			
    			ViewManager.changeItemDescription(newDescriptionValue, item);
    		}
    	});
    }
    
    
    private void initChangePictureButton() {
    	
    }
    
    
    private void initRemoveItemButton() {
    	removeItemButton = new JButton("Remove item from menu");
    	backToMenuButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  ViewManager.removeItemFromMenu(item);
    	    	}
    	 });
    	 this.add(removeItemButton);
    	
    }
    
    private void initBackToMenuButton() {
    	
    	backToMenuButton = new JButton("Back to Menu");
    	backToMenuButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			ViewManager.switchTo("EMPLOYEE_MENU_VIEW");
    		}
    	});
    	this.add(backToMenuButton);
    	
    }
    
    

}