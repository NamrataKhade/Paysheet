package com.nts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nts.model.response.ApiResponce;





@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce> resorceNotFoundException(ResourceNotFoundException ex){
		String message= ex.getMessage();
		ApiResponce apiResponse=new ApiResponce(message,false);
		return new ResponseEntity<ApiResponce>(apiResponse,HttpStatus.NOT_FOUND);
	}
}