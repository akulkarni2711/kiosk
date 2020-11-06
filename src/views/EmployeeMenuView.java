package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import controller.ViewManager;
import model.Item;
import model.Menu;


@SuppressWarnings("serial")
public class EmployeeMenuView extends MenuView {
	
	
    public EmployeeMenuView(ViewManager manager) {
    	super(manager);
        this.manager = manager;
        m = Menu.getInstance();
    }
    
    protected void initTitle() {
    	JLabel title = new JLabel("Menu(Employee)", SwingConstants.CENTER);
    	title.setBounds(0, 20, 500, 35);
    	title.setFont(new Font("DialogInput", Font.BOLD, 21));
    	
    	this.add(title);
    }
    
    @SuppressWarnings("rawtypes")
	protected void initMenuTable() {
    	HashMap<Integer, Item> h = m.getHashMap();
    	
    	Item i = null;
    	
    	Iterator<?> it = h.entrySet().iterator();
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
    				manager.goToItemEmployee(action_id);
    			}
    		});
    		
    		n++;
    		
    		menuItemPanel.add(itemLabel);
    		menuItemPanel.add(orderButton);
    		
    	}
    	menuItemPanel.setPreferredSize(new Dimension(750, 40*h.size()));
    }

}

