package exceptions;

public class NotYourTurnException extends CheckersException {

	public NotYourTurnException() {
		super();
	}

	public NotYourTurnException(String errMsg) {
		super(errMsg);
	}
}
