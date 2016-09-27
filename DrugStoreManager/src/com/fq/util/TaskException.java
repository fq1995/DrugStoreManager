package com.fq.util;

public class TaskException extends RuntimeException {

	public TaskException(){
	}
	
	public TaskException(String message){
		super(message);
	}
	
	public TaskException(String message,Throwable able){
		super(message,able);
	}
}
