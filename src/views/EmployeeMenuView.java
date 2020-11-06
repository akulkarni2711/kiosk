package views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import controller.ViewManager;
import model.Item;
import model.Menu;


@SuppressWarnings("serial")
public class EmployeeMenuView extends MenuView {
	
	private JButton addItemButton;
	private JFileChooser chooser;
	private Image image;
	private Item item;
	private String newName;
	private String newPrice;
	private String newDescription;
	
	
	public EmployeeMenuView(ViewManager manager) {
    	super(manager);
    	initAddItemButton();
        this.manager = manager;
        m = Menu.getInstance();
        this.item = manager.getActiveItem();
        cartButton.setEnabled(false);
        cartButton.setVisible(false);
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
    
    private void initAddItemButton() {
    	addItemButton = new JButton("Add new item");
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.addNewItem();
				//name
				do {
					newName = JOptionPane.showInputDialog("New name for this item: ");
					manager.changeItemName(newName, item);
				} while (newName == null);
				
				//price
				newPrice = JOptionPane.showInputDialog("New item price:");
				try {
					Double newPriceValue = Double.valueOf(newPrice);
					manager.changeItemPrice(newPriceValue, item);
				} catch (NumberFormatException h) {
					JOptionPane.showMessageDialog(null, "Please enter a valid price",
							"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
				}
				
				//description
				do {
					newDescription = JOptionPane.showInputDialog("Description for this item: ");
					manager.changeItemDescription(newDescription, item);
				} while (newDescription == null);
				
				
				//image
				chooser = new JFileChooser();
    			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    				try {
    					File selectedFile = chooser.getSelectedFile();
    					image = ImageIO.read(selectedFile);
    					manager.changeItemPicture(image, item);
    				} catch (IOException h) {
    					image = null;
    					h.printStackTrace();
    				}
    			}
				
			}
		});
    	
    	JOptionPane.showMessageDialog(null, "Item succesfully added to cart",
				"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
        addItemButton.setBounds(400,0,100,100);
        this.add(addItemButton);
    }

}

