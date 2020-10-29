package model;

public class Employee {
	
	private static long employeeCounter = 0;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private long phoneNumber;
	private int pin;
	private long employeeID;
	
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
