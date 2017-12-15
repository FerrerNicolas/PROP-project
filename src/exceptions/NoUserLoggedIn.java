package exceptions;

public class NoUserLoggedIn extends Exception {
	public NoUserLoggedIn() {
		super();
	}
	public NoUserLoggedIn(String msg) {
		super(msg);
	}
}