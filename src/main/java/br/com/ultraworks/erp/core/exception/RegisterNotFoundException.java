package br.com.ultraworks.erp.core.exception;

public class RegisterNotFoundException extends BusinessException {

	private static final long serialVersionUID = -3545018620895882458L;

	public RegisterNotFoundException() {
		super("Registro não encontrado");
	}
	
	public RegisterNotFoundException(String msg) {
		super(msg);
	}

	public RegisterNotFoundException( String message,  Throwable cause) {
		super(message, cause);
	}
}
