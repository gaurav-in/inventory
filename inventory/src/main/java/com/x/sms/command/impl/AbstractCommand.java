package com.x.sms.command.impl;

import java.text.DecimalFormat;

import com.x.service.IInventoryManagementService;
import com.x.service.impl.InventoryManagementService;

public abstract class AbstractCommand {
	protected IInventoryManagementService service;
	protected DecimalFormat df = new DecimalFormat(".##");
	protected String [] commands;
	
	public AbstractCommand(){
		this.service = new InventoryManagementService(); 
	}
	
	public AbstractCommand(String commandString){
		this.service = new InventoryManagementService(); 
		this.commands =  commandString.split("\\s");
	}
	
	public IInventoryManagementService getService() {
		return service;
	}

	public void setService(IInventoryManagementService service) {
		this.service = service;
	}
}
