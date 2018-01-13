package com.x.service.impl;

import com.x.service.ISmsService;
import com.x.sms.command.ICommand;
import com.x.sms.command.impl.CreateCommand;
import com.x.sms.command.impl.DeleteCommand;
import com.x.sms.command.impl.ReportCommand;
import com.x.sms.command.impl.UpdateBuyCommand;
import com.x.sms.command.impl.UpdateSellCommand;
import com.x.sms.command.impl.UpdateSellPriceCommand;
import com.x.sms.command.listner.CommandExecutor;

public class SmsService implements ISmsService{
	
	private String commandString;
	private CommandExecutor executor;
	
	public SmsService (){
		
	}
	
	public SmsService(String commandString){
		this.commandString = commandString;
		this.executor = CommandExecutor.getInstance();
	}
	
	@Override
	public void executeCommand(){
		ICommand command = getCommand();
		executor.executeCommand(command);
	}
	
	private ICommand getCommand(){
		
		String command = commandString.indexOf(" ") == -1 ? commandString : commandString.substring(0, commandString.indexOf(" "));
		String parameters = commandString.indexOf(" ") == -1 ? "" : commandString.substring(commandString.indexOf(" ")+1);	
		
		switch (command.toUpperCase()){
			case "CREATE" :
				return new CreateCommand(parameters);
			case "DELETE" :
				return new DeleteCommand(parameters);
			case "UPDATESELL" :
				return new UpdateSellCommand(parameters);
			case "UPDATEBUY" :
				return new UpdateBuyCommand(parameters);
			case "UPDATESELLPRICE" :
				return new UpdateSellPriceCommand(parameters);	
			case "REPORT" :
				return new ReportCommand();
			default :
				break;
		}
		return null;
	}
}
