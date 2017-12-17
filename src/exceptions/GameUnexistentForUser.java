package exceptions;

public class GameUnexistentForUser extends Exception {
	public GameUnexistentForUser() {
		super();
	}
	public GameUnexistentForUser(String msg) {
		super(msg);
	}
}