package views;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSpinner.NumberEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import GUI.Kiosk;
import controller.ViewManager;
import model.Employee;
import model.Item;
import model.Menu;
import model.Cart;

public class ItemView extends JPanel {

	// TODO: Add messages to page when item is successfully added to cart

	protected ViewManager manager;
	protected JLabel itemName;
	protected JLabel itemPrice;
	protected JTextArea itemDescription;
	protected Image picture;
	protected JLabel pictureLabel;
	protected JButton backToMenuButton;
	protected JSpinner spinQuantity;
	protected JButton addToCartButton;
	protected Item item;
	protected Menu m;
	protected ImageIcon pictureIcon;
	protected JLabel picLabel;
	protected int quantity;

	public ItemView(ViewManager manager) {
		super();

		m = Menu.getInstance();

		this.manager = manager;
	}

	protected void init() {
		this.removeAll();
		this.setLayout(null);
		this.item = manager.getActiveItem();
		initTitle();
		initInformation(item);
		initQuantity();
		initAddToCartButton();
		initBackToMenuButton();

	}

	public void updateCard() {
		init();
	}

	protected void initTitle() {
		JLabel title = new JLabel("Joe's Restaraunt Kiosk", SwingConstants.CENTER);
		title.setBounds(0, 20, 500, 35);
		title.setFont(new Font("DialogInput", Font.BOLD, 21));

		this.add(title);
	}

	protected void initInformation(Item item) {
		itemName = new JLabel("Item Name: " + item.getName());
		itemName.setBounds(100, 0, 300, 300);
		itemName.setHorizontalTextPosition(JLabel.CENTER);
		this.add(itemName);

		itemPrice = new JLabel("Price: $" + Double.toString(item.getCost()));
		itemPrice.setBounds(100, 100, 100, 200);
		this.add(itemPrice);
		Color color = new Color(238, 238, 238);
		itemDescription = new JTextArea("Description: " + item.getDescription());
		itemDescription.setWrapStyleWord(true);
		itemDescription.setLineWrap(true);
		itemDescription.setFocusable(false);
		itemDescription.setBounds(100, 225, 225, 250);
		itemDescription.setBackground(color);
		itemDescription.setEditable(false);
		this.add(itemDescription);

		Image picture = manager.resizeImage(item.getPicture());

		picLabel = new JLabel(new ImageIcon(picture));
		picLabel.setBounds(400, 100, 250, 200);
		this.add(picLabel);
	}

	private void initQuantity() {
		SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 100, 1);
		spinQuantity = new JSpinner(model);
		spinQuantity.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity = ((Integer) spinQuantity.getValue()).intValue();
			}
		});
		spinQuantity.setBounds(25, 500, 40, 40);

		this.add(spinQuantity);
	}

	private void initAddToCartButton() {

		addToCartButton = new JButton("Add Item to Cart");
		addToCartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quantity = ((Integer) spinQuantity.getValue()).intValue();
				manager.addItem(item.getItemID(), quantity);
			}
		});
		addToCartButton.setBounds(400, 500, 150, 50);
		this.add(addToCartButton);

	}

	protected void initBackToMenuButton() {

		backToMenuButton = new JButton("Back to menu");
		backToMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.switchTo("MENU_VIEW");
			}
		});
		backToMenuButton.setBounds(150, 500, 150, 50);
		this.add(backToMenuButton);

	}

}