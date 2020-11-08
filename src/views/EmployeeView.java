package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.GregorianCalendar;
import java.util.Calendar;
import java.time.Month;
import java.util.Date;
import java.time.LocalDate;

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import placeholders.PHTF;
import model.Cart;


public class EmployeeView extends JPanel {
	
	//TODO: 
	
	private ViewManager manager;
	private Employee employee;
	private JLabel employeeName;
	private JLabel employeePhone;
	private JLabel employeeEmail;
	private JLabel employeePin;
	private JLabel employeeID;
	private JButton goToMenuButton;
	private JButton changeFirstNameButton;
	private JButton changeLastNameButton;
	private JButton changeEmailButton;
	private JButton changePhoneButton;
	private JButton changePinButton;
	
	public EmployeeView(ViewManager manager) {
		super();
		this.manager = manager;
		init();
	}
	
	
	   private void init() {
	    	this.removeAll();
	    	this.setLayout(null);
	    	this.employee = manager.getActiveEmployee();
	    	initTitle();
	    	if (employee != null ) {
	    		initInformation(employee);
	    	}
	    	initGoToMenuButton();
	    	
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
	    
	    private void initInformation(Employee employee) {
	    	employeeName = new JLabel(employee.getFirstName() + " " + employee.getLastName());
	    	employeeName.setBounds(100,50,150,40);
	    	this.add(employeeName);
	    	
	    	employeePhone = new JLabel(Long.toString(employee.getPhoneNumber()));
	    	employeePhone.setBounds(200,110,60,30);
	    	this.add(employeePhone);
	    	
	    	employeeEmail = new JLabel(employee.getEmailAddress());
	    	employeeEmail.setBounds(100,200,250,100);
	    	this.add(employeeEmail);
	    	
	    	employeeID = new JLabel(Long.toString(employee.getAccount()));
	    	employeeID.setBounds(200,250,250,100);
	    	this.add(employeeID);
	    	
	    	
	    	employeePin = new JLabel(Integer.toString(employee.getPin()));
	    	employeePin.setBounds(400,400,250,100);
	    	this.add(employeePin);
	    }
	    
	    private void initGoToMenuButton() {
	    	
	        goToMenuButton = new JButton("Go to employee menu");
	        goToMenuButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		manager.switchTo(Kiosk.EMPLOYEE_MENU_VIEW);
	        	}
	        });
	        goToMenuButton.setBounds(400,500,250,50);
	        this.add(goToMenuButton);
	    	
	    }
	    
//		private void initChangeFirstNameButton() {
//
//			changeFirstNameButton = new JButton("Price:");
//			changeFirstNameButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					newPrice = JOptionPane.showInputDialog("New item price:");
//					try {
//						Double newPriceValue = Double.valueOf(newPrice);
//						if (newPriceValue < 0 || newPriceValue == null) {
//							JOptionPane.showMessageDialog(null, "Please enter a valid price",
//									"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
//						} else {
//							manager.changeItemPrice(newPriceValue, item);
//						}
//					} catch (NumberFormatException h) {
//						JOptionPane.showMessageDialog(null, "Please enter a valid price",
//								"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			});
//
//			changeFirstNameButton.setBounds(50,500,100,75);
//			this.add(changeFirstNameButton);
//
//		}
//
//		private void initChangeNameButton() {
//			changeItemNameButton = new JButton("Name");
//			changeItemNameButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					newName = JOptionPane.showInputDialog("New name for this item: ");
//					if (newName != null && newName.length() != 0) {
//						manager.changeItemName(newName, item);
//					} else {
//						JOptionPane.showMessageDialog(null,  "Please enter a valid name",
//								 "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			});
//			changeItemNameButton.setBounds(250,500,100,75);
//			this.add(changeItemNameButton);
//
//		}
//
//		private void initChangeDescriptionButton() {
//			changeItemDescriptionButton = new JButton("Description");
//			changeItemDescriptionButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					newDescription = JOptionPane.showInputDialog("New description for this item: ");
//					if (newDescription != null && newDescription.length() != 0) {
//						manager.changeItemName(newName, item);
//					} else {
//						JOptionPane.showMessageDialog(null,  "Please enter a valid description",
//								 "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			});
//			changeItemDescriptionButton.setBounds(400,500,100,75);
//			this.add(changeItemDescriptionButton);
//		}
//
//	    private void initChangePictureButton() {
//	    	changeItemPictureButton = new JButton("Pic");
//	    	changeItemPictureButton.addActionListener(new ActionListener() {
//	    		public void actionPerformed(ActionEvent e) {
//	    			chooser = new JFileChooser();
//	    			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//	    				try {
//	    					File selectedFile = chooser.getSelectedFile();
//	    					image = ImageIO.read(selectedFile);
//	    					manager.changeItemPicture(image, item);
//	    				} catch (IOException h) {
//	    					image = null;
//	    					h.printStackTrace();
//	    				}
//	    			}
//	    		}
//	    	});
//	    	changeItemPictureButton.setBounds(300,300,100,75);
//	    	this.add(changeItemPictureButton);
//	    }
//
//		private void initRemoveItemButton() {
//			removeItemButton = new JButton("Remove");
//			removeItemButton.addActionListener(new ActionListener() {
//				public void actionPerformed(ActionEvent e) {
//					manager.removeItemFromMenu(item);
//				}
//			});
//			removeItemButton.setBounds(700,500,100,75);
//			this.add(removeItemButton);
//
//		}
	    
	    

}