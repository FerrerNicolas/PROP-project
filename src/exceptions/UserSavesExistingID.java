package exceptions;

public class UserSavesExistingID extends Exception {
	public UserSavesExistingID() {
		super();
	}
	public UserSavesExistingID(String msg) {
		super(msg);
	}
}