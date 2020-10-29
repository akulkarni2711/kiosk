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
	private JTextField emailField;
	private JPasswordField pinField;
	private JButton loginButton;
	private JButton guestLoginButton;
	private JLabel errorMessageLabel;
	
	public LoginView(ViewManager manager) {
		super();
		this.manager = manager;
		this.init();
		
		
	}

}
