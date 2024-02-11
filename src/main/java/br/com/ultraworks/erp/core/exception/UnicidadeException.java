package br.com.ultraworks.erp.core.exception;

public class UnicidadeException extends RuntimeException {

	private ValidationErrorResponse validationErrorResponse;

    public UnicidadeException(ValidationErrorResponse validationErrorResponse) {
        this.validationErrorResponse = validationErrorResponse;
    }

    public UnicidadeException(ValidationErrorResponse validationErrorResponse, String message) {
        super(message);  
        this.validationErrorResponse = validationErrorResponse;
    }

    public ValidationErrorResponse getValidationErrorResponse() {
        return validationErrorResponse;
    }	
}
