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
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import GUI.Kiosk;
import controller.ViewManager;
import model.Item;
import model.Menu;

@SuppressWarnings("serial")
public class EmployeeMenuView extends JPanel {

	private ViewManager manager;
	private JButton orderButton;
	private JLabel totalCost;
	private JScrollBar scrollBar;
	private JButton logoutButton;
	private JButton cartButton;
	private JTable itemMenu;
	private Menu m;
	private Object[][] data;
	private JLabel itemLabel;
	private JPanel menuItemPanel;
	private JScrollPane menuPane;
	private JButton addItemButton;
	private JButton backButton;
	private String newName;
	private String newPrice;
	private String newDescription;
	private JFileChooser chooser;
	private Image image;

	public EmployeeMenuView(ViewManager manager) {
		super();
		this.manager = manager;
		m = Menu.getInstance();
		init();
	}

	private void init() {
		this.removeAll();
		this.setLayout(null);

		initTitle();
		initBackButton();
		initMenuList();
		initLogoutButton();
		initAddItemButton();
		initScrollBar();
	}

	public void updateCard() {
		init();
	}

	private void initTitle() {
		JLabel title = new JLabel("Menu(Employee)", SwingConstants.CENTER);
		title.setBounds(160, 40, 500, 35);
		title.setFont(new Font("DialogInput", Font.BOLD, 21));

		this.add(title);
	}

	@SuppressWarnings("rawtypes")
	private void initMenuTable() {
		HashMap<Integer, Item> h = m.getHashMap();

		Item i = null;

		Iterator<?> it = h.entrySet().iterator();
		int n = 0;
		while (it.hasNext()) {
			HashMap.Entry pair = (HashMap.Entry) it.next();
			int id = (Integer) pair.getKey();
			i = (Item) pair.getValue();
			itemLabel = new JLabel(i.getName() + "- $" + i.getCost(), SwingConstants.LEFT);
			itemLabel.setBounds(50, 80 + 40 * n, 200, 35);

			orderButton = new JButton("Select");
			orderButton.setBounds(300, 80 + 40 * n, 200, 35);
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
		menuItemPanel.setPreferredSize(new Dimension(750, 40 * h.size()));
	}

	private void initAddItemButton() {
		addItemButton = new JButton("Add new item");
		addItemButton.setBounds(550, 490, 150, 50);
		this.add(addItemButton);
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// name
				do {
					newName = JOptionPane.showInputDialog("New name for this item: ");
				} while (newName == null);

				// price
				Double newPriceValue = null;
				do {
					newPrice = JOptionPane.showInputDialog("New item price:");
					try {
						newPriceValue = Double.valueOf(newPrice);
					} catch (NumberFormatException h) {
						JOptionPane.showMessageDialog(null, "Please enter a valid price", "Joe's Kiosk",
								JOptionPane.ERROR_MESSAGE);
					}

				} while (newPriceValue == null || newPriceValue < 0);

				// description
				do {
					newDescription = JOptionPane.showInputDialog("Description for this item: ");
				} while (newDescription == null);

				// image
				chooser = new JFileChooser();
				File selectedFile = null;
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						selectedFile = chooser.getSelectedFile();
						image = ImageIO.read(selectedFile);
					} catch (IOException h) {
						image = null;
						h.printStackTrace();
					}
				}

				manager.addNewItem(newName, newPriceValue, newDescription, selectedFile.getAbsolutePath());
				JOptionPane.showMessageDialog(null, "Item successfully added to menu", "Joe's Kiosk",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		this.add(addItemButton);
	}

	private void initLogoutButton() {
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(100, 490, 175, 50);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.logOut();
			}
		});
		this.add(logoutButton);
	}

	private void initBackButton() {
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				manager.switchTo(Kiosk.EMPLOYEE_VIEW);
			}
		});
		backButton.setBounds(400, 490, 100, 50);
		this.add(backButton);

	}

	private void initScrollBar() {
		scrollBar = new JScrollBar();
		this.add(scrollBar);
	}

	private void initMenuList() {

		menuItemPanel = new JPanel();
		menuItemPanel.setLayout(null);
		menuItemPanel.setBounds(0, 80, 600, 600);

		menuPane = new JScrollPane(menuItemPanel);
		menuPane.setBounds(100, 80, 600, 400);

		menuPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		menuPane.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
		menuPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		if (m.getHashMap() != null) {
			initMenuTable();
		}

		this.add(menuPane);

	}

}
