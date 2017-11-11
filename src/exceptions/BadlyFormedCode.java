package exceptions;

public class BadlyFormedCode extends Exception {
	public BadlyFormedCode() {
		super();
	}
	public BadlyFormedCode(String msg) {
		super(msg);
	}
}