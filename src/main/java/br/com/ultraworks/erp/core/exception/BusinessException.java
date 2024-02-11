package br.com.ultraworks.erp.core.exception;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 309507567549600954L;

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException( String message,  Throwable cause) {
		super(message, cause);
	}
}
