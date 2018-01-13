package com.x.service;

import com.x.model.Item;

public interface IInventoryManagementService{
	public void create(Item item);
	public void delete(String itemName);
	public void updateBuy(String itemName, int quantity);
	public void updateSell(String itemName, int quantity);
}
