package GUI;
import java.awt.CardLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controller.ViewManager;
import model.Employee;
import model.Cart;
import model.Menu;
import model.Item;
import views.LoginView;
import views.MenuView;
import views.CartView;
import views.CheckoutView;
import views.ItemView;
import views.EmployeeMenuView;
import views.EmployeeItemView;
import views.AfterCheckoutView;
import views.EmployeeView;

//1. Employee uses a file picker to choose an image off computer
//2. Save the file into a director inside of project
//3. Reference the image using a relative path

@SuppressWarnings("serial")
public class Kiosk extends JFrame {
	
	public static final String LOGIN_VIEW = "LOGIN_VIEW";
	public static final String MENU_VIEW = "MENU_VIEW";
	public static final String CART_VIEW = "CART_VIEW";
	public static final String CHECKOUT_VIEW = "CHECKOUT_VIEW";
	public static final String ITEM_VIEW = "ITEM_VIEW";
	public static final String EMPLOYEE_MENU_VIEW = "EMPLOYEE_MENU_VIEW";
	public static final String EMPLOYEE_ITEM_VIEW = "EMPLOYEE_ITEM_VIEW";
	public static final String AFTER_CHECKOUT_VIEW = "AFTER_CHECKOUT_VIEW";
	public static final String EMPLOYEE_VIEW = "EMPLOYEE_VIEW";
	
	public static final int LOGIN_VIEW_INDEX = 0;
	public static final int MENU_VIEW_INDEX = 1;
	public static final int CART_VIEW_INDEX = 2;
	public static final int CHECKOUT_VIEW_INDEX = 3;
	public static final int ITEM_VIEW_INDEX = 4;
	public static final int EMPLOYEE_MENU_VIEW_INDEX = 5;
	public static final int EMPLOYEE_ITEM_VIEW_INDEX = 6;
	public static final int AFTER_CHECKOUT_VIEW_INDEX = 7; 
	public static final int EMPLOYEE_VIEW_INDEX = 8;
	
	private static String filePath = new File("").getAbsolutePath();
	
	public static final String[] errorMessages = {
			"",
			"The given email and password combination does not exist. Please try again.",
			"The given credit card has already expired.",
			"The given credit card is invalid."
	};
	
	
	public static ArrayList<Employee> employees;
	private static Kiosk instance = null;
	public static ArrayList<Item> items;
	public static Menu menu;
	public static Cart cart;
	
	public static Kiosk getInstance() {
		if (instance == null) {
			instance = new Kiosk();
		}
		return instance;
	}
	
	private Kiosk() {
		super("Kiosk");
		Kiosk.employees = new ArrayList<Employee>();
		
		Kiosk.employees.add(new Employee("Joe", "Schmoe", "jschmoe@gmail.com", 1234567890, 1234));
		Kiosk.employees.add(new Employee("Bob", "Dabuilder", "bdabuilder@gmail.com", 1234566543, 4321));
		
		menu = menu.getInstance();
		
		menu.addMenuItem(new Item("Hot Sour Soup", 2.34, "Soup that is both hot and sour", "\\references\\hotAndSourSoup.jpg", filePath));
		menu.addMenuItem(new Item("Fried Rice", 3.99, "Fried white rice with corn, peas, and mushrooms.", "\\references\\friedRice.jpg",filePath));
		menu.addMenuItem(new Item("Fried Chicken", 4.56, "In Congress, July 4, 1776\r\n"
				+ "\r\n"
				+ "The unanimous Declaration of the thirteen united States of America, When in the Course of human events, it becomes necessary for one people to dissolve the political bands which have connected them with another, and to assume among the powers of the earth, the separate and equal station to which the Laws of Nature and of Nature's God entitle them, a decent respect to the opinions of mankind requires that they should declare the causes which impel them to the separation.\r\n"
				+ "\r\n"
				+ "We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness.--That to secure these rights, Governments are instituted among Men, deriving their just powers from the consent of the governed, --That whenever any Form of Government becomes destructive of these ends, it is the Right of the People to alter or to abolish it, and to institute new Government, laying its foundation on such principles and organizing its powers in such form, as to them shall seem most likely to effect their Safety and Happiness. Prudence, indeed, will dictate that Governments long established should not be changed for light and transient causes; and accordingly all experience hath shewn, that mankind are more disposed to suffer, while evils are sufferable, than to right themselves by abolishing the forms to which they are accustomed. But when a long train of abuses and usurpations, pursuing invariably the same Object evinces a design to reduce them under absolute Despotism, it is their right, it is their duty, to throw off such Government, and to provide new Guards for their future security.--Such has been the patient sufferance of these Colonies; and such is now the necessity which constrains them to alter their former Systems of Government. The history of the present King of Great Britain is a history of repeated injuries and usurpations, all having in direct object the establishment of an absolute Tyranny over these States. To prove this, let Facts be submitted to a candid world.\r\n"
				+ "\r\n"
				+ "He has refused his Assent to Laws, the most wholesome and necessary for the public good.\r\n"
				+ "\r\n"
				+ "He has forbidden his Governors to pass Laws of immediate and pressing importance, unless suspended in their operation till his Assent should be obtained; and when so suspended, he has utterly neglected to attend to them.\r\n"
				+ "\r\n"
				+ "He has refused to pass other Laws for the accommodation of large districts of people, unless those people would relinquish the right of Representation in the Legislature, a right inestimable to them and formidable to tyrants only.\r\n"
				+ "\r\n"
				+ "He has called together legislative bodies at places unusual, uncomfortable, and distant from the depository of their public Records, for the sole purpose of fatiguing them into compliance with his measures.\r\n"
				+ "\r\n"
				+ "He has dissolved Representative Houses repeatedly, for opposing with manly firmness his invasions on the rights of the people.\r\n"
				+ "\r\n"
				+ "He has refused for a long time, after such dissolutions, to cause others to be elected; whereby the Legislative powers, incapable of Annihilation, have returned to the People at large for their exercise; the State remaining in the mean time exposed to all the dangers of invasion from without, and convulsions within.\r\n"
				+ "\r\n"
				+ "He has endeavoured to prevent the population of these States; for that purpose obstructing the Laws for Naturalization of Foreigners; refusing to pass others to encourage their migrations hither, and raising the conditions of new Appropriations of Lands.\r\n"
				+ "\r\n"
				+ "He has obstructed the Administration of Justice, by refusing his Assent to Laws for establishing Judiciary powers.\r\n"
				+ "\r\n"
				+ "He has made Judges dependent on his Will alone, for the tenure of their offices, and the amount and payment of their salaries.\r\n"
				+ "\r\n"
				+ "He has erected a multitude of New Offices, and sent hither swarms of Officers to harrass our people, and eat out their substance.\r\n"
				+ "\r\n"
				+ "He has kept among us, in times of peace, Standing Armies without the Consent of our legislatures.\r\n"
				+ "\r\n"
				+ "He has affected to render the Military independent of and superior to the Civil power.\r\n"
				+ "\r\n"
				+ "He has combined with others to subject us to a jurisdiction foreign to our constitution, and unacknowledged by our laws; giving his Assent to their Acts of pretended Legislation:\r\n"
				+ "\r\n"
				+ "For Quartering large bodies of armed troops among us:\r\n"
				+ "\r\n"
				+ "For protecting them, by a mock Trial, from punishment for any Murders which they should commit on the Inhabitants of these States:\r\n"
				+ "\r\n"
				+ "For cutting off our Trade with all parts of the world:\r\n"
				+ "\r\n"
				+ "For imposing Taxes on us without our Consent:\r\n"
				+ "\r\n"
				+ "For depriving us in many cases, of the benefits of Trial by Jury:\r\n"
				+ "\r\n"
				+ "For transporting us beyond Seas to be tried for pretended offences\r\n"
				+ "\r\n"
				+ "For abolishing the free System of English Laws in a neighbouring Province, establishing therein an Arbitrary government, and enlarging its Boundaries so as to render it at once an example and fit instrument for introducing the same absolute rule into these Colonies:\r\n"
				+ "\r\n"
				+ "For taking away our Charters, abolishing our most valuable Laws, and altering fundamentally the Forms of our Governments:\r\n"
				+ "\r\n"
				+ "For suspending our own Legislatures, and declaring themselves invested with power to legislate for us in all cases whatsoever.\r\n"
				+ "\r\n"
				+ "He has abdicated Government here, by declaring us out of his Protection and waging War against us.\r\n"
				+ "\r\n"
				+ "He has plundered our seas, ravaged our Coasts, burnt our towns, and destroyed the lives of our people.\r\n"
				+ "\r\n"
				+ "He is at this time transporting large Armies of foreign Mercenaries to compleat the works of death, desolation and tyranny, already begun with circumstances of Cruelty & perfidy scarcely paralleled in the most barbarous ages, and totally unworthy the Head of a civilized nation.\r\n"
				+ "\r\n"
				+ "He has constrained our fellow Citizens taken Captive on the high Seas to bear Arms against their Country, to become the executioners of their friends and Brethren, or to fall themselves by their Hands.\r\n"
				+ "\r\n"
				+ "He has excited domestic insurrections amongst us, and has endeavoured to bring on the inhabitants of our frontiers, the merciless Indian Savages, whose known rule of warfare, is an undistinguished destruction of all ages, sexes and conditions.\r\n"
				+ "\r\n"
				+ "In every stage of these Oppressions We have Petitioned for Redress in the most humble terms: Our repeated Petitions have been answered only by repeated injury. A Prince whose character is thus marked by every act which may define a Tyrant, is unfit to be the ruler of a free people.\r\n"
				+ "\r\n"
				+ "Nor have We been wanting in attentions to our Brittish brethren. We have warned them from time to time of attempts by their legislature to extend an unwarrantable jurisdiction over us. We have reminded them of the circumstances of our emigration and settlement here. We have appealed to their native justice and magnanimity, and we have conjured them by the ties of our common kindred to disavow these usurpations, which, would inevitably interrupt our connections and correspondence. They too have been deaf to the voice of justice and of consanguinity. We must, therefore, acquiesce in the necessity, which denounces our Separation, and hold them, as we hold the rest of mankind, Enemies in War, in Peace Friends.\r\n"
				+ "\r\n"
				+ "We, therefore, the Representatives of the united States of America, in General Congress, Assembled, appealing to the Supreme Judge of the world for the rectitude of our intentions, do, in the Name, and by Authority of the good People of these Colonies, solemnly publish and declare, That these United Colonies are, and of Right ought to be Free and Independent States; that they are Absolved from all Allegiance to the British Crown, and that all political connection between them and the State of Great Britain, is and ought to be totally dissolved; and that as Free and Independent States, they have full Power to levy War, conclude Peace, contract Alliances, establish Commerce, and to do all other Acts and Things which Independent States may of right do. And for the support of this Declaration, with a firm reliance on the protection of divine Providence, we mutually pledge to each other our Lives, our Fortunes and our sacred Honor.", "\\references\\chicken.jpeg",filePath));
		
		
	}
	
	
	public static Employee lookupUser(long employeeID, int pin) {
		for (int k = 0; k < employees.size(); k++) {
			if (employees.get(k).getAccount() == employeeID && employees.get(k).getPin() == pin) {
				return employees.get(k);
			}
		}
		return null;
	}
	
	private void init() {
		cart = new Cart();
		
		JFrame frame = new JFrame("Kiosk");
		JPanel views = new JPanel(new CardLayout());
		ViewManager manager = new ViewManager(views);
		views.add(new LoginView(manager), LOGIN_VIEW);
		views.add(new MenuView(manager), MENU_VIEW);
		views.add(new CartView(manager, cart), CART_VIEW);
		views.add(new CheckoutView(manager), CHECKOUT_VIEW);
		views.add(new ItemView(manager), ITEM_VIEW);
		views.add(new EmployeeMenuView(manager), EMPLOYEE_MENU_VIEW);
		views.add(new EmployeeItemView(manager), EMPLOYEE_ITEM_VIEW);
		views.add(new AfterCheckoutView(manager), AFTER_CHECKOUT_VIEW);
		views.add(new EmployeeView(manager), EMPLOYEE_VIEW);
		
		
		
		this.add(views);
		this.setBounds(100,100,800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					Kiosk.getInstance().init();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
