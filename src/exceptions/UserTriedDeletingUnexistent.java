package exceptions;

public class UserTriedDeletingUnexistent extends Exception {
	public UserTriedDeletingUnexistent() {
		super();
	}
	public UserTriedDeletingUnexistent(String msg) {
		super(msg);
	}
}