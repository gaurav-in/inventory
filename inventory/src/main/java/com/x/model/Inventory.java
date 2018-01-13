package com.x.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
	
	private Item item;
	private int quantity;
	private boolean isDeleted;
	
	private Map<Double, Integer> itemsBySellingPrice = new HashMap<>();
	
	public Inventory(){
		
	}
	
	public Inventory(Item item, int quantity) {
		super();
		this.item = item;
		this.quantity = quantity;
	}
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Map<Double, Integer> getItemsBySellingPrice() {
		return itemsBySellingPrice;
	}

	public void setItemsBySellingPrice(Map<Double, Integer> itemsBySellingPrice) {
		this.itemsBySellingPrice = itemsBySellingPrice;
	}
}
