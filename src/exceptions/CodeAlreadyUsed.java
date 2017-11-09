package exceptions;

public class CodeAlreadyUsed extends Exception {
	public CodeAlreadyUsed() {
		super();
	}
	public CodeAlreadyUsed(String msg) {
		super(msg);
	}
}