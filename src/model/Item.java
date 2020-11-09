package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Item {

	private static int itemCounter = 0;

	private String name;
	private double cost;
	private String description;
	private String picturePath;
	private int itemID;
	private Image image;

	public Item(String name, double cost, String description, String picturePath, String filePath) {
		this.name = name;
		this.cost = cost;
		this.description = description;
		this.picturePath = picturePath;
		this.itemID = itemCounter++;

		try {
			File sourceImage = new File(filePath + picturePath);
			this.image = ImageIO.read(sourceImage);
		} catch (IOException e) {
			this.image = null;
			e.printStackTrace();
		}
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

	public String getPicturePath() {
		return picturePath;
	}

	public Image getPicture() {
		return image;
	}

	public int getItemID() {
		return itemID;
	}

	public void setCost(double newCost) {
		this.cost = newCost;
	}

	public void setName(String newName) {
		if (newName != null && newName.length() != 0) {
			this.name = newName;
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a valid name", "Joe's Kiosk", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setDescription(String newDescription) {
		if (newDescription != null && newDescription.length() != 0) {
			this.description = newDescription;
		} else {
			JOptionPane.showMessageDialog(null, "Please enter a valid description", "Joe's Kiosk",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void setPicture(Image image) {
		this.image = image;
	}

}
