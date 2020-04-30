package com.santimanuelr.accountbook.web.rest.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NegativeBalanceException extends ResponseStatusException {

	public NegativeBalanceException(HttpStatus status, String reason) {
		super(status, reason);
		// TODO Auto-generated constructor stub
	}

}
