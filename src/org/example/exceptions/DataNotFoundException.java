package org.example.exceptions;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 951103117560420329L;
	
	public DataNotFoundException(String message){
		super(message);
	}

}
