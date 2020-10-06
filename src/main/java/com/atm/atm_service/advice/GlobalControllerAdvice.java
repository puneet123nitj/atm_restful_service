package com.atm.atm_service.advice;

import java.util.List;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;

import org.springframework.beans.TypeMismatchException;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author puneet
 *
 */
@ControllerAdvice
@Slf4j
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
	private final Environment env;

	private static final String FAILED = "FAILED";

	/**
	 * Instantiates a new Global controller advice.
	 *
	 * @param env the env
	 */
	public GlobalControllerAdvice(Environment env) {
		this.env = env;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Exception [handleBindException] for request", ex);

		BindingResult bindingResult = ex.getBindingResult();
		List<ObjectError> allErrors = bindingResult.getAllErrors();
		StringBuilder error = new StringBuilder();
		for (ObjectError obj : allErrors) {
			obj.getDefaultMessage();
			String arr[] = obj.getCodes()[1].toString().split("\\.");
			error.append(arr[1] + " " + obj.getDefaultMessage() + " ");
		}
		String err = error.toString();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

	@Override
	protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		log.error("Exception [handleBindException] for request", ex);

		List<ObjectError> allErrors = ex.getAllErrors();
		StringBuilder error = new StringBuilder();
		for (ObjectError obj : allErrors) {
			error.append(obj.getCodes()[0] + " ");
		}
		String err = error.toString();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	/**
	 * Handle sql exception response entity.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler({ PersistenceException.class, DataAccessException.class })
	public ResponseEntity<Object> handleSqlException(Exception ex) {
		log.error("Exception [handleSqlException] for request", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}

	@NotNull
	@Override
	public ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull HttpMessageNotReadableException ex,
			@NotNull HttpHeaders headers, @NotNull HttpStatus status, @NotNull WebRequest request) {
		log.error("Exception [handleHttpMessageNotReadable] for request", ex.getMessage());
		InvalidFormatException inException = (InvalidFormatException) ex.getCause();
		List<Reference> paths = inException.getPath();
		StringBuilder error = new StringBuilder();
		for (Reference path : paths) {
			error.append(path.getFieldName().toString() + " type is invalid ");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@NotNull
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(@NotNull TypeMismatchException ex, @NotNull HttpHeaders headers,
			@NotNull HttpStatus status, @NotNull WebRequest request) {
		log.error("Exception [handleTypeMismatch] for request", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

	}

	@NotNull
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
			@NotNull MissingServletRequestParameterException ex, @NotNull HttpHeaders headers,
			@NotNull HttpStatus status, @NotNull WebRequest request) {
		log.error("Exception [handleMissingServletRequestParameter] for request", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

	}

	/**
	 * Handle constraint violation exception response entity.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(Exception ex) {
		log.error("Exception [handleConstraintViolationException] for request", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAccountingInternalFailureError(Exception ex) {
		log.error("Exception [AccountingInternalFailureError] for request", ex);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());

	}

}
