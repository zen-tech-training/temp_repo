package com.stock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidStockIdException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public String handleInvalidStockIdException(HttpServletRequest request, 
			HttpServletResponse response, Exception ex) {
		return ex.toString();
	}

}
