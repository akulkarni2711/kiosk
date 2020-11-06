package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.util.Iterator;
import javax.swing.JTable;
import java.util.HashMap;
import java.util.Map.Entry;

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import model.Cart;


public class MenuView extends JPanel {
	
	protected ViewManager manager;
	protected JButton orderButton;
	protected JLabel totalCost;
	protected JScrollBar scrollBar;
	protected JButton logoutButton;
	protected JButton cartButton;
	protected JTable itemMenu;
	protected Menu m;
	protected Object[][] data;
	protected JLabel itemLabel;
	protected JPanel menuItemPanel;
	protected JScrollPane menuPane;
	
    public MenuView(ViewManager manager) {
        super();
        this.manager = manager;
        m = Menu.getInstance();
        init();
    }
    
    private void init() {
    	this.removeAll();
    	this.setLayout(null);
    	
    	initTitle();
    	initMenuList();
    	initLogoutButton();
    	initScrollBar();
    	initCartButton();
    	
    }
    
    public void updateCard() {
    	init();
    }
    
    protected void initTitle() {
    	JLabel title = new JLabel("Menu", SwingConstants.CENTER);
    	title.setBounds(0, 20, 500, 35);
    	title.setFont(new Font("DialogInput", Font.BOLD, 21));
    	
    	this.add(title);
    }
    
    protected void initMenuTable() {
    	
    	HashMap<Integer, Item> h = m.getHashMap();
    	
    	Item i = null;
    	
    	Iterator it = h.entrySet().iterator();
    	int n = 0;
    	while (it.hasNext()) {
    		HashMap.Entry pair = (HashMap.Entry)it.next();
    		int id = (Integer) pair.getKey(); 
    		i = (Item) pair.getValue();
    		itemLabel = new JLabel(i.getName() + "- $" + i.getCost(), SwingConstants.LEFT);
    		itemLabel.setBounds(50, 80 + 40*n, 200, 35);
    		
    		orderButton = new JButton("Select");
    		orderButton.setBounds(300, 80 + 40*n, 200, 35);
    		orderButton.setActionCommand(Integer.toString(id));
    		
    		orderButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String action = e.getActionCommand();
    				int action_id = Integer.parseInt(action);
    				manager.goToItem(action_id);
    			}
    		});
    		
    		n++;
    		
    		menuItemPanel.add(itemLabel);
    		menuItemPanel.add(orderButton);
    		
    	}
    	menuItemPanel.setPreferredSize(new Dimension(750, 40*h.size()));
    }
    
    protected void initLogoutButton() {
    	logoutButton = new JButton("Logout");
    	logoutButton.setBounds(100,490,250,50);
    	logoutButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			manager.logOut();
    		}
    	});
    	this.add(logoutButton);
    }
    
    protected void initScrollBar() {
    	scrollBar = new JScrollBar();
    	this.add(scrollBar);
    }
    
    protected void initCartButton() {
    	cartButton = new JButton("Go to your cart");
    	cartButton.setBounds(450,490,250,50);
    	cartButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			manager.goToCart();
    		}
    	});
    	this.add(cartButton);
    }
    
    protected void initMenuList() {
    	
    	
    	
    	menuItemPanel = new JPanel();
    	menuItemPanel.setLayout(null);
    	menuItemPanel.setBounds(0, 80, 600, 600);
    	
    	
    	menuPane = new JScrollPane(menuItemPanel);
    	menuPane.setBounds(100, 80, 600, 400);
    	
    	menuPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	menuPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
    	menuPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	
    	if(m.getHashMap() != null) {
    		initMenuTable();
    	}
    	
    	this.add(menuPane);
    	
    }
    

}
