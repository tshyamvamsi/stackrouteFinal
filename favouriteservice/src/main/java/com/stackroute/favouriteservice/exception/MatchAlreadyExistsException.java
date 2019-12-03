package com.stackroute.favouriteservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason = "Match already exists")
public class MatchAlreadyExistsException extends Exception {

/*	private String message;

	public MatchAlreadyExistsException(String message) {
		super(message);
		this.message = message;

	}

	@Override
	public String toString() {

		return "MatchAlreadyExistsException: [" + message + "]";
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}*/

}
