package entities.exceptions;

@SuppressWarnings("serial")
public class SenhaInvalidaException extends Exception {

	public SenhaInvalidaException(String senha) {
		super(senha);
	}

}
