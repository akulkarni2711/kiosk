package views;

import java.awt.Color;
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


public class ItemView extends JPanel {
	
	private ViewManager manager;
	private JLabel itemName;
	private JLabel itemPrice;
	private JLabel itemDescription;
	private JLabel picture;
	private JButton backToMenu;
	private JButton cancelAndLogout;
	private JSpinner quantity;
	private int itemID;
	
	
    public ItemView(ViewManager manager) {
        super();
        
        this.manager = manager;
    }
    
    public void init(int itemID) {
    	this.itemID = itemID;
    	initTitle();
    	initInformation(itemID);
    	initQuantity();
    	
    }
    
    private void initTitle() {
    	JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
    	title.setBounds(0, 20, 500, 35);
    	title.setFont(new Font("DialogInput", Font.BOLD, 21));
    	
    	this.add(title);
    }
    
    private void initInformation(int itemID) {
    	itemName = new JLabel(Kiosk.items.get(itemID).getName());
    	itemPrice = new JLabel(String.valueOf(Kiosk.items.get(itemID).getCost()));
    	itemDescription = new JLabel(Kiosk.items.get(itemID).getDescription());
    	try {
    		File sourceImage = new File("C:\\Users\\Aaron\\OneDrive\\Pictures\\Camera Roll");
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
    
    private void initQuantity() {
    	SpinnerNumberModel quantity = new SpinnerNumberModel(0,0,100,1); 
    }
    
    public int endQuantity = (int) quantity.getValue();

}