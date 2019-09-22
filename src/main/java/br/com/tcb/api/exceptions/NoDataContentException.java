package br.com.tcb.api.exceptions;

public class NoDataContentException extends Exception{

	private static final long serialVersionUID = 1L;

	public NoDataContentException(Long id) {
		super("Não existe o registro de id ".concat(id.toString()));
	}
}
