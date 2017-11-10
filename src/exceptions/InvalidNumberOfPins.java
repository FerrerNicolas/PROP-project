package exceptions;

public class InvalidNumberOfPins extends Exception{
	public InvalidNumberOfPins() {
		super();
	}
	public InvalidNumberOfPins(String msg) {
		super(msg);
	}
}
