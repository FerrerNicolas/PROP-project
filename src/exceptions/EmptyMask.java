package exceptions;

public class EmptyMask extends Exception {
	public EmptyMask() {
		super();
	}
	public EmptyMask(String msg) {
		super(msg);
	}
}