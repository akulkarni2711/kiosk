package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Employee;
import controller.ViewManager;


public class LoginView extends JPanel {
	
	private ViewManager manager;
	private JTextField IDField;
	private JPasswordField pinField;
	private JButton loginButton;
	private JButton guestButton;
	private JLabel errorMessageLabel;
	
	public LoginView(ViewManager manager) {
		super();
		this.manager = manager;
		init();
	}
	
	public void toggleErrorMessage(boolean show) {
		if (show) {
			errorMessageLabel.setText("Invalid email and/or PIN.");
		}
		else {
			errorMessageLabel.setText("");
		}
	}
	
	public void clear() {
		IDField.setText("");
		pinField.setText("");
		
		toggleErrorMessage(false);
	}
	
	private void init() {
		this.setLayout(null);;
		
		initTitle();
		initErrorMessageLabel();
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
	
	private void initErrorMessageLabel() {
		errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		errorMessageLabel.setBounds(0, 110, 500, 35);
		errorMessageLabel.setFont(new Font("DialogInput", Font.ITALIC, 12));
		errorMessageLabel.setForeground(Color.RED);
		
		this.add(errorMessageLabel);
	}
	
	private void initIDField() {
		JLabel label = new JLabel("Employee ID: ", SwingConstants.RIGHT);
		label.setBounds(100, 160, 95, 35);
		label.setLabelFor(IDField);;
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		IDField = new JTextField(20);
		IDField.setBounds(205, 160, 200, 35);
		
		IDField.addKeyListener(new KeyAdapter() {
		
		});
		
		
		this.add(label);
		this.add(IDField);
	}
	
	private void initPinField() {
		JLabel label = new JLabel("PIN :", SwingConstants.RIGHT);
		label.setBounds(100, 200, 95, 35);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setBounds(205, 200, 200, 35);;
		
		pinField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (pinField.getPassword().length >=4) {
					e.consume();
				}
				else if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
					e.consume();
				}
			}
		});
		
		this.add(label);
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
					Long employeeID = Long.parseLong(emp);
					char[] digits = pinField.getPassword();
					int pin = Integer.parseInt(new String(digits));
					
//					manager.employeeLogin(employeeID, pin);
				}
			}
		});
		
		this.add(loginButton);
	}
	
	private void initGuestButton() {
		guestButton = new JButton("Continue as Guest");
		guestButton.setBounds(205, 300, 200, 35);;
		
		guestButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				
				if (source.equals(guestButton)) {
					
					manager.guestLogin();
				}
			}
		});
		
		this.add(loginButton);
	}
	

}
