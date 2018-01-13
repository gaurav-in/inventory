package com.x.sms.command.impl;

import com.x.service.impl.OfficeExtension;
import com.x.sms.command.ICommand;

public class UpdateSellPriceCommand extends AbstractCommand implements ICommand {

	private String itemName;
	private double sellingPrice;
	private OfficeExtension extension;
	
	public UpdateSellPriceCommand(String commandString){
		super(commandString);
		this.itemName = commands[0];
		this.sellingPrice = Double.parseDouble(df.format(Double.parseDouble(commands[1])));
		
		this.extension = new OfficeExtension();
	}
	
	@Override
	public void execute() {
		extension.updateSellingPrice(itemName, sellingPrice);
	}
}
