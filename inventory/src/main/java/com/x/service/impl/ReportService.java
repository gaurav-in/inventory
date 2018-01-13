package com.x.service.impl;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

import com.x.model.Inventory;
import com.x.model.Item;
import com.x.repository.InventoryRepository;
import com.x.service.IReportService;

public class ReportService implements IReportService {
	
	private static Double lastProfit = 0.00;
	
	@Override
	public void print(){
		
		int itemNameLength = "Item Name".length();
		int boughtLength = "Bought At".length();
		int soldAtLength = "Bought At".length();
		int availableQtyLength = "AvailableQty".length();
		int valueLength = "Value".length();
		
		int distance = 15;
		int extraCharacters = 0;
		double totalValue = 0.00;
		
		//profit calculations
		double totalCostPriceOfDeletedItems = 0.00;
		double sumOfDifferenceBetweenCpAndSp = 0.00;
		
		System.out.print(new String(new char[2*distance]).replace("\0", " "));
		System.out.println("INVENTORY REPORT");
		System.out.print(String.format("%1$-"+distance+"s","Item Name"));
		System.out.print(String.format("%1$-"+distance+"s","Bought At"));
		System.out.print(String.format("%1$-"+distance+"s","Sold At"));
		System.out.print(String.format("%1$-"+distance+"s","AvailableQty"));
		System.out.print(String.format("%1$s","Value"));
		System.out.println();

		System.out.print(String.format("%1$-"+distance+"s",new String(new char[itemNameLength]).replace("\0", "-")));
		System.out.print(String.format("%1$-"+distance+"s",new String(new char[boughtLength]).replace("\0", "-")));
		System.out.print(String.format("%1$-"+distance+"s",new String(new char[soldAtLength]).replace("\0", "-")));
		System.out.print(String.format("%1$-"+distance+"s",new String(new char[availableQtyLength]).replace("\0", "-")));
		System.out.print(String.format("%1$-"+distance+"s",new String(new char[valueLength]).replace("\0", "-")));
		System.out.println();
		
		DecimalFormat format = new DecimalFormat(".00");
		
		Map<String, Inventory> map = InventoryRepository.getMap();
		
		for(Entry<String, Inventory> entry : map.entrySet()){
			
			Inventory inventory = entry.getValue();
			
			Item item = inventory.getItem();
			
			int quantity = inventory.getQuantity();
			double cp = item.getCostPrice();
			double sp = item.getSellingPrice();
			
			double value = quantity * cp;
			totalValue += value;
			
			//Get all the items sold.
			if(inventory.getItemsBySellingPrice().size() > 0){
				for(Entry <Double, Integer> sellingEntry : inventory.getItemsBySellingPrice().entrySet()){
					
					int quantitySoldForSP = sellingEntry.getValue();
					double sellingPrice = sellingEntry.getKey();
					
					sumOfDifferenceBetweenCpAndSp += (sellingPrice - cp) * quantitySoldForSP;
				}
			}
			
			if(inventory.isDeleted()){
				totalCostPriceOfDeletedItems += cp * quantity;
				continue;
			}
			
			System.out.print(String.format("%1$-"+(distance)+"s", inventory.getItem().getName()));
			System.out.print(String.format("%1$-"+(distance)+"s", format.format(cp)));
			
			System.out.print(String.format("%1$-"+(distance)+"s", format.format(sp)));
			System.out.print(String.format("%1$-"+(distance)+"d", quantity));
			
			System.out.print(String.format("%s\n", format.format(value)));
			
			extraCharacters = format.format(value).length() >  extraCharacters
					? format.format(value).length()
					: extraCharacters;
		}
		
		lastProfit = sumOfDifferenceBetweenCpAndSp - totalCostPriceOfDeletedItems - lastProfit;
		
		System.out.println(new String(new char[4*distance + extraCharacters]).replace("\0", "-"));
		System.out.println(String.format("%1$-"+(4*distance)+"s%2$s", "Total Value", format.format(totalValue)));
		System.out.println(String.format("%1$-"+(4*distance)+"s%2$s", "Profit since previous report", format.format(lastProfit)));
	}
}
