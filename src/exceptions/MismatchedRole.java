package exceptions;

public class MismatchedRole extends Exception {
	public MismatchedRole() {
		super();
	}
	public MismatchedRole(String msg) {
		super(msg);
	}
}