package br.com.ultraworks.erp.core.exception;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.ultraworks.erp.core.annotation.FriendlyName;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@Component
public class GlobalExceptionHandler {

	@Value("${server.error.include-exception}")
	private boolean printStackTrace;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {

		List<ValidationError> validationErrors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
			String fieldName = fieldError.getField();
			Class<?> classe = null;
			String friendlyName = fieldName;
			try {
				classe = Class.forName(fieldError.getObjectName());
				friendlyName = getFieldFriendlyName(classe, fieldName);
			} catch (ClassNotFoundException e) {
			}
			return new ValidationError(friendlyName, fieldError.getDefaultMessage());
		}).collect(Collectors.toList());

		return buildErrorResponse(ex, "Erro de validação de campos", HttpStatus.UNPROCESSABLE_ENTITY, request,
				validationErrors);
	}

	@ExceptionHandler(RegisterNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleRegisterNotFoundException(RegisterNotFoundException registerNotFoundException,
			WebRequest request) {
		log.error("Failed to validate element", registerNotFoundException);
		return buildErrorResponse(registerNotFoundException, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(UnicidadeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<Object> handleUnicidadeException(UnicidadeException unicidadeException, WebRequest request) {
		log.error("Failed to validate element", unicidadeException);
		return buildErrorResponse(unicidadeException, unicidadeException.getMessage(), HttpStatus.BAD_REQUEST, request,
				unicidadeException.getValidationErrorResponse().getErrors());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ResponseEntity<Object> handleConstraintViolationException(
			DataIntegrityViolationException constraintViolationException, WebRequest request) {
		log.error("Failed to validate element", constraintViolationException);
		return buildErrorResponse(constraintViolationException,
				extrairConteudoEntreColchetes(constraintViolationException.getCause().getMessage()),
				HttpStatus.UNPROCESSABLE_ENTITY, request);
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Object> handleAllBadCredentialException(Exception exception, WebRequest request) {
		final String errorMessage = "Credênciais inválidas";
		log.error(errorMessage, exception);
		return buildErrorResponse(exception, errorMessage, HttpStatus.UNAUTHORIZED, request);
	}

	@ExceptionHandler(InsufficientAuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Object> handlenotAuthorizationException(Exception exception, WebRequest request) {
		final String errorMessage = "Credênciais inválidas";
		log.error(errorMessage, exception);
		return buildErrorResponse(exception, errorMessage, HttpStatus.UNAUTHORIZED, request);
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleRecursoNaoEncontrado(Exception exception, WebRequest request) {
		final String errorMessage = "Recurso não encontrado";
		log.error(errorMessage, exception);
		return buildErrorResponse(exception, errorMessage, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(EndPointNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
	public ResponseEntity<Object> handleEndPointNotFoundException(EndPointNotFoundException endPointNotFoundException,
			WebRequest request) {
		log.error("Failed to validate element", endPointNotFoundException);
		return buildErrorResponse(endPointNotFoundException, HttpStatus.NOT_IMPLEMENTED, request);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
		
		if (exception instanceof CustomValidationException || exception.getCause() instanceof CustomValidationException) {
			CustomValidationException customEx = exception.getCause() != null ? (CustomValidationException) exception.getCause() : (CustomValidationException) exception; 
			List<ValidationError> validationErrors = customEx.getFieldErrors().stream().map(fieldError -> {
				String fieldName = fieldError.getField();
				Class<?> classe = null;
				String friendlyName = fieldName;
				try {
					classe = Class.forName(fieldError.getObjectName());
					friendlyName = getFieldFriendlyName(classe, fieldName);
				} catch (ClassNotFoundException e) {
				}
				return new ValidationError(friendlyName, fieldError.getDefaultMessage());
			}).collect(Collectors.toList());

			return buildErrorResponse(exception, "Erro de validação de campos", HttpStatus.UNPROCESSABLE_ENTITY,
					request, validationErrors);
		} else {

			final String errorMessage = "Ocorreu um erro desconhecido";
			log.error(errorMessage, exception);
			return buildErrorResponse(exception, errorMessage, HttpStatus.INTERNAL_SERVER_ERROR, request);
		}
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
		return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest request,
			List<ValidationError> validationErrors) {
		return buildErrorResponse(exception, exception.getMessage(), httpStatus, request, validationErrors);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus,
			WebRequest request) {
		return buildErrorResponse(exception, message, httpStatus, request, new ArrayList<>());
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatus httpStatus,
			WebRequest request, List<ValidationError> validationErrors) {
		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
		errorResponse.setExceptionName(exception.getClass().getSimpleName());
		if (this.printStackTrace) {
			errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
		}
		if (validationErrors != null && !validationErrors.isEmpty()) {
			validationErrors.forEach((el) -> {
				errorResponse.addValidationError(el.getField(), el.getMessage());
			});
		}
		return ResponseEntity.status(httpStatus).body(errorResponse);
	}

	private String getFieldFriendlyName(Class<?> dtoClass, String fieldName) {
		try {
			Field field = dtoClass.getDeclaredField(fieldName);
			FriendlyName friendlyNameAnnotation = field.getAnnotation(FriendlyName.class);
			if (friendlyNameAnnotation != null) {
				return friendlyNameAnnotation.value();
			}
		} catch (NoSuchFieldException e) {
			// Lidar com exceções, se necessário
		}
		return fieldName; // Use o nome do campo se não houver anotação FriendlyName
	}

	private String extrairConteudoEntreColchetes(String mensagemDeErro) {
		int abertura = mensagemDeErro.indexOf("[");
		int fechamento = mensagemDeErro.indexOf("]");

		if (abertura != -1 && fechamento != -1) {
			return mensagemDeErro.substring(abertura + 1, fechamento);
		}

		return null;
	}
}
