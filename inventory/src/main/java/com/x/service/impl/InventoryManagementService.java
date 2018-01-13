package com.x.service.impl;

import java.util.Map;

import com.x.model.Inventory;
import com.x.model.Item;
import com.x.repository.InventoryRepository;
import com.x.service.IInventoryManagementService;

public class InventoryManagementService implements IInventoryManagementService {
	
	protected Object lock = InventoryRepository.getLock();
	protected Map<String, Inventory> map = InventoryRepository.getMap();
	
	public InventoryManagementService(){
		
	}
	
	@Override
	public void create(Item item) {
		map.put(item.getName(), new Inventory(item, 0));
	}

	@Override
	public void delete(String itemName) {
		Inventory i = map.get(itemName);
		
		if(i != null && !i.isDeleted()){
			synchronized(lock){
				i.setDeleted(true);
			}
		}
	}

	@Override
	public void updateBuy(String itemName, int quantity) {
		
		//guard against negative
		if(quantity <= 0){
			return;
		}
		
		Inventory i = map.get(itemName);
		
		if(i != null && !i.isDeleted()){
			synchronized(lock){
				i.setQuantity(i.getQuantity() + quantity);
			}
		}
	}

	@Override
	public void updateSell(String itemName, int quantity) {

		//guard against negative
		if(quantity <= 0){
			return;
		}
		
		Inventory i = map.get(itemName);
		
		if(i != null && !i.isDeleted()){
			synchronized(lock){
				if(quantity <= i.getQuantity()){
					i.setQuantity(i.getQuantity() - quantity);
					
					Integer soldCount = i.getItemsBySellingPrice().get(i.getItem().getSellingPrice());
					
					soldCount = soldCount == null ? quantity : soldCount + quantity;
					
					i.getItemsBySellingPrice().put(i.getItem().getSellingPrice(), soldCount);
				}
			}
		}
	}
}
