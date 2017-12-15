package exceptions;

public class UnexistingUser extends Exception {
	public UnexistingUser() {
		super();
	}
	public UnexistingUser(String msg) {
		super(msg);
	}
}