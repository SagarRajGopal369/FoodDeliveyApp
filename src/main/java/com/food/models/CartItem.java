package com.food.models;

public class CartItem {
	private int itemId;
	private int restaurantId;
	private String  itemName;
	private double price;
	private int quantity;
	private String imagePath;
	
	public CartItem() {
	}

	public CartItem(int itemId, int restaurantId, String itemName, double price, int quantity) {
		super();
		this.itemId = itemId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getImagePath() {
	        return imagePath;
    }

	public void setImagePath(String imagePath) {
    	this.imagePath = imagePath;
    }
	
	
	
}
