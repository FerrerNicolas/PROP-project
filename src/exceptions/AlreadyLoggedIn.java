package exceptions;

public class AlreadyLoggedIn extends Exception {
	public AlreadyLoggedIn() {
		super();
	}
	public AlreadyLoggedIn(String msg) {
		super(msg);
	}
}