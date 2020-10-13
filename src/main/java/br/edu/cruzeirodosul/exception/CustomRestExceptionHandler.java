package br.edu.cruzeirodosul.exception;


import java.net.UnknownHostException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> errors = Stream
				.concat(ex.getBindingResult().getFieldErrors()
						.stream()
						.map(error -> error.getField() + ": " + error.getDefaultMessage()),
						ex.getBindingResult().getGlobalErrors()
						.stream()
						.map(error -> error.getObjectName() + ": " + error.getDefaultMessage())
				).collect(Collectors.toList());

		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors()
				.stream()
				.filter(fieldError -> fieldError.getRejectedValue().toString().length() > 200)
				.collect(Collectors.toList());

		ApiError apiError = new ApiError(fieldErrors.isEmpty() ? HttpStatus.BAD_REQUEST : HttpStatus.PAYLOAD_TOO_LARGE,
				ex.getLocalizedMessage(), errors);

		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = ex.getParameterName() + " PARÂMETRO NÃO ENCONTRADO ";

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

		List<String> violations = ex.getConstraintViolations().stream()
				.map(violation -> violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
						+ violation.getMessage())
				.collect(Collectors.toList());

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), violations);
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			WebRequest request) {
		String error = ex.getName() + " DEVERIA SER DO TIPO " + ex.getRequiredType().getName();

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex,
															   WebRequest request) {

		String error = request.getDescription(false) + " VALOR NÃO ENCONTRADO ";

		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ UnknownHostException.class })
	public ResponseEntity<Object> handleUnknownHostException(
			UnknownHostException ex, WebRequest request){
		logger.error("UnknownHostException: " + ex.getMessage());

		String error = "SERVIÇO: " + ex.getMessage() + " ESTÁ FORA."  ;

		ApiError apiError = new ApiError(HttpStatus.SERVICE_UNAVAILABLE, ex.getLocalizedMessage(), error);

		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
