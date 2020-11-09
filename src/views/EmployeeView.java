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
	private JButton addEmployeeButton;
	private String newFirstName;
	private String newLastName;
	private String newPhone;
	private String newEmail;
	private String newPin;
	private Long newPhoneValue;
	private int newPinValue;
	private JButton removeEmployeeButton;
	
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
	    	initAddEmployeeButton();
	    	initGoToMenuButton();
	    	initChangeFirstNameButton();
	    	initChangeLastNameButton();
	    	initChangeEmailButton();
	    	initChangePhoneButton();
	    	initChangePinButton();
	    	initRemoveEmployeeButton();
	    }
	    
	    public void updateCard() {
	    	init();
	    }
	    
	    private void initTitle() {
	    	JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
	    	title.setBounds(125, 20, 500, 35);
	    	title.setFont(new Font("DialogInput", Font.BOLD, 21));
	    	
	    	this.add(title);
	    }
	    
	    private void initAddEmployeeButton() {
			addEmployeeButton = new JButton("Add new Employee");
			addEmployeeButton.setBounds(575, 300, 175, 50);
			this.add(addEmployeeButton);
			addEmployeeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// first name
					do {
						newFirstName = JOptionPane.showInputDialog("First name for this employee: ");
					} while (newFirstName == null);
					
					//last name
					do {
						newLastName = JOptionPane.showInputDialog("Last name for this employee: ");
					} while (newLastName == null);

					// email
					do {
						newEmail = JOptionPane.showInputDialog("Email for this employee: ");
					} while (newEmail == null);
					
					// phone
					newPhone = null;
					do {
						newPhone = JOptionPane.showInputDialog("New phone number for this employee:");
						try {
							newPhoneValue = Long.valueOf(newPhone);
						} catch (NumberFormatException h) {
							JOptionPane.showMessageDialog(null, "Please enter a valid phone number", "Joe's Kiosk",
									JOptionPane.ERROR_MESSAGE);
						}

					} while (newPhoneValue == null && newPhoneValue < 0 && newPhone.length() == 10);

					// pin
					do {
						newPin = JOptionPane.showInputDialog("Please enter a 4-digit numeric-only pin: ");
						try {
							newPinValue = Integer.valueOf(newPinValue);
						} catch (NumberFormatException b) {
							JOptionPane.showMessageDialog(null,  "Please enter a valid numeric-only pin",
									"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
						}
					} while (newPin != null && newPin.length() != 4);

					manager.addNewEmployee(newFirstName, newLastName, newEmail, newPhoneValue, newPinValue);
					
					manager.logOut();
					manager.employeeLogin(Employee.employeeCounter, newPinValue);
					JOptionPane.showMessageDialog(null, "Employee succesfully added. ID: " + employee.getAccount() + ", Pin: " + employee.getPin() , "Joe's Kiosk",
							JOptionPane.INFORMATION_MESSAGE);

				}
			});
		}
	    
	    private void initInformation(Employee employee) {
	    	employeeName = new JLabel("Name: " + employee.getFirstName() + " " + employee.getLastName());
	    	employeeName.setBounds(300,75,200,40);
	    	this.add(employeeName);
	    	
	    	employeePhone = new JLabel("Phone Number: " + Long.toString(employee.getPhoneNumber()));
	    	employeePhone.setBounds(300,100,200,40);
	    	this.add(employeePhone);
	    	
	    	employeeEmail = new JLabel("Email: " + employee.getEmailAddress());
	    	employeeEmail.setBounds(300,125,200,40);
	    	this.add(employeeEmail);
	    	
	    	employeeID = new JLabel("ID: " + Long.toString(employee.getAccount()));
	    	employeeID.setBounds(300,150,200,40);
	    	this.add(employeeID);
	    	
	    	
	    	employeePin = new JLabel("Pin: " + Integer.toString(employee.getPin()));
	    	employeePin.setBounds(300,175,200,40);
	    	this.add(employeePin);
	    }
	    
	    private void initGoToMenuButton() {
	    	
	        goToMenuButton = new JButton("Go to employee menu");
	        goToMenuButton.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		manager.switchTo(Kiosk.EMPLOYEE_MENU_VIEW);
	        	}
	        });
	        goToMenuButton.setBounds(375,300,175,50);
	        this.add(goToMenuButton);
	    	
	    }
	    
		private void initChangeFirstNameButton() {

			changeFirstNameButton = new JButton("Change First Name");
			changeFirstNameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newFirstName = JOptionPane.showInputDialog("New first name:");
					if (newFirstName != null && newFirstName.length() != 0) {
						manager.changeEmployeeFirstName(newFirstName, employee);
					} else {
						JOptionPane.showMessageDialog(null,  "Please enter a first name",
						 "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			changeFirstNameButton.setBounds(25,300,150,50);
			this.add(changeFirstNameButton);

		}

		private void initChangeLastNameButton() {

			changeLastNameButton = new JButton("Change Last Name");
			changeLastNameButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newLastName = JOptionPane.showInputDialog("New last name:");
					if (newLastName != null && newLastName.length() != 0) {
						manager.changeEmployeeLastName(newLastName, employee);
					} else {
						JOptionPane.showMessageDialog(null,  "Please enter a last name",
						 "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			changeLastNameButton.setBounds(25,400,150,50);
			this.add(changeLastNameButton);

		}
		
		private void initChangeEmailButton() {
			changeEmailButton = new JButton("Change Email");
			changeEmailButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newEmail = JOptionPane.showInputDialog("New email for this employee: ");
					if (newEmail != null && newEmail.length() != 0) {
						manager.changeEmployeeEmail(newEmail, employee);
					} else {
						JOptionPane.showMessageDialog(null,  "Please enter a valid email address",
								 "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			changeEmailButton.setBounds(200,300,150,50);
			this.add(changeEmailButton);
		}
		
		private void initChangePhoneButton() {
			changePhoneButton = new JButton("Change Phone Number");
			changePhoneButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newPhone = JOptionPane.showInputDialog("New phone for this employee: ");
					try {
						newPhoneValue = Long.valueOf(newPhone);
					} catch (NumberFormatException h) {
						JOptionPane.showMessageDialog(null, "Please enter a valid phone number", "Joe's Kiosk",
								JOptionPane.ERROR_MESSAGE);
					}
					if (newPhoneValue != null && newPhoneValue > 0 && newPhone.length() == 10) {
						manager.changeEmployeePhone(newPhoneValue, employee);
						JOptionPane.showMessageDialog(null,  "Phone number succesfully changed",
								"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Please enter a valid phone number", "Joe's Kiosk",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			changePhoneButton.setBounds(375,400,175,50);
			this.add(changePhoneButton);
		}
		
		private void initChangePinButton() {
			changePinButton = new JButton("Change Pin");
			changePinButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					newPin = JOptionPane.showInputDialog("New pin for this employee: (Pin must be 4 digits) ");
					try {
						newPinValue = Integer.valueOf(newPin);
					} catch (NumberFormatException h) {
						JOptionPane.showMessageDialog(null, "Please enter a valid pin", "Joe's Kiosk",
								JOptionPane.ERROR_MESSAGE);
					}
					if (newPin != null && newPinValue > 0 && newPin.length() == 4) {
						manager.changeEmployeePin(newPinValue, employee);
						JOptionPane.showMessageDialog(null,  "Pin succesfully changed",
								"Joe's Kiosk", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Please enter a valid pin", "Joe's Kiosk",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			changePinButton.setBounds(200,400,150,50);
			this.add(changePinButton);
		}


		private void initRemoveEmployeeButton() {
			removeEmployeeButton = new JButton("Remove this employee");
			removeEmployeeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					manager.removeEmployee(employee);
				}
			});
			removeEmployeeButton.setBounds(575,400,175,50);
			this.add(removeEmployeeButton);

		}
	    
}