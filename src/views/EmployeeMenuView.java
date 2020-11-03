package views;

import java.awt.Color;
import java.awt.Container;
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

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import model.Cart;


public class EmployeeMenuView extends JPanel {
	
	//TODO everything is wrong fix it
	
	private ViewManager manager;
	private JButton orderButton;
	private JLabel totalCost;
	private JLabel JScrollBar;
	private JButton logoutButton;
	private JTable itemMenu;
	private Menu m;
	
	
    public EmployeeMenuView(ViewManager manager) {
        super();
        this.manager = manager;
        m = Menu.getInstance();
    }
    
    private void init() {
    	this.setLayout(null);
    	
    	initTitle();
    	initMenuTable();
    	initLogoutButton();
    	
    	int count = 0;
    	
    }
    
    private void initTitle() {
    	JLabel title = new JLabel("Menu(Employee)", SwingConstants.CENTER);
    	title.setBounds(0, 20, 500, 35);
    	title.setFont(new Font("DialogInput", Font.BOLD, 21));
    	
    	this.add(title);
    }
    
    private void initMenuTable() {
    	
    	JTable menuItems = new JTable(new DefaultTableModel(new Object[]{"", ""}, 1));
    	menuItems.setBounds(0, 100, 200, 200);
    	HashMap<Integer, Item> entry = m.getHashMap();
    	int length = entry.size();
    	Object[][] data = new Object[2][length];
    	Iterator it = entry.entrySet().iterator();
    	while (it.hasNext()) {
    		HashMap.Entry pair = (HashMap.Entry)it.next();
    		int count = (Integer) pair.getKey();
    		data[count][0] = entry.get(count).getName() + entry.get(count).getCost();
    		orderButton = new JButton("Order");
    		orderButton.setActionCommand(Integer.toString(count));
    		
    		orderButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String action = orderButton.getActionCommand();
    				int action_id = Integer.parseInt(action);
    				manager.goToItem(action_id);	
    			}
    		});
    		
    		data[count][1] = orderButton;
    	}
    	this.add(menuItems);
    	
    }
    
    private void initLogoutButton() {
    	logoutButton = new JButton("Logout");
    	logoutButton.setBounds(100,300,200,100);
    	logoutButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			manager.logOut();
    		}
    	});
    	this.add(logoutButton);
    }
    
    public void updateCard() {
    	init();
    }

}

