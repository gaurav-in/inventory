package com.x.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.x.service.impl.SmsService;
import com.x.sms.command.listner.CommandExecutor;

public class SmsServiceTest {

	private List<ISmsService> alist;
	private CommandExecutor executor;
	@Before
	public void setUp(){
		alist = new ArrayList<>();
		
		alist.add(new SmsService("create Book01 10.50 13.79"));
		alist.add(new SmsService("create Food01 1.47 3.98"));
		alist.add(new SmsService("create Med01 30.63 34.29"));
		alist.add(new SmsService("create Tab01 57.00 84.98"));
		alist.add(new SmsService("updateBuy Tab01 100"));
		alist.add(new SmsService("updateSell Tab01 2"));
		alist.add(new SmsService("updateBuy Food01 500"));
		alist.add(new SmsService("updateBuy Book01 100"));
		alist.add(new SmsService("updateBuy Med01 100"));
		alist.add(new SmsService("updateSell Food01 1"));
		alist.add(new SmsService("updateSell Food01 1"));
		alist.add(new SmsService("updateSell Tab01 2"));
		alist.add(new SmsService("report"));
		alist.add(new SmsService("delete Book01"));
		alist.add(new SmsService("updateSell Tab01 5"));
		alist.add(new SmsService("create Mobile01 10.51 44.56"));
		alist.add(new SmsService("updateBuy Mobile01 250"));
		alist.add(new SmsService("updateSell Food01 5"));
		alist.add(new SmsService("updateSell Mobile01 4"));
		alist.add(new SmsService("updateSell Med01 10"));
		alist.add(new SmsService("report"));
		alist.add(new SmsService("updateSellPrice Mobile01 30.56"));
		alist.add(new SmsService("updateSellPrice Food01 5.56"));
		alist.add(new SmsService("updateSell Mobile01 4"));
		alist.add(new SmsService("updateSell Food01 50"));
		alist.add(new SmsService("report"));
		
		executor = CommandExecutor.getInstance();
	}
	
	@Test
	public void test() {
		for(ISmsService service : alist){
			service.executeCommand();
		}
	}
	
	@After
	public void tearDown(){
		executor.shutdown();
	}
}
