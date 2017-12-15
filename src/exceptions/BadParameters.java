package exceptions;

public class BadParameters extends Exception {
	public BadParameters() {
		super();
	}
	public BadParameters(String msg) {
		super(msg);
	}
}