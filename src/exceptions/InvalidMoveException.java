package exceptions;

public class InvalidMoveException extends CheckersException {

	public InvalidMoveException() {
		super();
	}

	public InvalidMoveException(String errMsg) {
		super(errMsg);
	}
}
