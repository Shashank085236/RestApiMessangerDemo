package org.example.models;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * This class represents a Error/Exception 
 * This provides some useful information about error meant for Clients.
 *
 */
@XmlRootElement
public class ErrorMessage {
	private int errorCode;
	private String errorMessage;
	private String errorDocumentation;
	
	public ErrorMessage(){}

	public ErrorMessage(int errorCode, String errorMessage,
			String errorDocumentation) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorDocumentation = errorDocumentation;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorDocumentation() {
		return errorDocumentation;
	}

	public void setErrorDocumentation(String errorDocumentation) {
		this.errorDocumentation = errorDocumentation;
	}
	
	
}
