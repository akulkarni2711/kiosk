package model;

import java.awt.Image;

public class Item {
	
	private static int itemCounter = 0;
	
	
	private String name;
	private double cost;
	private String description;
	private Image picture;
	private int itemID;
	
	public Item(String name, double cost, String description, Image picture) {
		this.name = name;
		this.cost = cost;
		this.description = description;
		this.picture = picture;
		this.itemID = itemCounter++;
	}
	
	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}

	
	public String getDescription() {
		return description;
	}
	
	public Image getPicture() {
		return picture;
	}
	
	public int getItemID() {
		return itemID;
	}

	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPicture(Image picture) {
		this.picture = picture;
	}
	
}
