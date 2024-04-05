package br.com.ultraworks.erp.core.exception;

public class ProcessamentoNFeException extends RuntimeException {

	private static final long serialVersionUID = 309507567549600954L;

	public ProcessamentoNFeException(String msg) {
		super(msg);
	}

	public ProcessamentoNFeException( String message,  Throwable cause) {
		super(message, cause);
	}
}
