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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import model.Cart;


public class ItemView extends JPanel {
	
	//TODO: Add messages to page when item is successfully added to cart
	
	private ViewManager manager;
	private JLabel itemName;
	private JLabel itemPrice;
	private JTextArea itemDescription;
	private Image picture;
	private JLabel pictureLabel;
	private JButton backToMenuButton;
	private JSpinner spinQuantity;
	private JButton addToCartButton;
	private Item item;
	private Menu m;
	private ImageIcon pictureIcon;
	private JLabel picLabel;
	private int quantity;
	
    public ItemView(ViewManager manager) {
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
    	initQuantity();
    	initAddToCartButton();
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
    	itemName.setBounds(100,50,150,40);
    	this.add(itemName);
    	
    	itemPrice = new JLabel(Double.toString(item.getCost()));
    	itemPrice.setBounds(200,110,60,30);
    	this.add(itemPrice);
    	
    	itemDescription = new JTextArea(item.getDescription());
    	itemDescription.setWrapStyleWord(true);
    	itemDescription.setLineWrap(true);
    	itemDescription.setFocusable(false);
    	itemDescription.setBounds(100,200,250,100);
    	itemDescription.setEditable(false);
    	this.add(itemDescription);
    	
    	
    	Image picture = item.getPicture();
    	picLabel = new JLabel(new ImageIcon(picture));
    	picLabel.setBounds(300,50,300,300);
    	this.add(picLabel);
    }
    
    private void initQuantity() {
    	SpinnerNumberModel model = new SpinnerNumberModel(1,1,100,1);
    	spinQuantity = new JSpinner(model);
    	spinQuantity.addChangeListener(new ChangeListener() {
    		public void stateChanged(ChangeEvent e) {
    			quantity = ((Integer)spinQuantity.getValue()).intValue();
    		}
    	});
    	spinQuantity.setBounds(25,500, 100, 80);
    	
    	this.add(spinQuantity);
    }
    
    private void initAddToCartButton() {
    	
        addToCartButton = new JButton("Add Item to Cart");
        addToCartButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		quantity = ((Integer)spinQuantity.getValue()).intValue();
        	    manager.addItem(item.getItemID(), quantity);
        	    System.out.println("Item added to cart " + quantity + "," + item.getItemID());
        	}
        });
        addToCartButton.setBounds(600,500,250,50);
        this.add(addToCartButton);
    	
    }
    
    private void initBackToMenuButton() {
    	
    	backToMenuButton = new JButton("Back to menu");
    	backToMenuButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			ViewManager.switchTo("MENU_VIEW");
    		}
    	});
    	backToMenuButton.setBounds(150,500,250,50);
    	this.add(backToMenuButton);
    	
    }
    
    

}