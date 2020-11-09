package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Employee;
import controller.ViewManager;
import placeholders.PHTF;
import placeholders.PHPF;


public class LoginView extends JPanel {
	
	private ViewManager manager;
	public static PHTF IDField;
	public static PHPF pinField;
	private JButton loginButton;
	private JButton guestButton;
	
	public LoginView(ViewManager manager) {
		super();
		this.manager = manager;
		init();
	}
	
	public void clear() {
		IDField.setText("");
		pinField.setText("");
	}
	
	private void init() {
		this.setLayout(null);
		
		initTitle();
		initIDField();
		initPinField();
		initLoginButton();
		initGuestButton();
	}
	
	private void initTitle() {
		JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
		title.setBounds(0, 20, 500, 35);
		title.setFont(new Font("DialogInput", Font.BOLD, 21));
		
		this.add(title);
	}
	
	private void initIDField() {
		
		IDField = new PHTF(20);
		IDField.setPlaceholder("Employee ID: ");
		IDField.setBounds(205, 160, 200, 35);
		
		IDField.addKeyListener(new KeyAdapter() {
		
		});
		
		
		this.add(IDField);
	}
	
	private void initPinField() {
		pinField = new PHPF(4);
		pinField.setPlaceholder("PIN: ");
		pinField.setBounds(205, 200, 200, 35);
		
		pinField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (pinField.getPassword().length >=4 || e.getKeyChar() < 48 || e.getKeyChar() > 57) {
					e.consume();
				}
			}
		});
		
		this.add(pinField);
	}
	
	private void initLoginButton() {
		loginButton = new JButton("Login as Employee");
		loginButton.setBounds(205, 260, 200, 35);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(loginButton)) {
					String emp = IDField.getText();
					if (emp.isBlank()) {
						JOptionPane.showMessageDialog(null, "Please enter a valid ID and password, or login as a guest",
								"Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
					}
					else {
						Long employeeID = Long.parseLong(emp);
						char[] digits = pinField.getPassword();
						int pin = Integer.parseInt(new String(digits));
						manager.employeeLogin(employeeID, pin);
					}
				}
			}
		});
		
		this.add(loginButton);
	}
	
	private void initGuestButton() {
		guestButton = new JButton("Continue as Guest");
		guestButton.setBounds(205, 300, 200, 35);
		
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(guestButton)) {
					
					manager.guestLogin();
				}
			}
		});
		
		this.add(guestButton);
	}
	
	public void updateCard() {
		init();
	}
	

}
