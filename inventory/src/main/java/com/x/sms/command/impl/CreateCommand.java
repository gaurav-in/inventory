package com.x.sms.command.impl;

import com.x.model.Item;
import com.x.sms.command.ICommand;

public class CreateCommand extends AbstractCommand implements ICommand {

	private String itemName;
	private double costPrice;
	private double sellingPrice;
	  
	public CreateCommand(){
		super();
	}
	
	//itemName costPrice sellingPrice
	public CreateCommand(String commandString){
		super(commandString);
		
		this.itemName = commands[0];
		this.costPrice = Double.parseDouble(df.format(Double.parseDouble(commands[1])));
		this.sellingPrice = Double.parseDouble(df.format(Double.parseDouble(commands[2])));
	}
	
	@Override
	public void execute() {
		Item item = new Item(itemName, costPrice, sellingPrice);
		service.create(item);
	}
}
