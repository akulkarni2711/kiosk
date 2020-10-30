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

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import model.Cart;


public class CartView extends JPanel {
	
	private ViewManager manager;
	private JLabel itemName;
	private JLabel itemPrice;
	private JButton removeButton;
	private JButton checkout;
	private JLabel totalCost;
	private Menu m;
	
	
    public CartView(ViewManager manager) {
        super();
        
        this.manager = manager;

        init();
    }
    
    public void init() {
    	initTitle();
    	initItemTable();
    	
    }
    
    private void initTitle() {
    	JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
    	title.setBounds(0, 20, 500, 35);
    	title.setFont(new Font("DialogInput", Font.BOLD, 21));
    	
    	this.add(title);
    }
    
    private void initItemTable() {
    	
    	JTable items = new JTable(new DefaultTableModel(new Object[]{"", ""}, 1));
    	HashMap<Integer, Item> entry = m.getHashMap();
    	int length = entry.size();
    	Object[][] data = new Object[2][length];
    	Iterator it = entry.entrySet().iterator();
    	while (it.hasNext()) {
    		HashMap.Entry pair = (HashMap.Entry)it.next();
    		int count = (Integer) pair.getKey();
    		data[count][0] = entry.get(count).getName() + entry.get(count).getCost();
    		removeButton = new JButton("Remove");
    		removeButton.setActionCommand(Integer.toString(count));
    		
    		removeButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String action = removeButton.getActionCommand();
    				int action_id = Integer.parseInt(action);
    				init();
    			}
    		});
    		
    		data[count][1] = removeButton;
    	}
    	this.add(menuItems);
    	
    }
    
    

}