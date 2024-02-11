package br.com.ultraworks.erp.core.exception;

import java.util.List;

import org.springframework.validation.FieldError;

public class CustomValidationException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final List<FieldError> fieldErrors;

    public CustomValidationException(List<FieldError> fieldErrors) {
        super("Validation failed");
        this.fieldErrors = fieldErrors;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}