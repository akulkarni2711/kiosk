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
	private JLabel picture;
	private JButton backToMenuButton;
	private JSpinner quantity;
	private JButton addToCartButton;
	private JButton removeItemButton;
	private JButton changeItemPrice;
	private Item item;
	private Menu m;
	private JTextField itemPriceSet;
	
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
    	try {
    		File sourceImage = new File("C:\\Users\\Aaron\\OneDrive\\Pictures\\Camera Roll\\Sam.jpg");
    		Image image = ImageIO.read(sourceImage);
    		picture = new JLabel(new ImageIcon(image));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	this.add(itemName);
    	this.add(itemPrice);
    	this.add(itemDescription);
    	this.add(picture);
    }
    
    private void initChangePriceButton() {
    	
    }
    
    
    private void initRemoveItemButton() {
    	removeItemButton = new JButton("Remove item from menu");
    	backToMenuButton.addActionListener(new ActionListener() {
    	  public void actionPerformed(ActionEvent e) {
    		  int removedItemID = item.getItemID();
    		  ViewManager.removeItemFromMenu(removedItemID);
    	    	}
    	 });
    	 this.add(backToMenuButton);
    	
    }
    
    private void initBackToMenuButton() {
    	
    	backToMenuButton = new JButton("Cancel Order and Logout");
    	backToMenuButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			ViewManager.switchTo("MENU_VIEW");
    		}
    	});
    	this.add(backToMenuButton);
    	
    }
    
    

}