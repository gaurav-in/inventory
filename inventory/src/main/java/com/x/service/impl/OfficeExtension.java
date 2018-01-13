package com.x.service.impl;

import com.x.model.Inventory;
import com.x.service.IOfficeExtension;

public class OfficeExtension extends InventoryManagementService implements IOfficeExtension {

	@Override
	public void updateSellingPrice(String itemName, double sellingPrice) {
		
		if(sellingPrice <= 0){
			return;
		}
		
		Inventory i = map.get(itemName);
		
		if(i != null && !i.isDeleted()){
			
			synchronized (lock) {
				i.getItem().setSellingPrice(sellingPrice);
			}
		}
	}

}
