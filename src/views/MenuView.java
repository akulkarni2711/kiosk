package views;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
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
	
	private ViewManager manager;
	private JButton orderButton;
	private JLabel totalCost;
	private JScrollBar scrollBar;
	private JButton logoutButton;
	private JButton cartButton;
	private JTable itemMenu;
	private Menu m;
	private Object[][] data;
	
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
    	initMenuTable();
    	initLogoutButton();
    	initScrollBar();
    	initCartButton();
    	
    }
    
    public void updateCard() {
    	init();
    }
    
    private void initTitle() {
    	JLabel title = new JLabel("Menu", SwingConstants.CENTER);
    	title.setBounds(0, 20, 500, 35);
    	title.setFont(new Font("DialogInput", Font.BOLD, 21));
    	
    	this.add(title);
    }
    
    private void initMenuTable() {
    	HashMap<Integer, Item> entry = m.getHashMap();
    	String[] columns = new String[] { "Item", ""};
    	int length = entry.size();
    	
    	data = new Object[length + 1][2];
    	
    	data[0][0] = "";
    	data[0][1] = new JButton("Invisible");
    	
    	//JTable menuItems = new JTable( data, columns );
    	JTable menuItems = new JTable( new JTableButtonModel());
    	menuItems.setBounds(0,100,400,200);
    	TableCellRenderer buttonRenderer = menuItems.getDefaultRenderer(JButton.class);
    	menuItems.setDefaultRenderer(JButton.class, new JTableButtonRenderer( buttonRenderer ));
    	
    	Iterator it = entry.entrySet().iterator();
    	while (it.hasNext()) {
    		HashMap.Entry pair = (HashMap.Entry)it.next();
    		int count = (Integer) pair.getKey();
    		data[count][0] = entry.get(count).getName() + " - $" + entry.get(count).getCost();
    		orderButton = new JButton("Select");
    		orderButton.setActionCommand(Integer.toString(count));
    		orderButton.setBounds(105, 260, 110, 35);
    		
    		orderButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				String action = orderButton.getActionCommand();
    				int action_id = Integer.parseInt(action);
    				manager.goToItem(action_id);	
    			}
    		});
    		TableColumn column = menuItems.getColumnModel().getColumn(1);
    		
    		data[count][1] = orderButton;
    		menuItems.add(orderButton);
    	}
    	this.add(menuItems);
    	
    	
    	
    }
    
    private void initLogoutButton() {
    	logoutButton = new JButton("Cancel Order and Logout");
    	logoutButton.setBounds(0,350,250,50);
    	logoutButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			manager.logOut();
    		}
    	});
    	this.add(logoutButton);
    }
    
    private void initScrollBar() {
    	scrollBar = new JScrollBar();
    	this.add(scrollBar);
    }
    
    private void initCartButton() {
    	cartButton = new JButton("Go to your cart");
    	cartButton.setBounds(250,350,250,50);
    	cartButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			manager.goToCart();
    		}
    	});
    	this.add(cartButton);
    }
    
    class JTableButtonModel extends AbstractTableModel {
    	   //private Object[][] rows = {{"Button1", new JButton("Button1")},{"Button2", new JButton("Button2")},{"Button3", new JButton("Button3")}, {"Button4", new JButton("Button4")}};
    	private Object[][] rows = data;   
    	private String[] columns = {"Count", "Buttons"};
    	   public String getColumnName(int column) {
    	      return columns[column];
    	   }
    	   public int getRowCount() {
    	      return rows.length;
    	   }
    	   public int getColumnCount() {
    	      return columns.length;
    	   }
    	   public Object getValueAt(int row, int column) {
    	      return rows[row][column];
    	   }
    	   public boolean isCellEditable(int row, int column) {
    	      return false;
    	   }
    	   public Class getColumnClass(int column) {
    	      return getValueAt(0, column).getClass();
    	   }
    }
    	   
    class JTableButtonRenderer implements TableCellRenderer {
    	   private TableCellRenderer defaultRenderer;
    	   public JTableButtonRenderer(TableCellRenderer renderer) {
    	      defaultRenderer = renderer;
    	   }
    	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    	      if(value instanceof Component)
    	         return (Component)value;
    	         return defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    	   }
    	}

}
//javax.swing.JButton[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@5dca965c,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Order,defaultCapable=true]
//javax.swing.JButton[,0,0,0x0,invalid,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@515e2be2,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Order,defaultCapable=true]
//javax.swing.JButton[,205,260,200x35,alignmentX=0.0,alignmentY=0.5,border=javax.swing.plaf.BorderUIResource$CompoundBorderUIResource@7c8b3aa1,flags=296,maximumSize=,minimumSize=,preferredSize=,defaultIcon=,disabledIcon=,disabledSelectedIcon=,margin=javax.swing.plaf.InsetsUIResource[top=2,left=14,bottom=2,right=14],paintBorder=true,paintFocus=true,pressedIcon=,rolloverEnabled=true,rolloverIcon=,rolloverSelectedIcon=,selectedIcon=,text=Order,defaultCapable=true]