package com.x.sms.command.impl;

import com.x.service.IReportService;
import com.x.service.impl.ReportService;
import com.x.sms.command.ICommand;

public class ReportCommand implements ICommand {

	private IReportService service;
	
	public ReportCommand(){
		super();
		service = new ReportService();
	}
	
	public ReportCommand(String commandString){
		super();
		service = new ReportService();
	}
	
	@Override
	public void execute() {
		service.print();
	}
}
