package exceptions;

public class ExistingUser extends Exception {
	public ExistingUser() {
		super();
	}
	public ExistingUser(String msg) {
		super(msg);
	}
}