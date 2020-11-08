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
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

//TODO Limit values for new name/description

@SuppressWarnings("serial")
public class EmployeeItemView extends ItemView {

	private JButton changeItemPriceButton;
	private String newPrice;
	private JButton changeItemNameButton;
	private JButton changeItemDescriptionButton;
	private JButton changeItemPictureButton;
	private String newName;
	private String newDescription;
	private JButton removeItemButton;
	private JFileChooser chooser;
	private Image image;

	public EmployeeItemView(ViewManager manager) {
		super(manager);

		m = Menu.getInstance();

		this.manager = manager;
	}

	protected void init() {
		this.removeAll();
		this.setLayout(null);;
		this.item = manager.getActiveItem();
		initTitle();
		initInformation(item);
		initBackToMenuButton();
		initChangePriceButton();
		initChangeNameButton();
		initChangeDescriptionButton();
		initChangePictureButton();
		initRemoveItemButton();
	}

	public void updateCard() {
		init();
	}

    protected void initBackToMenuButton() {
    	
    	backToMenuButton = new JButton("Back to menu");
    	backToMenuButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			manager.switchTo("EMPLOYEE_MENU_VIEW");
    		}
    	});
    	backToMenuButton.setBounds(150,400,250,50);
    	this.add(backToMenuButton);
    	
    }
	
	private void initChangePriceButton() {

		changeItemPriceButton = new JButton("Price:");
		changeItemPriceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPrice = JOptionPane.showInputDialog("New item price:");
				try {
					Double newPriceValue = Double.valueOf(newPrice);
					if (newPriceValue < 0 || newPriceValue == null) {
						JOptionPane.showMessageDialog(null, "Please enter a valid price",
								"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
					} else {
						manager.changeItemPrice(newPriceValue, item);
					}
				} catch (NumberFormatException h) {
					JOptionPane.showMessageDialog(null, "Please enter a valid price",
							"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		changeItemPriceButton.setBounds(50,500,100,75);
		this.add(changeItemPriceButton);

	}

	private void initChangeNameButton() {
		changeItemNameButton = new JButton("Name");
		changeItemNameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newName = JOptionPane.showInputDialog("New name for this item: ");
				if (newName != null && newName.length() != 0) {
					manager.changeItemName(newName, item);
				} else {
					JOptionPane.showMessageDialog(null,  "Please enter a valid name",
							 "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		changeItemNameButton.setBounds(250,500,100,75);
		this.add(changeItemNameButton);

	}

	private void initChangeDescriptionButton() {
		changeItemDescriptionButton = new JButton("Description");
		changeItemDescriptionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newDescription = JOptionPane.showInputDialog("New description for this item: ");
				if (newDescription != null && newDescription.length() != 0) {
					manager.changeItemName(newName, item);
				} else {
					JOptionPane.showMessageDialog(null,  "Please enter a valid description",
							 "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		changeItemDescriptionButton.setBounds(400,500,100,75);
		this.add(changeItemDescriptionButton);
	}

    private void initChangePictureButton() {
    	changeItemPictureButton = new JButton("Pic");
    	changeItemPictureButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
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
    	changeItemPictureButton.setBounds(300,300,100,75);
    	this.add(changeItemPictureButton);
    }

	private void initRemoveItemButton() {
		removeItemButton = new JButton("Remove");
		removeItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.removeItemFromMenu(item);
			}
		});
		removeItemButton.setBounds(700,500,100,75);
		this.add(removeItemButton);

	}

}