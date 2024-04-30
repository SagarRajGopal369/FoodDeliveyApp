package com.food.models;

import java.util.HashMap;

public class Cart {
	
	private HashMap<Integer, CartItem> mapItems;
	
	public Cart() {
		this.mapItems = new HashMap<Integer, CartItem>();
	}
	
	public void addItem(CartItem item) 
	{
		int itemId = item.getItemId();
		if(mapItems.containsKey(itemId))
		{
			//If item is already exits, increase the quantity
			CartItem existingItem = mapItems.get(itemId);
			int oldQuantity = existingItem.getQuantity();
			int newQuantity = item.getQuantity();
			existingItem.setQuantity(oldQuantity + newQuantity);
		}
		else
		{
			//If item is new, add to cart
			mapItems.put(itemId, item);
		}
	}
	
	//update the quantity of an item in the cart
	public void updateItem(int itemId, int quantity)
	{
		if(mapItems.containsKey(itemId))
		{
			if(quantity <= 0)
			{
				mapItems.remove(itemId);
			}
			else
			{
				CartItem cartItem = mapItems.get(itemId);
				cartItem.setQuantity(quantity);
			}
		}
	}
	
	//Remove the item from the car
	public void removeItem(int itemId)
	{
		mapItems.remove(itemId);
	}
	
	//Get all items in the cart
	public HashMap<Integer, CartItem> getItems() 
	{
		return mapItems;
	}
	
	//Clear the cart
	public void clear()
	{
		mapItems.clear();
	}
}
