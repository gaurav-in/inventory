package com.x.sms.command.listner;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.x.sms.command.ICommand;

public class CommandExecutor {
	
	private static CommandExecutor _instance;
	private static Object instanceLock = new Object();
	
	private BlockingQueue<ICommand> queueCommand = new LinkedBlockingQueue<>();
	
	private static ExecutorService executorService = Executors.newFixedThreadPool(1);
	
	private CommandExecutor(){
		super();
	}
	
	public static CommandExecutor getInstance(){
		
		if(_instance == null){
			synchronized (instanceLock) {
				if(_instance == null){
					_instance = new CommandExecutor();
				}
			}
		}
		
		return _instance;
	}
	
	public void executeCommand(ICommand command){
		queueCommand.add(command);
		
		if(!executorService.isTerminated()){
			executorService.submit(new Runnable(){
				
				@Override
				public void run() {
					_instance.executeCommand();					
				}
			});
		}
	}
	
	private void executeCommand(){

		while(true){
			try {
				ICommand command = queueCommand.poll();
				if(command != null)
					command.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void shutdown(){
		while(!queueCommand.isEmpty()){
			//do nothing
		}
		executorService.shutdown();
		
		try {
			executorService.awaitTermination(1000, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
