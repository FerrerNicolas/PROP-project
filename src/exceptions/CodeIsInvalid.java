package exceptions;

public class CodeIsInvalid extends Exception{
	public CodeIsInvalid() {
		super();
	}
	public CodeIsInvalid(String msg) {
		super(msg);
	}
}
