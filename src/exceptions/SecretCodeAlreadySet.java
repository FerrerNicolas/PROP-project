package exceptions;

public class SecretCodeAlreadySet extends Exception {
	public SecretCodeAlreadySet() {
		super();
	}
	public SecretCodeAlreadySet(String msg) {
		super(msg);
	}
}