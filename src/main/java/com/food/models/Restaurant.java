package com.food.models;

public class Restaurant {

	private int restaurantId;
	private String name;
	private String address;
	private double rating;
	private String cuisineType;
	private String IsActive;
	private int DeliveryTime;
	private int adminUserId;
	private String imagePath;
	
	
	
	public Restaurant() {
		super();
	}

	public Restaurant(int restaurantId, String name, String address,double rating, String cuisineType,
						String IsActive, int DeliveryTime, int adminUserId, String imagePath)
	{
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.cuisineType = cuisineType;
		this.IsActive = IsActive;
		this.DeliveryTime = DeliveryTime;
		this.adminUserId = adminUserId;
		this.imagePath = imagePath;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getCuisineType() {
		return cuisineType;
	}

	public void setCuisineType(String cuisineType) {
		this.cuisineType = cuisineType;
	}

	public String getIsActive() {
		return IsActive;
	}

	public void setIsActive(String IsActive) {
		this.IsActive = IsActive;
	}

	public int getDeliveryTime() {
		return DeliveryTime;
	}

	public void setDeliveryTime(int DeliveryTime) {
		this.DeliveryTime = DeliveryTime;
	}

	public int getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(int adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
	
}
