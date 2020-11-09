package model;

public class Employee {
	
	public static long employeeCounter = 1;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private long phoneNumber;
	private int pin;
	private long employeeID;
	private static Employee instance;
	
	public Employee(String firstName, String lastName, String emailAddress, long phoneNumber, int pin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.pin = pin;
		this.employeeID = employeeCounter++;
	}
	
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public long getAccount() {
		return employeeID;
	}
	
	public int getPin() {
		return pin;
	}
	
	public void setFirstName(String fName) {
		this.firstName = fName;
	}
	
	public void setLastName(String lName) {
		this.lastName = lName;
	}
	
	public void setEmailAddress(String email) {
		this.emailAddress = email;
	}
	
	public void setPhoneNumber(long number) {
		this.phoneNumber = number;
	}
	
	public void setPin(int pin) {
		this.pin = pin;
	}

}
