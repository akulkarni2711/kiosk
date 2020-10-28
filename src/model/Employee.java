package model;

public class Employee {
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	private long phoneNumber;
	private int pin;
	private Order order;
	
	public Employee(String firstName, String lastName, String emailAddress, long phoneNumber, int pin) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.pin = pin;
		
		this.order = new Order();
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
	
	public Order getAccount() {
		return order;
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
