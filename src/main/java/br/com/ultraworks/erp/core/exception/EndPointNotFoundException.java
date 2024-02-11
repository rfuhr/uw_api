package br.com.ultraworks.erp.core.exception;

public class EndPointNotFoundException extends BusinessException {

	private static final long serialVersionUID = -3545018620895882458L;

	public EndPointNotFoundException() {
		super("Endpoint não encontrado");
	}
	
	public EndPointNotFoundException(String msg) {
		super(msg);
	}

	public EndPointNotFoundException( String message,  Throwable cause) {
		super(message, cause);
	}
}
