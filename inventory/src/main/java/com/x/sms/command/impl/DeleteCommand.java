package com.x.sms.command.impl;

import com.x.sms.command.ICommand;

public class DeleteCommand extends AbstractCommand implements ICommand {
	private String itemName;
	
	public DeleteCommand(){
	}
	
	//itemName
	public DeleteCommand(String commandString){
		super();
		this.itemName = commandString;
	}
	
	@Override
	public void execute() {
		service.delete(itemName);
	}
}
