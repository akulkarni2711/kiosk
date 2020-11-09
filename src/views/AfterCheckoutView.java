package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import model.Cart;

@SuppressWarnings("serial")
public class AfterCheckoutView extends JPanel {

	private ViewManager manager;
	private JLabel message;
	private JButton logoutButton;

	public AfterCheckoutView(ViewManager manager) {
		super();
		this.manager = manager;
		init();
	}

	public void init() {
		initMessage();
		initLogOutButton();
	}

	public void initMessage() {
		message = new JLabel("Thank you for ordering at Joe's Restaurant. Your order will be ready shortly.");
		message.setBounds(100, 100, 100, 100);
		message.setFont(new Font("DialogInput", Font.BOLD, 14));

		this.add(message);
	}

	public void initLogOutButton() {
		logoutButton = new JButton("Finish Order and Logout");
		logoutButton.setBounds(100, 300, 200, 100);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.logOut();
			}
		});
		this.add(logoutButton);
	}

}
