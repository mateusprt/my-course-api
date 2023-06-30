package mycourses.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;
import mycourses.dtos.exceptions.ResponseExceptionDto;
import mycourses.exceptions.ResourceAlreadyExistsException;
import mycourses.exceptions.ResourceNotFoundException;

@RestControllerAdvice
public class GlobalErrorController {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseExceptionDto> handleAllExceptions(Exception ex, WebRequest request) {
		ResponseExceptionDto dto = new ResponseExceptionDto(List.of("INTERNAL SERVER ERROR"), request.getDescription(false));
		return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<ResponseExceptionDto> handleInvalidDataAccessApiUsageExceptions(InvalidDataAccessApiUsageException ex, WebRequest request) {
		ResponseExceptionDto response = new ResponseExceptionDto(List.of("Request malformed"), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseExceptionDto> handleConstraintViolationExceptions(ConstraintViolationException ex, WebRequest request) {
		List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(violation -> {
            errors.add(violation.getMessage());
        });
        ResponseExceptionDto exceptionResponse = new ResponseExceptionDto(errors, request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ResponseExceptionDto> handleResourceAlreadyExistsExceptionExceptions(ResourceAlreadyExistsException ex, WebRequest request) {
		ResponseExceptionDto response = new ResponseExceptionDto(List.of(ex.getMessage()), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseExceptionDto> handleResourceNotFoundExceptionExceptions(ResourceNotFoundException ex, WebRequest request) {
		ResponseExceptionDto response = new ResponseExceptionDto(List.of(ex.getMessage()), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseExceptionDto> handleMethodArgumentTypeMismatchExceptionExceptions(MethodArgumentTypeMismatchException ex, WebRequest request) {
		ResponseExceptionDto response = new ResponseExceptionDto(List.of("Request malformed"), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ResponseExceptionDto> handleHttpMessageNotReadableExceptions(HttpMessageNotReadableException ex, WebRequest request) {
		ResponseExceptionDto response = new ResponseExceptionDto(List.of("Required request body is missing"), request.getDescription(false));
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
