package com.x.sms.command.impl;

import com.x.sms.command.ICommand;

public class UpdateBuyCommand extends AbstractCommand implements ICommand {
	private String itemName;
	private int quantity;
	
	public UpdateBuyCommand(){
	}
	
	public UpdateBuyCommand(String commandString){
		super(commandString);
		this.itemName = commands[0];
		this.quantity = Integer.parseInt(commands[1]);
	}
	
	@Override
	public void execute() {
		service.updateBuy(itemName, quantity);
	}
}
