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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import model.Order;


public class ItemView extends JPanel {
	
	private ViewManager manager;
	private JLabel itemName;
	private JLabel itemPrice;
	private JButton removeItem;
	private JButton checkout;
	private JLabel totalCost;
	
	
    public ItemView(ViewManager manager) {
        super();
        
        this.manager = manager;

        this.init();
    }

}